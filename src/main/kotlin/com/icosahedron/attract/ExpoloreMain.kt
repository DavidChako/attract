package com.icosahedron.attract

fun main() {
//    for (n in 1 until 5) {
//        val origin = Ray(n, n, n, n)
//        println()
//        println("Radials for origin $origin")
//
//        val endpoint = origin.copyOf()
//        val span = Span(origin, endpoint)
//
//        println("Radius is ${span.radius} for span $span")
//        while (endpoint[0] < 4*n) {
//
//        }
//
//
//    }
//    val scale = 4L
    val span = Span(Ray(10, 10, 10, 10), Ray(40, 0, 0, 0))
    println("Radius is ${span.radius} for span $span")

//    origin: [1 1 1 1] @ [10 10 10 10]
//    endpoint: [1 1 1 1] @ [40 0 0 0]

//    while (span.polarize(scale)) println("Radius is ${span.radius} for span $span")
}