(ns me.fogus.skiing)

;; Sxyz → xz(yz)
(defn S [x] 
  (fn [y] 
    (fn [z] 
      ((x z) (y z)))))

;; Kxy → x
(defn K [x]
  (fn [y]
    x))

(defn I [x] 
  (((S K) K) x))

(defn B [x]
  (fn [y]
    (fn [z]
      (x (y z)))))

(defn C [x]
  (fn [y]
    (fn [z]
      ((x z) y))))

(defn W [x]
  (fn [y]
    ((x y) y)))

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

(defn curry2 [f]
  (fn [a]
    (fn [b] (f a b))))

(defn uncurry
  [f]
  (fn [& args]
    (if args
      (uncurry (apply partial f args))
      (f))))
