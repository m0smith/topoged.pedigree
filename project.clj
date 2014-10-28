(defproject topoged/pedigree "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2322"]
                 [topoged/gedcom "0.1.0"]
                 [om "0.8.0-alpha1"]]
  :plugins [[lein-cljsbuild "1.0.3"]]
  :cljsbuild {
              :builds [{:id "dev"
                        :source-paths ["src"]
                        :compiler {
                                   :output-to "main.js"
                                   :output-dir "out"
                                   :optimizations :none
                                   :source-map true}}]}
  :profiles { :dev 
             { :dependencies [[org.clojure/core.async "0.1.346.0-17112a-alpha"]
                              
                              [marginalia "0.8.0"] 
                              [org.clojure/test.check "0.5.9"] ;; property testing
                              ]}}
)
