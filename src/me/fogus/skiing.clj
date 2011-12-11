(ns me.fogus.skiing)

;; Sfgx → fx(gx)
(defn S
  ""
  [f] 
  (fn [g] 
    (fn [x] 
      ((f x) (g x)))))

(def starling S)
(def <*> S)

;; Kxy → x
(defn K
  "The constant combinator.  Similar to Clojure's `constantly` and Haskell's `pure`."
  [x]
  (fn [y]
    x)) 

(def kestrel K)
(def pure K)

;; Ix → x
(defn I
  "The identity combinator.  Similar to Clojure's `identity` and Haskell's `id`."
  [x] 
  (((S K) K) x))

(def idiot I)
(def id I)

;; Bfgx → f(gx)
(defn B
  "The composition of f and g.  This is akin to the `<$>` operator in Haskell."
  [f]
  (fn [g]
    (fn [x]
      (f (g x)))))

(def bluebird B)
(def <$> B)

;; Cfxy → fyx
(defn C
  "Swaps the args x and y.  This is the same as the `flip` function in Haskell."
  [f]
  (fn [x]
    (fn [y]
      ((f y) x))))

(def cardinal C)
(def flip C)

;; Wfx → fxx
(defn W
  "Dups the arg b.  This is similar to Haskell's `join`."
  [f]
  (fn [x]
    ((f x) x)))

(def warbler W)
(def join W)


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


(defn T [& args]
  (reduce #(%2 %1) args))

(def thrush T)


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

(defn cleave2
  [x & fns]
  ((apply juxt fns) x))