package day05

fun part1(input: Sequence<String>): String {
    val stack = Array<MutableList<Char>>(9) { mutableListOf() }
    val (stackLines, moveLines) = input.partition { !it.startsWith("move") }
    stackLines.dropLast(2).reversed().forEach { line ->
        line.windowed(3, 4).forEachIndexed { index, item ->
            if (item.isNotBlank()) {
                stack[index].add(item[1])
            }
        }
    }
    moveLines.forEach { line ->
        val (count, fromIndex, toIndex) = line.removePrefix("move ")
            .split(" from ", " to ")
            .map(String::toInt)
        val (left, taken) = stack[fromIndex - 1].run { subList(0, size - count) to subList(size- count, size) }
        check(taken.size == count)
        stack[fromIndex - 1] = left.toMutableList()
        stack[toIndex - 1].addAll(taken.reversed())
    }
    return stack.map { it.last() }.joinToString("")
}

fun part2(input: Sequence<String>): String {
    val stack = Array<MutableList<Char>>(9) { mutableListOf() }
    val (stackLines, moveLines) = input.partition { !it.startsWith("move") }
    stackLines.dropLast(2).reversed().forEach { line ->
        line.windowed(3, 4).forEachIndexed { index, item ->
            if (item.isNotBlank()) {
                stack[index].add(item[1])
            }
        }
    }
    moveLines.forEach { line ->
        val (count, fromIndex, toIndex) = line.removePrefix("move ")
            .split(" from ", " to ")
            .map(String::toInt)
        val (left, taken) = stack[fromIndex - 1].run { subList(0, size - count) to subList(size- count, size) }
        check(taken.size == count)
        stack[fromIndex - 1] = left.toMutableList()
        stack[toIndex - 1].addAll(taken)
    }
    return stack.map { it.last() }.joinToString("")
}