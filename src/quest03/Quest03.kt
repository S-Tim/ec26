package quest03

import println
import readInput
import kotlin.math.pow

fun main() {
    fun parseVote(vote: String): Pair<String, String> {
        val pattern = """ISO2?-(\d+) (\d+)""".toRegex()
        val match = pattern.matchEntire(vote) ?: error("Invalid vote: $vote")
        return match.groupValues[1] to match.groupValues[2]
    }

    fun calculateDecimalValue(vote: Pair<String, String>): Long {
        val (standard, value) = vote
        return value.indices.sumOf {
            val base = standard[standard.length - (it % standard.length) - 1].digitToInt()
            val digit = value[value.length - it - 1].digitToInt()
            base.toDouble().pow(it.toDouble()).toLong() * digit
        }
    }

    fun isMinimalRepresentation(vote: Pair<String, String>): Boolean {
        val (standard, representation) = vote
        val decimal = calculateDecimalValue(vote)
        return (0L until representation.toLong()).none { candidate ->
            calculateDecimalValue(standard to candidate.toString()) == decimal
        }
    }

    fun countVotes(input: List<String>, onlyMinimal: Boolean = false): Long {
        return input.sumOf { line ->
            val vote = parseVote(line)
            if (onlyMinimal && !isMinimalRepresentation(vote)) {
                0L
            } else {
                calculateDecimalValue(vote)
            }
        }
    }

    fun part1(input: List<String>) = countVotes(input)

    fun part2(input: List<String>) = countVotes(input)

    fun part3(input: List<String>) = countVotes(input, onlyMinimal = true)

    check(part1(readInput("quest03/t1")) == 1478L)
    check(part2(readInput("quest03/t2")) == 1161463L)
    check(part3(readInput("quest03/t3")) == 50987L)

    val input = readInput("quest03/p1")
    part1(input).println()

    val input2 = readInput("quest03/p2")
    part2(input2).println()

    val input3 = readInput("quest03/p3")
    part3(input3).println()
}
