(ns game-of-life.core
  (:use clojure.core clojure.repl))

(def pres-area (new javax.swing.JTextArea))

(defn set-text [text]
  (.setText pres-area text))

(defn create-empty-board [size-x size-y]
  (vec (for [x (range size-x) y (range size-y)] 0)))

(defn create-2d-vec [size-x size-y]
  (vec (repeat size-y (vec (for [x (range size-x)] x)))))

(defn generate-row-string [to-print]
  (reduce (fn [x y] (.concat x y))
          (map  (fn [x] (.concat (.toString x) " ")) 
               to-print)))

(defn print-2d-vector [to-print]
  (reduce (fn [x y] (.concat(.concat x "\n") y))
          (map generate-row-string to-print)))



(defn create-frame [title size-x size-y]
  (doto(new javax.swing.JFrame)
    (.setSize size-x size-y)
    (.setTitle title)))
  
(defn create-panel [layout-manager]
   (new javax.swing.JPanel layout-manager true))

(defn create-gof-frame [text-area]
  (doto (create-frame "Game of life" 200 200)
    (.add 
      (doto (create-panel (new java.awt.BorderLayout))
        (.add text-area (. java.awt.BorderLayout CENTER))
        (.add (new javax.swing.JButton "Start") (. java.awt.BorderLayout SOUTH))))))

(def frame (create-gof-frame pres-area))