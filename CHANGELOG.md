# Change Log
All notable changes to Cirneco project will be documented in this file.
This project adheres to [Semantic Versioning](http://semver.org/).

## [Unreleased]

### Added
- New matchers in package `it.ozimov.cirneco.hamcrest.number`, i.e. `IsPositive` and  `IsNegative`
to match positive and negative numbers, respectively.
- Updated class `it.ozimov.cirneco.hamcrest.CirnecoMatchersJ7` to expose the new matchers.
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
