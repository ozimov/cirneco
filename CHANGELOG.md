# Change Log
All notable changes to Cirneco project will be documented in this file.
This project adheres to [Semantic Versioning](http://semver.org/).

## [Unreleased]
### Added
- New matcher in package `it.ozimov.cirneco.hamcrest.java7.base`, i.e. `IsSameHashcode`

### Fixed
- Fixed mismatch description in `it.ozimov.cirneco.hamcrest.guava.collect.IsMultimapWithKeySet`
- Fixed mismatch description in `it.ozimov.cirneco.hamcrest.java7.collect.IsMapWithSameKeySet`



## [0.3.0] - 2016-01-10
### Added
- Added Hamcrest matchers in `J7Matchers`
- Extended matchers `IsIterableContainingInAnyOrder`, `IsIterableContainingInOrder` and
  `IsIterableContainingInRelativeOrder`

### Changed
- Imported `hamcrest-java 2.0.0.0` instead of `hamcrest-library 1.3`
- Removed old modules and created three new ones (for Google Guava, JDK7 and JDK8), with artifact id:
    -`java7-hamcrest-matchers` that replaces module with artifact `cirneco-matchers-jdk7`
    -`guava-hamcrest-matchers` that extract matchers from module with artifact `cirneco-matchers-jdk7`
    -`java8-hamcrest-matchers` that replaces module with artifact `cirneco-matchers-jdk8`
- Guava matchers are now in module with artifact `guava-hamcrest-matchers`
- Package is now in the form `it.ozimov.cirneco.hamcrest.$MODULE$`,
  where `$MODULE$` is `guava`, `java7` or `java8`, respectively for Google Guava, JDK7 and JDK8 matchers.
- Classes in packages `it.ozimov.cirneco.hamcrest.iterable` and
  `it.ozimov.cirneco.hamcrest.map` are now in package
  `it.ozimov.cirneco.hamcrest.$MODULE$.collect`, where `$MODULE$` is `guava` or `java7`
- Matchers are grouped and exposed in classes `it.ozimov.cirneco.hamcrest.guava.GuavaMatchers`
   `it.ozimov.cirneco.hamcrest.java7.J7Matchers` and `it.ozimov.cirneco.hamcrest.java8.J8Matchers`
   respectively for modules with artifacts



## [0.2] - 2016-01-04
### Added
- New matcher in package `it.ozimov.cirneco.hamcrest.iterable`, i.e. `IsIterableWithDistinctElements`
- New matcher in package `it.ozimov.cirneco.hamcrest.date`, i.e. `IsDateWithInWeekOfYear`
- New matchers in package `it.ozimov.cirneco.hamcrest.number`, i.e. `IsPositive` and  `IsNegative`
to match positive and negative numbers, respectively.
- Updated class `it.ozimov.cirneco.hamcrest.CirnecoMatchersJ7` to expose the new matchers.
- Minor refactoring in unit tests.
- Script for JavaDoc release.
- JavaDoc website on GitHub Pages.

### Changed
- Release script to use ASCII art and JavaDoc release script.
- Minor fixes in the JavaDoc.

### Fixed
- Fix typos in description and mismatch description of `IsInfinity` matcher.
- Update outdated unreleased diff link.



## [0.1] - 2015-12-30
### Added
- New matchers in package `it.ozimov.cirneco.hamcrest.base`:
  * For _JDK7_: `IsBetween`, `IsBetweenInclusive`,  `IsBetweenLowerBoundInclusive`,  `IsBetweenUpperBoundInclusive`,
     `IsEmptyGuavaOptional`, `IsEquivalent`,  `IsSame`;
  * For _JDK8_: `IsEmptyOptional`.
- New matchers in package `it.ozimov.cirneco.hamcrest.date`:
  * For _JDK7_: `IsDate`, `IsDateInDay`, `IsDateInLeapYear`, `IsDateInMonth`, `IsDateWithTime`.
- New matchers in package `it.ozimov.cirneco.hamcrest.iterable`:
  * For _JDK7_: `IsEmptyIterable`, `IsIterableWithSize`, `IsMultisetElementWithCount`, `IsSortedIterable`,
     `IsSortedIterableWithComparator`;
- New matchers in package `it.ozimov.cirneco.hamcrest.map`:
  * For _JDK7_: `IsMapWithSameKeySet`, `IsMultimapKeyWithCollectionSize`, `IsMultimapWithKeySet`, `IsMultimapWithKeySetSize`;
- New matchers in package `it.ozimov.cirneco.hamcrest.number`:
  * For _JDK7_: `IsInfinity`, `IsNegativeInfinity`, `IsPositiveInfinity`, `IsNotANumber`;
- New matchers in package `it.ozimov.cirneco.hamcrest.web`:
  * For _JDK7_: `IsEmail`;
- For JDK7 `it.ozimov.cirneco.hamcrest.CirnecoMatchersJ7` groups all the matchers together.
- For JDK8 `it.ozimov.cirneco.hamcrest.CirnecoMatchersJ8` groups all the matchers together.
