(ns realworld.database.schema
  (:require [clojure.java.jdbc :refer :all]
            [environ.core :refer [env]]
            [realworld.database.core :refer [get-db]]))

(defn create-users []
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
          [:bio :text]
          [:token :text :unique]))
      (catch Exception e (println e)))))

(defn create-articles []
  "Creates the article table on the database"
  (let [db (get-db)]
    (try 
      (db-do-commands (get-db)
        (create-table-ddl :articles
          [:id :integer :primary :key :autoincrement]
          [:slug :text :unique]
          [:title :text]
          [:description :text]
          [:body :text]
          [:createdAt :datetime]
          [:updatedAt :datetime]
          [:author-id :integer "references users(id)"]))
      (catch Exception e (println e)))))

(defn create-tags []
  "Creates the tags table on the database"
  (let [db (get-db)]
    (try 
      (db-do-commands (get-db)
        (create-table-ddl :comments
          [:id :integer :primary :key :autoincrement]
          [:name :text :unique]))
      (catch Exception e (println e)))))

(defn create-article-tags []
  "Creates the tags table on the database"
  (let [db (get-db)]
    (try 
      (db-do-commands (get-db)
        (create-table-ddl :comments
          [:article-id :integer "references article(id)"]
          [:tag-id :integer "references tag(id)"]))
      (catch Exception e (println e)))))

(defn create-comments []
  "Creates the comments table on the database"
  (let [db (get-db)]
    (try 
      (db-do-commands (get-db)
        (create-table-ddl :comments
          [:id :integer :primary :key :autoincrement]
          [:body :text]
          [:article-id :integer "references article(id)"]
          [:user-id :integer "references user(id)"]
          [:createdAt :datetime]
          [:updatedAt :datetime]
          [:author-id :integer "references users(id)"]))
      (catch Exception e (println e)))))



(defn generate []
  "Generates a new database from scratch using the schema."
  (create-users))
  
