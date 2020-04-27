(ns dynamic-programming.fibonacci
  (:require [dynamic-programming.time :refer [dp-time]]))


(defn lazy-seq-fib [n]
  (letfn [(fib [a b] (cons a (lazy-seq (fib b (+ b a)))))]
    (nth (fib 0N 1) n)))

(defn slow-fib [n]
  (cond (<= n 0) 0
        (<= n 2) 1
        :else (+ (slow-fib (- n 1)) (slow-fib (- n 2)))))

(def memo-fib
  (memoize
    (fn [n]
      (cond (<= n 0) 0
            (<= n 2) 1
            :else (+ (memo-fib (- n 1)) (memo-fib (- n 2)))))))

(defn tail-fib [n]
  (letfn [(fib [current next n]
            (if (= n 0)
              current
              (fib next (+ current next) (dec n))))]
    (fib 0N 1N n)))

(defn recur-fib [n]
  (letfn [(fib [current next n]
            (if (zero? n)
              current
              (recur next (+ current next) (dec n))))]
    (fib 0N 1N n)))


(comment

  (lazy-seq-fib 0)
  (lazy-seq-fib 1)
  (lazy-seq-fib 5)
  (lazy-seq-fib 6)
  (dp-time "lazy-seq-fib" (lazy-seq-fib 40))
  (dp-time "slow-fib" (slow-fib 40))
  (dp-time "memo-fib" (memo-fib 40))
  (dp-time "tail-fib" (tail-fib 40))
  (dp-time "recur-fib" (recur-fib 40))

  (dp-time "fib-lazy-seq" (lazy-seq-fib 100000))
  (dp-time "fib-lazy-seq" (recur-fib 100000))
  )