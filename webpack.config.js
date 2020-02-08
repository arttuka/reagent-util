var config = {
  entry: './src/js/index.js',
  output: {
    filename: 'reagent-util.inc.js'
  },
  externals: [
    function (context, request, callback) {
      const re = /^@material-ui\/core\/(.*)$/;
      if (re.test(request)) {
        const [_, name] = re.exec(request);
        const external =
          name === 'styles' ? 'MaterialUIStyles' :
          name === 'utils' ? undefined :
          `MaterialUI.${name}`;
        if (external !== undefined) {
          return callback(null, `root ${external}`);
        }
      }
      callback();
    },
    {
      'react': 'React',
      'react-dom': 'ReactDOM',
      '@material-ui/styles': 'MaterialUIStyles'
    }
  ]
};

module.exports = function (env, argv) {
  if (argv.mode === 'production') {
    config.output.filename = 'reagent-util.min.inc.js';
  }
  return config;
};
