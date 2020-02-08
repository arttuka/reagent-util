(ns reagent-util.material-ui-lab.autocomplete
  "Imports @material-ui/lab/Autocomplete as a Reagent component.
   Original documentation is at https://material-ui.com/api/autocomplete/ ."
  (:require [reagent-material-ui.util :refer [adapt-react-class]]
            [material-ui-lab]))

(def autocomplete (adapt-react-class material-ui-lab/Autocomplete "mui-autocomplete"))
