package com.icosahedron.attract

import kotlin.random.Random

fun main() {
    val originInertia = Tetray(2,2,2,2)
    val originLocation = Tetray(1,1,1,1)
    val origin = Event(originInertia, originLocation)

    val endpointInertia = Tetray(2,2,2,2)
    val endpointLocation = Tetray(2,1,1,0)
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