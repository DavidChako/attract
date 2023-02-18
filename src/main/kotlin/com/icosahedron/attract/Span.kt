package com.icosahedron.attract

data class Span(val from: Location, val to: Location) {
    init { require(from.conformsTo(to)) }
    var radius = computeRadius(); private set

    override fun toString() = "$radius from $from to $to"

    fun move(fromIndex: Int, toIndex: Int) {
        from.move(fromIndex)
        to.move(toIndex)
        radius = computeRadius()
    }

    fun moved(fromIndex: Int, toIndex: Int): Span {
        val span = Span(from.copyOf(), to.copyOf())
        span.move(fromIndex, toIndex)
        return span
    }

    private fun computeRadius(): Long {
        var deltaSum = 0L
        var minDelta = Long.MAX_VALUE

        repeat(from.dim) { index ->
            val delta = to[index] - from[index]
            deltaSum += delta
            if (delta < minDelta) minDelta = delta
        }

        return (deltaSum - 4L*minDelta) / 4L
    }
}
