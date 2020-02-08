(ns reagent-util.util)

(defn wrap-on-change [f]
  (fn [^js/Event event]
    (f (.. event -target -value))))
