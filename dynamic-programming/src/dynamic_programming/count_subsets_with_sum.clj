(ns dynamic-programming.count-subsets-with-sum
  "For more details about the Count subsets with sum equal to X problem see
  [Count Subsets](https://www.geeksforgeeks.org/count-of-subsets-with-sum-equal-to-x-using-recursion/)."
  (:require [dynamic-programming.time :refer [dp-time]]))

(defn count-subsets [[s & rst] sum]
  (cond
    (zero? sum) 1
    (nil? s) 0
    :else (+ (count-subsets rst (- sum s))
             (count-subsets rst sum))))


(comment

  (count-subsets [1 2 3 3] 6)
  (count-subsets (range 1 8) 6)

  (require '[clojure.string :as str])
  (->> (range 1 25)
       (repeat 2)
       (apply interleave)
       (str/join ", "))

  (let [n 15
        input (->> (range 1 15)
                   (repeat 2)
                   (apply interleave))]
    (dp-time "count-subsets" (count-subsets input n)))

  (count-subsets (repeat 4 1) 1)

  )
