(ns reagent-util.autocomplete
  (:require [reagent.core :as reagent :refer [atom]]
            [clojure.string :as str]
            [goog.object :as obj]
            [reagent-material-ui.core.grow :refer [grow]]
            [reagent-material-ui.core.menu-item :refer [menu-item]]
            [reagent-material-ui.core.menu-list :refer [menu-list]]
            [reagent-material-ui.core.paper :refer [paper]]
            [reagent-material-ui.core.popper :refer [popper]]
            [reagent-material-ui.core.text-field :refer [text-field]]
            [reagent-material-ui.core.typography :refer [typography]]
            [reagent-material-ui.util :refer [adapt-react-class js->clj' use-ref use-state]]
            [reagent-util.use-autocomplete :refer [use-autocomplete]]
            [reagent-util.util :refer [wrap-on-change]]))

(defn input [{:keys [input-props] :as props}]
  (let [{:keys [on-blur on-change on-focus ref]} input-props]
    [text-field (merge props {:InputProps  {:on-blur   on-blur
                                            :on-change on-change
                                            :on-focus  on-focus}
                              :input-props (dissoc input-props :on-blur :on-change :on-focus :ref)
                              :input-ref   ref})]))

(defn react-autocomplete [params]
  (let [{:keys [classes label on-select get-option-label
                on-input-change placeholder
                filter-options shrink-label max-results]
         :or   {get-option-label identity
                placeholder      ""}} (js->clj' params)
        value-ref (use-ref #js [])
        on-change (fn [_ v]
                    (on-select (js->clj' v)))
        [open set-open] (use-state false)
        on-open (wrap-on-change (fn [v]
                                  (when-not (str/blank? v)
                                    (set-open true))))
        on-close (fn [_]
                   (when on-input-change
                     (on-input-change nil))
                   (set-open false))
        autocomplete-params (merge {:options          (obj/get params "options")
                                    :on-change        on-change
                                    :on-open          on-open
                                    :on-close         on-close
                                    :open             open
                                    :get-option-label get-option-label
                                    :multiple         true
                                    :value            (obj/get params "value" (.-current value-ref))}
                                   (when filter-options
                                     {:filter-options          filter-options
                                      :filter-selected-options true}))
        {:keys [anchor-el
                get-input-label-props
                get-input-props
                get-listbox-props
                get-option-props
                get-root-props
                grouped-options
                popup-open
                set-anchor-el]} (use-autocomplete autocomplete-params)
        input-props (get-input-props)
        on-input-change' (fn [^js/Event e]
                           (on-input-change (.. e -target -value))
                           ((:on-change input-props) e))]
    (reagent/as-element
     [:div (merge (get-root-props)
                  {:class (:root classes)
                   :ref   set-anchor-el})
      [input {:full-width      true
              :InputLabelProps (cond-> (get-input-label-props)
                                 shrink-label (assoc :shrink true))
              :input-props     (cond-> input-props
                                 on-input-change (assoc :on-change on-input-change'))
              :label           label
              :placeholder     placeholder}]
      [popper {:open       popup-open
               :anchor-el  anchor-el
               :placement  :bottom-start
               :transition true}
       (fn [props]
         (reagent/as-element
          [grow (assoc (js->clj (obj/get props "TransitionProps"))
                       :style {:transform-origin "center top"})
           [paper {:style {:width (some-> anchor-el (.-clientWidth))}}
            [menu-list (get-listbox-props)
             (for [[index option] (map-indexed vector (cond->> grouped-options
                                                        max-results (take max-results)))]
               ^{:key option}
               (let [option-props (get-option-props {:index  index
                                                     :option option})]
                 [menu-item (assoc option-props :class (:menu-item classes))
                  [typography {:variant :inherit
                               :no-wrap true}
                   (get-option-label option)]]))
             (when (and max-results (< max-results (count grouped-options)))
               [menu-item {:disabled true}
                "···"])]]]))]])))

(def autocomplete (adapt-react-class react-autocomplete))
