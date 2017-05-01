(ns realworld.database.core
    (:require [clojure.java.jdbc :refer :all]
              [environ.core :refer [env]]))

(defn- get-db-path []
  "Gets the path of the database"
  (if (contains? env :database) (env :database) "db/database.db"))

(defn get-db []
  "Returns the sqlite database credentials to perform the operations"
  {:classname "org.sqlite.JDBC" :subprotocol "sqlite" :subname (get-db-path)})