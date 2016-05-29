// Karma configuration
// Generated on Tue May 03 2016 17:41:29 GMT+0200 (CEST)

module.exports = function(config) {
  config.set({

    // base path that will be used to resolve all patterns (eg. files, exclude)
    basePath: '',


    // frameworks to use
    // available frameworks: https://npmjs.org/browse/keyword/karma-adapter
    frameworks: ['jasmine'],


    // list of files / patterns to load in the browser
    files: [
      'src/main/resources/static/bower_components/jquery/dist/jquery.min.js',
      'src/main/resources/static/bower_components/angular/angular.min.js',
      'src/main/resources/static/bower_components/angular-bootstrap/ui-bootstrap-tpls.js',
      'src/main/resources/static/bower_components/angular-resource/angular-resource.min.js',
      'src/main/resources/static/bower_components/angular-route/angular-route.min.js',
      'src/main/resources/static/bower_components/angular-ui-router/release/angular-ui-router.js',
      'src/main/resources/static/bower_components/angular-local-storage/dist/angular-local-storage.js',
      'src/main/resources/static/bower_components/bootstrap/dist/js/bootstrap.min.js',

      'src/main/resources/static/bower_components/angular-mocks/angular-mocks.js',

      'src/main/resources/static/app/app.js',
      'src/main/resources/static/app/**/*.js',
      'src/test/resources/static/app/*.js'
    ],


    // list of files to exclude
    exclude: [
    ],


    // preprocess matching files before serving them to the browser
    // available preprocessors: https://npmjs.org/browse/keyword/karma-preprocessor
    preprocessors: {
    },


    // test results reporter to use
    // possible values: 'dots', 'progress'
    // available reporters: https://npmjs.org/browse/keyword/karma-reporter
    reporters: ['progress'],


    // web server port
    port: 9876,


    // enable / disable colors in the output (reporters and logs)
    colors: true,


    // level of logging
    // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
    logLevel: config.LOG_INFO,


    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: true,


    // start these browsers
    // available browser launchers: https://npmjs.org/browse/keyword/karma-launcher
    browsers: ['Chrome'],


    // Continuous Integration mode
    // if true, Karma captures browsers, runs the tests and exits
    singleRun: false,

    // Concurrency level
    // how many browser should be started simultaneous
    concurrency: Infinity
  })
}
