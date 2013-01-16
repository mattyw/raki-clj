(ns raki-clj.test.core
  (:use [raki-clj.core])
  (:use [clojure.test]))

(deftest test-get-ranking
    (let [data (get-fake-data)
          expected {:c [{:v "Keflavik, Iceland", :f "Iceland (Greenqloud)"} {:v 9.9997720012996, :f "0.000kg"}]}]
  (is (= expected (get-ranking data 0)))))

(deftest test-rank-to-list
    (let [data (get-fake-data)
        expected {:name "Iceland (Greenqloud)", :location "Keflavik, Iceland", :value 9.9997720012996, :co2 "0.000kg"}]
    (is (= expected (rank-to-list (get-ranking data 0))))))
