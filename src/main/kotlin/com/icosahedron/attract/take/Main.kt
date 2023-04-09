package com.icosahedron.attract.take

import kotlin.random.Random

fun main() {
    val tickCount = 100000000
    val random = Random(0)
    val radial = longArrayOf(4000L, 2000L, 0L, 2000L)
    val fromInertia = doubleArrayOf(10.0, 10.0, 10.0, 10.0)
    val toInertia = doubleArrayOf(10.0, 10.0, 10.0, 10.0)

    val span = Span(radial, fromInertia, toInertia)
    println(paddedTick(0) + ">".repeat(span.radius.toInt()) + span.radius.toString())

    repeat(tickCount) { move ->
//        println()
//        println("Before move $move: $span")
        span.makeRandomMove(random)
        if (move % 1000000 == 0) println(paddedTick(move + 1) + ">".repeat(span.radius.toInt()) + span.radius.toString())
    }

//    println()
//    println("After move $tickCount: $span")
//    println(">".repeat(span.radius.toInt()) + span.radius.toString())
//    println(paddedTick(tickCount) + ">".repeat(span.radius.toInt()) + span.radius.toString())
}

fun paddedTick(tick: Int) = tick.toString().padStart(5, '0')
