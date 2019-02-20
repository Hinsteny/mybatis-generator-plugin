package org.hisoka.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.internal.project.ProjectInternal
import org.hisoka.gradle.tasks.MybatisGeneratorTask

/**
 * MybatisGeneratorPlugin content
 * @author Hinsteny
 * @version $ID: MybatisGeneratorPlugin 2019-02-17 20:40 All rights reserved.$
 */
class MybatisGeneratorPlugin implements Plugin<ProjectInternal> {

    def MYBATIS_GENERATOR_RUNTIME_CONFIGURATION = "mybatisGenerator"

    def MYBATIS_GENERATOR_RUNTIME_ACTION = "mybatisGenerate"

    def MYBATIS_GENERATOR_ACTION_DESCRIPTION = "MyBatis Generator With Ant for gradle plugin"

    @Override
    void apply(ProjectInternal project) {
        project.logger.info "Configuring MyBatis Generator for project: $project.name"
        MybatisGeneratorTask task = project.tasks.create(MYBATIS_GENERATOR_RUNTIME_ACTION, MybatisGeneratorTask).configure {
            group = MYBATIS_GENERATOR_RUNTIME_CONFIGURATION
            description = MYBATIS_GENERATOR_ACTION_DESCRIPTION
        }
        project.configurations.create(MYBATIS_GENERATOR_RUNTIME_CONFIGURATION).with {
            description = 'The MyBatis Generator to be used for this project.'
        }
        project.extensions.create(MYBATIS_GENERATOR_RUNTIME_CONFIGURATION, MybatisGeneratorExtension)

        task.conventionMapping.with {
            mybatisGeneratorClasspath = {
                def config = project.configurations[MYBATIS_GENERATOR_RUNTIME_CONFIGURATION]
                if (config.dependencies.empty) {
                    project.logger.info "The mybatisGenerator not config dependencies, so use default: /n mybatis-generator-core:1.3.7 /n mysql-connector-java:8.0.15"
                    project.dependencies {
                        mybatisGenerator 'org.mybatis.generator:mybatis-generator-core:1.3.7'
                        mybatisGenerator 'mysql:mysql-connector-java:8.0.15'
                    }
                }
                config
            }
            configFile = { project.mybatisGenerator.configFile }
            overwrite = { project.mybatisGenerator.overwrite }
            verbose = { project.mybatisGenerator.verbose }
            targetDir = { project.mybatisGenerator.targetDir }
            configPropertiesFile = { project.mybatisGenerator.configPropertiesFile }
        }

    }

}
