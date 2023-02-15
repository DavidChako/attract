package com.icosahedron.attract

import kotlin.random.Random

class Spanner(private val pole: Pole, originInertia: Tetray, endpointInertia: Tetray) {
    private val adjacentPoleMatrix = Array(4) { x -> Array(4) { y -> pole.moved(x,y) } }
    private val radiusMatrix = Array(4) { x -> LongArray(4) { y -> adjacentPoleMatrix[x][y].radius } }
    private val inertiaMatrix = Array(4) { x -> DoubleArray(4) { y -> (originInertia[x] * endpointInertia[y]).toDouble() } }
    private var distributionSum = (0 until 4).flatMap { x -> (0 until 4).map { y -> weight(x,y) } }.sum()
    private var tick = 0L

    override fun toString() = "Span {" +
            "\n\tPole: $pole" +
            "\n\n\tAdjacent:\n\t${adjacentPoleMatrix.joinToString("\n\t"){ it.contentToString()}}" +
            "\n\n\tRadii:\n\t${radiusMatrix.joinToString("\n\t"){ it.contentToString()}}" +
            "\n\n\tInertia:\n\t${inertiaMatrix.joinToString("\n\t"){it.contentToString()}}" +
            "\n\n\tDistribution sum: $distributionSum" +
            "\n}"

    fun evolve(duration: Int, random: Random = Random(0)) = LongArray(duration) { move(random) }

    fun move(random: Random): Long {
        tick++
        val discriminant = random.nextDouble(distributionSum)
        var weightSum = 0.0

        for (x in 0 until 4) {
            for (y in 0 until 4) {
                weightSum += weight(x,y)
                println("[$x][$y] weight sum is $weightSum")

                if (weightSum >= discriminant) {
                    println("Moving $x $y because weight sum=$weightSum and discriminant=$discriminant")
                    return adjust(x, y)
                }
            }
        }

        return pole.radius
    }

    private fun weight(x: Int, y: Int) = weight(inertiaMatrix[x][y], radiusMatrix[x][y])
    private fun weight(inertia: Double, radius: Long) = if (radius == 0L) 0.0 else inertia/radius.toDouble()

    private fun adjust(x: Int, y: Int): Long {
        if (x == y) return pole.radius

        for (j in 0 until 4) {
            for (k in 0 until 4) {
                val adjacentPole = adjacentPoleMatrix[j][k]
                adjacentPole.move(x,y)

                val radius = adjacentPole.radius
                val priorRadius = radiusMatrix[j][k]
                if (radius != priorRadius) {
                    val inertia = inertiaMatrix[j][k]
                    val priorWeight = weight(inertia, priorRadius)
                    val weight = weight(inertia, radius)
                    distributionSum += (weight - priorWeight)
                    radiusMatrix[j][k] = radius
                }
            }
        }

        pole.move(x, y, tick)
        return pole.radius
    }
}
