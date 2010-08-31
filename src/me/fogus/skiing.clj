(ns me.fogus.skiing)

(defn S [x] 
  (fn [y] 
    (fn [z] 
      ((x z) (y z)))))

(defn K [x]
  (fn [y] x))

(defn I [x] 
  (((S K) K) x))

(defn Y [r]
  ((fn [f] (f f))
   (fn [f]
     (r 
      (fn [x] 
        ((f f) x))))))

(defn M [x] 
  (((S I) I) x))
 
(defn U [f]
  (f f))

          
(comment
  (deftest- can-U-fixed-point
    (let [fct (fn [f]
                (fn [n] 
                  (if (zero? n)
                    1
                    (* n ((f f) (- n 1))))))]
      (is (= ((U fct) 3) 6))
      (is (= 120 ((U (fn [f] 
                       (fn [n] 
                         (if (zero? n)
                           1
                           (* n ((f f) (- n 1))))))) 5))))))
