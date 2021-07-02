package randomAlgorithms


/**
 * Power -> [a] to the power of [n]
 *
 * Time Complexity = O(n)
 * @param a number
 * @param n exponent
 * @return power
 */
fun power(a: Int, n: Int): Int =
    if (a == 1 || n == 0) 1 else a * power(a, n-1)


/**
 * Power -> [a] to the power of [n]
 *
 * Time Complexity = O(log₂n)
 * @param a number
 * @param n exponent
 * @return power
 */
fun power2(a: Int, n: Int): Int{
    if (a == 1 || n == 0) return 1
    val z = power(a, n/2)
    return if (a % 2 == 0) z*z else z*z*a
}
