# reagent-util

Personal utility repository for Reagent/CLJS

## HOWTO

### `project.clj`
`:source-paths`: add `"reagent-util/src/cljs"`

### `dev.cljs.edn`
```
 :foreign-libs  [{:file           "reagent-util/dist/reagent-util.inc.js"
                  :provides       ["material-ui-lab"]
                  :global-exports {material-ui-lab MaterialUILab}}]
```

### `prod.cljs.edn`
```
 :foreign-libs  [{:file           "reagent-util/dist/reagent-util.min.inc.js"
                  :provides       ["material-ui-lab"]
                  :global-exports {material-ui-lab MaterialUILab}}]
 :externs       ["reagent-util/src/js/reagent-util.ext.js"]
```
