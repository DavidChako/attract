package com.icosahedron.attract

import kotlin.math.log10
import kotlin.random.Random

data class Pole(val origin: Event, val endpoint: Event) {
    init { require(endpoint.conformsTo(origin)) }

    private val span = Span(origin.location, endpoint.location)
    private val dimension get() = span.dimension
    private val flipMatrix = Array(dimension) { x -> Array(dimension) { y -> span.flipped(x, y) } }
    private val weights = Array(dimension) { LongArray(dimension) }
    private var weightSum = computeWeights()

    override fun toString(): String {
        return "Pole {" +
                "\n     origin: $origin" +
                "\n   endpoint: $endpoint" +
                "\n       span: $span" +
                "\n     radius: ${span.radius}" +
                "\n    radials: ${formatMatrix(radialMatrix(), "    radials:")}" +
                "\n    weights: ${formatMatrix(weights, "    weights:")}" +
                "\n  weightSum: $weightSum" +
                "\n}"
    }

    fun move(random: Random): Long {
        if (weightSum == 0L) return 0

        val cutoff = random.nextLong(weightSum)

        var sum = 0L
        repeat(dimension) { x ->
            repeat(dimension) { y ->
                sum += weights[x][y]
                if (sum >= cutoff) {
                    move(x, y)
                    return span.radius
                }
            }
        }

        throw IllegalStateException("Should never reach this point")
    }

    private fun move(from: Int, to: Int) {
        span.flip(from, to)
        repeat(dimension) { x -> repeat(dimension) { y -> flipMatrix[x][y].flip(from, to) } }
        weightSum = computeWeights()
    }

    private fun computeWeights(): Long {
        var weightSum = 0L
        val radius = span.radius

        repeat(dimension) { x ->
            repeat(dimension) { y ->
//                val radiusFactor = when (val nextRadius = nextSpanMatrix[x][y].radius) {
//                    0L -> 0L
//                    radius -> (radius - 1) * (radius + 1)
//                    radius + 1 -> (radius - 1) * radius // centerRadius = radius - s
//                    radius - 1 -> radius * (radius + 1)
//                    else -> throw IllegalArgumentException("Next radius $nextRadius has no relation to radius $radius")
//                }
//
                val inertiaFactor = origin.inertia[x] * endpoint.inertia[y]
//                val weight = radiusFactor * inertiaFactor
                val weight = inertiaFactor
                weights[x][y] = weight
                weightSum += weight
            }
        }

        return weightSum
    }

    private fun formatMatrix(matrix: Array<LongArray>, leader: String): String {
        val max = matrix.maxOf(LongArray::max)
        val separator = "\n".padEnd(leader.length + 2, ' ')
        val width = if (max == 0L) 1 else log10(max.toDouble()).toInt() + 1
        return matrix.joinToString(separator) { x -> x.joinToString(" ") { it.toString().padStart(width, ' ') } }
    }

    private fun radialMatrix() : Array<LongArray> {
        return flipMatrix.map { x -> x.map(Span::radius).toLongArray() }.toTypedArray()
    }
}
