(ns reagent.crypt
  (:refer-clojure :exclude [hash])
  (:require
    [goog.crypt :as crypt]
    [goog.crypt.Md5 :as Md5]
    [goog.crypt.Sha1 :as Sha1]
    [goog.crypt.Sha2 :as Sha2]
    [goog.crypt.Sha256 :as Sha256]
    [goog.crypt.Sha384 :as Sha384]
    [goog.crypt.Sha512 :as Sha512]))

(defn string->bytes [s]
  (crypt/stringToUtf8ByteArray s))

(defn bytes->hex
  "convert bytes to hex"
  [bytes-in]
  (crypt/byteArrayToHex bytes-in))

(defn digest [hasher bytes]
  (.update hasher bytes)
  (.digest hasher))

(defn hash-bytes [s hash-type]
  (digest
    (case hash-type
      :md5 (goog.crypt.Md5.)
      :sha1 (goog.crypt.Sha1.)
      :sha2 (goog.crypt.Sha2.)
      :sha256 (goog.crypt.Sha256.)
      :sha384 (goog.crypt.Sha384.)
      :sha512 (goog.crypt.Sha512.)
      (throw (js/Error. (str "'" hash-type "' is not a valid hash algorithm."))))
    (string->bytes s)))

(defn hash [s hash-type & [hex?]]
  (let [hashed (hash-bytes s hash-type)]
    (if hex? (bytes->hex hashed) hashed)))
