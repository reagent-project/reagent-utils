(defproject reagent-utils "0.3.3"
  :description "various utility functions for Reagent based projects"
  :url "https://github.com/reagent-project/reagent-utils"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :clojurescript? true

  :plugins [[lein-codox "0.9.1"]
            [lein-cljsbuild "1.1.7"]]

  :codox {:language :clojurescript
          :exclude  clojure.string}

  :profiles {:dev
             {:dependencies [[org.clojure/clojure "1.10.0"]
                             [org.clojure/clojurescript "1.10.439"]
                             [reagent "0.8.1"]]
              :plugins      [[lein-doo "0.1.7"]]}}

  :cljsbuild {:builds
              {:test
               {:source-paths ["src/reagent" "test/reagent"]
                :compiler     {:main          "reagent.runner"
                               :output-to     "target/cljsbuild/public/js/testable.js"
                               :source-map    true
                               :optimizations :none
                               :pretty-print  true}}}})
