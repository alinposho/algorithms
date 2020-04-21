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

(comment

  (coin-change-count-recursive [] 9)
  (coin-change-count-recursive nil 9)

  (coin-change-count-recursive [1 2 3] 4)
  (coin-change-count-recursive [2 5 3 6] 10)
  )
