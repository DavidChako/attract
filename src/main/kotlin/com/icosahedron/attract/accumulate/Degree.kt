package com.icosahedron.attract.accumulate

import java.math.BigInteger

class Degree(private var numerator: BigInteger, private var denominator: BigInteger) {
    constructor(base: Degree, scale: Long): this(BigInteger.valueOf(scale) * base.numerator, base.denominator)
    init { require(denominator > BigInteger.ZERO) }

    fun add(other: Degree) {
        numerator = numerator * other.denominator + denominator * other.numerator
        denominator *= other.denominator
    }

    fun reduce(frequency: Long): Long {
        val bigFrequency = BigInteger.valueOf(frequency)
        val multiple = bigFrequency * numerator / denominator
        if (multiple == BigInteger.ZERO) return 0L
        numerator = bigFrequency * numerator - multiple * denominator
        denominator = bigFrequency * denominator
        return multiple.toLong()
    }



//    operator fun times(scale: Long): Degree = Degree(numerator*other.numerator, denominator*other.denominator)
//    operator fun div(ratio: Ratio): Ratio = Ratio(numerator*ratio.denominator, denominator*ratio.numerator)
    operator fun unaryMinus(): Degree = Degree(-numerator, denominator)

    operator fun plus(other: Degree): Degree {
        val sumNumerator = numerator*other.denominator + denominator*other.numerator
        val sumDenominator = denominator*other.denominator
        return Degree(sumNumerator, sumDenominator)
    }
//
//    operator fun minus(ratio: Ratio): Ratio {
//        val diffNumerator = numerator*ratio.denominator - denominator*ratio.numerator
//        val diffDenominator = denominator*ratio.denominator
//        return Ratio(diffNumerator, diffDenominator)
//    }
//
//    override fun compareTo(ratio: Ratio): Int {
//        val thisScaled = numerator * ratio.denominator
//        val ratioScaled = denominator * ratio.numerator
//        return if (thisScaled < ratioScaled) -1 else if (thisScaled > ratioScaled) 1 else 0
//    }

    override fun toString(): String {
        return "($numerator:$denominator)"
    }
}