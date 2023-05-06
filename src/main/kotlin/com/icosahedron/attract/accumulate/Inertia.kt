package com.icosahedron.attract.accumulate

import java.math.BigDecimal
import kotlin.random.Random

class Inertia(w: Long, x: Long, y: Long, z: Long) {
    private val weight = longArrayOf(w, x, y, z)
    var frequency = weight.sum(); private set
    private val flux = generateZeroFlux()

    init {
        require(weight.min() > 0)
    }

    fun shift(delta: Array<BigDecimal>, outward: Boolean) {
        for (direction in weight.indices) {
            val fluxChange = if (outward) delta[direction] else -delta[direction]
            val accruedFlux = flux[direction] + fluxChange
            val change = (BigDecimal.valueOf(frequency) * accruedFlux).toLong()

            weight[direction] += change
            flux[direction] = accruedFlux - change.toBigDecimal()
            frequency += change
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
        return "Inertia(weight=${weight.contentToString()}, frequency=$frequency, flux=${fluxToString((flux))})"
    }

    companion object {
        val ZERO_FLUX = generateZeroFlux()
        private fun generateZeroFlux() = Array<BigDecimal>(4) { BigDecimal.ZERO }
        private fun fluxToString(flux: Array<BigDecimal>) = flux.joinToString(",", "[", "]") { String.format("%.6f", it) }
    }
}