# web3j-maven-plugin
[![Build Status](https://travis-ci.org/bigmlab/web3j-maven-plugin.svg?branch=master)](https://travis-ci.org/bigmlab/web3j-maven-plugin)
[![codecov.io](https://codecov.io/github/web3j/web3j-maven-plugin/coverage.svg?branch=master)](https://codecov.io/github/web3j/web3j-maven-plugin?branch=master)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

web3j maven plugin is used to create java classes based on the solidity contract files.

## Usage
The base configuration for the plugin will take the solidity files from `src/main/resources` and generates the java classes into the folder `src/main/java`.

```xml
<repositories>
    <repository>
        <id>bigmlab</id>
        <url>https://bigmlab.github.io/repo/</url>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
<build>
    <plugins>
        <plugin>
            <groupId>org.web3j</groupId>
            <artifactId>web3j-maven-plugin</artifactId>
            <version>0.1.3</version>
            <configuration>
                <soliditySourceFiles/>
            </configuration>
        </plugin>
    </plugins>
</build>
```

to run the plugin execute the goal `generate-sources`
```bash
mvn web3j:generate-sources
```


## Configuration
The are several variable to select the solidity source files, define a source destination path or change the package name.

| Name                   | Format                                         | Default value       |
| -----------------------|------------------------------------------------| --------------------|
| `<packageName/>`       | valid java pacakge name                        | `org.web3j.model`   |
| `<sourceDestination/>` | relativ or absolut path                        | `src/main/java`     |
| `<soliditySourceFiles>`| Standard maven [fileset](https://maven.apache.org/shared/file-management/fileset.html)  | `<soliditySourceFiles>`<br>`  <directory>src/main/resources</directory>`<br>`  <includes>`<br>`    <include>**/*.sol</include>`<br>`  </includes>`<br>`</soliditySourceFiles>`   |

## Getting Started

Create a standard java maven project. Add following `<plugin>` - configuration into the `pom.xml` file:

```xml
<plugin>
    <groupId>org.web3j</groupId>
    <artifactId>web3j-maven-plugin</artifactId>
    <version>0.1.3</version>
    <configuration>
        <packageName>com.zuehlke.blockchain.model</packageName>
        <sourceDestination>src/main/java/generated</sourceDestination>
        <soliditySourceFiles>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.sol</include>
            </includes>
        </soliditySourceFiles>
    </configuration>
</plugin>
```

Add your solidity contract files into the folder `src/main/resources`. Make sure that the solidity files ends with `.sol`.

start the generating process:


```
> mvn web3j:generate-sources

[INFO] --- web3j-maven-plugin:0.1.3:generate-sources (default-cli) @ hotel-showcase ---
[INFO] process 'HotelShowCaseProxy.sol'
[INFO] 	Built Class for contract 'HotelShowCaseProxy'
[INFO] 	Built Class for contract 'HotelShowCaseV2'
[INFO] 	Built Class for contract 'Owned'
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 4.681 s
[INFO] Finished at: 2017-06-13T07:07:04+02:00
[INFO] Final Memory: 14M/187M
[INFO] ------------------------------------------------------------------------

Process finished with exit code 0
```

You find the generated java classes inside the directory `src/main/java/generated/`.

Next step is to interact with the smart contract. See for that [deploying and interacting with smart contracts](https://web3j.readthedocs.io/en/latest/smart_contracts.html#deploying-and-interacting-with-smart-contracts) in the official web3j documentation.


## Changelog
### 0.1.3
 * Update solc compiler to 0.4.14  

### 0.1.2
 * Better Contract Handling

### 0.1.1
 * Initial Release