(ns skiing.test.core
  (:use [me.fogus.skiing] :reload)
  (:use [clojure.test]))

(deftest can-U-fixed-point
  (let [fct (fn [f]
              (fn [n] 
                (if (zero? n)
                  1
                  (* n ((f f) (- n 1))))))]
    (is (= 6   ((U fct) 3)))
    (is (= 93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000M   
           ((U fct) 100)))
    (is (= 120 ((U (fn [f] 
                     (fn [n] 
                       (if (zero? n)
                         1
                         (* n ((f f) (- n 1))))))) 5)))))

