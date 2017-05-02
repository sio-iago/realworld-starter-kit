(ns realworld.util.request-util
    (:require [clojure.string :as str]
              [clojure.walk :refer [keywordize-keys]]))

(defn get-auth-token [req]
    "Extracts the Authorization token from a given request object."
    (let [headers (-> req :headers keywordize-keys)
          token-header (-> headers :authorization)
          token (-> (str/split token-header #" ") last)]
      token))