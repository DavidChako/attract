package com.icosahedron.attract.take

import kotlin.random.Random

fun main() {
    val verbose = true
    val tickCount = 10
    val random = Random(0)
    val radial = longArrayOf(20L, 10L, 10L, 0L)
    val fromInertia = doubleArrayOf(10.0, 10.0, 10.0, 10.0)
    val toInertia = doubleArrayOf(10.0, 10.0, 10.0, 10.0)

    val span = Span(radial, fromInertia, toInertia, true)
    println(paddedTick(0) + ">".repeat(span.radius.toInt()) + span.radius.toString())

    repeat(tickCount) { move ->
        if (verbose) {
            println("Before move $move: $span")
            println()
        }
        span.makeRandomMove(random)
        println(paddedTick(move + 1) + ">".repeat(span.radius.toInt()) + span.radius.toString())
    }

    if (verbose) {
        println()
        println("After move $tickCount: $span")
        println(">".repeat(span.radius.toInt()) + span.radius.toString())
    }

    println(paddedTick(tickCount) + ">".repeat(span.radius.toInt()) + span.radius.toString())
}

fun paddedTick(tick: Int) = tick.toString().padStart(5, '0')
