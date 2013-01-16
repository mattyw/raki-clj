(ns raki-clj.core
    (:require [clojure.pprint]
              [clj-http.client :as client])
    (:use [cheshire.core :refer :all]))

(defn get-fake-data []
    (let [data (slurp "dc_rankings.json")]
        (parse-string data (fn [k] (keyword k)))))

(defn get-data []
    (let [data (:body (clj-http.client/get "https://www.mastodonc.com/dc_rankings"))]
        (parse-string data (fn [k] (keyword k)))))

(defn get-ranking [data n]
        (nth (:rows data) n))

(defn rank-to-list [rank]
    (let [name (get-in rank [:c 0 :f])
          location (get-in rank [:c 0 :v])
          value (get-in rank [:c 1 :v])
          co2 (get-in rank [:c 1 :f])]
       {:name name :location location :value value :co2 co2}))
     
(defn print-rankings [rankings]
    (let [ls (map rank-to-list rankings)]
        (clojure.pprint/print-table ls)))

(defn -main []
    (let [data (get-data)]
        (print-rankings (:rows data))))
