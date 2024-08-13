BootsFaces-OSP
==============

<p align="center">
    <img src="http://www.bootsfaces.net/javax.faces.resource/bsf.full.teal.png.jsf?ln=images" width="200">
</p>

BootsFaces - Open Source Project

[![License](https://img.shields.io/:license-Apache2-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.bootsfaces/bootsfaces/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.bootsfaces/bootsfaces)

This is the next gen JSF Framework that takes the best from Bootstrap 3 and jQuery UI to let you develop Front-end Enterprise Applications fast and easy.

To learn more about the framework, take a look at  [BootsFaces.net](http://www.bootsfaces.net/) and visit our [Showcase](http://showcase.bootsfaces.net) for live demos.

The source for our page can be found on GitHub. If you want to have a deeper look on how to use the library or found a mistake, visit the [Showcase Project](https://github.com/TheCoder4eu/BootsFacesWeb) or the [Examples Project](https://github.com/TheCoder4eu/BootsFaces-examples).

### Version 2.0.0
Basically, Version 2.0.0 is a considerable number of bug fixes, plus support for JakartaEE. We've dropped support for the old `javax` namespace.

The minumum required version of Java is Java 11.

### State of the project
At the moment, this project is not maintained actively. If you need a feature, please send us a pull request,
so we can build a new version. It's a good idea to reach out to use beforehand, so we can check if (or make sure that) we've got enough time to review your PR and to publish a new version.

### Getting started
To get up and running quickly, have a look at the [Quick Start](https://www.bootsfaces.net/quick-start.jsf).

Starting with version 2.0.0, BootsFaces requires JSF 4.0 and JakartaEE. It's no longer compatible to the old JavaEE libraries. Instead, it requires the Jakarta libraries..

### Maven

    <dependency>
        <groupId>net.bootsfaces</groupId>
        <artifactId>bootsfaces</artifactId>
        <version>2.0.0</version>
    </dependency>

You can also get a version that's compiled to Java 17:

    <dependency>
        <groupId>net.bootsfaces</groupId>
        <artifactId>bootsfaces</artifactId>
        <version>2.0.0</version>
        <classifier>java17</classifier>
    </dependency>

Probably, there's no big difference between the Java 17 build and the Java 11 build. However, in the past,
there is at least one Java version that ran newer builds faster than older builds. 

### Gradle

    compile 'net.bootsfaces:bootsfaces:2.0.0'

### Development Snapshots

See [this issue](https://github.com/TheCoder4eu/BootsFaces-OSP/issues/369) for instructions on how to get a current developer snapshot via Maven.  
There's also a [Snapshot Showcase](http://www3.bootsfaces.net/Showcase/) with the newest upstream changes.

### Contributing
If you want to get your feet wet yourself, we suggest you to take a look at our [cheat-sheet](cheat-sheet.md) and the [contribution guidelines](CONTRIBUTING.md).

### Notes on the new Java Release Cycle
As of March 20, 2018 a new six-month Java release scheme has been adopted.
There will be three years between a LTS release and the next and Java 11 has been the next LTS after Java 8(LTS). Bootsfaces currently supports Java 11 LTS, Java 17 LTS,
and Java 21 LTS. Older versions of Java are not supported.

With Gradle, you can build the library using the following Targets:

```
./gradlew :javaBuild:buildJava11Jar
./gradlew :javaBuild:buildJava17Jar
```

