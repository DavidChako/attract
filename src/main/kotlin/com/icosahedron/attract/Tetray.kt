package com.icosahedron.attract

class Tetray(private var w: Long, private var x: Long, private var y: Long, private var z: Long) {
    constructor(w: Int, x: Int, y: Int, z: Int): this(w.toLong(), x.toLong(), y.toLong(), z.toLong())

    val sum get() = w + x + y + z
    val radius get() = sum - 4*minOf(w, x, y, z)

    override fun toString() = "[$w $x $y $z]"

    operator fun get(n: Int) = when (n) {
        0 -> w
        1 -> x
        2 -> y
        3 -> z
        else -> throw IndexOutOfBoundsException("$n not in 0..3")
    }

    operator fun minus(tetray: Tetray) = Tetray(w-tetray.w, x-tetray.x, y-tetray.y, z-tetray.z)

    fun flip(from: Int, to: Int) {
        if (from == to) return
        add(from, -1L)
        add(to, 1L)
    }

    fun flipped(from: Int, to: Int): Tetray {
        val tetray = Tetray(w, x, y, z)
        tetray.flip(from, to)
        return tetray
    }

    private fun add(n: Int, amount: Long) = when (n) {
        0 -> w += amount
        1 -> x += amount
        2 -> y += amount
        3 -> z += amount
        else -> throw IndexOutOfBoundsException("$n not in 0..3")
    }
}