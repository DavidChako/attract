package com.icosahedron.attract.accumulate

import java.math.BigDecimal
import java.text.DecimalFormat

class Span(w: Long, x: Long, y: Long, z: Long) {
    private val radial = longArrayOf(w, x, y, z)
    private var tick = radial.sum() / 4L
    private var radius = 0L
    private var scale = BigDecimal.ONE

    init {
        require ( (radial.min() >= 0) && (radial.sum() % 4L == 0L) )
        radial.indices.forEach { direction -> radial[direction] -= tick }
        scale = computeScale()
    }

    fun canMove(from: Int, to: Int): Boolean = (radius > 1) || (radial[from] != 1L) || (radial[to] != -1L)

    fun move(from: Int, to: Int): Array<BigDecimal> {
        if (from == to) return Inertia.ZERO_FLUX

        val delta = Array(radial.size) { direction -> -scale * BigDecimal.valueOf(radial[direction]) }

        tick += 1
        radial[from] -= 1L
        radial[to] += 1L
        computeScale()

        delta.indices.forEach { direction -> delta[direction] += scale * BigDecimal.valueOf(radial[direction]) }
        return delta
    }

    private fun computeScale(): BigDecimal {
        radius = radial.filter { it > 0L }.sum()
        val proximity = BigDecimal(1.0 / radius)
        return proximity * proximity
    }

    override fun toString(): String {
        return "Span(radial=${radial.contentToString()}, tick=$tick, radius=$radius, scale=${formatScale(scale)})"
    }

    companion object {
        private val scaleFormat = DecimalFormat(".000000000000")
        private fun formatScale(scale: BigDecimal) = scaleFormat.format(scale)
    }
}