(ns example-clojure.core
  (:require [org.httpkit.server :as s]))

; entry point to your program
; clojure employs prefix notation, meaning operators always comes first in an expression

;;Conceptually, the REPL is similar to Secure Shell (SSH). In the same
;way that you can use SSH to interact with a remote server, the Clojure REPL
;allows you to interact with a running Clojure process.

;;This feature can be
;very powerful because you can even attach a REPL to a live production app
;and modify your program as it runs.


(defn -main
  "I don't do a whole lot."
  []
  (println "Hello, World!"))

; operation signature
; (operator operand1 operand2 ... operandn)


; Control flow
; if
;(if boolean-form
; then-form
; optional-else-form)

(if true
  "I am true"
  "I am not true")

(if false
  "i am true")

;do
;The do operator lets you wrap up multiple forms in parentheses and run
;each of them. Try the following in your REPL:

(if true
  (do (println "I am true")
      "And I love mangos")
  (do (println "I am false")
      "And I love bananas"))

; when
;The when operator is like a combination of if and do, but with no else branch.
;Use when if you want to do multiple things when some condition is true,
;and you always want to return nil when the condition is false.

(when true
  (println "Success!")
  "Hooray")

;nil, true, false, Truthiness, Equality, and Boolean Expressions
;Clojure has true and false values. nil is used to indicate no value in Clojure.
;You can check if a value is nil with the appropriately named nil? function:

; false
(nil? 1)

; true
(nil? nil)

;Both nil and false are used to represent logical falsiness, whereas all
;other values are logically truthy. Truthy and falsey refer to how a value is
;treated in a Boolean expression, like the first expression passed to if:

(if "im just a string but this if still evaluates to true!"
  "I am true")

(if nil
  "This will evaluate to false because nil is falsey")

; equality =

;true
(= 1 1)

;true
(= nil nil)

;false
(= 1 2)

;or
;Clojure uses the Boolean operators or and and. or returns either the first
;truthy value or the last value.

; since anything other nil false are trusey, this will evaluate to :large_T_mean_venti
(or false nil :large_T_mean_venti :why_cant_I_just_say_large)

(or (= 0 1) (= "yes" "no"))

;and
;and returns the first falsey value or, if no values
;;are falsey, the last truthy value. Let’s look at or first:

; since no values are falsey, it will return :hot_coffee
(and :free_wifi :hot_coffee)

; return the first falsey value, which is nil
(and :feelin_super_cool nil false)

; naming values with def
; you use def to bind a name to a value
; you should treat
;def as if it’s defining constants

(def failed-protagonist-names
  ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"])

(def severity :mild)

(defn error-message
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (if (= severity :mild)
         "MILDLY INCONVENIENCED!"
         "DOOOOOMED")))

;map

; empty map
{}

; not so empty map
(def map-example
  {:first-name "Charlie"
   :last-name "Mcfishwich"})

(get map-example :first-name)

; associating "string-key" to + function
{"string-key" +}

; multi-level map
(def mmap
  {:greeting "hello"
   :contacts {
       :first-name "Bobby"
       }})

; these returns the nested map, to get the value, you use get-in
(get mmap :contacts)
(get mmap :contacts :first-name)

; get will return nil if it doesn’t find your key, or you can give it a default
;value to return, such as "unicorns?":

(get {:greeting 0 :contacts 1} :c)

(get mmap :abc "this is my default value")

(get-in mmap [:contacts :first-name] )

(if  (= (get-in mmap [:contacts :first-name]) "Bobby")
  (println "gotcha!"))

; keyword

; Keywords can be used as functions that look up the corresponding
; value in a data structure. For example, you can look up :a in a map:

(:a {:a 1 :b 2 :c 4})

; equivalent to

(get {:a 1 :b 2 :c 3} :a "default value")

; vectors
; 0-indexed

; 3
(get [3 2 1] 0)

; vectors can be of any type
(get ["a" {:name "Pugsley"} "C"] 1)

; using vector to create vectors
(vector "Creepy" "Apple" "Banana")

; append to last of vector
(conj [1 2 3] 4)

; lists
; Lists are similar to vectors in that they’re linear collections of values. But
;there are some differences. For example, you can’t retrieve list elements
;with get. To write a list literal, just insert the elements into parentheses and
;use a single quote at the beginning:


(def list-of-numbers
  '(1 2 3 4 5 6))

; to retrieve an element

(nth list-of-numbers 0)
(nth '(:a :b :c) 2)

; lists can be of any type
(list 1 "two")

; append to first of list
(conj list-of-numbers 1)

; sets
;Sets are collections of unique values. Clojure has two kinds of sets: hash
;sets and sorted sets. I’ll focus on hash sets because they’re used more often.
;Here’s the literal notation for a hash set:

#("A" "B" 1)

; {1 2}
(hash-set 1 2 1 2)

; this append has no effect
(conj #{:a :b} :b)

; check membership - true
(contains? #{:a :b} :a)

; use as keyword

(:a #{:a :b})

; is equivalent to
(get #{:a :b} :a)

;;;;;;; functions;;;;;;;;

;  Function call is just
;another term for an operation where the operator is a function or a function expression

(or + -)

; this returns 10 because or returns the first truesy value
((or + - ) 1 2 3 4)

((first [+ 0]) 1 2 3 4 5)

; this function increments the input by 1
(inc 1)

; map function
(map-example inc '(1 2 3 4 5))

;The last detail that you need know about function calls is that Clojure
;evaluates all function arguments recursively before passing them to the
;function. Here’s how Clojure would evaluate a function call whose arguments are also function calls:

(+ (inc 199) (/ 100 (- 7 2)))
(+ 200 (/ 100 (- 7 2))) ; evaluated "(inc 199)"
(+ 200 (/ 100 5)) ; evaluated (- 7 2)
(+ 200 20) ; evaluated (/ 100 5)

;The function call kicks off the evaluation process, and all subforms are
;evaluated before applying the + function.

; defining functions
;•	 defn
;•	 Function name
;•	 A docstring describing the function (optional)
;•	 Parameters listed in brackets
;•	 Function body

(defn too-enthusiastic
   "Return a cheer that might be a bit too enthusiastic"
   [name]
   (str "OH. MY. GOD! " name " YOU ARE MOST DEFINITELY LIKE THE BEST "
         "MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"))

; function with no args: 0-arity

(defn no-arg-function
  "An docstring that describes the functionality of this api"
  []
  (println "I am no arguments!"))

; 1-arity
(defn one-arg-function
  "One arg example"
  [x]
  (println "This function prints" x ", how useful...."))

; 2-arity
(defn two-arg-function
  "Two args now!"
  [x y]
  (println "This function is capable of printing the inputs" x y))

; multi-arity function, one way to provide default arguments
(defn multi-arity-function
  ([first second third]
   (println "this is a 3-arity function" first second third))
  ([first second]
   (println "this is a 2-arity function") first second)
  ([first]
   (println "this is a 1-arity function") first))

(defn x-chop
  "Descibes the kind of chop you're inflicting on someone"
  ([name chop-type]
   (str "I " chop-type " chop " name "! Take that!"))
  ([name]
   (x-chop name "karate")))

; rest parameter - variable-arity function
; & whippersnapper is basically a list, the map simply takes the codger-communication and maps it
; to the list


(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
  [& whippersnappers]
  (map-example codger-communication whippersnappers))

(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things)))

; destructuring
;The basic idea behind destructuring is that it lets you concisely bind names
;to values within a collection. Let’s look at a basic example:

(defn my-first
  [[first-thing]]
  first-thing)

;Here, the my-first function associates the symbol first-thing with
;the first element of the vector that was passed in as an argument. You tell
;my-first to do this by placing the symbol first-thing within a vector.

; >> A
(my-first ["A" "B" "C"])

; >> A
(my-first '("A" "B"))

(defn chooser
  [[first-choice second-choice & unimportant-choice]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "We are ignoring the rest: " (clojure.string/join ", " unimportant-choice))))

(defn chooser-alt
  [& choices]
  (println (str "Your first" (nth choices 0))))

; destructuring maps

(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(defn announce-treasure-location-shorter-syntax
  [{:key [lat lng]}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

; You can retain access to the original map argument by using the :as
;keyword.

(defn receive-treasure-location
  [{:key [lat lng] :as treasure-location}]
  (println (str "Treasure lat" lat))
  (println (str "Treasure lng" lng)))

;In general, you can think of destructuring as instructing Clojure on
;how to associate names with values in a list, map, set, or vector. Now, on to
;the part of the function that actually does something: the function body!

;function body
;The function body can contain forms of any kind. Clojure automatically
;returns the last form evaluated. This function body contains just three
;forms, and when you call the function, it spits out the last form, "joe":

(defn illustrative-function
  []
  (+ 1 304)
  30
  "joe")

;All Functions Are Created Equal
;One final note: Clojure has no privileged functions. + is just a function, - is
;just a function, and inc and map are just functions. They’re no better than
;the functions you define yourself. So don’t let them give you any lip!
;More important, this fact helps demonstrate Clojure’s underlying
;simplicity. In a way, Clojure is very dumb. When you make a function call,
;Clojure just says, “map? Sure, whatever! I’ll just apply this and move on.” It
;doesn’t care what the function is or where it came from; it treats all functions the same. At its core, Clojure doesn’t give two burger flips about addition, multiplication, or mapping. It just cares about applying functions.
;As you continue to program with Clojure, you’ll see that this simplicity
;is ideal. You don’t have to worry about special rules or syntax for working
;with different functions. They all work the same!

;anonymous function

;signature
;(fn [param-list]
; function body)

; think of below as lambda expression
; s -> return "Hi " + name
(map (fn [name] (str "Hi, " name))
             '("Hello" "Welcome"))

((fn [x] (* x 3)) 8)

(def add-function (fn [x y] (+ x y)))

(#(* % %) 5)

(map #(* % %) [1 2 3 4 5])
(map inc [1 2 3 4 5])
(map #(str "Hi, I am " %)
     ["Brendon" "Ming"])

; returning function

(defn inc-maker
  [x]
  #(+ % x))

(def inc-by-3 (inc-maker 3))

(inc-by-3 3)

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))


(symmetrize-body-parts asym-hobbit-body-parts)

; let

(let [x 3] x)

(def dalmatian-list
  ["Pongo" "Perdita" "Puppy"])

(let [dalmatians (take 2 dalmatian-list)]
  dalmatians)

; let has a narrower scope than def

; def has a global scope
(def x 8)
(* x x)
;let has a local scope that is scoped to this particular form, it is not accessible outside of let form
(let [x 1])
(* x x)

(def square #(* % %))

; you can use the global def in let form
(def x 9)
(let [x (map square [x])] x)

; you can use rest parameters for let form

; this is letting pongo be the first element and the rest be in dalmatians -> destructuring concept
(let [[pongo & dalmatians] dalmatian-list]
  [pongo, dalmatians])

;let forms have two main uses. First, they provide clarity by allowing you
;to name things.
;Second, they allow you to evaluate an expression only once
;and reuse the result.

(defn incrementer-factory
  [x]
  #(+ % x))

(map (incrementer-factory 3) [1 2 3 4 5 ])


(into [] (set '("A" "A" "B")))

; binding iteration to 0
(loop [iteration 0]
  (println (str "Iteration " iteration))
  (if (> iteration 3)
    (println "Goodbye!")
    (recur (inc iteration))))

(defn recursive-printer
  ([]
   (recursive-printer 0))
  ([iteration]
   (println iteration)
   (if (> iteration 3)
     (println "goodbye")
     (recursive-printer (inc iteration)))))

(str "hello" "buddy")
(vector 1 2 3 4 5)
(hash-set :a 1 :b 2 :c 3 3 3 4 4 3 43 4 3 43 4)
(:a (hash-map :a 2 :b 1 :c 9 :d 9))

(defn dec-factory
  "this method decrements values"
  [value]
  #(- % value))

(def dec9 (dec-factory 9))
(dec9 10)

; (2 2 3 3)
(map inc [1 1 2 2])


(defn mapset
  "this is similar to map, but returns a set"
  [fn v]
  (set (map fn v)))

(mapset inc [1 1 2 2])
(1 2 3 4 5)


; as long as the sequence work with the below functions,
; it will with other core sequence function for free
; map, reduce, into, conj, concat, some, filter, take, drop ,sort, sort-by, identity

;For example, the battery abstraction includes the operation “connect a
;conducting medium to its anode and cathode,” and the operation’s output
;is electrical current. It doesn’t matter if the battery is made out of lithium or
;out of potatoes. It’s a battery as long as it responds to the set of operations
;that define battery.

(def even-number-predicate
  (fn [x]
    (if (= (mod x 2) 0) true false)))

(defn multiple-by-2
  [x]
  (* x 2))

(def random-lists '(1, 2, 3, 4, 5, 4, 43, 190))
(first random-lists)
(rest random-lists)
(cons 999 random-lists)
;non failed, that means it will work with core sequence operators like...
(map inc random-lists)
(reduce + random-lists)
(into '(1 2 3) random-lists) ;(5 4 3 2 1 1 2 3)
(conj '(1 2 3) random-lists) ;((1 2 3 4 5) 1 2 3)
(some (fn [x] (< x 1)) random-lists) ;nil (failed the predicte)
(filter even-number-predicate random-lists)
(take 3 random-lists) ;take 3 items beginning of list
(drop 3 random-lists) ;drop 3 items beginning of list
(sort random-lists) ;if no comparator is supplied, numbers will be sorts in ascending order
(identity random-lists) ;returns its argument (the list)


(def random-vector [1 2 3 4 5])
(first random-vector)
(rest random-vector)
(cons 999 random-vector) ; returns a list
;non failed, that means it will work with core sequence operators like...
(map multiple-by-2 random-vector)
(first random-vector)
(rest random-vector)
(conj '(999) random-vector) ; append the value to the end, returns a new list
(some even-number-predicate random-vector)
(filter even-number-predicate random-vector)
(take 3 random-vector)
(drop 3 random-vector)
(sort random-vector)
(identity random-vector) ; returns its argument (the vector)


(def random_hashset (hash-set 1 2 2 3 3 4 4 1 1 23 3 4))
(first random_hashset)
(rest random_hashset)
(cons 999 random_hashset) ; returns a list
;non failed, that means it will work with core sequence operators like...
(map multiple-by-2 random_hashset)
(first random_hashset)
(rest random_hashset)
(conj #{999} random_hashset)
(some even-number-predicate random_hashset)
(filter even-number-predicate random_hashset)
(take 3 random_hashset)
(drop 3 random_hashset)
(sort random_hashset)
(identity random_hashset)

(defn multiple-by-2-for-map [x]
  (* (second x) 2))

(def random_hashmap (hash-map :a 1 :b 1 :c 5 :d 7 :e 9 :g 2))
(first random_hashmap)
(rest random_hashmap)
(cons 999 random_hashmap) ; returns a list
;non failed, that means it will work with core sequence operators like...
(map multiple-by-2-for-map random_hashmap) ; don't know how this workts



































