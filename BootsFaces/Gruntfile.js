module.exports = function(grunt) {

  // Project configuration.
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
	concat: {
		options: {
		  separator: ';',
		},
		dist: {
		  src: ['js/**/*.js', 'jq/**/*.js'],
		  dest: 'temp/allinone.js',
		},
	},
	concat_css: {
                option: {},
		dist: {
		  src: ['css/**/*.css'],
		  dest: 'temp/BootsFaces.css',
		},
	},
    uglify: {
      options: {
        banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
      },
      build: {
        src: 'temp/allinone.js',
        dest: '../BootsFaces/min/js/BootsFaces.min.js'
      }
    },
    cssmin:{
      target: {
        files: [{
          expand: true,
          cwd: 'temp',
          src: ['BootsFaces.css'],
          dest: '../BootsFaces/min/css',
          ext: '.min.css'
        }]
      }    
    },
    mavenPrepare: {
      options: {
        resources: ['css/**', 'js/**', 'jq/**']
      },
      prepare: {}
    }, 
  });

  // Load the plugin that provides the "uglify" task.
  grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-concat-css');
  grunt.loadNpmTasks('grunt-contrib-cssmin');
  grunt.loadNpmTasks('grunt-maven');
  
  // Default task(s).
  grunt.registerTask('default', ['mavenPrepare', 'concat','uglify', 'concat_css','cssmin']);

};