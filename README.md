# ![Cirneco logo](https://raw.github.com/ozimov/cirneco/master/Cirneco.png) Cirneco

***Easy-to-use extensions for unit test libraries***

**Source Website:** *[github.com/ozimov/cirneco](http://github.com/ozimov/cirneco/)*<br />
**JavaDoc Website:** *[ozimov.github.io/cirneco/](http://ozimov.github.io/cirneco/)*<br />
**Wiki Website:** *[github.com/ozimov/cirneco/wiki](http://github.com/ozimov/cirneco/wiki)*<br />

**Latest Release:** *0.8.1*<br />
**Latest Artifacts:** *it.ozimov:java7-hamcrest-matchers:jar:0.8.1*,
  *it.ozimov:java8-hamcrest-matchers:jar:0.8.1*,
  *it.ozimov:guava-hamcrest-matchers:jar:0.8.1* <br />
**Continuous Integration:**<br />
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/it.ozimov/cirneco-parent/badge.svg)](https://maven-badges.herokuapp.com/maven-central/it.ozimov/cirneco-parent)
<br />
[![Build Status](https://travis-ci.org/ozimov/cirneco.svg?branch=master)](https://travis-ci.org/ozimov/cirneco) [![Coverage Status](https://coveralls.io/repos/ozimov/cirneco/badge.svg?branch=master&service=github)](https://coveralls.io/github/ozimov/cirneco?branch=master)
[![codecov.io](https://codecov.io/github/ozimov/cirneco/coverage.svg?branch=master)](https://codecov.io/github/ozimov/cirneco?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/7a4364b93df6473fb18a597e900edceb)](https://www.codacy.com/app/roberto-trunfio/cirneco)

![codecov.io](https://codecov.io/github/ozimov/cirneco/branch.svg?branch=master)

## What is Cirneco?

Cirneco is a collection of libraries aimed to make unit test in Java cleaner and easier.
The more code you have to write, the highest is the probability to do a mistake. Moreover,
we like easy-to-read and concise code, where the method name already embeds all the semantics that you
need to explain what you are assuming/asserting.

Hence, in Cirneco we aim to extend the most valuable toolkits for  unit test to provide a better developing experience.
The current version (*Cirneco 0.8.1*) only provides some extensions for Hamcrest for Java.

# JDK compatibility
The API is JDK7 compatible.
There are dedicated matchers for JDK8 provided in a complementary API.


# Extended libraries
For now, we provided some extensions of the [Hamcrest](https://github.com/hamcrest/JavaHamcrest) library.
There are some extra feature for [fluent assertions](https://github.com/ozimov/cirneco/wiki/Fluent-Assertions) that you may also want to checkout.

## Hamcrest extensions
There are some interesting matchers based on [Guava](https://github.com/google/guava) library. The next release will focus more on Guava collections and money (the plan would be to use Joda Money or to dirfectly provide the matchers for the JDK8 extension).
To use the extensions for a JDK7 compliant project, you can embed the following dependency in your `pom.xml`
```xml
<dependency>
  <groupId>it.ozimov</groupId>
  <artifactId>java7-hamcrest-matchers</artifactId>
  <version>0.8.1</version>
  <scope>test</scope>
</dependency>
```
and if you use JDK8, the dependency to be added is:

```xml
<dependency>
  <groupId>it.ozimov</groupId>
  <artifactId>java8-hamcrest-matchers</artifactId>
  <version>0.8.1</version>
  <scope>test</scope>
</dependency>
```

There are matchers dedicated to Guava libraries that are JDK7-compliant, that you can import with the following dependency:

```xml
<dependency>
  <groupId>it.ozimov</groupId>
  <artifactId>guava-hamcrest-matchers</artifactId>
  <version>0.8.1</version>
  <scope>test</scope>
</dependency>
```

Some libraries are not using _Hamcrest v.2.0.0.0_. It may be necessary to explicity remove the dependency of older Hamcrest version. E.g. in a _Spring Boot_ application:
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-test</artifactId>
  <scope>test</scope>
  <exclusions>
    <exclusion>
      <groupId>org.hamcrest</groupId>
      <artifactId>hamcrest-core</artifactId>
    </exclusion>
    <exclusion>
      <groupId>org.hamcrest</groupId>
      <artifactId>hamcrest-library</artifactId>
    </exclusion>
  </exclusions>
</dependency>
```
Otherwise, just put the Cirneco dependencies before to import these dependencies. I suggest to use [Maven dependency tree resolver](https://maven.apache.org/plugins/maven-dependency-plugin/examples/resolving-conflicts-using-the-dependency-tree.html) to detect which library is shipped with _Hamcrest 1.3.3_.

# What does it mean Cirneco?
Maybe you are curious about the name Cirneco. Cirneco dell'Etna is a unique dog native of Sicily, Italy.
It is very fast and agile, neat and friendly. A Cirneco is also excellent for hunting. So, all the quality
we love to have in an API.
