package com.icosahedron.attract

class Event(val inertia: Ray, val location: Ray) {
    init { require(inertia.dim == location.dim) }
    override fun toString() = "$inertia @ $location"
    fun conformsTo(event: Event) = location.sum == event.location.sum
}