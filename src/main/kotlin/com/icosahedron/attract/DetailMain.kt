package com.icosahedron.attract

import kotlin.random.Random

fun main() {
    val originInertia = Ray(2,2,2,2)
    val originLocation = Ray(1,1,1,1)
    val origin = Event(originInertia, originLocation)

    val endpointInertia = Ray(2,2,2,2)

    val endpointLocations = listOf(
        Ray(1,1,1,1),
        Ray(2,1,1,0),
        Ray(2,2,0,0),
        Ray(3,1,0,0),
        Ray(4,0,0,0)
    )

    endpointLocations.forEach { endpointLocation ->
        val endpoint = Event(endpointInertia, endpointLocation)
        val pole = Pole(origin, endpoint)
        println()
        println("Pole for endpoint $endpoint")
        println(pole)
    }
}