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

(defn random-enemy-type
  []
  (rand-nth [:slime :bee]))

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

(defonce state
  (cell
    {:index 0
     :correct-char 0
     :incorrect-char 0
     :finished-words 0
     :enemy-type :slime
     :got-right ""
     :extra-stats true
     :horizontal (rand-nth horizontal-values)
     :vertical (rand-nth vertical-values)
     :color (random-color)
     :status :idle
     :started-at (js/Date.now)
     :last-updated-at (js/Date.now)
     :selected-word (create-selected (nth words @index))}))

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
  (h/div :class (cell= {:bee-die (= (:status state) :die)
                        :bee-idle (= (:status state) :idle)})
    :style (cell= (str "background-position-y:" (if (= (:status state) :idle) "0px" "90px") ";"))))

(defn wpm
  [state]
  (let [wpm-result (js/Math.trunc (/ (:finished-words state)
                           (/ (/ (- (:last-updated-at state)
                                   (:started-at state)
                                   (* 800 (:finished-words state))) 1000) 60)))]
    (if (js/Number.isNaN wpm-result)
      0
      wpm-result)))

(defn accuracy
  [state]
  (let [accuracy-result (js/Math.trunc (* 100 (/ (:correct-char state)
                                                (+ (:correct-char state)
                                                  (:incorrect-char state)))))]
    (if (js/Number.isNaN accuracy-result)
      0
      accuracy-result)))

(defn hello []
  (h/div
    (h/div :class "game"
      (h/div :class "score"
        (h/div (h/text "Correct Chars: ~(:correct-char state)"))
        (h/div (h/text "Correct Words: ~(:finished-words state)"))
        (h/div (h/text "Wrong Chars  : ~(:incorrect-char state)"))
        (h/when-tpl (cell= (:extra-stats state))
          [(h/div (h/text "WPM          : ~(wpm state)"))
           (h/div (h/text "Accuracy     : ~(accuracy state)%"))]))
      (h/div  :style (cell= (str "font-size: 56px; margin:0px; text-transform:uppercase; position:absolute; right:" (:horizontal state) "vw; bottom: " (:vertical state) "vh;"))
        (h/if-tpl (cell= (= :slime (:enemy-type state))) 
          (slime)
          (bee))
        (h/for-tpl [char (cell= (:selected-word state))]
          (h/span :class (cell=  {:red (= :miss (:status char))
                                  :green (not= :miss (:status char))})
            (h/text "~(:char char)")))))))

(def errou (js/Audio. "sound/errou.wav"))
(def bum (js/Audio. "sound/bum.wav"))

(defn- ignore?
  [^js e]
  (or (.-platformModifierKey e)
    (.-metaKey e)
    (.-altKey e)
    (contains? #{"Alt"
                 "AltGraph"
                 "CapsLock"
                 "Control"
                 "Escape"
                 "GroupNext"
                 "Meta"
                 "Shift"
                 "Tab"
                 "F1"
                 "F2"
                 "F3"
                 "F4"
                 "F5"
                 "F6"
                 "F7"
                 "F8"
                 "F9"
                 "F10"
                 "F11"
                 "F12"}
      (.-key e))))

(defn toggle-extra-stats
  []
  (swap! state update :extra-stats not))

(defn process-key
  [e]
  (when (= "Control" (.-key e))
    (toggle-extra-stats))
  (when-not (ignore? e)
    (if (str/starts-with? (nth words (:index @state))
          (str (:got-right @state) (.-key e)))
      (let [right (swap! state (fn [os]
                                 (-> os
                                   (update :got-right str (.-key e))
                                   (update :correct-char inc)
                                   (assoc :last-updated-at (js/Date.now))
                                   (update-in [:selected-word (dec (count (str (:got-right @state) (.-key e))))]
                                     assoc :status :hit))))]
        (when (= (:got-right right) (nth words (:index @state)))
          (.play bum)
          (swap! state (fn [os]
                         (assoc os :status :die
                           :last-updated-at (js/Date.now))))
          (h/with-timeout 800
            (js/console.log 
              (swap! state (fn [os]
                             (let [new-index (inc (:index os))]
                               (-> os
                                 (update :finished-words inc)
                                 (assoc :got-right ""
                                   :last-updated-at (js/Date.now)
                                   :color (random-color)
                                   :status :idle
                                   :enemy-type (random-enemy-type)
                                   :horizontal (rand-nth horizontal-values)
                                   :vertical (rand-nth vertical-values)
                                   :selected-word (create-selected (nth words new-index))
                                   :index new-index)))))))))
      (do 
        (swap! state (fn [os]
                       (-> os
                         (assoc :last-updated-at (js/Date.now))
                         (update :incorrect-char inc))))
        (.play errou)))))
