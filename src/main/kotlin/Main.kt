import java.io.File
import java.time.LocalDate

fun main(args: Array<String>) {
    val date = LocalDate.now()
    val day = date.takeIf { it in LocalDate.of(2022, 12, 1)..LocalDate.of(2022, 12, 25) }
        ?.dayOfMonth
        ?: args.firstOrNull()?.toIntOrNull()
        ?: 1

    Class.forName("day%02d.SolutionKt".format(day)).run {
        for (part in listOf("part1", "part2")) {
            File("day${day}.txt").useLines {
                getDeclaredMethod(part, Sequence::class.java).invoke(null, it)
            }.let(::println)
        }
    }
}
