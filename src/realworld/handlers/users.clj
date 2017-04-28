(ns realworld.handlers.users
    )

(defn login [req]
    (let [user (-> req :body :user)
          email (user :email)
          password (user :password)]
     {:body {
             :user {
                    :id 1
                    :email email
                    :username "test"
                    :bio "lorem ipsum"
                    :image nil
                    :token "123456"}}}))

(defn register [req]
    (let [user (-> req :body :user)
          username (user :username)
          email (user :email)
          password (user :password)]
     {:body {
             :user {
                    :id 1
                    :email email
                    :username username
                    :bio ""
                    :image nil
                    :token "123456"}}}))