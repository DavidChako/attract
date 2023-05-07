package com.icosahedron.attract.accumulate

import java.math.BigInteger

class Span(w: Long, x: Long, y: Long, z: Long) {
    private val radial = longArrayOf(w, x, y, z)
    private var tick = radial.sum() / 4L

    init {
        require ( (radial.min() >= 0) && (radial.sum() % 4L == 0L) )
        radial.indices.forEach { direction -> radial[direction] -= tick }
    }

    private var radius = computeRadius()
    private var scale = computeScale()

    fun canMove(from: Int, to: Int): Boolean = (radius > 1L) || (radial[from] != 1L) || (radial[to] != -1L)

    fun move(from: Int, to: Int): Array<Degree> {
        if (from == to) return Inertia.ZERO_FLUX

        val delta = Array(radial.size) { direction -> Degree(scale, -radial[direction]) }

        tick += 1
        radial[from] -= 1L
        radial[to] += 1L
        recomputeRadiusAndScale()

        delta.indices.forEach { direction -> delta[direction].add(Degree(scale, radial[direction])) }
        return delta
    }

    private fun computeRadius() = radial.filter { it > 0L }.sum()

    private fun computeScale(): Degree {
        val bigRadius = BigInteger.valueOf(radius)
        return Degree(BigInteger.ONE, bigRadius * bigRadius)
    }

    private fun recomputeRadiusAndScale() {
        radius = computeRadius()
        scale = computeScale()
    }

    override fun toString(): String {
        return "Span(radial=${radial.contentToString()}, tick=$tick, radius=$radius)"
    }
//
//    companion object {
//        private val scaleFormat = DecimalFormat(".000000000000")
//        private fun formatScale(scale: BigDecimal) = scaleFormat.format(scale)
//    }
}