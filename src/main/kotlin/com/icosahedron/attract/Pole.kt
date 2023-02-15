package com.icosahedron.attract

data class Pole(val origin: Event, val endpoint: Event) {
    private val centerSpan = Span(origin.location, endpoint.location)
    private val spanMatrix = Array(4) { x -> Array(4) { y -> centerSpan.moved(x, y) } }

    private var weightSum = 0.0
    private val weightMatrix = Array(4) { x ->
        Array(4) { y ->
            val radius = spanMatrix[x][y].radius

            if (radius == 0L) {
                0.0
            } else {
                val weight = origin.inertia[x] * endpoint.inertia[y] / radius.toDouble()
                weightSum += weight
                weight
            }
        }
    }

    fun move(originIndex: Int, endpointIndex: Int) {
        centerSpan.move(originIndex, endpointIndex)

        for (x in 0 until 4) {
            for (y in 0 until 4) {
                val span = spanMatrix[x][y]
                val priorRadius = span.radius
                span.move(x, y)

                if (x != y) {
                    val radius = span.radius
                    val priorWeight = weightMatrix[x][y]
                    weightSum -= priorWeight

                    if (radius == 0L) {
                        weightMatrix[x][y] = 0.0
                    } else if (priorRadius == 0L) {
                        val weight = origin.inertia[x] * endpoint.inertia[y] / radius.toDouble()
                    } else {
                        priorWeight * priorRadius / radius
                    }
                }
            }
        }
    }
}
