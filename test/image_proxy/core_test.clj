(ns image-proxy.core-test
  (:require [clojure.test :refer :all]
            [clj-http.client :as client]
            [image-proxy.core :refer :all]))

(defn setup []
  (println "setup ...")
  (def imgReq (client/get "https://www.google.com/images/srpr/logo4w.png" {:as :byte-array})))

(defn teardown []
  (println "teardown ..."))

(defn url-file-fixture [f]
  (setup)
  (println (type f))
  (f)
  (teardown))

(use-fixtures :once url-file-fixture)

(deftest get-file-test
  (is (true? imgReq)))
