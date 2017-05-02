(ns realworld.handler.users
  (:require [realworld.repository.user-repo :as usr-repo]
            [realworld.util.request-util :as util]))
    
(defn login [req]
    (let [user (-> req :body :user)
          email (user :email)
          password (user :password)
          reg-user (usr-repo/find-one email password)
          new-tok (if (nil? reg-user ) nil (usr-repo/update-token email))]
     {:body {:user (into reg-user {:token new-tok})}}))

(defn register [req]
    (let [user (-> req :body :user)]
     {:body {:user (usr-repo/persist user)}}))

(defn current-user [req]
  (let [token (util/get-auth-token req)]
   {:body {:user (usr-repo/find-by-token token)}}))