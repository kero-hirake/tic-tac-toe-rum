(ns tic-tac-toe-rum.core
  (:require [rum.core :refer [defc mount]]
            ["react-dom/client" :as rdom]))

(defc label [text]
  [:div.label text])

(defn init [] 
  ; FIX: react-dom.development.js:87 Warning: ReactDOM.render is no longer supported in React 18
  ; FIX: react-dom.development.js:87 Warning: render(): Rendering components directly into document.
  (mount (label "foo") js/document.body)  

  #_(println "hi!!"))

