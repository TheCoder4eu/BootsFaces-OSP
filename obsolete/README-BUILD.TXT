Preparations for the Build.
--------------------------
1) Install Node js
   *buntu/debian : sudo apt-get install nodejs
   
2) Install Less (lessc less compiler)
  *buntu/debian : sudo apt-get install node-less
  
3) Download Bootstrap from http://getbootstrap.com/getting-started/#download the "Source code" version
   and unzip it in Bootstrap (should create a subdirectory with the version name)
   (direct link: https://github.com/twbs/bootstrap/archive/v3.3.0.zip )
   Update the version in the property bootstrap.version in the file build.properties, if necessary.
   
4) follow the istructions in the file README in the directory lib;

Doing the build
-------------------
Execute the ant command in the Project root folder.
