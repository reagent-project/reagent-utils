(ns reagent.cookies-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [reagent.cookies :as cookies]))

(deftest reading-cookie-values

  (testing "get provides default if cookie not set"
    (is (= (cookies/get :non-existent "default-val") "default-val")))

  (testing "get reads edn from cookie"
    (.set goog.net.cookies "some-cookie" "{:edn 123}")
    (is (= (cookies/get :some-cookie) {:edn 123})))

  (testing "get can't read invalid edn from cookie"
    (.set goog.net.cookies "some-cookie" "123abc")
    (is (thrown-with-msg? js/Error #"Invalid number format" (cookies/get :some-cookie))))

  (testing "get raw provides default value if cookie not set"
    (is (= (cookies/get-raw :non-existent "default-val") "default-val")))

  (testing "set writes raw string to cookie"
    (cookies/set! :some-cookie "123abc" {:raw? true})
    (is (= (.get goog.net.cookies "some-cookie") "123abc")))

  (testing "get reads raw string from cookie"
    (.set goog.net.cookies "some-cookie" "123abc")
    (is (= (cookies/get-raw :some-cookie) "123abc")))

  (testing "get reads edn vals from cookie"
    (.set goog.net.cookies "some-cookie" "{:edn 123}")
    (is (= (cookies/vals) [{:edn 123}])))

  (testing "get reads raw vals from cookie"
    (.set goog.net.cookies "some-cookie" "123abc")
    (is (= (cookies/raw-vals) ["123abc"]))))