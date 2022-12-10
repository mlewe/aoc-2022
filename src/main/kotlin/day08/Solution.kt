package day08

fun part1(input: Sequence<String>): Int {
    val map = input.map { line ->
        line.map { it.digitToInt() }
            .toIntArray()
    }.toList().toTypedArray()

    val seen = mutableSetOf<Pair<Int, Int>>()

    map.forEachIndexed { i, line ->
        var start = -1
        for (j in line.indices) {
            if (start >= line[j]) continue
            seen += i to j
            start = line[j]
        }

        start = -1
        for (j in line.indices.reversed()) {
            if (start >= line[j]) continue
            seen += i to j
            start = line[j]
        }
    }

    map[0].indices.forEach { j ->
        var start = -1
        for (i in map.indices) {
            if (start >= map[i][j]) continue
            seen += i to j
            start = map[i][j]
        }

        start = -1
        for (i in map.indices.reversed()) {
            if (start >= map[i][j]) continue
            seen += i to j
            start = map[i][j]
        }
    }
    return seen.size
}

fun part2(input: Sequence<String>): Int {
    val map = input.map { line ->
        line.map { it.digitToInt() }
            .toIntArray()
    }.toList().toTypedArray()

    var max = -1

    val rowi = map.indices
    val coli = map[0].indices
    for (i in rowi) {
        for (j in coli) {
            var left = 0
            for (jj in (0..j - 1).reversed()) {
                left += 1
                if (map[i][j] <= map[i][jj]) break
            }
            var right = 0
            for (jj in (j + 1..coli.last)) {
                right += 1
                if (map[i][j] <= map[i][jj]) break
            }
            var up = 0
            for (ii in (0..i - 1).reversed()) {
                up += 1
                if (map[i][j] <= map[ii][j]) break
            }
            var down = 0
            for (ii in (i + 1..rowi.last)) {
                down += 1
                if (map[i][j] <= map[ii][j]) break
            }
            max = max.coerceAtLeast(up * down * left * right)
        }
    }
    return max
}

