package com.icosahedron.attract.drift

class Tetrahedron(val frequency: Int) : Iterable<Vertex> {
    private val vertexes = arrayOf<Vertex>()

    override fun iterator(): Iterator<Vertex> = vertexes.iterator()
}