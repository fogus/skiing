(ns me.fogus.skiing)

;; Sfgx → fx(gx)
(defn S [f] 
  (fn [g] 
    (fn [x] 
      ((f x) (g x)))))

(def starling S)

;; Kxy → x
(defn K [x]
  (fn [y]
    x))

(def kestrel K)

;; Ix → x
(defn I [x] 
  (((S K) K) x))

(def idiot I)

;; Bfgx → f(gx)
(defn B
  "The composition of f and g"
  [f]
  (fn [g]
    (fn [x]
      (f (g x)))))

(def bluebird B)

;; Cfxy → fyx
(defn C
  "Swaps the args x and y"
  [f]
  (fn [x]
    (fn [y]
      ((f y) x))))

(def cardinal C)

;; Wfx → fxx
(defn W
  "Dups the arg b"
  [f]
  (fn [x]
    ((f x) x)))

(def warbler W)

(defn Y [r]
  ((fn [f] (f f))
   (fn [f]
     (r 
      (fn [a] 
        ((f f) a))))))

(def sage Y)


(defn Z [f]
  ((fn [x] (f (fn [y] ((x x) y))))
   (fn [x] (f (fn [y] ((x x) y))))))

(def sage-once-removed Z)


(defn M [a] 
  (((S I) I) a))

(def mockingbird M)

 
(defn U [f]
  (f f))

(def turing U)


;; currying/uncurrying

(defn curry2 [f]
  (fn [a]
    (fn [b] (f a b))))

(defn uncurry
  [f]
  (fn [& args]
    (if args
      (uncurry (apply partial f args))
      (f))))

;; cleave

(defn cleave
  [& args]
  ((apply juxt (butlast args))
   (last args)))


(cleave 42 int str)
((juxt int str) 42)

(defn cleave2
  [x & fns]
  ((apply juxt fns) x))