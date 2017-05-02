(ns realworld.repository.user-repo
  (:require [realworld.database.users :as usr-db]))

(defn find-one [email password]
  "Find an user by it's email and password."
  (usr-db/find-by-email-password email password))

(defn find-by-token [token]
  "Finds an user by it's token."
  (usr-db/find-by-token token))

(defn update-token [email]
  "Updates the user token."
  (usr-db/update-token email))
 
(defn persist [user-map]
  "Persists a new user."
  (usr-db/insert-user user-map)
  (find-one (user-map :email) (user-map :password)))

(defn update-by-token [token user-map]
  "Updates an user by it's given token."
  (usr-db/update-by-token token user-map))