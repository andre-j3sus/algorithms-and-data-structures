package randomAlgorithms

data class Triple(val left:Int, val sum:Int, val r:Int)


/**
 * Time Complexity: O(n)
 *
 * Sliding Window Algorithm - Kadane
 */
fun subArrayMax(a: IntArray, left: Int, right: Int): Triple{
    var l = left
    var actualL = left
    var r = left-1
    var bestSum = 0
    var actualSum:Int = 0

    for (i in left..right){
        actualSum += a[i]
        if (actualSum > bestSum){
            bestSum = actualSum
            r = i
            l = actualL
        }
        else if(actualSum < 0){
            actualL = if(i == right) i else i + 1
            actualSum = 0
        }

    }
    return Triple(l, bestSum, r)
}
