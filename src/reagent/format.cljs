(ns reagent.format
  (:refer-clojure :exclude [hash])
  (:require
    [clojure.string :as s]
    [goog.string :as gstring]
    [goog.string.format]
    [goog.i18n.DateTimeFormat :as dtf]
    [goog.i18n.NumberFormat :as nf]
    [goog.i18n.NumberFormatSymbols :as symbols]
    [goog.crypt :as crypt]
    [goog.crypt.Md5 :as Md5]
    [goog.crypt.Sha1 :as Sha1]
    [goog.crypt.Sha2 :as Sha2]
    [goog.crypt.Sha256 :as Sha256]
    [goog.crypt.Sha384 :as Sha384]
    [goog.crypt.Sha512 :as Sha512]))

(defn add-slashes [s]
  (->> s
       (str)
       (mapcat (fn [c]
                 (if (or (= \" c) (= \' c))
                   [\\ c]
                   [c])))
       (apply str)))

(defn center [text w]
  (let [c (count text)
        l (.ceil js/Math (/ (- w c) 2))
        r (.floor js/Math (/ (- w c) 2))]
    (str
      (apply str (repeat l \space))
      text
      (apply str (repeat r \space)))))

(defn format
  "Formats a string using goog.string.format.
   e.g: (format \"Cost: %.2f\" 10.0234)"
  [fmt & args]
  (apply gstring/format fmt args))

(defn printf
  "Prints formatted output, as per format"
  [fmt & args]
  (print (apply format fmt args)))

(defn currency-format
  "formats currency using the current locale
   to change locale set goog.i18n.NumberFormatSymbols eg:
   (set! goog.i18n.NumberFormatSymbols goog.i18n.NumberFormatSymbols_it_IT)
   see here for supported locales
   https://github.com/google/closure-library/blob/master/closure/goog/i18n/compactnumberformatsymbols.js
  "
  [n]
  (.format (goog.i18n.NumberFormat. (.-CURRENCY goog.i18n.NumberFormat.Format)) n))

(defn date-format [date fmt & [tz]]
  (let [formatter (goog.i18n.DateTimeFormat. fmt)]
    (if tz
      (.format formatter date tz)
      (.format formatter date))))

(defn line-numbers [s]
  (let [s (str s)]
    (->> (s/split s #"\n")
         (map-indexed #(str (inc %1) ". " %2))
         (s/join "\n"))))

(defn pluralize
  "pluralizes the word based on the number of items
   (util/pluralize [\"John\"] \"lad\")
   (util/pluralize [\"John\" \"James\"] \"lad\")
   (util/pluralize [\"Alice\"] \"lad\" \"y\" \"ies\")"
  [items & [word ending1 ending2 :as opts]]
  (let [n (count items)
        plural (case (count opts)
                 1 "s"
                 2 ending1
                 3 ending2)
        singular (case (count opts)
                   (list 1 2) ""
                   3 ending1)]
    (str n " " word (if (== 1 n) singular plural))))

(defn capitalize-words [s]
  (->> (s/split (str s) #" ")
       (map s/capitalize)
       (s/join " ")))

(defn remove-tags
  "removes specified tags, eg:
   (remove-tags \"<p>foo bar</p>\" \"p\")"
  [s & tags]
  (if-not tags
    s
    (let [s (str s)
          tags (str "(" (s/join "|" tags) ")")
          opening (re-pattern (str "(?i)<" tags "(/?>|(\\s+[^>]*>))"))
          closing (re-pattern (str "(?i)</" tags ">"))]
      (-> s
          (s/replace opening "")
          (s/replace closing "")))))

(defn encode-uri [uri]
  (js/encodeURIComponent uri))

