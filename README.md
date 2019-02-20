Mybatis Generator  Gradle Plugin
======================================


[![Travis Build Status](https://img.shields.io/travis/Hinsteny/mybatis-generator-plugin.svg)](https://travis-ci.org/Hinsteny/mybatis-generator-plugin)


## Introduction

Because of the MyBatis-Generator 

## Quick Start

### 1. Add MyBatis-Generator gradle plugin

In your ```build.gradle``` file, add following plugin:

``` groovy
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "gradle.plugin.org.hisoka.gradle:mybatis-generator-plugin:0.0.1"
  }
}

apply plugin: "org.hisoka.gradle.mybatis-generator-plugin"

dependencies {
    mybatisGenerator('org.mybatis.generator:mybatis-generator-core:1.3.7')
    mybatisGenerator('mysql:mysql-connector-java:8.0.15')
}

configurations {
    mybatisGenerator
}

mybatisGenerator {
    verbose = true
    configFile = 'src/main/resources/autogen/generatorConfig.xml'
    configPropertiesFile = 'src/main/resources/autogen/mybatis.properties'
}
```

### 2. Create MyBatis-Generator config file

src/main/resources/autogen/generatorConfig.xml, example at below

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
  PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
  "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
  <context id="Mysql" targetRuntime="MyBatis3">

    <commentGenerator>
      <property name="suppressDate" value="true"></property>
      <property name="javaFileEncoding" value="utf-8"/>
    </commentGenerator>

    <!-- jdbc 连接信息  -->
    <jdbcConnection driverClass="${db.driver.class}"
      connectionURL="${db.url}"
      userId="${db.username}"
      password="${db.password}">
    </jdbcConnection>

    <javaTypeResolver>
      <property name="forceBigDecimals" value="false"/>
    </javaTypeResolver>

    <!-- 实体类所在包名 -->
    <javaModelGenerator targetPackage="${model.package}" targetProject="src/main/java">
      <property name="enableSubPackages" value="true"></property>
      <property name="trimStrings" value="true"></property>
    </javaModelGenerator>

    <sqlMapGenerator targetPackage="${xml.package}"
      targetProject="src/main/resources">
      <property name="enableSubPackages" value="true"/>
    </sqlMapGenerator>

    <!-- mapper 所在包名 -->
    <javaClientGenerator targetPackage="${mapper.package}" targetProject="src/main/java" type="XMLMAPPER">
      <property name="enableSubPackages" value="true"/>
    </javaClientGenerator>

    <table tableName="v_%">
      <!--<domainObjectRenamingRule searchString="^V" replaceString=""/>-->
    </table>
  </context>
</generatorConfiguration>
```

src/main/resources/autogen/mybatis.properties, example at below

```
db.driver.class=com.mysql.jdbc.Driver
db.url=jdbc:mysql://127.0.0.1:3306/pay?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true
db.username=pay_user
db.password=welcome
model.package=org.kirin.mybatis.pojo
mapper.package=org.kirin.mybatis.mapper
xml.package=mapper
```

### 3. Execute plugin action

```batch
gradle mybatisGenerate
```

## Deep development

Any body can do one forward develop at this project!

### 1. Add yourself mybatis-generator plugins

- extend your function in `MybatisGeneratorPlugin`

- Build plugin
```
gradle build
```

- Publish plugin to local maven_gradle_plugin repositories
```
gradle publishPluginMavenPublicationToLocalMavenGradlePluginRepository
```

- Publish plugin to local maven repositories
```
gradle publishPluginMavenPublicationToMavenLocal
```

- Publish plugin to gradle plugins center to the account of yourself
```
gradle publishPlugins
```


## Trouble Shooting and Logs

Please commit messages to github-project-issues dashboard if you found any bugs or have any ideas!

## License
[MIT](http://opensource.org/licenses/MIT)

Copyright (c) 2019-present, Hinsteny