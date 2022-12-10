package day07

fun part1(input: Sequence<String>): Int {
    val commandsWithOutput = input.fold(mutableListOf<Pair<String, MutableList<String>>>()) { acc, line ->
        acc.apply {
            if (line.startsWith("$")) {
                add(line to mutableListOf())
            } else {
                last().second.add(line)
            }
        }
    }
    val currentPath = mutableListOf<String>()
    val directories = mutableSetOf("/")
    val fileSizes = mutableMapOf<String, Int>()

    commandsWithOutput.forEach { (command, output) ->
        when {
            command.startsWith("\$ cd /") -> currentPath.clear()
            command.startsWith("\$ cd ..") -> currentPath.removeLast()
            command.startsWith("\$ cd ") -> currentPath.add(command.removePrefix("\$ cd "))
            command == "\$ ls" -> {
                output.forEach { line ->
                    val (dirOrSize, name) = line.split(" ")
                    val absoluteName = if (currentPath.isEmpty()) {
                        "/$name"
                    } else {
                        currentPath.joinToString(separator = "/", prefix = "/", postfix = "/") + name
                    }
                    if (dirOrSize == "dir") {
                        directories.add(absoluteName)
                    } else {
                        fileSizes[absoluteName] = dirOrSize.toInt()
                    }
                }
            }
        }
    }

    return directories.sumOf { dir ->
        fileSizes.filterKeys { it.startsWith(dir) }
            .values
            .sum()
            .takeIf { it <= 100000 }
            ?: 0
    }
}

fun part2(input: Sequence<String>): Int {
    val commandsWithOutput = input.fold(mutableListOf<Pair<String, MutableList<String>>>()) { acc, line ->
        acc.apply {
            if (line.startsWith("$")) {
                add(line to mutableListOf())
            } else {
                last().second.add(line)
            }
        }
    }
    val currentPath = mutableListOf<String>()
    val directories = mutableSetOf("/")
    val fileSizes = mutableMapOf<String, Int>()

    commandsWithOutput.forEach { (command, output) ->
        when {
            command.startsWith("\$ cd /") -> currentPath.clear()
            command.startsWith("\$ cd ..") -> currentPath.removeLast()
            command.startsWith("\$ cd ") -> currentPath.add(command.removePrefix("\$ cd "))
            command == "\$ ls" -> {
                output.forEach { line ->
                    val (dirOrSize, name) = line.split(" ")
                    val absoluteName = if (currentPath.isEmpty()) {
                        "/$name"
                    } else {
                        currentPath.joinToString(separator = "/", prefix = "/", postfix = "/") + name
                    }
                    if (dirOrSize == "dir") {
                        directories.add(absoluteName)
                    } else {
                        fileSizes[absoluteName] = dirOrSize.toInt()
                    }
                }
            }
        }
    }

    val dirsWithSize = directories.associateWith { dir ->
        fileSizes.filterKeys { it.startsWith(dir) }
            .values
            .sum()
    }
    val totalUsedSize = dirsWithSize.getValue("/")
    val freeSize = 70000000 - totalUsedSize
    val requiredSizeForUpdate = 30000000 - freeSize

    return dirsWithSize.entries
        .sortedBy { it.value }
        .first { it.value >= requiredSizeForUpdate }
        .value
}

