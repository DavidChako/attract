package com.icosahedron.attract.take

import kotlin.random.Random

class Span(
    private val radial: LongArray,
    private val from: DoubleArray,
    private val to: DoubleArray,
    private val verbose: Boolean = false
) {
    init {
        require (radial.find { it < 0 } == null)
        require (radial.sum() % 4L == 0L)
        require (from.find { it < 0.0 } == null)
        require (to.find { it < 0.0 } == null)
    }

    private val fromWeight = from.sum()
    private val toWeight = to.sum()
    var radius = computeRadius(); private set

    fun makeRandomMove(random: Random) {
        var moved = false
        while (!moved) {
            val fromStep = randomStep(random, from, fromWeight)
            val toStep = randomStep(random, to, toWeight)
            moved = makeMove(fromStep, toStep)
        }
    }

    private fun makeMove(fromStep: Int, toStep: Int): Boolean {
        if (verbose) println("Attempting to move: fromStep=$fromStep toStep=$toStep")

        val radialDelta = LongArray(4) { n ->
            when (n) {
                fromStep -> 0L
                toStep -> 2L
                else -> 1L
            }
        }

        if (verbose) println ("Radial delta is ${radialDelta.contentToString()}")
        radial.indices.forEach { n ->  radial[n] = radial[n] + radialDelta[n] }

        val nextRadius = computeRadius()
        if (nextRadius == 0L) {
            radial.indices.forEach { n ->  radial[n] = radial[n] - radialDelta[n] }
            return false
        }

        val delta = 1.0 / (radius * nextRadius)
        if (verbose) println ("Inertial delta is 1.0/($radius * $nextRadius) = $delta")
        //val delta = 1.0 / radius
        //val delta = nextRadius.toDouble() / radius
        if (from[fromStep] < delta || to[toStep] < delta) {
            radial.indices.forEach { n ->  radial[n] = radial[n] - radialDelta[n] }
            return false
        }

        radius = nextRadius
        from[fromStep] -= delta
        to[fromStep] += delta
        to[toStep] -= delta
        from[toStep] += delta

        if (verbose) println("Moved: fromStep=$fromStep toStep=$toStep")
        return true
    }

    private fun randomStep(random: Random, inertia: DoubleArray, weight: Double): Int {
        val roll = random.nextDouble(0.0, weight)
//        println("Roll: $roll")
        var sumSoFar = 0.0

        for (n in 0 until 4) {
            sumSoFar += inertia[n]
//            println("Sum so far: $sumSoFar")
            if (sumSoFar > roll) return n
        }

        throw IllegalStateException("This statement is intended to be unreachable")
    }

    private fun computeRadius(): Long {
        val min = radial.min()
        val reducedRadial = radial.map { it - min }

        val discriminant = reducedRadial.sum() / 4
        return reducedRadial.filter { it > discriminant }.sumOf { it - discriminant }
    }

    override fun toString() = "Span {" +
            "\n     radial = ${radial.contentToString()}" +
            "\n       from = ${from.contentToString()}" +
            "\n         to = ${to.contentToString()}" +
            "\n     radius = $radius" +
            "\n fromWeight = $fromWeight" +
            "\n   toWeight = $toWeight" +
            "\n}"
}