NEEDED LIBRARIES INSTRUCTIONS
-----------------------------

The build needs the following Libraries to be downloaded in this directory (/lib):

1) Download the library javax.faces from https://javaserverfaces.java.net/nonav/2.2/download.html ;
   choose the first link "Implementation jars" and download the last version, javax.faces-2.2.8-02.jar .
   (the library compiles with versions of javax.faces starting from 2.1 )
   
2) Download the library javax.el from https://maven.java.net/content/repositories/releases/org/glassfish/javax.el/ ;
   (last version is la 3.0.0, download javax.el-3.0.0.jar)
   (the library compiles with versions of javax.el starting from 2.2 )
   
3) Download the library javax.servlet-api from http://central.maven.org/maven2/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar

4) Download the YUIcompressor from https://github.com/yui/yuicompressor/releases ;
   last version is  2.4.8, download yuicompressor-2.4.8.jar . Put it into the lib folder.
   Windows user must use 2.4.7 instead (2.4.8 doesn't cope with Windows filenames). Don't forget to correct the version number in the ant file (build.xml, lines 207 and 224).
   You find the old version at https://github.com/yui/yuicompressor/downloads.
   
5) Download xmltask.jar from http://sourceforge.net/projects/xmltask/ ;
   (last version is 1.16.1)
   
6) Windows only: enter your correct path where you installed the less.cmd file (build.xml, line 238). The variable %appdata% isn't resolved automatically.

7) Run build.xml. 

