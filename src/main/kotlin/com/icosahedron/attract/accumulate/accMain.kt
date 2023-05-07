package com.icosahedron.attract.accumulate

import kotlin.random.Random

fun main() {
    val frequency = 100L
    val a = Inertia(frequency, frequency, frequency, frequency)
    val b = Inertia(frequency, frequency, frequency, frequency)

    val span = Span(12L, 0L, 0L, 0L)
    val pole = Pole(a, b, span)

    val randomSeed = 0
    val random = Random(randomSeed)
    val stepCount = 1000000

    println("Before move 0: $pole")

    repeat (stepCount) { move ->
        var from = a.pickDirection(random)
        var to = b.pickDirection(random)

        while (!pole.canMove(from, to)) {
            from = a.pickDirection(random)
            to = b.pickDirection(random)
        }

        pole.move(from, to)
        println()
        println("After move $move [$from,$to]: $pole")
    }
}