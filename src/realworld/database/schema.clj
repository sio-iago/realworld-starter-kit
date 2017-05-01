(ns realworld.database.schema
  (:require [clojure.java.jdbc :refer :all]))


(defn- get-db []
  "Returns the sqlite database credentials to perform the operations"
  {:classname "org.sqlite.JDBC" :subprotocol "sqlite" :subname "db/database.db"})

(defn create-user []
  "Creates the user table on the database"
  (let [db (get-db)]
    (try 
      (db-do-commands (get-db)
        (create-table-ddl :users
          [:id :integer :primary :key :autoincrement]
          [:email :text :unique]
          [:username :text :unique]
          [:password :text]
          [:image :text]
          [:bio :text]))
      (catch Exception e (println e)))))


(defn generate []
  "Generates a new database from scratch using the schema."
  (create-user))
  
