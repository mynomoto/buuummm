(ns buuummm.main
  (:require
    [goog.events :as events]
    [goog.dom :as dom]
    [buuummm.view :as view]
    [hoplon.goog]))

(defn mount-components []
  (.replaceChildren (.getElementById js/document "app") (view/hello)))

(defn start []
  (mount-components)
  (js/console.log "Starting..."))

(defn stop []
  (js/console.log "Stopping..."))

(defn init []
  (js/console.log "Initializing...")
  (start)
  (events/listen (dom/getDocument) goog.events.EventType.KEYDOWN view/process-key))
