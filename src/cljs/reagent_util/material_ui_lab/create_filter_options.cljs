(ns reagent-util.material-ui-lab.create-filter-options
  "Imports @material-ui/lab/create-filter-options.
   Original documentation is at https://material-ui.com/api/autocomplete/ ."
  (:require [reagent-material-ui.util :refer [adapt-react-class clj->js']]
            [material-ui-lab]))

(defn create-filter-options
  "Factory to create a filter method that can be provided as the
   filter-option prop of the autocomplete component."
  ([]
   (create-filter-options {}))
  ([config]
   (material-ui-lab/createFilterOptions (clj->js' config))))
