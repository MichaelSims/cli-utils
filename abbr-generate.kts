#!/usr/bin/env kotlin

fun generateZshMultiStageAbbreviations(abbrParts: List<String>, commandParts: List<String>): String = buildString {
    // First abbreviation: first letter to first word
    appendLine("abbr \"${abbrParts[0]}\"=\"${commandParts[0]}\"")

    // Progressive abbreviations
    for (i in 1 until abbrParts.size) {
        val abbr = commandParts.subList(0, i).joinToString(" ")
        val cmd = commandParts.subList(0, i + 1).joinToString(" ")
        appendLine("abbr \"$abbr ${abbrParts[i]}\"=\"$cmd\"")
    }

    // Final abbreviation: all letters combined to full command
    val shortAbbr = abbrParts.joinToString("")
    val fullCmd = commandParts.joinToString(" ")
    appendLine("abbr \"$shortAbbr\"=\"$fullCmd\"")
}

// Prompt for the abbreviation
print("Enter the abbreviation with spaces between each letter/group of letters (e.g., 'g r c'): ")
val leftSide = readlnOrNull().orEmpty()
// Prompt for right-hand side of the equals
print("Enter the full command (e.g., 'git rebase --continue'): ")
val rightSide = readlnOrNull().orEmpty()
// Generate abbreviations
println(generateZshMultiStageAbbreviations(leftSide.split(" "), rightSide.split(" ")))
