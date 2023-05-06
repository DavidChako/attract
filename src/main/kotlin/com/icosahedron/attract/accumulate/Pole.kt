package com.icosahedron.attract.accumulate

class Pole(private val a: Inertia, private val b: Inertia, private val span: Span) {
    init {
        require ( a.frequency == b.frequency ) // just for now
    }

    fun canMove(from: Int, to: Int): Boolean = span.canMove(from, to)

    fun move(from: Int, to: Int) {
        if (from == to) return
        val delta = span.move(from, to)
        a.shift(delta, true)
        b.shift(delta, false)
    }

    override fun toString(): String {
        return "Pole(" +
                "\n\ta: $a" +
                "\n\tb: $b" +
                "\n\tspan: $span" +
                "\n)"
    }
}