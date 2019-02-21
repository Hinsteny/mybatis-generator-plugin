package org.hisoka.gradle

import org.hisoka.gradle.plugin.MybatisGeneratorPlugin

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.hisoka.gradle.tasks.MybatisGeneratorTask
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*

/**
 *
 * @author Hinsteny
 * @version $ID: MybatisGeneratorPluginTest 2019-02-21 08:50 All rights reserved.$
 */
class MybatisGeneratorPluginTest {

    Project project

    @Before
    void setUp() {
        project = ProjectBuilder.builder().build();
    }

    /**
     * Apply the plugin by name and make sure it creates tasks.  We don't go nuts
     * here, just look for a task that takes an argument, and one that doesn't
     */
    @Test
    void applyPluginByName() {
//        project.apply plugin: 'mybatisGenerator'
        project.apply plugin: org.hisoka.gradle.plugin.MybatisGeneratorPlugin
        assertTrue("Project is missing plugin", project.plugins.hasPlugin(MybatisGeneratorPlugin))
        // the tag task takes an arg...
        def task = project.tasks.findByName('mybatisGenerate')
        assertNotNull("Project is missing mybatisGenerate task", task)
        assertTrue("mybatisGenerate task is the wrong type", task instanceof MybatisGeneratorTask)
        assertTrue("mybatisGenerate task should be enabled", task.enabled)
    }

}
