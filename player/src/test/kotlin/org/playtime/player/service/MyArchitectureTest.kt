package org.playtime.player.service

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import org.junit.jupiter.api.Test

class MyArchitectureTest {

    @Test
    fun some_architecture_rule() {
        val importedClasses = ClassFileImporter().importPackages("org.playtime")

        val rule = classes().that().resideInAPackage("..playtime.player..")
            .should().onlyBeAccessed().byAnyPackage("..playtime.application..", "..playtime.player..")

        rule.check(importedClasses);
    }
}