(function () {
  const autocomplete = require('@material-ui/lab/Autocomplete');
  const useAutocomplete = require('@material-ui/lab/useAutocomplete');
  window["MaterialUILab"] = {
    Autocomplete: autocomplete.default,
    useAutocomplete: useAutocomplete.default,
    createFilterOptions: autocomplete.createFilterOptions
  };
})();
