package com.icosahedron.hydrox

fun main() {
    Fuel.values().forEach { fuel ->
        println("Formula for combustion of $fuel: ${fuel.formula}")
    }
}