package day10

import kotlin.math.abs

fun part1(input: Sequence<String>): Int {
    val adds = input.flatMap { line ->
        val parts = line.split(" ")
        if (parts[0] == "noop") {
            listOf(0)
        } else {
            listOf(0, parts.getOrNull(1)?.toInt() ?: 0)
        }
    }.toList()
    var sum = 0
    for (cycle in 20..220 step 40) {
        sum += cycle * (1 + adds.take(cycle - 1).sum())
    }
    return sum
}

fun part2(input: Sequence<String>): String {
    val adds = input.flatMap { line ->
        val parts = line.split(" ")
        if (parts[0] == "noop") {
            listOf(0)
        } else {
            listOf(0, parts.getOrNull(1)?.toInt() ?: 0)
        }
    }

    val duringXs = adds.runningFold(1, Int::plus)
    return duringXs.chunked(40).joinToString("\n") { line ->
        line.mapIndexed { i, x -> abs(x - i) <= 1 }
            .joinToString("") { if (it) "#" else "." }
    }
}
