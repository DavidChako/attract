package com.icosahedron.attract.accumulate

import kotlin.random.Random

class Inertia(w: Long, x: Long, y: Long, z: Long) {
    private val weight = longArrayOf(w, x, y, z)
    var frequency = weight.sum(); private set
    private val flux = doubleArrayOf(0.0, 0.0, 0.0, 0.0)

    fun shift(delta: DoubleArray, outward: Boolean) {
        val wavelength = 1.0 / frequency

        for (direction in weight.indices) {
            val fluxDelta = if (outward) delta[direction] else -delta[direction]
            val accruedFlux = flux[direction] + fluxDelta
            val weightChange = (accruedFlux / wavelength).toLong()
            flux[direction] = accruedFlux - wavelength * weightChange
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
        return "Inertia(weight=${weight.contentToString()}, frequency=$frequency, flux=${fluxToString(flux)})"
    }

    companion object {
        val ZERO_FLUX = doubleArrayOf(0.0, 0.0, 0.0, 0.0)
        private fun fluxToString(flux: DoubleArray) = flux.joinToString(",", "[", "]") { String.format("%.6f", it) }
    }
}