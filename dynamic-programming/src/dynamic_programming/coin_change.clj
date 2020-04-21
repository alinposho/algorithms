(ns dynamic-programming.coin-change
  "For more details about the Coin Change problem see
  [Coin Change | DP-7](https://www.geeksforgeeks.org/coin-change-dp-7/).")

(defn coin-change-count-recursive
  "Naive solution, i.e. non DP, to the coin change problem."
  [[coin & rst :as coins] amount]
  (cond
    (zero? amount) 1
    (< amount 0) 0
    (and (nil? coin) (< 0 amount)) 0
    :else (+ (coin-change-count-recursive rst amount)
             (coin-change-count-recursive coins (- amount coin)))))


(def coin-change-count-memoized
  (fn [[coin & rst :as coins] amount]
    (cond
      (zero? amount) 1
      (< amount 0) 0
      (and (nil? coin) (< 0 amount)) 0
      :else (+ (coin-change-count-memoized rst amount)
               (coin-change-count-memoized coins (- amount coin))))))

(defn coin-change-count-dp
  "Dynamic Programming solution to the coin change problem"
  ([coins amount]
   (let [table (vec (cons 1 (repeat amount 0)))]
     (coin-change-count-dp coins amount table)))
  ([[coin & rst] amount known-solutions]
   (if-not coin
     ;; We've traversed all coins, therefore the table of known solutions should hold the coin change count as
     ;; its last element
     (last known-solutions)
     ;; build the know solutions vector
     (let [new-known-solutions (reduce (fn [acc c] (update acc c + (acc (- c coin))))
                                       known-solutions
                                       (range coin (inc amount)))]
       (recur rst amount new-known-solutions)))))

  (comment

    (coin-change-count-recursive [] 9)
    (coin-change-count-recursive nil 9)

    (coin-change-count-recursive [1 2 3] 4)
    (coin-change-count-recursive [1 2 3] 100)
    (coin-change-count-recursive [2 5 3 6] 10)

    ;; Notice there is no significant change in the execution time for the following inputs
    (time (coin-change-count-recursive [2 5 3 6] 500))
    (time (coin-change-count-memoized [2 5 3 6] 500))

    (-> (range 1 30)
        (coin-change-count-recursive 50)
        time)

    (-> (range 1 30)
        (coin-change-count-memoized 50)
        time)


    (coin-change-count-dp [] 9)
    (coin-change-count-dp nil 9)
    (coin-change-count-dp #{11 12} 9)
    (coin-change-count-dp #{1 3} 4)

    ;; Notice DP solution is quite fast compare to the others
    (time (coin-change-count-dp #{2 5 3 6} 500))
    (time (coin-change-count-dp #{2 5 3 6} 1000))


    )
