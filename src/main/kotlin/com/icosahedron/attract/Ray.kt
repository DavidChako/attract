package com.icosahedron.attract

class Ray(private val array: LongArray) {
    constructor(list: List<Long>): this(list.toLongArray())
    constructor(vararg coordinates: Int): this(coordinates.map(Int::toLong))
    init { rectify() }

    val dim get() = array.size
    var sum = array.sum(); private set
    val radius get() = sum

    override fun toString() = array.joinToString(" ", "[", "]")
    operator fun get(n: Int) = array[n]
    operator fun minus(ray: Ray) = Ray(array.indices.map { array[it] - ray.array[it] })

    fun flip(from: Int, to: Int) {
        if (from == to) return
        array[from] = array[from] - 1
        array[to] = array[to] + 1
        rectify()
    }

    fun flipped(x: Int, y: Int): Ray {
        val tetray = Ray(array.copyOf())
        tetray.flip(x, y)
        return tetray
    }

    private fun rectify() {
        val min = array.min()
        array.indices.forEach { array[it] = array[it] - min }
    }
}