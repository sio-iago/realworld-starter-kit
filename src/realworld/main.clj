(ns realworld.main
  (:require
        [org.httpkit.server :refer [run-server]]
        [clojure.tools.logging :refer [info]]
        [environ.core :refer [env]]
        [realworld.routes :as api]
        [realworld.database.schema :as schema])
  (:gen-class))

(defonce server (atom nil))

(defn get-port [port]
  (if (contains? env :port) (read-string (env :port)) port))

(defn bootstrap? []
  (contains? env :bootstrap))

(defn start-server []
  (reset! server (run-server (api/app) {:port (get-port 5000)})))


(defn -main [& args]
  (start-server)
  (info (str "Server started. listen on 0.0.0.0:" (get-port 5000)))
  
  (if (bootstrap?)
    (do
      (info "Generating schema")
      (schema/generate))
    (info "Skipping schema generation")))
  
  
