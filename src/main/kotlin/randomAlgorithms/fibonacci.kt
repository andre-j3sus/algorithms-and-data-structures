package randomAlgorithms


/**
 * Time Complexity: O(2^n)
 */
fun fibonacci(n:Int):Int = if (n <= 1) n else fibonacci(n-1) + fibonacci(n-2)


/**
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
fun fibonacci2(n:Int):Int{
    val fib = IntArray(n+2)
    fib[0] = 0
    fib[1] = 1

    for (i in 2..n){
        fib[i] = fib[i-1] + fib[i-2]
    }

    return fib[n]
}

/**
 * Time Complexity: O(n)
 * Space Complexity: O(2) = O(1) -> Constant
 */
fun fibonacci3(n:Int):Int{
    val fib = IntArray(2)
    fib[0] = 0
    fib[1] = 1

    for (i in 2..n){
        val newFib = fib[0] + fib[1]
        fib[0] = fib[1]
        fib[1] = newFib
    }

    return fib[1]
}


fun main() {
    println(fibonacci(15))
    println(fibonacci2(15))
    println(fibonacci3(15))
}
