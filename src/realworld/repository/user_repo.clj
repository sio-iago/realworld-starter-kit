(ns realworld.repository.user-repo)

(defn find-one [email password]
  {:id 1
   :email email
   :username "dummy"
   :bio "Lorem Ipsum!"
   :image nil
   :token "123456"})
 
(defn persist-user [& {:keys [username email password]}]
  {:id 1
   :email email
   :username username
   :bio "Lorem Ipsum!"
   :image nil
   :token "123456"})

(comment (persist-user :username "iago"
           :password "lalaland"
           :email "iago"))
