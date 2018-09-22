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

### Getting started
To get up and running quickly, have a look at the [Quick Start](http://www.bootsfaces.net/quick-start.jsf).

### Maven

    <dependency>
        <groupId>net.bootsfaces</groupId>
        <artifactId>bootsfaces</artifactId>
        <version>1.4.0</version>
    </dependency>

### Gradle

    compile 'net.bootsfaces:bootsfaces:1.4.0'

### Development Snapshots

See [this issue](https://github.com/TheCoder4eu/BootsFaces-OSP/issues/369) for instructions on how to get a current developer snapshot via Maven.  
There's also a [Snapshot Showcase](http://www3.bootsfaces.net/Showcase/) with the newest upstream changes.

### Contributing
If you want to get your feet wet yourself, we suggest you to take a look at our [cheat-sheet](cheat-sheet.md) and the [contribution guidelines](CONTRIBUTING.md).

### Notes on the new Java Release Cycle
As of March 20, 2018 a new six-month Java release scheme has been adopted and Java 10 is the currently supported rapid release version.
There will be three years between a LTS release and the next and Java 11 will be the next LTS after Java 8(LTS).
Java 10 support ends on the same date that support for Java 11 begins the day of its release, planned for September 25, 2018.

BootsFaces want to support each Java runtime released after the minimum Java version used to build the library.
However, to prevent too many Java subdirectories in the project root, we created the legacyJava directory, that will collect the build scripts specific for non-LTS runtimes.
This means that our official build will only include non obsolete LTS targets but you will have the option of building for not non-LTS Java targets using commands like follows:

```
./gradlew :legacyJava:java7:defaultJar
./gradlew :legacyJava:java9:defaultJar
./gradlew :legacyJava:java10:defaultJar
```


