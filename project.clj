(defproject reagent-utils "0.2.0"
  :description "various utility functions for Reagent based projects"
  :url "https://github.com/reagent-project/reagent-utils"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :clojurescript? true


  :plugins [[lein-codox "0.9.1"]]
  :codox {:language :clojurescript
          :exclude clojure.string}
  :profiles {:dev
             {:dependencies [[org.clojure/clojure "1.7.0"]
                             [org.clojure/clojurescript "1.7.170"]
                             [reagent "0.5.1"]]}})
