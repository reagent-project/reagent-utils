(ns reagent.cookies
  (:refer-clojure :exclude [count get keys vals empty? reset!])
  (:require [goog.net.cookies :as cks]
            [cljs.reader :as reader]))

(defn set!
  "sets a cookie, the max-age for session cookie
   following optional parameter may be passed in as a map:
   :max-age - defaults to -1
   :path - path of the cookie, defaults to the full request path
   :domain - domain of the cookie, when null the browser will use the full request host name
   :secure? - boolean specifying whether the cookie should only be sent over a secure channel
  "
  [k content & [{:keys [max-age path domain secure?]} :as opts]]
  (let [k (name k)
        content (pr-str content)]
    (if opts
      (.set goog.net.cookies k content)
      (.set goog.net.cookies k content (or max-age -1) path domain (boolean secure?)))))

(defn- read-value [v]
  (when v
    (reader/read-string v)))

(defn get
  "gets the value at the key, optional default when value is not found"
  [k & [default]]
  (or
    (->> (name k) (.get goog.net.cookies) read-value)
    default))

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
  "returns cookie values"
  []
  (map read-value (.getValues goog.net.cookies)))


(defn empty?
  "true if no cookies are set"
  []
  (.isEmpty goog.net.cookies))

(defn remove!
  "removes a cookie"
  [k]
  (.remove goog.net.cookies (name k)))

(defn clear!
  "removes all cookies"
  []
  (.clear goog.net.cookies))
