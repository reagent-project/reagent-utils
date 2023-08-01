(defproject reagent-utils "0.3.8"
  :description "various utility functions for Reagent based projects"
  :url "https://github.com/reagent-project/reagent-utils"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :clojurescript? true

  :plugins [[lein-codox "0.9.1"]
            [lein-cljsbuild "1.1.7"]]

  :codox {:language :clojurescript
          :exclude  clojure.string}

  :profiles {:dev {:dependencies [[org.clojure/clojure "1.10.0"]
                                  [org.clojure/clojurescript "1.10.439"]
                                  [reagent "0.8.1"]]
                   :plugins      [[lein-doo "0.1.7"]]}
             :gcl-v20210505 [:dev {:dependencies [[org.clojure/clojurescript "1.10.866" :exclusions [com.google.javascript/closure-compiler-unshaded
                                                                                                     org.clojure/google-closure-library
                                                                                                     org.clojure/google-closure-library-third-party]]
                                                  [com.google.javascript/closure-compiler-unshaded "v20210505"]
                                                  [org.clojure/google-closure-library "0.0-20201211-3e6c510d"]
                                                  [org.clojure/google-closure-library-third-party "0.0-20201211-3e6c510d"]]}]}

  :cljsbuild {:builds
              {:test
               {:source-paths ["src/reagent" "test/reagent"]
                :compiler     {:main          "reagent.runner"
                               :output-to     "target/cljsbuild/public/js/testable.js"
                               :source-map    true
                               :optimizations :none
                               :pretty-print  true}}}})
