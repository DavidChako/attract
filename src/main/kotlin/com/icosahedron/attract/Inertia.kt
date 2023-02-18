package com.icosahedron.attract

class Inertia(vararg coordinates: Long) {
    init { require(coordinates.min() >= 0) }
    private val momenta = coordinates
    val dim get() = momenta.size

    override fun toString() = "[${momenta.joinToString(",")}]"
    operator fun get(index: Int) = momenta[index]
}
