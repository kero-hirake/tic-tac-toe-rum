(ns tic-tac-toe-rum.core
  (:require [rum.core :refer [defc mount]]
            ["react" :as r]
            ["react-dom/client" :as rdom]))

(defc square []
  [:*
   [:div.board-row 
    [:div.square "1"]
    [:div.square "2"]
    [:div.square "3"]]
   [:div.board-row
    [:div.square "4"]
    [:div.square "5"]
    [:div.square "6"]]
   [:div.board-row
    [:div.square "7"]
    [:div.square "8"]
    [:div.square "9"]]])

(defc app []
  (square))

(defn init []
  ; FIX: react-dom.development.js:87 Warning: ReactDOM.render is no longer supported in React 18 
  
  (mount (app) (js/document.getElementById "app")))

