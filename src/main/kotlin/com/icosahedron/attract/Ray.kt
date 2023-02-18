package com.icosahedron.attract

class Ray(private val array: LongArray) {
    constructor(list: List<Long>): this(list.toLongArray())
    constructor(vararg coordinates: Int): this(coordinates.map(Int::toLong))

    init {
        val min = array.min()
        if (min < 0) array.indices.forEach { array[it] = array[it] - min }
    }

    val dim get() = array.size
    var sum = array.sum(); private set
    val radius get() = sum / 4L

    override fun toString() = array.joinToString(" ", "[", "]")
    operator fun get(n: Int) = array[n]
    operator fun minus(ray: Ray) = Ray(array.indices.map { array[it] - ray.array[it] })

    fun move(x: Int, y: Int) {
        if (x == y) return

        array[x] = array[x] - 1L
        array[y] = array[y] + 1L

        if (array[x] == -1L) {
            array.indices.forEach { array[it] = array[it] + 1 }
            sum += array.size
        }
    }

    fun moved(x: Int, y: Int): Ray {
        val tetray = Ray(array.copyOf())
        tetray.move(x, y)
        return tetray
    }
}