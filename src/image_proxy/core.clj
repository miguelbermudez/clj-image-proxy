(ns image-proxy.core
  (:use compojure.core)
  (:use ring.util.response)
  (:require [compojure.handler :as handler]
            [clj-http.client :as client]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]))

;; Import Apache Common's Base64 encoder/decoder
(import (org.apache.commons.codec.binary Base64))
;; Import Java's ImageIO lib
(import [javax.imageio ImageIO])

(defn get-file "Get file from url as byte array" [url]
  (client/get url {:as :byte-array})))

(defn get-base64-img-str "Convert an image to base64 string" [bArray]
  (String. (Base64/encodeBase64 bArray)))

(defn proccess-image "create java image for basic properties" [bArray]
  (ImageIO/read (java.io.ByteArrayInputStream. (bArray))))

(defn get-img "return base64 img str, width, height as json" [url]
  (response
    (let
      [imgBArray (get-file url)]
      [b64Str (get-base64-img-str imgBArray)]
      [img (proccess-image imgBArray)]
      {:width (.getWidth img)
       :height (.getHeight img)
       :data  ()})
    ))





(defroutes app-routes
  ; to serve document root address
  (GET "/" [] "<h1>Hello from Image-Proxy")
  (GET "/image" {params :params} (pr-str params))


  ; to server static pages saved in resources/public directory
  (route/resources "/")
  ; if page is not found
  (route/not-found "Page non found"))

(def app
  (-> (handler/api app-routes)
      (middleware/wrap-json-body)
      (middlew(are/wrap-json-response)))
