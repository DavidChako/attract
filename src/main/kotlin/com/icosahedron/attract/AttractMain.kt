package com.icosahedron.attract

import kotlin.random.Random

fun main() {
    val endpoint = Tetray(12, 0, 0, 0)
    val pole = Pole(endpoint)
    val originInertia = Tetray(1, 1, 1, 1)
    val endpointInertia = Tetray(1, 1, 1, 1)
    val span = Span(pole, originInertia, endpointInertia)

    var move = 0
    println("[$move] $span")

    val random = Random(0)
    move++
    println("Move $move:")
    span.move(random)
    println()
    println("[$move] $span")
}