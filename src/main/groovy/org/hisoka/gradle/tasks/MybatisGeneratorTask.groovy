package org.hisoka.gradle.tasks

import org.gradle.api.file.FileCollection
import org.gradle.api.internal.ConventionTask
import org.gradle.api.internal.project.IsolatedAntBuilder
import org.gradle.api.tasks.TaskAction

/**
 * Mybatis Generator Plugin Task
 * @author Hinsteny
 * @version $ID: MybatisGeneratorTask 2019-02-17 20:41 All rights reserved.$
 */
class MybatisGeneratorTask extends ConventionTask {

    def configFile
    def overwrite
    def verbose
    def targetDir
    def configPropertiesFile

    FileCollection mybatisGeneratorClasspath

    @TaskAction
    void executeGenerateAction() {
        services.get(IsolatedAntBuilder).withClasspath(getMybatisGeneratorClasspath()).execute {
            logger.info "MyBatis Generator configPropertiesFile: $configPropertiesFile"
            ant.taskdef(name: 'mbgenerator', classname: 'org.mybatis.generator.ant.GeneratorAntTask')
            ant.properties['generated.source.dir'] = getTargetDir()
            def mybatisProperties = getMyBatisProperties(configPropertiesFile)
            mybatisProperties.each {
                ant.project.setProperty(it.key, it.value)
            }
            ant.mbgenerator(overwrite: getOverwrite(), configfile: getConfigFile(), verbose: getVerbose()) {
                propertyset {
                    mybatisProperties.each {
                        propertyref(name: it.key)
                    }
                }
            }
        }
    }

    /**
     * load mybatis properties file from
     */
    def getMyBatisProperties(configPropertiesFile) {
        def properties = new Properties()
        project.file(configPropertiesFile).withInputStream { inputStream ->
            properties.load(inputStream)
        }
        logger.info "MyBatis Generator configProperties: $properties"
        properties
    }

}
