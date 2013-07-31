(defproject image-proxy "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [clj-http "0.3.4"]
                 [compojure "1.1.5"]
                 [ring/ring-json "0.2.0"]]

  :plugins [[lein-ring "0.8.5"]]

  ; ring tasks configuration
  :ring {:handler image-proxy.core/app})
