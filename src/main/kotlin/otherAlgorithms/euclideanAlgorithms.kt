package otherAlgorithms


/**
 * Finding the greatest common divisor of two numbers.
 *
 * Time Complexity: O(log min(a, b))
 * @param a first number
 * @param b second number
 * @return GDC
 */
fun gdc(a: Int, b: Int): Int {
    return if (a == 0) b else gdc(b % a, a)
}


data class GCDTriple(val x: Int, val y: Int, val gcd: Int)


/**
 * Extended Euclidean algorithm also finds integer coefficients x and y such that:
 * - ax + by = gcd(a, b)
 *
 * Time Complexity: O(log min(a, b))
 * @param a first number
 * @param b second number
 */
fun gcdExtended(a: Int, b: Int): GCDTriple {
    return if (a == 0) GCDTriple(0, 1, b)
    else {
        val gcdT = gcdExtended(b % a, a)
        GCDTriple(gcdT.y - (b / a) * gcdT.x, gcdT.x, gcdT.gcd)
    }
}


/**
 * Test function of Euclidean algorithms.
 */
fun main() {
    println(gdc(36, 60)) // 12

    val gcd = gcdExtended(36, 60)
    println("36 * ${gcd.x} + 60 * ${gcd.y} = GCD(36, 60) = ${gcd.gcd}")
}