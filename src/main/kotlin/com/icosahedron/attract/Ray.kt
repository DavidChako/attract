package com.icosahedron.attract

class Ray(private val array: LongArray) {
    constructor(list: List<Long>): this(list.toLongArray())
    constructor(vararg coordinates: Int): this(coordinates.map(Int::toLong))

    val dim get() = array.size
    val sum get() = array.sum()
    var rad = array.filter{ it > 0L }.sum(); private set

    override fun toString() = array.joinToString(" ", "[", "]")
    operator fun get(n: Int) = array[n]
    operator fun minus(ray: Ray) = Ray(array.indices.map { array[it] - ray.array[it] })

    fun increment(n: Int) {
        array[n] = array[n] + 1
        if (array[n] > 0) rad += 1
    }

    fun decrement(n: Int) {
        if (array[n] > 0) rad -= 1
        array[n] = array[n] - 1
    }

    fun copyOf() = Ray(array.copyOf())
}