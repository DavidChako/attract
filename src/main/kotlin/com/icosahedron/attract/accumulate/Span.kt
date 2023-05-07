package com.icosahedron.attract.accumulate

class Span(w: Long, x: Long, y: Long, z: Long) {
    private val radial = longArrayOf(w, x, y, z)
    private var tick = radial.sum() / 4L

    init {
        require ( (radial.min() >= 0) && (radial.sum() % 4L == 0L) )
        radial.indices.forEach { direction -> radial[direction] -= tick }
    }

    private var radius = radial.filter { it > 0L }.sum()
    //private var scale = 1.0 / (radius * radius)
    private var scale = 1.0 / radius

    fun canMove(from: Int, to: Int): Boolean = (radius > 1L) || (radial[from] != 1L) || (radial[to] != -1L)

    fun move(from: Int, to: Int): DoubleArray {
        if (from == to) return Inertia.ZERO_FLUX

        val delta = DoubleArray(radial.size) { direction -> -scale * radial[direction] }

        tick += 1
        radial[from] -= 1L
        radial[to] += 1L
        radius = radial.filter { it > 0L }.sum()
        scale = 1.0 / (radius * radius)

        delta.indices.forEach { direction -> delta[direction] += scale * radial[direction] }
        return delta
    }

    override fun toString(): String {
        return "Span(radial=${radial.contentToString()}, tick=$tick, radius=$radius)"
    }
}