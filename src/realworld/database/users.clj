(ns realworld.database.users
  (:require [clojure.java.jdbc :refer :all]
            [java-jdbc.sql :as sql]
            [realworld.database.core :refer [get-db]]
            [crypto.password.pbkdf2 :as crypto]
            [clj-jwt.core :refer :all]
            [clj-time.core :refer [now plus days]]
            [environ.core :refer [env]]))

(defn- get-secret []
  "Retrieves the secret to generate the token"
  (if (contains? env :secret) (env :secret) "some-default-secret-dont-use-it"))

(defn encrypt-password [pwd]
  "Encrypts a string to be a safety compliant password."
  (-> pwd crypto/encrypt str))

(defn generate-token [email]
  "Generates a new JWT token to the user.
  Each token expires after 7 days."
  (let [claim {:iss email
               :exp (plus (now) (days 7))
               :iat (now)}]
    (-> claim jwt (sign :HS256 (get-secret)) to-str)))

(defn insert-user [user]
  "Inserts a new user to the database."
  (let [db (get-db)
        email (user :email)
        username (user :username)
        bio (if (contains? user :bio) (user :bio) "")
        image ""
        password (-> user :password encrypt-password)
        token (generate-token email)]
    (insert! db
             :users
             {:email email
              :username username
              :bio bio
              :image image
              :token token
              :password password})))

(defn update-token [email]
  "Updates an user's token."
  (let [new-tok (generate-token email)]
    (if
      (not 
        (nil? (update! (get-db) :users {:token new-tok} ["email=?" email])))
      new-tok
      nil)))

(defn find-by-email-password [email password]
  "Finds an user by it's email and password."
  (let [results (query (get-db)
                  (sql/select * :users (sql/where {:email email})))
        user (first results)]
    (if (crypto/check password (user :password)) user nil)))

(defn find-by-token [token]
  "Finds an user by it's token."
  (let [results (query (get-db)
                  (sql/select * :users (sql/where {:token token})))
        user (first results)]
    user))