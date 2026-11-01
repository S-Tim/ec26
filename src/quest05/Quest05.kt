package quest05

import Point
import println
import readInput

fun main() {
    fun parseInput(input: List<String>): List<MutableList<Char>> {
        return input.map { it.toCharArray().toMutableList() }
    }

    fun getNeighbors(position: Point, map: List<List<Char>>): List<Char> {
        val coordinates = (-1..1).flatMap { x -> (-1..1).map { y -> x + position.first to y + position.second } }
            .filter { it != position }
        return coordinates.map { map.getOrNull(it.second)?.getOrNull(it.first) ?: '.' }
    }

    fun printMap(map: List<List<Char>>) {
        for (row in map) println(row.joinToString(""))
    }

    fun expandLand(map: List<MutableList<Char>>): List<MutableList<Char>> {
        // Initial land in the middle
        map[map.size / 2][map[0].size / 2] = '#'
        var changed = true
        while (changed) {
            changed = false
            for (y in map.indices) {
                for (x in map[0].indices) {
                    if (map[y][x] == '#') continue

                    val neighbors = getNeighbors(x to y, map)
                    if (neighbors.count { it == '#' || it == '1' } >= 5 && neighbors.any { it == '#' }) {
                        map[y][x] = '#'
                        changed = true
                    }
                }
            }
        }
        return map
    }

    fun expandSand(map: List<MutableList<Char>>): List<MutableList<Char>> {
        var changed = true
        while (changed) {
            changed = false
            for (y in map.indices) {
                for (x in map[0].indices) {
                    if (map[y][x] == '=') continue

                    val neighbors = getNeighbors(x to y, map)
                    if (map[y][x] == '#' && neighbors.any { it in "10." } || neighbors.count { it == '=' } >= 5) {
                        map[y][x] = '='
                        changed = true
                    }
                }
            }
        }
        return map
    }

    fun part1(input: List<String>): Int {
        val map = parseInput(input)
        return expandLand(map).flatten().count { it == '#' }
    }

    fun part2(input: List<String>): Int {
        val map = parseInput(input)
        val landMap = expandLand(map)
        return expandSand(landMap).flatten().count { it == '=' }
    }

    fun part3(input: List<String>): Long {
        return 0
    }

    check(part1(readInput("quest05/t1")) == 137)
    check(part2(readInput("quest05/t2")) == 69)
//    check(part3(readInput("quest05/t3")) == 2998L)

    val input = readInput("quest05/p1")
    part1(input).println()

    val input2 = readInput("quest05/p2")
    part2(input2).println()
//
//    val input3 = readInput("quest05/p3")
//    part3(input3).println()
}
