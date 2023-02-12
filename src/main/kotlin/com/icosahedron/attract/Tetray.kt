package com.icosahedron.attract

class Tetray(w: Long, x: Long, y: Long, z: Long) {
    private val array = longArrayOf(w, x, y, z)

    constructor(w: Int, x: Int, y: Int, z: Int): this(w.toLong(), x.toLong(), y.toLong(), z.toLong())
    fun copy() = Tetray(array[0], array[1], array[2], array[3])

    operator fun get(at: Int) = array[at]
    operator fun set(at: Int, value: Long) { array[at] = value }
}
