package com.icosahedron.hydrox

enum class Compound(val commonName: String, val gramsPerMole: Double) {
    H2("HYDROGEN", 2.016),
    O2("OXYGEN", 32.0),
    H2O("WATER", 18.01528),
    CH4("METHANE", 16.04),
    CO2("CARBON_DIOXIDE", 44.01);

    fun symbol(ratio: Double = 1.0): String = if (ratio == 1.0) name else "$ratio*$name"
}