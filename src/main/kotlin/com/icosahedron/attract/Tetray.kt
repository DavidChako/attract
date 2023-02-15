package com.icosahedron.attract

data class Tetray(private var w: Long, private var x: Long, private var y: Long, private var z: Long) {
    constructor(w: Int, x: Int, y: Int, z: Int): this(w.toLong(), x.toLong(), y.toLong(), z.toLong())
    constructor(tetray: Tetray): this(tetray.w, tetray.x, tetray.y, tetray.z)

    private val array = longArrayOf(w, x, y, z)

    var tick = w + x + y + z
        private set

    operator fun get(index: Int) = array[index]

    fun move(index: Int) {
        array[index] = array[index] + 1
        tick += 1
    }
}
