(ns holdem.core)

(defn suit-val [s] (s {:Diamond 0 :Heart 1 :Spade 2 :Club 3}))
(defn rank-val [r] (if (number? r) r (r {:Jack 11 :Queen 12 :King 13 :Ace 14})))
(defn compare-suits [s1 s2] (- (suit-val s1) (suit-val s2)))
(defn compare-ranks [r1 r2] (- (rank-val r1) (rank-val r2)))

(defn compare-cards
  "Compares 2 cards. Orders by suit first, then by rank" 
  [[s1 r1] [s2 r2]] 
  (let [cs (compare-suits s1 s2) cr (compare-ranks r1 r2)]
    (if (not (= 0 cs)) cs cr)))

(defn make-deck [suits ranks] (for [s suits r ranks] [s r]))
(defn print-cards [out cards]
  (doseq [c cards] 
    (.println out c))
  (.println out))

(def suits [:Diamond :Heart :Spade :Club])
(def ranks (apply conj  (vec (range 2 11)) [:Jack :Queen :King :Ace]))

(def deck (make-deck suits ranks))
(def sort-cards (partial sort-by identity compare-cards))

(defn -main []
  (let [shuffled (shuffle deck) [hand after-pick] (split-at 5 shuffled)]
    (doto System/out
      (.println "Deck: ")
      (print-cards deck)
      (.println)
      (.println "Shuffled: ") 
      (print-cards shuffled)
      (.println)
      (.println "Take 5: ")
      (print-cards hand)
      (.println)
      (.println "Deck after taking 5:")
      (print-cards after-pick))))
