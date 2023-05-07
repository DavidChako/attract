package com.icosahedron.attract.accumulate

import java.math.BigInteger

class Ratio(private var numerator: BigInteger, private var denominator: BigInteger) {
    init { require(numerator >= BigInteger.ZERO && denominator > BigInteger.ZERO) }

    fun reduce(frequency: BigInteger): BigInteger {
        val reduction = numerator / denominator
        numerator -= reduction * denominator
        return reduction
    }

    operator fun times(ratio: Ratio) = Ratio(numerator*ratio.numerator, denominator*ratio.denominator)
    operator fun times(scalar: Long) = Ratio(numerator*BigInteger.valueOf(scalar), denominator)
    operator fun div(ratio: Ratio) = Ratio(numerator*ratio.denominator, denominator*ratio.numerator)
    operator fun unaryMinus() = Ratio(-numerator, denominator)

    operator fun plus(ratio: Ratio): Ratio {
        val sumNumerator = numerator*ratio.denominator + denominator*ratio.numerator
        val sumDenominator = denominator*ratio.denominator
        return Ratio(sumNumerator, sumDenominator)
    }

    operator fun minus(ratio: Ratio): Ratio {
        val diffNumerator = numerator*ratio.denominator - denominator*ratio.numerator
        val diffDenominator = denominator*ratio.denominator
        return Ratio(diffNumerator, diffDenominator)
    }

    override fun toString(): String {
        return "($numerator:$denominator)"
    }

    companion object {
        val ZERO = Ratio(BigInteger.ZERO, BigInteger.ONE)
        val ONE = Ratio(BigInteger.ONE, BigInteger.ONE)
    }
}