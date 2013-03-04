(ns game-of-life.core
  (:use clojure.core clojure.repl))

(def pres-area (new javax.swing.JTextArea))
(def neighbors [[-1 -1] ])


(def board [[:dead :dead :dead  :dead :dead]
            [:dead :dead :dead :dead :dead]
            [:dead :dead :alive :dead :dead]
            [:dead :dead :alive :dead :dead]
            [:dead :dead :alive :dead :dead]
            [:dead :dead :dead :dead :dead]])

(defn set-text [text]
  (.setText pres-area text))

(defn create-empty-board [size-x size-y]
  (vec (for [x (range size-x) y (range size-y)] 0)))

(defn create-2d-vec [size-x size-y]
  (vec (repeat size-y (vec (for [x (range size-x)] :dead)))))

(defn generate-row-string [to-print]
  (->> to-print
       (map (fn [x] 
              (.concat 
                (if (= :dead x)
                  "_"
                  "*") " ")))
       (reduce (fn [x y] (.concat x y)))))

(defn print-2d-vector [to-print]
  (->> to-print
    (map generate-row-string)
    (reduce (fn [x y] (.concat(.concat x "\n") y)))))

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


(def frame (create-gof-frame pres-area))

(defn one-if-alive [cell]
  (if (= :alive cell)
    1
    0)) 
 
(defn find-neighbor-indices [cell-x cell-y]
  (for [x [-1 0 1] y [-1 0 1]:when  (not (= x y))] 
    [(+ cell-x x)  (+ cell-y y)]))
  
 (defn count-alive-neighbors [cell-x cell-y board]
   (->> (find-neighbor-indices cell-x cell-y)
        (map (fn [position] (get-in board position)))
        (map one-if-alive)
        (reduce +)))

(defn handle-alive [amount-of-neighbors]
  (if (or (= 2 amount-of-neighbors) (= 3 amount-of-neighbors))
    :alive
    :dead))

(defn handle-dead [amount-of-neighbors]
  (if  (= 3 amount-of-neighbors)
    :alive
    :dead))

 
 (defn calculate-new-board [board]
   (vec (for [x (range (.size board))]
     (vec
       (for [y (range (.size (get board 0)))]
        (let [alive-neighbors (count-alive-neighbors x y board)
              element (get-in board [x y])]
          (print x y)
              (if (= :alive  element)
                (handle-alive alive-neighbors)
                (handle-dead alive-neighbors))))))))

        
