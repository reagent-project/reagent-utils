(ns reagent.cookies
  (:refer-clojure :exclude [count get keys vals empty? reset!])
  (:require
    [cljs.reader :as reader]
    [goog.net.Cookies :refer [SetOptions]]))

(defonce ^:dynamic *cookies* (or goog.net.cookies (.getInstance goog.net.Cookies)))

;Pre Google Closure library v20200204
(defonce ^:private legacy-setter?
  (delay (= 6 (.-length (.-set *cookies*)))))

(defn supports-same-site?
  "True if the underlying version of `goog.net.Cookies` supports the same-site attribute
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
      (clojure.core/empty? (dissoc opts :raw?))
      (.set *cookies* k content)

      @legacy-setter?
      (.set *cookies* k content (or max-age -1) path domain (boolean secure?))

      :else
      (.set *cookies* k content
            (doto (SetOptions.)
              (set! -maxAge (or max-age -1))
              (set! -path path)
              (set! -domain domain)
              (set! -secure (boolean secure?))
              (set! -sameSite (condp = same-site
                                :strict (.-STRICT goog.net.Cookies.SameSite)
                                :lax (.-LAX goog.net.Cookies.SameSite)
                                (.-NONE goog.net.Cookies.SameSite))))))))

(defn- read-edn-value [v]
  (when v
    (reader/read-string v)))

(defn- read-raw-value [v] v)

(defn- get-value
  [k r default]
  (or (->> (name k) (.get *cookies*) r) default))

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
  (.containsKey *cookies* (name k)))

(defn contains-val?
  "is the value present in the cookies (as string)"
  [v]
  (.containsValue *cookies* v))

(defn count
  "returns the number of cookies"
  []
  (.getCount *cookies*))

(defn keys
  "returns all the keys for the cookies"
  []
  (map keyword (.getKeys *cookies*)))

(defn vals
  "returns cookie values (as edn)"
  []
  (map read-edn-value (.getValues *cookies*)))

(defn raw-vals
  "returns cookie values (as strings)"
  []
  (map read-raw-value (.getValues *cookies*)))

(defn empty?
  "true if no cookies are set"
  []
  (.isEmpty *cookies*))

(defn remove!
  "removes a cookie, optionally for a specific path and/or domain"
  ([k]
   (.remove *cookies* (name k)))
  ([k path domain]
   (.remove *cookies* (name k) path domain)))

(defn clear!
  "removes all cookies"
  []
  (.clear *cookies*))
