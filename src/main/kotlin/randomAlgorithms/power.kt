package randomAlgorithms


/**
 * Time Complexity = n
 */
fun power(a: Int, n: Int): Int = if (a == 1 || n == 0) 1 else a * power(a, n-1)


/**
 * Time Complexity = log₂n
 */
fun power2(a: Int, n: Int): Int{
    if (a == 1 || n == 0) return 1
    val z = power(a, n/2)
    return if (a % 2 == 0) z*z else z*z*a
}
