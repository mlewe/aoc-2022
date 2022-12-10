package day02

import day02.RPS.*

enum class RPS {
    ROCK, PAPER, SCISSORS
}

fun part1(input: Sequence<String>): Int {
    var score = 0
    input.forEach { line ->
        val (abc, xyz) = line.split(" ", limit = 2)
        val first = when (abc) {
            "A" -> ROCK
            "B" -> PAPER
            "C" -> SCISSORS
            else -> error("")
        }
        val second = when (xyz) {
            "X" -> ROCK
            "Y" -> PAPER
            "Z" -> SCISSORS
            else -> error("")
        }

        score += when (first to second) {
            ROCK to ROCK, PAPER to PAPER, SCISSORS to SCISSORS -> 3
            ROCK to PAPER, PAPER to SCISSORS, SCISSORS to ROCK -> 6
            ROCK to SCISSORS, PAPER to ROCK, SCISSORS to PAPER -> 0
            else -> error("")
        }

        score += when (second) {
            ROCK -> 1
            PAPER -> 2
            SCISSORS -> 3
        }
    }
    return score
}

fun part2(input: Sequence<String>): Int {
    var score = 0
    input.forEach { line ->
        val (abc, xyz) = line.split(" ", limit = 2)
        val first = when (abc) {
            "A" -> ROCK
            "B" -> PAPER
            "C" -> SCISSORS
            else -> error("")
        }
        val second = when (xyz) {
            // lose
            "X" -> when (first) {
                ROCK -> SCISSORS
                PAPER -> ROCK
                SCISSORS -> PAPER
            }
            // draw
            "Y" -> first
            // win
            "Z" -> when (first) {
                ROCK -> PAPER
                PAPER -> SCISSORS
                SCISSORS -> ROCK
            }

            else -> error("")
        }

        score += when (first to second) {
            ROCK to ROCK, PAPER to PAPER, SCISSORS to SCISSORS -> 3
            ROCK to PAPER, PAPER to SCISSORS, SCISSORS to ROCK -> 6
            ROCK to SCISSORS, PAPER to ROCK, SCISSORS to PAPER -> 0
            else -> error("")
        }

        score += when (second) {
            ROCK -> 1
            PAPER -> 2
            SCISSORS -> 3
        }
    }
    return score
}
