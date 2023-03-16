{
  mode: 'development',
  resolve: {
    modules: [
      'node_modules'
    ]
  },
  plugins: [
    ProgressPlugin {
      profile: false,
      handler: [Function: handler],
      modulesCount: 500,
      showEntries: false,
      showModules: true,
      showActiveModules: true
    },
    TeamCityErrorPlugin {}
  ],
  module: {
    rules: [
      {
        test: /\.js$/,
        use: [
          'source-map-loader'
        ],
        enforce: 'pre'
      }
    ]
  },
  entry: {
    main: [
      'C:\\Users\\ksain\\OneDrive\\Documents\\java_sandbox\\mobilecafe\\build\\js\\packages\\mobilecafe\\kotlin\\mobilecafe.js'
    ]
  },
  output: {
    path: 'C:\\Users\\ksain\\OneDrive\\Documents\\java_sandbox\\mobilecafe\\build\\distributions',
    filename: [Function: filename],
    library: 'mobilecafe',
    libraryTarget: 'umd',
    globalObject: 'this'
  },
  devtool: 'eval-source-map',
  stats: {
    warningsFilter: [
      /Failed to parse source map/
    ],
    warnings: false,
    errors: false
  },
  devServer: {
    open: true,
    contentBase: [
      'C:\\Users\\ksain\\OneDrive\\Documents\\java_sandbox\\mobilecafe\\build\\processedResources\\js\\main'
    ]
  }
}