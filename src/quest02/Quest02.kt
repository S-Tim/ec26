package quest02

import println
import readInput

fun main() {
    fun parseInput(alloyGroups: List<String>): List<List<Pair<Char, Char>>> {
        return alloyGroups.map { group ->
            group.split(" ")
                .map { comparison -> if (comparison[1] == '<') comparison[0] to comparison[2] else comparison[2] to comparison[0] }
        }
    }

    fun sortByHardness(alloyGroup: List<Pair<Char, Char>>): List<Char> {
        val alloys = alloyGroup.flatMap { listOf(it.first, it.second) }.distinct().toMutableList()
        var swapped = true
        while (swapped) {
            swapped = false
            for ((first, second) in alloyGroup) {
                val firstIndex = alloys.indexOf(first)
                val secondIndex = alloys.indexOf(second)
                if (firstIndex > secondIndex) {
                    val temp = alloys[firstIndex]
                    alloys[firstIndex] = alloys[secondIndex]
                    alloys[secondIndex] = temp
                    swapped = true
                }
            }
        }
        return alloys
    }

    fun part1(input: List<String>): Long {
        val sortedAlloys = parseInput(input).map { sortByHardness(it).reversed() }
        return sortedAlloys.map { it.fold("") { acc, next -> acc + next } }.sumOf { it.toLong() }
    }

    fun part2(input: List<String>): Long {
        return part1(input)
    }

    fun part3(input: List<String>): Long {
        return part1(input)
    }

    check(part1(readInput("quest02/t1")) == 122157L)
    check(part2(readInput("quest02/t2")) == 122157L)
    check(part3(readInput("quest02/t3")) == 122157L)

    val input = readInput("quest02/p1")
    part1(input).println()

    val input2 = readInput("quest02/p2")
    part2(input2).println()

    val input3 = readInput("quest02/p3")
    part3(input3).println()
}