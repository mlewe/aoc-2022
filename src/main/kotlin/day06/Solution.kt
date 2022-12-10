package day06

fun part1(input: Sequence<String>): Int = findMarker(input.first())

fun part2(input: Sequence<String>): Int = findMarker(input.first(), 14)

private fun findMarker(
    line: String,
    size: Int = 4
) = line.windowed(size)
    .indexOfFirst {
        it.toSet().size == size
    } + size

