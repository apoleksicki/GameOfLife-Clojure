(ns game-of-life.test
  (:use clojure.test clojure.repl game-of-life.core ))

(def expected-board 
  [[:dead :dead :dead :dead :dead]
   [:dead :dead :dead :dead :dead]
   [:dead :dead :dead :dead :dead]
   [:dead :alive :alive :alive :dead]
   [:dead :dead :dead :dead :dead]
   [:dead :dead :dead :dead :dead]])

(def vector-string "_ _ _ _ _ \n_ _ _ _ _ \n_ _ * _ _ \n_ _ * _ _ \n_ _ * _ _ \n_ _ _ _ _ ")

(def board [[:dead :dead :dead  :dead :dead]
            [:dead :dead :dead :dead :dead]
            [:dead :dead :alive :dead :dead]
            [:dead :dead :alive :dead :dead]
            [:dead :dead :alive :dead :dead]
            [:dead :dead :dead :dead :dead]])

(defn foo-test []
  (is (= 4 (+ 2 2)) "Two plus two should be 4"))

(defn calculate-new-board-test []
  (is (= expected-board (calculate-new-board board))))

(defn string-to-vector-test [] 
  (is (= board (string-to-vector vector-string))))