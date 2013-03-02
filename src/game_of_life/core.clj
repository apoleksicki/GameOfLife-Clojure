(ns game-of-life.core
  (:use clojure.core clojure.repl ))

(def pres-area (new javax.swing.JTextArea))
(def neighbors [[-1 -1] ])

(defn set-text [text]
  (.setText pres-area text))

(defn create-empty-board [size-x size-y]
  (vec (for [x (range size-x) y (range size-y)] 0)))

(defn create-2d-vec [size-x size-y]
  (vec (repeat size-y (vec (for [x (range size-x)] :dead)))))

(defn generate-row-string [to-print]
  (reduce (fn [x y] (.concat x y))
          (map  (fn [x] (.concat 
                          (if (= :dead x)
                            "_"
                            "*" " ")) 
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

(defmacro on-action [component event & body]
  `(. ~component addActionListener
      (proxy [java.awt.event.ActionListener] []
        (actionPerformed [~event] ~@body))))

(defn create-start-button []
  (doto (new javax.swing.JButton "Start")
    (on-action event
               (.append pres-area "foo"))))

      
 
(defn create-gof-frame [text-area]
  (doto (create-frame "Game of life" 200 200)
    (.add 
      (doto (create-panel (new java.awt.BorderLayout))
        (.add text-area (. java.awt.BorderLayout CENTER))
        (.add (create-start-button) (. java.awt.BorderLayout SOUTH))))))

<<<<<<< TREE
(def frame (create-gof-frame pres-area))

(defn transform-row-to-vec [row] 

(defn count-alive-neighbors [board x y])
  
(defn one-if-alive [cell]
  (if (= :alive cell)
    1
    0)) 
 
(defn find-neighbor-indices [cell-x cell-y]
  (for [x [-1 0 1] y [-1 0 1]:when  (not (= x y))] 
    [(+ cell-x x)  (+ cell-y y)]))
  
 (defn count-alive-neighbors [cell-x cell-y board]
   (map 
