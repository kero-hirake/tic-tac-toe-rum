(ns tic-tac-toe-rum.core
  (:require [rum.core :refer [defc mount]]
            ["react" :as r]
            ["react-dom/client" :as rdom]))

(defc square [& {:keys [value]}]
  [:div.square value])

(defc board []
  [:*
   [:div.board-row 
    (square :value 1)
    (square :value 2)
    (square :value 3) ]
   [:div.board-row
    (square :value 4)
    (square :value 5)
    (square :value 6)]
   [:div.board-row
    (square :value 7)
    (square :value 8)
    (square :value 9)]])

(defc app []
  (board))

(defn init []
  ; FIX: react-dom.development.js:87 Warning: ReactDOM.render is no longer supported in React 18 
  
  (mount (app) (js/document.getElementById "app")))

