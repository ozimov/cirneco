#!/bin/bash

echo 'Javadoc deploy started'

mvn --settings ~/.m2/home-settings.xml javadoc:aggregate
if [ -d "target/site/apidocs/" ]; then
    cd target/site/apidocs/
    git init
    git remote add javadoc git@github.com:ozimov/cirneco.git
    git fetch --depth=1 javadoc gh-pages
    git add --all
    git commit -m "Added docs for current release"
    git merge --no-edit -s ours remotes/javadoc/gh-pages
    git push javadoc master:gh-pages
    rm -r -f .git
    cd ../../..
fi

echo 'Javadoc deploy complete'
