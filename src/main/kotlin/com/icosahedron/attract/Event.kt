package com.icosahedron.attract

class Event(val inertia: Inertia, val location: Location) {
    init { require(location.dim == inertia.dim) }
    val dim get() = location.dim

    override fun toString() = "$inertia @ $location"
    fun conformsTo(event: Event) = location.conformsTo(event.location)
}