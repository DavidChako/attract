package com.icosahedron.attract.drift

class AddressSpace(private val megabyteLimit: Int) {
    private val addressCount = 1_000_000 * megabyteLimit / (4 * Long.SIZE_BYTES)
    //private val
    override fun toString(): String {
        return "AddressSpace(megabyteLimit=$megabyteLimit, addressCount=$addressCount)"
    }
}