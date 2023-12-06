(def input (slurp "input.txt"))

(def games(clojure.string/split-lines input))

(defn get-winning-numbers [game]
  (-> game
      (clojure.string/split #"\|")
      first
      clojure.string/trim
      (clojure.string/split #": ")
      second
      clojure.string/trim
      (clojure.string/split #"\s+")
      set))

(defn get-user-numbers [game]
  (-> game
      (clojure.string/split #"\|")
      second
      clojure.string/trim
      (clojure.string/split #"\s+")
      set))

(defn calculate-game-point [game]
  (let [user-numbers (get-user-numbers game)
        winning-numbers (get-winning-numbers game)
        common-numbers (count (clojure.set/intersection winning-numbers user-numbers))]
    (if (zero? common-numbers)
      0
      (Math/pow 2 (- common-numbers 1)))))

   
(def answer-1 (int (reduce + 0(map calculate-game-point games))))




