(ns reagent.cookies
  (:refer-clojure :exclude [count get keys vals empty? reset!])
  (:require
    [cljs.reader :as reader]
    [goog.net.cookies :as cks]))

;Pre Google Closure library v20200204
(defonce ^:private legacy-setter?
  (delay (= 6 (.-length goog.net.cookies.set))))

(defn supports-same-site?
  "True if the the underlying version of `goog.net.cookies` supports the same-site attribute
   when setting cookies
  "
  []
  (not @legacy-setter?))

(defn set!
  "sets a cookie, the max-age for session cookie
   following optional parameters may be passed in as a map:
   :max-age - defaults to -1
   :path - path of the cookie, defaults to the full request path
   :domain - domain of the cookie, when null the browser will use the full request host name
   :secure? - boolean specifying whether the cookie should only be sent over a secure channel
   :raw? - boolean specifying whether content should be stored raw, or as EDN
   :same-site - A keyword of either :strict, :lax, or :none (defaults to :none). Only supported when `supports-same-site?` is true
  "
  [k content & [{:keys [max-age path domain secure? raw? same-site] :as opts}]]
  (let [k (name k)
        content (if raw?
                  (str content)
                  (pr-str content))]
    (cond
      (not (dissoc opts :raw?))
      (.set goog.net.cookies k content)

      @legacy-setter?
      (.set goog.net.cookies k content (or max-age -1) path domain (boolean secure?))

      :else
      (.set goog.net.cookies k content #js{:maxAge   (or max-age -1)
                                           :path     path
                                           :domain   domain
                                           :sameSite (condp = same-site
                                                       :strict (.-STRICT goog.net.Cookies.SameSite)
                                                       :lax (.-LAX goog.net.Cookies.SameSite)
                                                       (.-NONE goog.net.Cookies.SameSite))
                                           :secure   (boolean secure?)}))))

(defn- read-edn-value [v]
  (when v
    (reader/read-string v)))

(defn- read-raw-value [v] v)

(defn- get-value
  [k r default]
  (or (->> (name k) (.get goog.net.cookies) r) default))

(defn get
  "gets the value at the key (as edn), optional default when value is not found"
  [k & [default]]
  (get-value k read-edn-value default))

(defn get-raw
  "gets the value at the key (as string), optional default when value is not found"
  [k & [default]]
  (get-value k read-raw-value default))

(defn contains-key?
  "is the key present in the cookies"
  [k]
  (.containsKey goog.net.cookies (name k)))

(defn contains-val?
  "is the value present in the cookies (as string)"
  [v]
  (.containsValue goog.net.cookies v))

(defn count
  "returns the number of cookies"
  []
  (.getCount goog.net.cookies))

(defn keys
  "returns all the keys for the cookies"
  []
  (map keyword (.getKeys goog.net.cookies)))

(defn vals
  "returns cookie values (as edn)"
  []
  (map read-edn-value (.getValues goog.net.cookies)))

(defn raw-vals
  "returns cookie values (as strings)"
  []
  (map read-raw-value (.getValues goog.net.cookies)))

(defn empty?
  "true if no cookies are set"
  []
  (.isEmpty goog.net.cookies))

(defn remove!
  "removes a cookie, optionally for a specific path and/or domain"
  ([k]
   (.remove goog.net.cookies (name k)))
  ([k path domain]
   (.remove goog.net.cookies (name k) path domain)))

(defn clear!
  "removes all cookies"
  []
  (.clear goog.net.cookies))
