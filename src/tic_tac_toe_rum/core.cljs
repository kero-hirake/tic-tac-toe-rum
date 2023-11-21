(ns tic-tac-toe-rum.core
  (:require [rum.core :refer [defc defcs mount local]]
            ["react" :as r]
            ["react-dom/client" :as rdom]))

(defc square  [& {:keys [value on-square-click]}]  
  [:div.square
   {:on-click on-square-click}
   value])

(defcs board < (local (vec (repeat 9 nil)) ::squares)
  [{::keys [squares]}] 
  (let [handle-click (fn [i]
                       (println "clicked " (swap! squares assoc i "X")))]
    [:*
     [:div.board-row 
      (square :value (get @squares 0) :on-square-click #(handle-click 0))
      (square :value (get @squares 1) :on-square-click #(handle-click 1))
      (square :value (get @squares 2) :on-square-click #(handle-click 2)) ]
     [:div.board-row
      (square :value (get @squares 3) :on-square-click #(handle-click 3))
      (square :value (get @squares 4) :on-square-click #(handle-click 4))
      (square :value (get @squares 5) :on-square-click #(handle-click 5))]
     [:div.board-row
      (square :value (get @squares 6) :on-square-click #(handle-click 6))
      (square :value (get @squares 7) :on-square-click #(handle-click 7))
      (square :value (get @squares 8) :on-square-click #(handle-click 8))]]))

(defc app []
  [:*  
   (board)])

(defn init []
  ; FIX: react-dom.development.js:87 Warning: ReactDOM.render is no longer supported in React 18 
  
  (mount (app) (js/document.getElementById "app")))

