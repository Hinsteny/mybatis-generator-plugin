package org.hisoka.gradle.plugin

import groovy.transform.ToString

/**
 * This is the Gradle extension that configures the MybatisGenerator plugin.
 * @author Hinsteny
 * @version $ID: MybatisGeneratorExtension 2019-02-19 18:01 All rights reserved.$
 */
@ToString(includeNames = true)
class MybatisGeneratorExtension {

    // Specifies the name of the configuration file
    def configFile

    // If "true", "yes", etc., then MBG will overwrite existing Java files if an existing Java file if found with the same name as a generated file.
    def overwrite = true

    // If "true", "yes", etc., then MBG will log progress messages to the ant console (if Ant is running in verbose mode). The default is "false".
    def verbose = false

    //
    def targetDir = "."

    // Specifies the name of configuration properties file for render configuration file's placeholder
    def configPropertiesFile

}
