#!/bin/bash

# This file is used to publish the library to Maven Central.
# First, it runs the Gradle task to generate the resource files and to override the
# resource files of the Maven Central version with the ones in the gradleResources directory.
# After that, it calls the Maven deploy command to publish the library to Maven Central.
# It would be more convenient to use the Gradle plugin to publish the library to Maven Central,
# but that doesn't seem to be possible. The Gradle plugin always stops with a "400 Bad Request" error.

# This script requires a settings.xml file in the .m2 directory with the following content:
#<settings>
#  <servers>
#    <server>
#        <id>ossrh</id>
#        <username>(token-user from the Maven Central Nexus UI)</username>
#        <password>(token from the Maven Central Nexus UI)</password>
#    </server>
#  </servers>
#        <profiles>
#        <profile>
#            <id>sign-artifacts</id>
#            <properties>
#                <gpg.keyname>(id of the gpg key you're using to sign the files)</gpg.keyname>
#                <gpg.passphrase>(passphrase of that gpg key)</gpg.passphrase> <!-- Store encrypted if possible -->
#            </properties>
#        </profile>
#    </profiles>
#    <activeProfiles>
#        <activeProfile>sign-artifacts</activeProfile>
#    </activeProfiles>
#</settings>

./gradlew clean publishToMavenLocal

mvn clean deploy -P MavenCentral