(ns reagent.runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [reagent.cookies-test]))

; run the tests from the command line with
; lein doo phantom test [auto|once]

; add tests by 'requiring them and adding them to the list below

(enable-console-print!)

(doo-tests
  'reagent.cookies-test)

; see https://github.com/bensu/doo for more information
