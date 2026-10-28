package quest01

import println
import readInput

fun main() {
    fun calculateExtraPotions(group: String): Int {
        val enemies = group.count { it != 'x' }
        return when (enemies) {
            2 -> 1
            3 -> 2
            else -> 0
        }
    }

    fun calculatePotionsNeeded(enemies: String, windowSize: Int): Int {
        val scores = mapOf('A' to 0, 'B' to 1, 'C' to 3, 'D' to 5)
        val grouped = enemies.windowed(windowSize, windowSize);

        return grouped.sumOf { group ->
            val extraPotions = calculateExtraPotions(group)
            group.mapNotNull { scores[it] }.sumOf { it + extraPotions }
        }
    }

    fun part1(input: List<String>): Int {
        return calculatePotionsNeeded(input.joinToString(), 1)
    }

    fun part2(input: List<String>): Int {
        return calculatePotionsNeeded(input.joinToString(), 2)
    }

    fun part3(input: List<String>): Int {
        return calculatePotionsNeeded(input.joinToString(), 3)
    }


    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("quest01/quest01_test")
    check(part1(testInput1) == 5)

    val testInput2 = readInput("quest01/quest01_2_test")
    check(part2(testInput2) == 28)

    val testInput3 = readInput("quest01/quest01_3_test")
    check(part3(testInput3) == 30)

    val input = readInput("quest01/quest01")
    part1(input).println()

    val input2 = readInput("quest01/quest01_2")
    part2(input2).println()

    val input3 = readInput("quest01/quest01_3")
    part3(input3).println()
}