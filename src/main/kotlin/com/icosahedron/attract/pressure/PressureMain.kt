package com.icosahedron.attract.pressure


fun main() {
    val location = intArrayOf(0, 0, 0)
    val momentum = doubleArrayOf(1.0, 1.0, 1.0)

    val maximumRadius = 100
    val pressureDelta = 1.0

    val pressure = Array(3) { DoubleArray(maximumRadius) { 1.0 } }

    println("Pressure at $location is ")
}
