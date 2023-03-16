let config = {
  mode: 'development',
  resolve: {
    modules: [
      "node_modules"
    ]
  },
  plugins: [],
  module: {
    rules: []
  }
};

// entry
config.entry = {
    main: ["C:\\Users\\ksain\\OneDrive\\Documents\\java_sandbox\\mobilecafe\\build\\js\\packages\\mobilecafe\\kotlin\\mobilecafe.js"]
};

config.output = {
    path: "C:\\Users\\ksain\\OneDrive\\Documents\\java_sandbox\\mobilecafe\\build\\distributions",
    filename: (chunkData) => {
        return chunkData.chunk.name === 'main'
            ? "mobilecafe.js"
            : "mobilecafe-[name].js";
    },
    library: "mobilecafe",
    libraryTarget: "umd",
    globalObject: "this"
};

// source maps
config.module.rules.push({
        test: /\.js$/,
        use: ["source-map-loader"],
        enforce: "pre"
});
config.devtool = 'eval-source-map';

config.stats = config.stats || {}
Object.assign(config.stats, config.stats, {
    warningsFilter: [/Failed to parse source map/]
})


// dev server
config.devServer = {
  "open": true,
  "contentBase": [
    "C:\\Users\\ksain\\OneDrive\\Documents\\java_sandbox\\mobilecafe\\build\\processedResources\\js\\main"
  ]
};

// Report progress to console
// noinspection JSUnnecessarySemicolon
;(function(config) {
    const webpack = require('webpack');
    const handler = (percentage, message, ...args) => {
        const p = percentage * 100;
        let msg = `${Math.trunc(p / 10)}${Math.trunc(p % 10)}% ${message} ${args.join(' ')}`;
        msg = msg.replace("C:\\Users\\ksain\\OneDrive\\Documents\\java_sandbox\\mobilecafe\\build\\js", '');;
        console.log(msg);
    };

    config.plugins.push(new webpack.ProgressPlugin(handler))
})(config);

// noinspection JSUnnecessarySemicolon
;(function(config) {
    const tcErrorPlugin = require('kotlin-test-js-runner/tc-log-error-webpack');
    config.plugins.push(new tcErrorPlugin())
    config.stats = config.stats || {}
    Object.assign(config.stats, config.stats, {
        warnings: false,
        errors: false
    })
})(config);
// save evaluated config file
;(function(config) {
    const util = require('util');
    const fs = require('fs');
    const evaluatedConfig = util.inspect(config, {showHidden: false, depth: null, compact: false});
    fs.writeFile("C:\\Users\\ksain\\OneDrive\\Documents\\java_sandbox\\mobilecafe\\build\\reports\\webpack\\mobilecafe\\webpack.config.evaluated.js", evaluatedConfig, function (err) {});
})(config);

module.exports = config
