package randomAlgorithms


/**
 * Fibonacci algorithm.
 *
 * Time Complexity: O(2^n)
 * @param n index of the fibonacci number
 * @return fibonacci number in the sequence
 */
fun fibonacci(n: Int): Int =
    if (n <= 1) n else fibonacci(n - 1) + fibonacci(n - 2)


/**
 * Fibonacci algorithm.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * @param n index of the fibonacci number
 * @return fibonacci number in the sequence
 */
fun fibonacci2(n: Int): Int {
    val fib = IntArray(n + 2)
    fib[0] = 0
    fib[1] = 1

    for (i in 2..n) {
        fib[i] = fib[i - 1] + fib[i - 2]
    }

    return fib[n]
}


/**
 * Fibonacci algorithm.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 * @param n index of the fibonacci number
 * @return fibonacci number in the sequence
 */
fun fibonacci3(n: Int): Int {
    val fib = IntArray(2)
    fib[0] = 0
    fib[1] = 1

    for (i in 2..n) {
        val newFib = fib[0] + fib[1]
        fib[0] = fib[1]
        fib[1] = newFib
    }

    return fib[1]
}


/**
 * Test function of the fibonacci algorithms.
 */
fun main() {
    println(fibonacci(15)) // 610
    println(fibonacci2(15)) // 610
    println(fibonacci3(15)) // 610
}
