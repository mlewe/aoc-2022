package day03

fun part1(input: Sequence<String>): Int =
    input.sumOf { line ->
        val first = line.substring(0, line.length / 2)
        val second = line.substring(line.length / 2)
        val key = first.first { it in second }
        key.priority
    }

fun part2(input: Sequence<String>): Int =
    input.chunked(3).sumOf { lines ->
        val (first, second, third) = lines
        val key = first.first { it in second && it in third }
        key.priority
    }

private val Char.priority: Int
    get() = when (this) {
        in 'a'..'z' -> code - 'a'.code
        in 'A'..'Z' -> code - 'A'.code + 26
        else -> error("invalid input")
    } + 1