package quest06

import println
import readInput

fun main() {
    fun madeFromGraph(input: List<String>): Map<String, List<String>> {
        val madeFrom = mutableMapOf<String, List<String>>()
        for (recipe in input) {
            val (item, ingredients) = recipe.split(":")
            madeFrom[item] = ingredients.split(",")
        }
        return madeFrom
    }

    fun makesGraph(input: List<String>): Map<String, List<String>> {
        val makes = mutableMapOf<String, MutableList<String>>()
        for (recipe in input) {
            val (item, ingredients) = recipe.split(":")
            for (ingredient in ingredients.split(",")) {
                makes.getOrPut(ingredient) { mutableListOf() }.add(item)
            }
        }
        return makes
    }

    fun search(start: String, graph: Map<String, List<String>>): Set<String> {
        val open = ArrayDeque(graph.getOrDefault(start, listOf()))
        val ingredients = mutableSetOf<String>()

        while (open.isNotEmpty()) {
            val current = open.removeFirst()
            if (current in ingredients) continue

            ingredients.add(current)
            open.addAll(graph.getOrDefault(current, listOf()))
        }

        return ingredients
    }

    fun part1(input: List<String>): Int {
        val madeFrom = madeFromGraph(input)
        val ingredients = search("NULL", madeFrom)
        return ingredients.filter { it != "NULL" }.size
    }

    fun part2(input: List<String>): Int {
        val makes = makesGraph(input)
        val items = search("NULL", makes)
        return items.filter { it != "NULL" }.size
    }

    fun part3(input: List<String>): Int {
        val makes = makesGraph(input)
        val fromNullV1 = search("NULLv1", makes)
        val fromNullV2 = search("NULLv2", makes)
        val fromNullV3 = search("NULLv3", makes)

        val from1Or2 = fromNullV1.intersect(fromNullV2)
        val from1Or3 = fromNullV1.intersect(fromNullV3)
        val from2Or3 = fromNullV2.intersect(fromNullV3)

        return (from1Or2 + from1Or3 + from2Or3).filter { !it.startsWith("NULL") }.size
    }

    check(part1(readInput("quest06/t1")) == 7)
    check(part2(readInput("quest06/t2")) == 6)
    check(part3(readInput("quest06/t3")) == 3)

    val input = readInput("quest06/p1")
    part1(input).println()

    val input2 = readInput("quest06/p2")
    part2(input2).println()

    val input3 = readInput("quest06/p3")
    part3(input3).println()
}
