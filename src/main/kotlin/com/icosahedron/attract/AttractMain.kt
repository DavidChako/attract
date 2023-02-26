package com.icosahedron.attract

import kotlin.random.Random

fun main() {
    val scale = 10

    val originInertia = Ray(1,1,1,1)
    val originLocation = Ray(scale, scale, scale, scale)
    val origin = Event(originInertia, originLocation)

    val endpointInertia = Ray(1,1,1,1)
    val endpointLocation = Ray(4*scale, 0, 0, 0)
    val endpoint = Event(endpointInertia, endpointLocation)

    val pole = Pole(origin, endpoint)

    println()
    println("Before moving:")
    println(pole)

    val random = Random(0)
    val duration = 10000
    val reportPeriod = 10
    var move = 0

    println()
    println("X".repeat(pole.radius.toInt()))

    repeat(duration) {
        move++
        pole.move(random)
        if (move % reportPeriod == 0) println("X".repeat(pole.radius.toInt()))
    }

    println()
    println("After moving $duration times:")
    println(pole)
}