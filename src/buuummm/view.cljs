(ns buuummm.view
  (:require
    [clojure.string :as str]
    [hoplon.core :as h]
    [javelin.core :as j :refer [cell cell= defc defc=]]))

(def level-1
  ["la;"
   "sal"
   "salsa"
   "dadas;"
   "assada"
   "sassa"
   "kakas"
   "ja"
   "safada"
   "kaka"
   "asa"
   "fadas;"
   "salada"
   "lalas"
   "fala;"
   "alas"
   "sala"
   "jaja"
   "lala"])

(def level-2
  ["sala"
   "sal"
   "fala"
   "gala"
   "asa"
   "jaja"
   "kaka"
   "fada"
   "haja"
   "gaja"
   "fadas"
   "alas"
   "lala"
   "safada"
   "adaga"
   "haja;"
   "salada"
   "safada;"
   "fadas;"
   "alfa"
   "dada"
   "ska"
   "lalas"
   "adagas"
   "shala"
   "hal"
   "lag"
   "fada;"
   "ah"
   "gah"
   "shh;"
   "haka"
   "salas"
   "gala"])

(def words
  (cycle
    (shuffle
      level-2)))

(defc index 0)

(defn create-selected
  [word]
  (mapv (fn [char]
          {:char char
           :status :miss}) word))

(defn random-color
  []
  (rand-nth [:purple :grey :brown :green :blue :orange :red]))

(def horizontal-values
  (vec (range 8 86)))

(def vertical-values
  (vec (range 14 75)))

(defc state
  {:index 0
   :correct-char 0
   :incorrect-char 0
   :finished-words 0
   :got-right ""
   :horizontal (rand-nth horizontal-values)
   :vertical (rand-nth vertical-values)
   :color (random-color)
   :status :idle
   :selected-word (create-selected (nth words @index))})

(defn color->px
  [color]
  (get 
  {:purple "0px"
   :grey "72px"
   :brown "144px"
   :green "216px"
   :blue "288px"
   :orange "360px"
   :red "432"} color :green))

(h/defelem slime
  []
  (h/div :class (cell= {:slime-die (= (:status state) :die)
                        :slime-idle (= (:status state) :idle)})
    :style (cell= (str "background-position-y:" (color->px (:color state)) ";"))))

(h/defelem bee
  []
  (h/div :class "bee-idle"
    :style "background-position-y: 0;"))

(defn hello []
  (h/div
    (h/div :class "game"
      (h/div :class "score"
        (h/div (h/text "Acertou: ~(:correct-char state)"))
        (h/div (h/text "Palavras ~(:finished-words state)"))
        (h/div (h/text "Errou: ~(:incorrect-char state)")))
      (h/div  :style (cell= (str "font-size: 56px; margin:0px; text-transform:uppercase; position:absolute; right:" (:horizontal state) "vw; bottom: " (:vertical state) "vh;"))
        (slime)
        (h/for-tpl [char (cell= (:selected-word state))]
          (h/span :class (cell=  {:red (= :miss (:status char))
                                  :green (not= :miss (:status char))})
            (h/text "~(:char char)")))))))

(def errou (js/Audio. "sound/errou.wav"))
(def bum (js/Audio. "sound/bum.wav"))

(defn process-key
  [e]
  (if (str/starts-with? (nth words (:index @state))
        (str (:got-right @state) (.-key e)))
    (let [right (swap! state (fn [os]
                               (-> os
                                 (update :got-right str (.-key e))
                                 (update :correct-char inc)
                                 (update-in [:selected-word (dec (count (str (:got-right @state) (.-key e))))]
                                   assoc :status :hit))))]
      (when (= (:got-right right) (nth words (:index @state)))
        (.play bum)
        (swap! state (fn [os]
                       (assoc os :status :die)))
        (h/with-timeout 800
          (js/console.log 
          (swap! state (fn [os]
                         (let [new-index (inc (:index os))]
                         (-> os
                           (update :finished-words inc)
                           (assoc :got-right ""
                             :color (random-color)
                             :status :idle
                             :horizontal (rand-nth horizontal-values)
                             :vertical (rand-nth vertical-values)
                             :selected-word (create-selected (nth words new-index))
                             :index new-index)))))))))
    (do 
      (swap! state (fn [os]
                     (-> os
                       (update :incorrect-char inc))))
      (.play errou))))
