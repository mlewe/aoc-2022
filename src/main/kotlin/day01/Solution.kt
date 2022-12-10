package day01

import java.util.*
import kotlin.math.max

fun part1(input: Sequence<String>): Int {
    val (currentMax, previous) = input.map { it.toIntOrNull() }
        .fold(0 to 0) { (currentMax, previous), curr ->
            if (curr == null) {
                max(currentMax, previous) to 0
            } else {
                currentMax to previous + curr
            }
        }
    return max(currentMax, previous)
}

fun part2(input: Sequence<String>): Int {
    val (maxes, previous) = input.map { it.toIntOrNull() }
        .fold(PriorityQueue<Int>() to 0) { (maxes, previous), curr ->
            if (curr == null) {
                maxes.insert(previous) to 0
            } else {
                maxes to previous + curr
            }
        }
    return maxes.insert(previous)
        .sum()
}

private fun <T : Any> PriorityQueue<T>.insert(element: T): PriorityQueue<T> = apply {
    offer(element)
    if (size > 3) {
        poll()
    }
}