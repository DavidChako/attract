package com.icosahedron.hydrox

import com.icosahedron.hydrox.Compound.*

enum class Fuel(
    val compound: Compound,
    val ratioO2: Double,
    val ratioH2O: Double,
    val ratioCO2: Double,
    val kilojoulesPerMole: Double
) {
    HYDROGEN(H2,0.5, 1.0, 0.0, 286.0),
    METHANE(CH4, 2.0, 2.0, 1.0, 890.0);

    val formula: String = "$compound + ${O2.symbol(ratioO2)} --> ${H2O.symbol(ratioH2O)}" +
            if (ratioCO2 == 0.0) "" else " + ${CO2.symbol(ratioCO2)}"
}