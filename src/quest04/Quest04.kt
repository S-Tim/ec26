package quest04

import println
import readInput

fun main() {
    data class Result(val id: String, val quest: String, val part: String, val time: Long)

    fun parseResult(line: String): Result {
        val pattern = """id=(\d+) quest=(\d+) part=(\d+) time=(\d+)""".toRegex()
        val match = pattern.matchEntire(line) ?: error("Invalid result")
        return Result(match.groupValues[1], match.groupValues[2], match.groupValues[3], match.groupValues[4].toLong())
    }

    fun part1(input: List<String>): Long {
        val results = input.map { parseResult(it) }
        val totalTimePerParticipant =
            results.groupBy { it.id }.map { result -> result.key to result.value.sumOf { it.time } }
                .sortedBy { it.second }
        return totalTimePerParticipant.mapIndexed { index, (_, time) -> (index + 1) * time }.sum()
    }

    fun part2(input: List<String>): Long {
        val results = input.map { parseResult(it) }

        // id to completed parts and total time
        val totalsByParticipant =
            results.groupBy { it.id }.map { participantResults ->
                participantResults.key to Pair(
                    participantResults.value.size,
                    participantResults.value.sumOf { it.time })
            }

        // sort by time and then by completed parts
        val sortedResultsByParticipant =
            totalsByParticipant.sortedBy { it.second.second }.sortedByDescending { it.second.first }

        return sortedResultsByParticipant.mapIndexed { index, (_, results) -> (index + 1) * results.second }.sum()
    }

    fun part3(input: List<String>): Long {
        return 0
    }

    check(part1(readInput("quest04/t1")) == 27598L)
    check(part2(readInput("quest04/t2")) == 6470L)
    check(part3(readInput("quest04/t3")) == 2998L)

    val input = readInput("quest04/p1")
    part1(input).println()

    val input2 = readInput("quest04/p2")
    part2(input2).println()

    val input3 = readInput("quest04/p3")
    part3(input3).println()
}
