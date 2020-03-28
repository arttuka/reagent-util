(ns reagent-util.use-autocomplete
  "Imports @material-ui/lab/use-autocomplete.
   Original documentation is at https://material-ui.com/api/autocomplete/ ."
  (:require [reagent-material-ui.lab.use-autocomplete :refer [use-autocomplete] :rename {use-autocomplete mui-use-autocomplete}]
            [reagent-material-ui.util :refer [clj->js' js->clj' wrap-js-function]]))

(defn use-autocomplete
  "React hook that provides autocomplete functionality for advanced customization use cases.
   Note: React hooks can't be used in regular Reagent components: http://reagent-project.github.io/docs/master/ReactFeatures.html#hooks"
  [props]
  (let [autocomplete-props (mui-use-autocomplete props)]
    (reduce (fn [m k]
              (update m k wrap-js-function))
            autocomplete-props
            [:get-root-props
             :get-input-label-props
             :get-input-props
             :get-clear-props
             :get-popup-indicator-props
             :get-tag-props
             :get-popup-props
             :get-listbox-props
             :get-option-props])))
*
