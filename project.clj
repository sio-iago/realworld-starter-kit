(defproject realworld "0.1.0-SNAPSHOT"
  :description "Realworld Backend Example"
  :url "https://github.com/sio-iago/realworld-starter-kit"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main realworld.main
  :aot [realworld.main clojure.tools.logging.impl]
  :uberjar-name "realworld-standalone.jar"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 
                 ; Web Server dependencies
                 [http-kit "2.1.16"]
                 [compojure "1.4.0"]
                 [ring/ring-json "0.4.0"]
                 
                 ; Env tool
                 [environ "1.1.0"]
                 
                 ; JWT Auth
                 [clj-jwt "0.1.1"]
                 
                 ; Pretty logging
                 [org.clojure/tools.logging "0.2.6"]
                 [ch.qos.logback/logback-classic "1.0.1"]
                 
                 ; SQLite Dependencies
                 [org.clojure/java.jdbc "0.3.7"]
                 [org.xerial/sqlite-jdbc "3.7.2"]]

  :profiles {:dev {:env {:port "9000"
                         :bootstrap "true"}}})
