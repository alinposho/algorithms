(ns dynamic-programming.time)

(defmacro dp-time
  "Evaluates expr and logs the time it took. Returns the value of expr.
  Adapted from clojure.core/time"
  [fn-name expr]
  `(let [start# (. System (nanoTime))
         ret# ~expr]
     (println (str "Called '" ~fn-name "' in: " (/ (double (- (. System (nanoTime)) start#)) 1000000.0) " msecs"))
     ret#))
