# Change Log

All notable changes to this project will be documented in this file.
This project adheres to [Semantic Versioning](http://semver.org/) and [Keep a changelog](https://github.com/olivierlacan/keep-a-changelog).

## [Unreleased](https://github.com/idealista/nexus-role/tree/develop)

### Added

- *[#78](https://github.com/idealista/nexus-role/issues/78)* Add option to allow LDAP dynamic group type* @mgnavarrete

## [2.3.2](https://github.com/idealista/nexus-role/tree/2.3.2)

[Full Changelog](https://github.com/idealista/nexus-role/compare/2.3.1...2.3.2)

### Added

- *[#75](https://github.com/idealista/nexus-role/issues/75)* Add option to allow redeploying the 'latest' tag in docker repositories
- Update molecule platforms config
- Fix typos

## [2.3.1](https://github.com/idealista/nexus-role/tree/2.3.1)

[Full Changelog](https://github.com/idealista/nexus-role/compare/2.3.0...2.3.1)

### Added

- *Add Pipfiles* @emepege

### Fixed

- *[#45](https://github.com/idealista/nexus-role/issues/70) Notify nexus service restart when composer plugin is downloaded* @emepege

## [2.3.0](https://github.com/idealista/nexus-role/tree/2.3.0)

[Full Changelog](https://github.com/idealista/nexus-role/compare/2.2.0...2.3.0)

### Added

- *Add `use_trust_store` LDAP configuration item* @jpiron
- [#64] *Allow to configure custom jvm options* @vsuarez
- [#66] *Support agents installation* @vsuarez
- [#67] *Now nexus.vmoptions can be overrided with a playbook template* @vsuarez

## [2.2.0](https://github.com/idealista/nexus-role/tree/2.2.0)

[Full Changelog](https://github.com/idealista/nexus-role/compare/2.1.0...2.2.0)

### Added

- [#60] *Update roled dependencies* @aren-pulid0
- [#58] *Add Debian 11 support* @aren-pulid0

### Fix

- *Fix no longer supported test filter syntax* @jpiron
- [#29] *Fix Nexus URLs in uri module calls* @jpiron
- *Fix wrong LDAP attributes in default variables* @jpiron

## [2.1.0](https://github.com/idealista/nexus-role/tree/2.1.0) (2020-10-08)

[Full Changelog](https://github.com/idealista/nexus-role/compare/2.0.1...2.1.0)

### Added

- [#48] *Add support for MaxDirectMemoyySize property* @vicsufer

## [2.0.1](https://github.com/idealista/nexus-role/tree/2.0.1) (2020-09-17)

[Full Changelog](https://github.com/idealista/nexus-role/compare/2.0.0...2.0.1)

### Added

- [#45] *Add test to multiple configuration elements using nexus REST API* @vicsufer

### Fix

- [#45] *LDAP Groovy script is now compatible with latest API.* @vicsufer

## [2.0.0](https://github.com/idealista/nexus-role/tree/2.0.0) (2020-09-16)

[Full Changelog](https://github.com/idealista/nexus-role/compare/1.7.0...2.0.0)

### Added

- [#39] *Support for nexus v3.21.2-03 and later versions.* @vicsufer
- [#38] *Support for composer repositories.* @vicsufer
- [#41] *Tests for Debian buster* @vicsufer

### Changed

- [#39] *clean_policy variable is mandatory for repositories, set to None if no policy needed* @vicsufer
- [#39] *change molecule image from jdk:8u222 to jdk:8u265* @vicsufer

### Removed

- [#39] *nexus_role is no longer comptatible with nexus versions previous to 3.21.2-03.* @vicsufer

## [1.7.0](https://github.com/idealista/nexus-role/tree/1.7.0) (2020-01-20)

[Full Changelog](https://github.com/idealista/nexus-role/compare/1.6.1...1.7.0)

## Changed

- *Enable multi ldap config. nexus_ldap_\* values have been renamed* @miguel-chacon

## [1.6.1](https://github.com/idealista/nexus-role/tree/1.6.1) (2020-01-20)

[Full Changelog](https://github.com/idealista/nexus-role/compare/1.6.0...1.6.1)

## Added

- *Read default password file if exists on initial installation* @miguel-chacon

## [1.6.0](https://github.com/idealista/nexus-role/tree/1.6.0) (2019-08-01)

[Full Changelog](https://github.com/idealista/nexus-role/compare/1.5.0...1.6.0)

### Changed

- *[#22](https://github.com/idealista/nexus-role/issues/22) Remove Java role deployment in tests* @jnogol
- *Remove pipenv use in Travis* @jnogol
- *[#26](https://github.com/idealista/nexus-role/issues/26) Enable jmx* @jmonterrubio

## [1.5.0](https://github.com/idealista/nexus-role/tree/1.5.0) (2019-05-21)

[Full Changelog](https://github.com/idealista/nexus-role/compare/1.4.0...1.5.0)

### Added

- *[#15](https://github.com/idealista/nexus-role/issues/15) Add Alert Email to Scheduled Tasks* @jnogol
- *[#16](https://github.com/idealista/nexus-role/issues/16) Migrate tests to Molecule 2* @jnogol

### Changed

- *Update Molecule test Docker image* @jnogol

## [1.4.0](https://github.com/idealista/nexus-role/tree/1.4.0) (2019-05-13)

[Full Changelog](https://github.com/idealista/nexus-role/compare/1.3.0...1.4.0)

### Added

- *[#12](https://github.com/idealista/nexus-role/issues/12) Ability to configure raw repositories* @jnogol

### Fixed

- *[#11](https://github.com/idealista/nexus-role/issues/11) Nuget and Pypi repositories were configured only if nexus_npm_enable: true* @jnogol

## [1.3.0](https://github.com/idealista/nexus-role/tree/1.3.0) (2018-11-20)

[Full Changelog](https://github.com/idealista/nexus-role/compare/1.2.0...1.3.0)

### Added

- *[#7](https://github.com/idealista/nexus-role/issues/7) Support cleanup policies* @jmonterrubio

## [1.2.0](https://github.com/idealista/nexus-role/tree/1.2.0) (2018-07-18)

[Full Changelog](https://github.com/idealista/nexus-role/compare/1.1.0...1.2.0)

### Added

- *[#4](https://github.com/idealista/nexus-role/issues/4) Enable security realms configuration* @jmonterrubio

## [1.1.0](https://github.com/idealista/nexus-role/tree/1.1.0) (2018-06-07)

[Full Changelog](https://github.com/idealista/nexus-role/compare/1.0.0...1.1.0)

### Added

- *[#2](https://github.com/idealista/nexus-role/issues/2) Add ldap user group* @jmonterrubio

## [1.0.0](https://github.com/idealista/nexus-role/tree/1.0.0) (2018-05-17)

### Added

- *First release* @jmonterrubio
