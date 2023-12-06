(def time-distance '[(49 356) (87 1378) (78 1502) (95 1882)])
(def Time 49877895)
(def Distance 356137815021882)

(defn get-part1-answer [time-distance]
  (apply *
         (map (fn [pair]
                (count (filter #(> % (second pair))
                               (map * (reverse (range (inc (first pair))))
                                    (range (inc (first pair)))))))
              time-distance)))

(get-part1-answer time-distance)
(def part2-ans (count (filter #(> % Distance) (map * (range (inc Time)) (reverse (range (inc Time)))))))
