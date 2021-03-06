(ns dynamic-programming.coin-change-test
  (:require [clojure.test :refer :all]
            [dynamic-programming.coin-change :as sut]))

(deftest coin-change-count-recursive-test
  (testing "coin-change-count-recursive given empty coins should return zero"
    (is (zero? (sut/coin-change-count-recursive nil 7)))
    (is (zero? (sut/coin-change-count-recursive #{} 7))))

  (testing "coin-change-count-recursive given no change possible should return zero"
    (is (zero? (sut/coin-change-count-recursive #{3 5 6} 7)))
    (is (zero? (sut/coin-change-count-recursive #{3 5 11 9} 4)))
    (is (zero? (sut/coin-change-count-recursive #{3 5 11 9} 2))))

  (testing "coin-change-count-recursive given trivial cases should return one"
    (is (= 1 (sut/coin-change-count-recursive #{7} 7))))

  (testing "coin-change-count-recursive given coins and possible solutions should return their count"
    (is (= 4 (sut/coin-change-count-recursive #{1 2 3} 4)))
    (is (= 5 (sut/coin-change-count-recursive #{2 5 3 6} 10)))))

(deftest coin-change-count-dp-test
  (testing "coin-change-count-dp given empty coins should return zero"
    (is (zero? (sut/coin-change-count-dp nil 7)))
    (is (zero? (sut/coin-change-count-dp #{} 7))))

  (testing "coin-change-count-dp given no change possible should return zero"
    (is (zero? (sut/coin-change-count-dp #{3 5 6} 7)))
    (is (zero? (sut/coin-change-count-dp #{3 5 11 9} 4)))
    (is (zero? (sut/coin-change-count-dp #{3 5 11 9} 2))))

  (testing "coin-change-count-dp given trivial cases should return one"
    (is (= 1 (sut/coin-change-count-dp #{7} 7))))

  (testing "coin-change-count-dp given coins and possible solutions should return their count"
    (is (= 4 (sut/coin-change-count-dp #{1 2 3} 4)))
    (is (= 5 (sut/coin-change-count-dp #{2 5 3 6} 10)))
    (is (= (sut/coin-change-count-recursive #{2 5 3 6} 100) (sut/coin-change-count-dp #{2 5 3 6} 100)))
    (is (= 928149593 (sut/coin-change-count-dp #{2 5 3 6} 10000)))))