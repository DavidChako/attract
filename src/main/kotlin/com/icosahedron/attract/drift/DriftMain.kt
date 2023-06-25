package com.icosahedron.attract.drift

import java.math.BigInteger
import kotlin.math.max

fun main() {
    var priorSeparation = 0.0

    for (extent in 100 .. 1000) {
        val space = Space(extent)
        val separation = space.averageSeparation()
        val delta = separation - priorSeparation
        println("$extent,${space.count},${String.format("%.12f", separation)},${String.format("%.12f", delta)}")
        priorSeparation = separation
    }
}
//0.428571428571429
class Space(private val extent: Int) {
    private val addressList = mutableListOf<Address>()
    val count = if (extent == 0) 1 else 2*extent*extent + 2

    init {
        for (layer in 0 .. extent) {
            for (x in 0 .. layer) {
                for (y in 0 .. layer-x) {
                    val z = layer - x - y
                    addressList.add(Address(x, y, z))
                }
            }
        }
    }

    override fun toString() = "Space" +
            "\nextent = $extent" +
            "\ncount = $count" +
            "\naddresses:\n" +
            addressList.joinToString("\n")

    fun averageSeparation(): Double {
        var pairCount = 0L
        var distanceSum = BigInteger.ZERO

        for (from in addressList) {
            for (to in addressList) {
                distanceSum += from.distance(to).toBigInteger()
                pairCount++
            }
        }

        return distanceSum.toDouble() / pairCount.toDouble()
    }

    data class Address(private val x: Int, private val y: Int, private val z: Int) {
        private val layer = x + y + z

        override fun toString() = "($x, $y, $z)"

        fun distance(address: Address): Int {
            var positiveDeltaSum = 0
            var negativeDeltaSum = 0

            val xDelta = x - address.x
            if (xDelta > 0) positiveDeltaSum += xDelta else negativeDeltaSum += xDelta

            val yDelta = y - address.y
            if (yDelta > 0) positiveDeltaSum += yDelta else negativeDeltaSum += yDelta

            val zDelta = z - address.z
            if (zDelta > 0) positiveDeltaSum += zDelta else negativeDeltaSum += zDelta

            return max(positiveDeltaSum, -negativeDeltaSum)
        }
    }
}
