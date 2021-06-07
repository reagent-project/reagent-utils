(ns reagent.cookies-test
  (:require
    [cljs.test :refer-macros [deftest testing is use-fixtures]]
    [clojure.string :as str]
    [reagent.cookies :as cookies]))

(defn- stub-cookies [f]
  (let [old (.getInstance goog.net.Cookies)
        stub (new goog.net.Cookies nil)]
    (set! goog.net.cookies stub) ;NOTE the google closure library marks this method as deprecated
    (f)
    (set! goog.net.cookies old)))

(defn- raw-cookie-val []
  (-> goog.net.cookies
      (.-document_)
      (.-cookie)))

(use-fixtures :once stub-cookies)

(deftest reading-cookie-values

  (testing "get provides default if cookie not set"
    (is (= (cookies/get :non-existent "default-val") "default-val")))

  (testing "get reads edn from cookie"
    (.set goog.net.cookies "some-cookie" "{:edn 123}")
    (is (= (cookies/get :some-cookie) {:edn 123})))

  (testing "get can't read invalid edn from cookie"
    (.set goog.net.cookies "some-cookie" "123abc")
    (is (thrown-with-msg? js/Error #"Invalid number: 123abc" (cookies/get :some-cookie))))

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
    (is (= (cookies/raw-vals) ["123abc"])))

  (testing "can specify options"

    (testing "path"
      (let [path "reagent-utils/cookie/path"]
        (cookies/set! :path-cookie "path-cookie" {:path path})
        (is (= (cookies/get :path-cookie) "path-cookie"))
        (is (str/includes? (raw-cookie-val) (str "path=" path)))))

    (testing "domain"
      (let [domain "http://reagent-utils.cookie.local"]
        (cookies/set! :domain-cookie "domain-cookie" {:domain domain})
        (is (= (cookies/get :domain-cookie) "domain-cookie"))
        (is (str/includes? (raw-cookie-val) (str "domain=" domain)))))

    (testing "max-age"
      (cookies/set! :max-age-cookie "max-age" {:max-age 200})
      (is (= (cookies/get :max-age-cookie) "max-age"))
      (is (str/includes? (raw-cookie-val) "\"max-age\";expires")))

    (when (cookies/supports-same-site?)
      (testing "same-site"
        (doseq [opt [:lax :strict :none]]
          (let [v (name opt)]
            (cookies/set! :same-site-cookie v {:same-site opt})
            (is (= (cookies/get :same-site-cookie) v))
            (is (str/includes? (raw-cookie-val) (str "samesite=" v)))))))))
