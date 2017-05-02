(ns realworld.routes
  (:require
      [compojure.core :as c]
      [clojure.tools.logging :refer [info error]]
      [ring.middleware.keyword-params :refer [wrap-keyword-params]]
      [ring.middleware.params :refer [wrap-params]]
      [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
      [realworld.handler.users :as users]))

(defn wrap-fail-safe [handler]
  "Wraps a safe failure response."
  (fn [req]
    (try
      (handler req)
      (catch Exception e
        (error e)
        {:status 422
         :body {:errors {:body ["Server error due to wrong user input."]}}}))))

(defn wrap-access-log [handler]
  "Wraps the server log for each request received."
  (fn [req]
    (info "==> " (req :uri))
    (let [response (handler req)]
      (info "<== " (req :uri) "[" (response :status) "]")
      response)))

(defn wrap-middlewares [routes]
  "Wraps all the common middlewares to the routes."
  (-> routes
      wrap-fail-safe
      wrap-params
      wrap-keyword-params
      wrap-json-response
      wrap-access-log
      (wrap-json-body {:keywords? true})))

; Public mapping definitions
(c/defroutes public-routes
  (c/GET "/resource-status" [] "OK")
  (c/GET "/health" [] "Service healthy! =)"))

; User mapping definitions
(c/defroutes
  user-routes
  (c/context "/users" []
    (c/POST "/login" req (users/login req))
    (c/POST "/" req (users/register req)))
  (c/GET "/user" req (users/current-user req))
  (c/PUT "/user" req (users/update-user req)))

(defn app [] 
  "Return the app configured routes wrapped."
  (wrap-middlewares public-routes)
  (wrap-middlewares user-routes))
