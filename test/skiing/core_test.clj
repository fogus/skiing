(ns skiing.core-test
  (:use [me.fogus.skiing] :reload)
  (:use [clojure.test]))

(def factU (fn [f]
            (fn [n] 
              (if (zero? n)
                1
                (* n ((f f) (- n 1)))))))

(deftest can-U-fixed-point
  (is (= 120 ((U factU) 5N)))
  (is (= 6   ((U factU) 3N)))
  (is (=     ((U factU) 100N))   
             93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000N))

(def factZ (fn [f]
             (fn [x]
               (if (zero? x)
                 1
                 (* x (f (dec x)))))))

(deftest can-Z-fixed-point
  (is (= 120 ((Z factZ) 5N)))
  (is (= 6   ((Z factZ) 3N)))
  (is (=     ((Z factZ) 100N))   
             93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000N))
