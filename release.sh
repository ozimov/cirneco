#!/bin/bash
# Deploy maven artifact in current directory into Maven central repository
# using maven-release-plugin goals

set -e

mvn --settings ~/.m2/home-settings.xml scm:check-local-modification

# release
echo "########################################"
echo "########################################"
echo "When at prompt, type the release version (e.g. from 1.0-SNAPSHOT to 1.0)\n"
mvn --settings ~/.m2/home-settings.xml versions:set
#git add pom.xml */pom.xml
git commit -am "[Deploy phase] Preparing release"
mvn --settings ~/.m2/home-settings.xml clean deploy -P release
mvn --settings ~/.m2/home-settings.xml scm:tag

# next development version
echo "########################################"
echo "########################################"
echo "When at prompt, type the SNAPSHOT version (e.g. from 1.0 to 2.0-SNAPSHOT)\n"
mvn --settings ~/.m2/home-settings.xml versions:set
#git add pom.xml */pom.xml
git commit -am "[Deploy phase] Preparing for next iteration"

# updating origin
git push --force --follow-tags

echo 'Staging deploy complete'
