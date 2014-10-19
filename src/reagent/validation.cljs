(ns reagent.validation
  "Functions for validating input and setting string errors on fields.")

;; validation helpers

(defn has-value?
  "Returns true if v is truthy and not an empty string."
  [v]
  (and v (not= v "")))

(defn has-values?
  "Returns true if all members of the collection has-value? This works on maps as well."
  [coll]
  (let [vs (if (map? coll)
             (vals coll)
             coll)]
    (every? has-value? vs)))

(defn not-nil?
  "Returns true if v is not nil"
  [v]
  (boolean (or v (false? v))))

(defn min-length?
  "Returns true if v is greater than or equal to the given len"
  [v len]
  (>= (count v) len))

(defn max-length?
  "Returns true if v is less than or equal to the given len"
  [v len]
  (<= (count v) len))


(defn matches-regex?
  "Returns true if the string matches the given regular expression"
  [v regex]
  (boolean (re-matches regex v)))


(defn is-email?
  "Returns true if v is an email address"
  [v]
  (if (nil? v)
    false
    (matches-regex? v #"(?i)[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")))


(defn valid-file?
  "Returns true if a valid file was supplied"
  [m]
  (and (:size m)
       (> (:size m) 0)
       (:filename m)))


(defn valid-number?
  "Returns true if the string can be parsed to a Long"
  [v]
  (try
    (js/parseFloat v)
    true
    (catch Exception e
      false)))


(defn greater-than?
  "Returns true if the string represents a number > given."
  [v n]
  (and (valid-number? v)
       (> (js/parseFloat v) n)))


(defn less-than?
  "Returns true if the string represents a number < given."
  [v n]
  (and (valid-number? v)
       (< (js/parseFloat v) n)))

(defn equal-to?
  "Returns true if the string represents a number = given."
  [v n]
  (and (valid-number? v)
       (== (js/parseFloat v) n)))
