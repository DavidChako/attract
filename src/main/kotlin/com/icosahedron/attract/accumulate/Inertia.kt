package com.icosahedron.attract.accumulate

import java.math.BigInteger
import kotlin.random.Random

class Inertia(w: Long, x: Long, y: Long, z: Long) {
    private val weight = longArrayOf(w, x, y, z)
    var frequency = weight.sum(); private set
    private val flux = generateZeroFlux()

    fun shift(delta: Array<Degree>, outward: Boolean) {
        for (direction in weight.indices) {
            val addend = if (outward) delta[direction] else -delta[direction]
            flux[direction].add(addend)
            val weightChange = flux[direction].reduce(frequency)
            weight[direction] += weightChange
            frequency += weightChange
        }
    }

    fun pickDirection(random: Random): Int {
        val discriminant = random.nextLong(0, frequency)
        var netWeight = 0L

        for (direction in weight.indices) {
            netWeight += weight[direction]
            if (netWeight > discriminant) return direction
        }

        throw IllegalStateException("Net weight $netWeight is less than frequency $frequency")
    }

    override fun toString(): String {
        return "Inertia(weight=${weight.contentToString()}, frequency=$frequency, flux=${flux.contentToString()})"
    }

    companion object {
        val ZERO_FLUX = generateZeroFlux()
        private fun generateZeroFlux() = Array(4) { Degree(BigInteger.ZERO, BigInteger.ONE) }
    }
}