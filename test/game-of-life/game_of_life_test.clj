(ns game-of-life.test
  (:use clojure.test clojure.repl game-of-life.core ))

(def expected-board 
  [[:dead :dead :dead :dead :dead]
   [:dead :dead :dead :dead :dead]
   [:dead :dead :dead :dead :dead]
   [:dead :alive :alive :alive :dead]
   [:dead :dead :dead :dead :dead]
   [:dead :dead :dead :dead :dead]])

(defn foo-test []
  (is (= 4 (+ 2 2)) "Two plus two should be 4"))

(defn calculate-new-board-test []
  (is (= expected-board (calculate-new-board board))))

(defn string-to-vector-test [] 
  (is (= board (string-to-vector vector-string))))