# ![Cirneco logo](https://raw.github.com/ozimov/cirneco/master/Cirneco.png) Cirneco

***Easy-to-use extensions for testing libraries***

**Source Website:** *[github.com/ozimov/cirneco](http://github.com/ozimov/cirneco/)*<br />
**JavaDoc Website:** *[ozimov.github.io/cirneco/](http://ozimov.github.io/cirneco/)*<br />
**Wiki Website:** *[github.com/ozimov/cirneco/wiki](http://github.com/ozimov/cirneco/wiki)*<br />
**Latest Release:** *0.1* &nbsp;
**Latest Artifacts:** *it.ozimov:cirneco-hamcrest-jdk7:jar:0.1*, *it.ozimov:cirneco-hamcrest-jdk8:jar:0.1* <br />
**Continuous Integration:** <br />
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/it.ozimov/cirneco-parent/badge.svg)](https://maven-badges.herokuapp.com/maven-central/it.ozimov/cirneco-parent)
<br />
[![Build Status](https://travis-ci.org/ozimov/cirneco.svg?branch=master)](https://travis-ci.org/ozimov/cirneco) [![Coverage Status](https://coveralls.io/repos/ozimov/cirneco/badge.svg?branch=master&service=github)](https://coveralls.io/github/ozimov/cirneco?branch=master) 
[![Codacy Badge](https://api.codacy.com/project/badge/grade/7a4364b93df6473fb18a597e900edceb)](https://www.codacy.com/app/roberto-trunfio/cirneco)


## What is Cirneco?

Cirneco is a collection of libraries aimed to make unit test in Java cleaner and easier.
The more code you have to write, the highest is the probability to do a mistake. Moreover,
we like easy-to-read and concise code, where the method name already embeds all the semantics that you
need to explain what you are assuming/asserting.

Hence, in Cirneco we aim to extend the most valuable toolkits for  unit test to provide a better developing experience.
The current version (*Cirneco 0.1*) only provides some extensions for Hamcrest for Java.

# JDK compatibility
The API is JDK7 compatible.
There are dedicated matchers for JDK8 provided in a complementary API.


# Extended libraries
For now, we provided some extensions of the [Hamcrest](https://github.com/hamcrest/JavaHamcrest) library. The plan for release *0.2* (most likely *0.3*) is to add support to [Google Truth](https://github.com/google/truth).

## Hamcrest extensions
There are some interesting matchers based on [Guava](https://github.com/google/guava) library. The next release will focus more on Guava collections and money (the plan would be to use Joda Money or to dirfectly provide the matchers for the JDK8 extension).
To use the extensions for a project JDK7 compliant, you can embed the following dependency in your `pom.xml`
```xml
<dependency>
  <artifactId>it.ozimov</artifactId>
  <artifactId>cirneco-hamcrest-jdk7</artifactId>
  <version>0.1</version>
</dependency>
```
and if you use JDK8, add the following dependency:

```xml
<dependency>
  <artifactId>it.ozimov</artifactId>
  <artifactId>cirneco-hamcrest-jdk8</artifactId>
  <version>0.1</version>
</dependency>
```


# What does it mean Cirneco?
Maybe you are curious about the name Cirneco. Cirneco dell'Etna is a unique dog native of Sicily, Italy.
It is very fast and agile, neat and friendly. A Cirneco is also excellent for hunting. So, all the quality
we love to have in an API.
