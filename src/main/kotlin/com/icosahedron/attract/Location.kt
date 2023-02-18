package com.icosahedron.attract

class Location(vararg coordinates: Long) {
    init { require(coordinates.min() >= 0) }
    private val array = coordinates
    val dim get() = array.size
    val sum get() = array.sum()

    override fun toString() = "[${array.joinToString(",")}]"
    operator fun get(index: Int) = array[index]
    fun move(index: Int) { array[index] = array[index] + 1 }
    fun conformsTo(location: Location) = dim == location.dim && sum == location.sum
    fun copyOf() = Location(*array)
}
