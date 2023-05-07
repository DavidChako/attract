package com.icosahedron.attract.accumulate

import java.math.BigDecimal
import java.math.BigInteger
import kotlin.random.Random

class Inertia(w: BigInteger, x: BigInteger, y: BigInteger, z: BigInteger) {
    private val weight = arrayOf(w, x, y, z)
    var frequency = w + x + y + z; private set
    private val flux = generateZeroFlux()

    init {
        require(weight.min() > BigInteger.ZERO)
    }

    fun shift(delta: Array<Ratio>, outward: Boolean) {
        val wavelength = Ratio(BigInteger.ONE, frequency)

        for (direction in weight.indices) {
            val fluxChange = if (outward) delta[direction] else -delta[direction]
            val accruedFlux = flux[direction] + fluxChange
            val change = accruedFlux.reduce(frequency)
            weight[direction] += change
            flux[direction] = accruedFlux
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
        return "Inertia(weight=${weight.contentToString()}, frequency=$frequency, flux=${flux.contentToString()})"
    }

    companion object {
        val ZERO_FLUX = generateZeroFlux()
        private fun generateZeroFlux() = Array(4) { Ratio.ZERO }
//        private fun fluxToString(flux: Array<BigDecimal>) = flux.joinToString(",", "[", "]") { String.format("%.6f", it) }
    }
}