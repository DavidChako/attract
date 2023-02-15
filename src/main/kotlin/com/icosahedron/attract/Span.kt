package com.icosahedron.attract

data class Span(val origin: Tetray, val endpoint: Tetray) {
    init { require(origin.tick == endpoint.tick) }

    private val deltas = LongArray(4) { index -> endpoint[index] - origin[index] }
    private var steps = deltas.sumOf { if (it < 0) it*3L else it }

    var radius = steps / 4L
        private set

    fun move(originIndex: Int, endpointIndex: Int) {
        origin.move(originIndex)
        endpoint.move(endpointIndex)
        if (originIndex == endpointIndex) return

        val endpointDelta = deltas[endpointIndex]
        val endpointStepChange = if (endpointDelta < 0) -3L else 1L
        deltas[endpointIndex] = endpointDelta + 1

        val originDelta = deltas[originIndex]
        val originStepChange = if (originDelta > 0) -1L else 3L
        deltas[originIndex] = deltas[originIndex] - 1

        steps += originStepChange + endpointStepChange
        radius = steps / 4L
    }

    fun moved(originIndex: Int, endpointIndex: Int): Span {
        val span = Span(Tetray(origin), Tetray(endpoint))
        span.move(originIndex, endpointIndex)
        return span
    }
}
