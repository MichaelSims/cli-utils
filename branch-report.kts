#!/usr/bin/env kscript
@file:DependsOn("com.lordcodes.turtle:turtle:0.6.0")

import com.lordcodes.turtle.shellRun

val exclude = setOf("origin/HEAD", "origin/main", "origin/legacy")

val branchesByAuthor = shellRun("git", listOf("branch", "-r"))
    .lines()
    .map { it.substringBefore("->") }
    .map(String::trim)
    .minus(exclude)
    .groupBy { branch -> shellRun("git", listOf("show", "-s", "--format=%an <%ae>", branch)) }
    .toSortedMap(compareBy(String::toLowerCase))

for ((author, branches) in branchesByAuthor) {
    println("$author:")
    for (branch in branches) {
        println("  $branch")
    }
}

println("\n${branchesByAuthor.values.flatten().size} total branches.")
