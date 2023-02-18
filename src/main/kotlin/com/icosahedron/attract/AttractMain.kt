package com.icosahedron.attract

import kotlin.random.Random

fun main() {
    val originInertia = Inertia(1,1,1,1)
    val originLocation = Location(1,1,1,1)
    val origin = Event(originInertia, originLocation)

    val endpointInertia = Inertia(1,1,1,1)
    val endpointLocation = Location(2,1,1,0)
    val endpoint = Event(endpointInertia, endpointLocation)

    val pole = Pole(origin, endpoint)

    println()
    println("Before moving:")
    println(pole)

//    val random = Random(0)
//    val duration = 10
//    var move = 0
//
//    println()
//    println("X".repeat(pole.radius.toInt()))
//
//    repeat(duration) {
//        move++
////        println()
////        println("Move $move:")
//        pole.move(random)
//        println("X".repeat(pole.radius.toInt()))
////        println(pole)
//    }
//
//    println()
//    println("After moving $duration times:")
//    println(pole)
}