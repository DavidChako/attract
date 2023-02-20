package com.icosahedron.attract

class Event(val inertia: Tetray, val location: Tetray) {
    override fun toString() = "$inertia @ $location"
    fun conformsTo(event: Event) = location.sum == event.location.sum
}