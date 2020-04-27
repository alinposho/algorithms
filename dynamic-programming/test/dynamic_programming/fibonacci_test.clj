(ns dynamic-programming.fibonacci-test
  (:require [clojure.test :refer :all]
            [dynamic-programming.fibonacci :refer :all]))


(deftest fib-test
  (testing "fibonacci functions for small values"
    (is (= 0 (lazy-seq-fib 0) (slow-fib 0) (memo-fib 0)))
    (is (= 1 (lazy-seq-fib 1) (slow-fib 1) (memo-fib 1)))
    (is (= 1 (lazy-seq-fib 2) (slow-fib 2) (memo-fib 2)))
    (is (= 5 (lazy-seq-fib 5) (slow-fib 5) (memo-fib 5)))
    (is (= 5 (lazy-seq-fib 5) (slow-fib 5) (memo-fib 5)))
    (is (= 55 (lazy-seq-fib 10) (slow-fib 10) (memo-fib 10))))

  (testing "fibonacci functions by comparing each other for large numbers"
    (is (= (lazy-seq-fib 20) (slow-fib 20) (memo-fib 20)))
    (is (= (lazy-seq-fib 50) (memo-fib 50) (tail-fib 50)))
    (is (= (lazy-seq-fib 100) (tail-fib 100) (recur-fib 100)))
    (is (= (lazy-seq-fib 500) (tail-fib 500) (recur-fib 500)))
    (is (= (lazy-seq-fib 10000) (recur-fib 10000)))
    (is (= (lazy-seq-fib 100000) (recur-fib 100000)))))
