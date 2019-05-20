![Logo](https://raw.githubusercontent.com/idealista/nexus-role/master/logo.gif)

[![Build Status](https://travis-ci.org/idealista/nexus-role.png)](https://travis-ci.org/idealista/nexus-role)

# Nexus Ansible role

This ansible role installs a Nexus Repository Manager 3 in a debian environment.

- [Getting Started](#getting-started)
  - [Prerequisities](#prerequisities)
  - [Installing](#installing)
- [Usage](#usage)
- [Testing](#testing)
- [Built With](#built-with)
- [Versioning](#versioning)
- [Authors](#authors)
- [License](#license)
- [Contributing](#contributing)
- [Acknowledgments](#acknowledgments)

## Getting Started

These instructions will get you a copy of the role for your ansible playbook. Once launched, it will install a [Nexus Repository Manager 3](https://help.sonatype.com/repomanager3) server in a Debian system.

### Prerequisities

Ansible 2.4.5.0 version installed.
Inventory destination should be a Debian environment.

Java must be installed to run the service. Check [Sonatype help](https://help.sonatype.com/repomanager3/installation/java-runtime-environment) to see supported versions. Java can be installed using the [Idealista's Java Ansible role](https://github.com/idealista/java-role).

For testing purposes, [Molecule](https://molecule.readthedocs.io/) with [Docker](https://www.docker.com/) as driver.

### Installing

Create or add to your roles dependency file (e.g requirements.yml) from GitHub:

```yml
- src: http://github.com/idealista/nexus-role.git
  scm: git
  version: 1.0.0
  name: nexus
```

or using [Ansible Galaxy](https://galaxy.ansible.com/idealista/nexus-role/) as origin if you prefer:

```yml
- src: idealista.nexus-role
  version: 1.0.0
  name: nexus
```

Install the role with ansible-galaxy command:

```sh
ansible-galaxy install -p roles -r requirements.yml -f
```

Use in a playbook:

```yml
---
- hosts: someserver
  roles:
    - nexus
```

## Usage

Look to the [defaults](defaults/main.yml) properties file to see the possible configuration properties.

Check in the maven section how can you set up a oracle maven repository.

You can edit nexus config and dashboards via template or webui.

## Testing

```sh
molecule test --platform=Debian9
```

## Built With

![Ansible](https://img.shields.io/badge/ansible-2.4.5.0-green.svg)

## Versioning

For the versions available, see the [tags on this repository](https://github.com/idealista/nexus-role/tags).

Additionaly you can see what change in each version in the [CHANGELOG.md](CHANGELOG.md) file.

## Authors

- **Idealista** - *Work with* - [idealista](https://github.com/idealista)

See also the list of [contributors](https://github.com/idealista/nexus-role/contributors) who participated in this project.

## License

![Apache 2.0 License](https://img.shields.io/hexpm/l/plug.svg)

This project is licensed under the [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0) license - see the [LICENSE](LICENSE) file for details.

## Contributing

Please read [CONTRIBUTING.md](.github/CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Acknowledgments

- [Sonatype Blog](https://blog.sonatype.com/developing-an-ansible-role-for-nexus-repository-manager-v3.x) - Developing an ansible role for nexus v3.x
