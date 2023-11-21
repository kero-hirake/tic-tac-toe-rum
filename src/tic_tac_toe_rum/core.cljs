(ns tic-tac-toe-rum.core
  (:require [rum.core :refer [defc defcs mount local]]
            ["react" :as r]
            ["react-dom/client" :as rdom]))

(defcs square < (local nil ::value) 
  [state]  
  (let [local-atom (::value state)
        handle-click (fn [] (swap! local-atom (fn[_] "X")))]
    [:div.square 
     {:on-click handle-click} 
     @local-atom]))

(defcs stateful < (local 0 ::key)
  [state label]
  (let [local-atom (::key state)]
    [:div {:on-click (fn [_] (swap! local-atom inc))}
     label ": " @local-atom]))

(defc board []
  [:*
   [:div.board-row 
    (square)
    (square)
    (square) ]
   [:div.board-row
    (square)
    (square)
    (square)]
   [:div.board-row
    (square)
    (square)
    (square)]])


(defc app []
  [:* 
   (stateful "click count")
   (board)])

(defn init []
  ; FIX: react-dom.development.js:87 Warning: ReactDOM.render is no longer supported in React 18 
  
  (mount (app) (js/document.getElementById "app")))

