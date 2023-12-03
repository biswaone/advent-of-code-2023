
(def input (slurp "input.txt"))

(def games (clojure.string/split-lines input))

(def max-red 12)
(def max-green 13)
(def max-blue 14)

(defn get-game-id [game] (Integer/parseInt (second (re-find #"Game (\d+)" game))))

;; "Game 1: 2 blue, 3 red; 3 green, 3 blue, 6 red; 4 blue, 6 red; 2 green, 2 blue, 9 red; 2 red, 4 blue"
(defn parse-game [game]
  (->> (second (clojure.string/split game #":"))
       (#(clojure.string/split % #";"))))

(defn parse-game-set [set]
  (->> (re-seq #"\d+ \w+" set)
       (map #(clojure.string/split % #" "))
       (map #(vector (second %) (Integer.(first %))))
       (into {})))
  
(defn get-game-sets [game] (map parse-game-set (parse-game game)))

(defn find-max-value [key data]
  (apply max (map #(get % key 0) data)))

(defn is-valid-game [game]
  (let [game-set (get-game-sets game)]
    (and (>= max-red (find-max-value "red" game-set))
         (>= max-green (find-max-value "green" game-set))
         (>= max-blue (find-max-value "blue" game-set)))))

(defn power-cubes-for-each-game [game]
  (let [game-set (get-game-sets game)]
    (* (find-max-value "red" game-set)
       (find-max-value "green" game-set)
       (find-max-value "blue" game-set))))


(def answer-part-1 (reduce + 0 (map get-game-id(filter is-valid-game games))))
(def answer-part-2 (reduce + 0 (map power-cubes-for-each-game games)))