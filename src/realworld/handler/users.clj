(ns realworld.handler.users
  (:require [realworld.repository.user-repo :as users]))
    

(defn login [req]
    (let [user (-> req :body :user)
          email (user :email)
          password (user :password)]
     {:body {:user (users/find-one email password)}}))

(defn register [req]
    (let [user (-> req :body :user)
          username (user :username)
          email (user :email)
          password (user :password)]
     {:body {
             :user (users/persist-user :username username
                                       :email email
                                       :password password)}}))
