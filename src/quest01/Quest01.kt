package quest01

import println
import readInput
import splitToInts

fun main() {
    fun part1(input: List<String>): Int {
        val cones = input.first().splitToInts(",")
        val squareNumbers = generateSequence(1) { it + 1 }.map { it * it }
        return cones.sumOf { count -> squareNumbers.first { it >= count } - count }
    }

    fun part2(input: List<String>): Int {
        val cones = input.first().splitToInts(",")
        val squareBorderNumbers = generateSequence(3) { it + 1 }.map { it * it - (it - 2) * (it - 2) }
        return cones.sumOf { count -> squareBorderNumbers.first { it >= count } - count }
    }

    fun part3(input: List<String>): Int {
        val cones = input.first().splitToInts(",")
        val almostFilledSquareNumbers = generateSequence(3) { it + 2 }.map { it * it - 1 }
        return cones.sumOf { count -> almostFilledSquareNumbers.first { it >= count } - count }
    }

    check(part1(listOf("3,11,12,16,24,25")) == 11)
    check(part2(listOf("3,11,12,16,24,25")) == 9)
    check(part3(listOf("3,11,12,16,24,25")) == 61)

    val input = readInput("quest01/p1")
    part1(input).println()

    val input2 = readInput("quest01/p2")
    part2(input2).println()

    val input3 = readInput("quest01/p3")
    part3(input3).println()
}