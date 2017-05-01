(ns realworld.routes
  (:require
      [compojure.core :as c]
      [ring.middleware.keyword-params :refer [wrap-keyword-params]]
      [ring.middleware.params :refer [wrap-params]]
      [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
      [realworld.handler.users :as users]))

;; Mapping definitions
(c/defroutes
  routes
  (c/GET "/resource-status" [] "OK")
  (c/context "/users" []
    (c/POST "/login" req (users/login req))
    (c/POST "/" req (users/register req))))

(defn app [] (-> routes
              wrap-params
              wrap-keyword-params
              wrap-json-response
              (wrap-json-body {:keywords? true})))
