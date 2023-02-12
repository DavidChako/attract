package com.icosahedron.attract

class Pole(private val pole: Tetray) {
    var radius = computeRadius(0)

    override fun toString() = "[${pole[0]} ${pole[1]} ${pole[2]} ${pole[3]}]"

    fun move(x: Int, y: Int, tick: Long) {
        pole[x]--
        pole[y]++
        radius = computeRadius(tick)
    }

    fun moved(x: Int, y: Int): Pole {
        val pole = Pole(pole.copy())
        pole.move(x,y,0)
        return pole
    }

    private fun computeRadius(tick: Long): Long {
        var sum = -4L * tick
        var min = Long.MAX_VALUE

        for (n in 0 until 4) {
            val delta = pole[n]
            sum += delta
            if (delta < min) min = delta
        }

        return sum/4 - min
    }
}
