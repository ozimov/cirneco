# Change Log
All notable changes to Cirneco project will be documented in this file.
This project adheres to [Semantic Versioning](http://semver.org/).

## [Unreleased]

## [0.8.1] - 2016-03-17
### Changed
- Inheritance from Hamcrest matchers. Now `it.ozimov.cirneco.hamcrest.java7.Matchers` will just focus on
    new matchers and those overridden from Hamcrest.

- Class `it.ozimov.cirneco.hamcrest.java8.base.OptionalMatcher` is not renamed into
    `it.ozimov.cirneco.hamcrest.java8.base.IsPresentOptionalAnd`

## [0.8.0] - 2016-03-17
### Added
- Added matcher `presentAnd()` in class `it.ozimov.cirneco.hamcrest.java8.base.OptionalMatcher`

## [0.7.0] - 2016-03-16
### Added
- Added matcher `hasToStringContainingInOrder()` in class `it.ozimov.cirneco.hamcrest.java7.base.HasToStringContaining`,

## [0.6.1] - 2016-03-14
### Fixed
- Fixed `given()` in class `AssertFluently`, it now accepts null

## [0.6.0] - 2016-03-14
### Added
- New matcher in package `it.ozimov.cirneco.hamcrest.java7.clazz`, i.e.
        class `IsValidNoArgumentConstructor`.

### Fixed
- The `yaml()` matcher is exposed in `J7Matchers` class

## [0.5.0] - 2016-03-12
### Added
- Assertion to check if a string is a YAML text in package
    `it.ozimov.cirneco.hamcrest.java7.filetype`, i.e. `IsYaml`

## [0.4.2] - 2016-03-12
### Removed
- Cleanup

## [0.4.1] - 2016-03-10
### Removed
- Json Matchers removed. Those from JayWay `json-path-assert` are enough

## [0.4.0] - 2016-01-28
### Added
- Fluent assertions in class `it.ozimov.cirneco.hamcrest.java7.AssertFluently`
- New matcher in package `it.ozimov.cirneco.hamcrest.java7.base`, i.e. `IsSameHashcode`
- New matchers in package `it.ozimov.cirneco.hamcrest.java7.web`, i.e. `IsJSON` and `IsJSONWith`.
  The latter is fully based on _JayWay_ JsonPath library.

### Fixed
- Fixed mismatch description in `it.ozimov.cirneco.hamcrest.guava.collect.IsMultimapWithKeySet`
- Fixed mismatch description in `it.ozimov.cirneco.hamcrest.java7.collect.IsMapWithSameKeySet`
- Fixed unit tests for dates (wrong conversion from Joda `DateTime` to `java.util.Date`


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