(ns tic-tac-toe-rum.core
  (:require [rum.core :refer [defc defcs mount local]]
            ["react" :as r]
            ["react-dom/client" :as rdom]))
(defn calcurate-winner [squares]
  (let [lines [[0 1 2]
               [3 4 5]
               [6 7 8]
               [0 3 6]
               [1 4 7]
               [2 5 8]
               [0 4 8]
               [2 4 6]]]
    (some-> (filter (fn [[a b c]]
                      (and (squares a)
                           (= (squares a)
                              (squares b)
                              (squares c))))
                    lines)
            first
            (#(squares (first %))))))

(defc square  [& {:keys [value on-square-click]}]  
  [:div.square
   {:on-click on-square-click}
   value])

(defcs board < (local {:x-is-next? true
                       :squares (vec (repeat 9 nil))} 
                      ::state)
  [{::keys [state]}] 
  (let [{:keys [x-is-next? squares]} @state
        winner (calcurate-winner squares)
        status (if winner
                 (str "Winner: " winner)
                 (str "Next player: " (if x-is-next? "X" "O")))
        handle-click (fn [i]
                       (when-not (or (get squares i) winner)
                         (swap! state assoc-in [:squares i] (if x-is-next? "X" "O"))
                         (swap! state update :x-is-next? not)))]
    [:* 
     [:div.status  status]
     [:div.board-row 
      (square :value (get squares 0) :on-square-click #(handle-click 0))
      (square :value (get squares 1) :on-square-click #(handle-click 1))
      (square :value (get squares 2) :on-square-click #(handle-click 2)) ]
     [:div.board-row
      (square :value (get squares 3) :on-square-click #(handle-click 3))
      (square :value (get squares 4) :on-square-click #(handle-click 4))
      (square :value (get squares 5) :on-square-click #(handle-click 5))]
     [:div.board-row
      (square :value (get squares 6) :on-square-click #(handle-click 6))
      (square :value (get squares 7) :on-square-click #(handle-click 7))
      (square :value (get squares 8) :on-square-click #(handle-click 8))]]))

(defc app []
  [:*  
   (board)])

(defn init []
  ; FIX: react-dom.development.js:87 Warning: ReactDOM.render is no longer supported in React 18 
  
  (mount (app) (js/document.getElementById "app")))

