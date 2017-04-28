(ns realworld.app-test
  (:use realworld.test-common
        clojure.test))

(deftest test-register
  (let [resp (test-app {:request-method :post
                        :uri "/users"
                        :body {:user {:username "iago"
                               :email "test@example.com"
                               :password "123"}}})]
    (is (= 200 (:status resp)))
    (is (-> resp :body read-json :user))
    (is (-> resp :body read-json :user :id))))
