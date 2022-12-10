package day04

fun part1(input: Sequence<String>): Int =
    input.map { line ->
        line.split(",", "-")
            .map(String::toInt)
    }.count { (start1, end1, start2, end2) ->
        val range1 = start1..end1
        val range2 = start2..end2
        range1.first in range2 && range1.last in range2 ||
                range2.first in range1 && range2.last in range1
    }

fun part2(input: Sequence<String>): Int =
    input.map { line ->
        line.split(",", "-")
            .map(String::toInt)
    }.count { (start1, end1, start2, end2) ->
        val range1 = start1..end1
        val range2 = start2..end2
        range1.first in range2 || range1.last in range2 ||
                range2.first in range1 || range2.last in range1
    }