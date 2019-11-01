(ns example-clojure.chapter-4)

;the core sequence functions first, rest, and cons work on a data structure,
;you can say the data structure implements the sequence abstraction.
;Lists, vectors, sets, and maps all implement the sequence abstraction,

; a sequence abstraction is any sequence that implements first, rest, cons

(defn titlesize
  [topic]
  (str topic " for the brave and true"))

(map titlesize ["Hamsters" "Ragnarok"])
(map titlesize '("Hamsters" "Ragnarok"))
(map titlesize #{"Hamsters" "Ragnarok"})
(map #(titlesize (second %)) {:uncomfortable-thing "Winking" :c 2})

(map (fn [x]
       (println (second x))
       x)
     {:a 1 :b 2})

;When it comes to sequences, Clojure also creates indirection by doing a
;kind of lightweight type conversion, producing a data structure that works
;with an abstraction’s functions. Whenever Clojure expects a sequence—for
;example, when you call map, first, rest, or cons—it calls the seq function
;on the data structure in question to obtain a data structure that allows for
;first, rest, and cons:

(seq '(1 2 3))
; => (1 2 3)
(seq [1 2 3])
; => (1 2 3)
(seq #{1 2 3})
; => (1 2 3)

; remember the map function treats your map like a list of vectors
(seq {:name "Bill Compton" :occupation "Dead mopey guy"})
; => ([:name "Bill Compton"] [:occupation "Dead mopey guy"])

; map

;When you pass map multiple collections, the elements of the first collection
;(["a" "b" "c"]) will be passed as the first argument of the mapping
;function (str), the elements of the second collection (["A" "B" "C"]) will be
;passed as the second argument, and so on.

;Just be sure that your mapping
;function can take a number of arguments equal to the number of collections
;you’re passing to map.

(map str [1 2 3] [1 2 3])

(def human-consumption [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])
(defn unify-diet-data [human critter]
  {:human human :critter critter})

(map unify-diet-data human-consumption critter-consumption)

(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))

(defn stats [numbers]
  "This function calculates the sum and average on a sequence of numbers"
  (map #(% numbers) [sum count avg]))

(def list-of-number '(1 2 3 4 5 6))
(println list-of-number)
(stats list-of-number)

(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-Man" :real "Peter Parker"}
   {:alias "Santa" :real "Your mom"}
   {:alias "Easter Bunny" :real "Your dad"}])

(map :real identities)

; reduce


(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])





















