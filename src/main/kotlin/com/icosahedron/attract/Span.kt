package com.icosahedron.attract

class Span(private val ray: Ray) {
    constructor(origin: Ray, endpoint: Ray): this(endpoint - origin)

    val dimension get() = ray.dim
    val radius get() = ray.rad

    override fun toString() = ray.toString()

    fun flip(from: Int, to: Int) {
        if (from == to) return
        ray.decrement(from)
        ray.increment(to)
    }

    fun flipped(from: Int, to: Int): Span {
        val span = Span(ray.copyOf())
        span.flip(from, to)
        return span
    }
}