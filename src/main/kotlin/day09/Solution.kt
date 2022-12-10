package day09

import kotlin.math.abs
import kotlin.math.sign

fun part1(input: Sequence<String>): Int {
    val seen = mutableSetOf(0 to 0)
    var headX = 0
    var headY = 0
    var tailX = 0
    var tailY = 0
    input.forEach { line ->
        val (direction, stepString) = line.split(" ", limit = 2)
        val step = stepString.toInt()
        for (i in 1..step) {
            when (direction) {
                "L" -> headX -= 1
                "R" -> headX += 1
                "U" -> headY += 1
                "D" -> headY -= 1
            }
            if (abs(tailX - headX) > 1
                || abs(tailY - headY) > 1) {
                tailX += (headX - tailX).sign
                tailY += (headY - tailY).sign
            }
            seen += tailX to tailY
        }
    }
    return seen.size
}

fun part2(input: Sequence<String>): Int {
    val seen = mutableSetOf(0 to 0)
    val xs = IntArray(10)
    val ys = IntArray(10)
    input.forEach { line ->
        val (direction, stepString) = line.split(" ", limit = 2)
        val step = stepString.toInt()
        for (i in 1..step) {
            when (direction) {
                "L" -> xs[0] -= 1
                "R" -> xs[0] += 1
                "U" -> ys[0] += 1
                "D" -> ys[0] -= 1
            }
            for (j in 1..9) {
                if (abs(xs[j] - xs[j-1]) > 1
                    || abs(ys[j] - ys[j-1]) > 1) {
                    xs[j] += (xs[j-1] - xs[j]).sign
                    ys[j] += (ys[j-1] - ys[j]).sign
                }
            }
            seen += xs.last() to ys.last()
        }
    }
    return seen.size
}
