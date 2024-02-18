(ns buuummm.view
  (:require
    [clojure.string :as str]
    [hoplon.core :as h]
    [hoplon.svg :as svg]
    [javelin.core :as j :refer [cell cell= defc defc=]]))

(defn heart
  [alive?]
  (svg/svg :width "50px" :height "50px" :viewBox "0 0 24 24" :fill "none" :xmlns "http://www.w3.org/2000/svg"
    (svg/path
      :d "M2 9.1371C2 14 6.01943 16.5914 8.96173 18.9109C10 19.7294 11 20.5 12 20.5C13 20.5 14 19.7294 15.0383 18.9109C17.9806 16.5914 22 14 22 9.1371C22 4.27416 16.4998 0.825464 12 5.50063C7.50016 0.825464 2 4.27416 2 9.1371Z"
      :fill (cell= (if alive? "#FF0000" "#BEBEBE")))))

(def level-1
  {:description "Mão esquerda, linha do meio."
   :words ["asa"
           "assada"
           "dadas"
           "daga"
           "fada"
           "safada"
           "saga"
           "sassa"]})

(def level-2
  {:description "Ambas mãos, linha do meio."
   :words ["adaga"
           "adagas"
           "ah"
           "alas"
           "alfa"
           "asa"
           "fada;"
           "fadas;"
           "fala"
           "falha"
           "falsa"
           "gah"
           "gaja"
           "gala"
           "haja;"
           "haka"
           "hal"
           "jaja"
           "kaka"
           "lag"
           "lala"
           "lalas"
           "sal"
           "sala"
           "salada"
           "salas"
           "shala"
           "shh;"
           "ska"]})

(def level-3
  {:description "Nível 2 + linha de cima para mão esquerda"
   :words [
           "areia"
           "data"
           "dedel"
           "des"
           "draga"
           "era"
           "eras"
           "essas"
           "esses"
           "falar"
           "fale"
           "falta"
           "farda"
           "farta"
           "fases"
           "fera"
           "gata"
           "jardas"
           "largada"
           "largas"
           "lata"
           "rasa"
           "rasga"
           "safra"
           "water"
           "watt"
           "week"
           "www"
           "lagarta"
           ]})

(def level-4
  {:description "Ambas mãos linha de cima e do meio."
   :words [
           "afago"
           "afoito"
           "ajuda"
           "alojado"
           "aro"
           "aura"
           "fajuto"
           "falho"
           "farol"
           "feira"
           "fogo"
           "folha"
           "forte"
           "fruta"
           "fuja"
           "gafieira"
           "galho"
           "galo"
           "jato"
           "jaula"
           "jipe"
           "joia"
           "juro"
           "justo"
           "kaiak"
           "lago"
           "lagoa"
           "lua"
           "luar"
           "ouro"
           "palha"
           "parque"
           "pasta"
           "porto"
           "preto"
           "puro"
           "quarto"
           "quase"
           "quatro"
           "queijo"
           "quer"
           "quieto"
           "quilo"
           "raposa"
           "rato"
           "rio"
           "rosa"
           "rua"
           "sagu"
           "saldo"
           "salgado"
           "salto"
           "sol"
           "teia"
           "teto"
           ]})

(def level-5
  {:description "Nível 4 + linha de baixo com mão esquerda."
   :words [
           "abacaxi"
           "alface"
           "arco"
           "axila"
           "bala"
           "barco"
           "boia"
           "bola"
           "bolo"
           "bote"
           "boxe"
           "boxeador"
           "bravo"
           "cabo"
           "caixa"
           "calha"
           "calvo"
           "capaz"
           "casa"
           "caveira"
           "cervo"
           "cobalto"
           "cobertor"
           "cobra"
           "cruz"
           "cruzado"
           "extra"
           "faz"
           "feliz"
           "flecha"
           "foice"
           "jabuti"
           "jaleco"
           "laca"
           "paz"
           "pizza"
           "quebrado"
           "queixo"
           "salsicha"
           "sobra"
           "vala"
           "voz"
           "xale"
           "xarope"
           "xaxim"
           "xerife"
           "xerox"
           "zebra"
           "zelador"
           "zelo"
           "ziguezague"
           ]})

(def level-6
  {:description "Todas as letras."
   :words [
           "buzina"
           "carambola"
           "faxina"
           "gafanhoto"
           "maluco"
           "maneira"
           "mano"
           "manteiga"
           "mar"
           "março"
           "menina"
           "menino"
           "mente"
           "menu"
           "mim"
           "minhoca"
           "minuto"
           "moinho"
           "monstro"
           "montanha"
           "morno"
           "movimento"
           "mulher"
           "nabo"
           "namorado"
           "nana"
           "nariz"
           "nata"
           "navio"
           "new"
           "ninho"
           "ninja"
           "noite"
           "nome"
           "norma"
           "norte"
           "nuca"
           "nuvem"
           "queima"
           "quente"
           "vexame"
           "zabumba"
           "zangado"
           "zombaria"
           ]})

(def level-7
  {:description "Números"
   :words [
           "29382"
           "695"
           "14026"
           "560"
           "719"
           ]
   })

(def level-8
  {:description "Acentos e cedilha"
   :words [
           "açafrão"
           "açaí"
           "balão"
           "calvário"
           "cão"
           "dália"
           "dálmatas"
           "fogão"
           "hálito"
           "laço"
           "mamão"
           "manhã"
           "maçã"
           "minério"
           "murmúrio"
           "mágico"
           "mão"
           "névoa"
           "nível"
           "nó"
           "núcleo"
           "número"
           "poço"
           "vexatório"
           "vulcão"
           "xícara"
           "zangão"
           "zíper"
           ]})

(defc current-level 1)
(def words
  (cycle (shuffle (:words level-3))))

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

(def MAX-LIFE 10)

(defn initial-state
  []
  {:index 0
   :game-status :playing
   :correct-char 0
   :incorrect-char 0
   :life MAX-LIFE
   :life-recovery-counter 0
   :finished-words 0
   :current-word-mistakes 0
   :enemy-type :slime
   :got-right ""
   :extra-stats true
   :horizontal (rand-nth horizontal-values)
   :vertical (rand-nth vertical-values)
   :color (random-color)
   :status :idle
   :started-at (js/Date.now)
   :last-updated-at (js/Date.now)
   :selected-word (create-selected (nth words @index))})

(defonce state
  (cell (initial-state)))

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

(defn- reset-game!
  [_]
  (reset! state (initial-state)))

(defn hello []
  (h/div
    (h/if-tpl (cell= (= :playing (:game-status state)))
      (h/div :class "game"
        (h/div :class "score"
          (heart (cell= (>= (:life state) 1)))
          (heart (cell= (>= (:life state) 2)))
          (heart (cell= (>= (:life state) 3)))
          (heart (cell= (>= (:life state) 4)))
          (heart (cell= (>= (:life state) 5)))
          (heart (cell= (>= (:life state) 6)))
          (heart (cell= (>= (:life state) 7)))
          (heart (cell= (>= (:life state) 8)))
          (heart (cell= (>= (:life state) 9)))
          (heart (cell= (>= (:life state) 10)))
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
              (h/text "~(:char char)")))))
      (h/div :class "game"
        (h/div :css {:display "flex"
                     :align-items "center"
                     :justify-content "center"
                     :flex-direction "column"
                     :width "100vw"
                     :height "100vh"
                     :color "white"
                     :font-size "36px"}
            (h/div "GAMEOVER")
            (h/div
              (h/button :click reset-game!
                "Tentar novamente")))))))

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

(def life-recovery-at 2)

(defn- inc-life
  [current-life]
  (min (inc current-life) MAX-LIFE))

(defn- inc-life-recovery
  [current-word-mistakes life-recovery-counter]
  (if (zero? current-word-mistakes)
    (inc life-recovery-counter)
    life-recovery-counter))

(defn process-key
  [e]
  (when (= "Control" (.-key e))
    (toggle-extra-stats))
  (when-not (ignore? e)
    (let [attempt (str (:got-right @state) (.-key e))]
      (if (str/starts-with? (nth words (:index @state))
            attempt)
        (let [right (swap! state (fn [os]
                                   (-> os
                                     (update :correct-char inc)
                                     (assoc :last-updated-at (js/Date.now)
                                       :got-right attempt)
                                     (update-in [:selected-word (dec (count (str (:got-right @state) (.-key e))))]
                                       assoc :status :hit))))]
          (when (= (:got-right right) (nth words (:index @state)))
            (.play bum)
            (swap! state (fn [os]
                           (assoc os :status :die
                             :last-updated-at (js/Date.now))))
            (h/with-timeout 800
              (swap! state (fn [os]
                             (let [new-index (inc (:index os))
                                   current-word-mistakes (:current-word-mistakes os)
                                   {:keys [life-recovery-counter]
                                    :as new-state} (-> os
                                                     (update :finished-words inc)
                                                     (update :life-recovery-counter #(inc-life-recovery current-word-mistakes %))
                                                     (assoc :got-right ""
                                                       :last-updated-at (js/Date.now)
                                                       :color (random-color)
                                                       :current-word-mistakes 0
                                                       :status :idle
                                                       :enemy-type (random-enemy-type)
                                                       :horizontal (rand-nth horizontal-values)
                                                       :vertical (rand-nth vertical-values)
                                                       :selected-word (create-selected (nth words new-index))
                                                       :index new-index))]
                               (if (>= life-recovery-counter life-recovery-at)
                                 (-> new-state
                                   (assoc :life-recovery-counter 0)
                                   (update :life inc-life))
                                 new-state)))))))
        (do 
          (swap! state (fn [os]
                         (let [{:keys [life]
                                :as new-state} (-> os
                                                 (assoc :last-updated-at (js/Date.now)
                                                   :life-recovery-counter 0)
                                                 (update :incorrect-char inc)
                                                 (update :current-word-mistakes inc)
                                                 (update :life dec))]
                           (if-not (pos? life)
                             (assoc new-state :game-status :gameover)
                             new-state))))
          (.play errou))))))
