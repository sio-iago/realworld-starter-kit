(defproject realworld "0.1.0-SNAPSHOT"
  :description "Realworld Backend Example"
  :url "https://github.com/sio-iago/realworld-starter-kit"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main realworld.main
  :aot [realworld.main]
  :uberjar-name "realworld-standalone.jar"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [http-kit "2.1.16"] 
                 [compojure "1.4.0"]
                 [ring/ring-json "0.4.0"]
                 [environ "1.1.0"]
                 [clj-jwt "0.1.1"]
                 [org.clojure/tools.logging "0.2.6"]
                 [ch.qos.logback/logback-classic "1.0.1"]])
