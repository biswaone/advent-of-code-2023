(def calibration (clojure.string/split-lines (slurp "input.txt")))

(def word-to-digit
  {"one" "1"
   "two" "2"
   "three" "3"
   "four" "4"
   "five" "5"
   "six" "6"
   "seven" "7"
   "eight" "8"
   "nine" "9"})

(def reversed-word-to-digit
  {"eno" "1"
   "owt" "2"
   "eerht" "3"
   "ruof" "4"
   "evif" "5"
   "xis" "6"
   "neves" "7"
   "thgie" "8"
   "enin" "9"})

(def reversed-calibration (vec (map #(apply str (reverse %)) calibration)))

(def replaced-words (map #(clojure.string/replace % #"one|two|three|four|five|six|seven|eight|nine" word-to-digit) calibration))

(def reverse-replaced-words (map #(clojure.string/replace % #"eno|owt|eerht|ruof|evif|xis|neves|thgie|enin" reversed-word-to-digit) reversed-calibration))

(def digits (map first (map #(re-seq #"\d" %) replaced-words)))

(def reversed-digits (map first (map #(re-seq #"\d" %) reverse-replaced-words)))

(def part-1-answer (reduce + 0  (map #(Integer/parseInt (str (first %) (last %))) (map #(re-seq #"\d" %) calibration))))

(def part-2-answer (reduce + 0 (map #(Integer/parseInt %) (map str digits reversed-digits))))
      


