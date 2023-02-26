package com.icosahedron.attract

class Span(private val origin: Ray, private val endpoint: Ray) {
    private val ray = endpoint - origin
    val dimension get() = origin.dim
    val radius get() = ray.rad

    override fun toString() = ray.toString()

    fun polarize(scale: Long): Boolean {
        val maxRadius = scale - 1L
        if (ray[dimension-1] == maxRadius) return false
        val atMin = -maxRadius/3L

        for (from in 0 until dimension) {
            if (ray[from] == atMin) continue

            for (to in from+1 until dimension) {
                if (ray[to] == atMin) continue
                move(from, to)
                return true
            }
        }

        return false
    }

    fun move(from: Int, to: Int) {
        if (from == to) return
        ray.decrement(from)
        ray.increment(to)
        origin.increment(from)
        endpoint.increment(to)
    }

    fun moved(from: Int, to: Int): Span {
        val span = Span(origin.copyOf(), endpoint.copyOf())
        span.move(from, to)
        return span
    }
}