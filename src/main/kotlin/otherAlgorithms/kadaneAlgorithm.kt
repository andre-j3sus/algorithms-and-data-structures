package otherAlgorithms


data class Triple(val left: Int, val sum: Int, val r: Int)

/**
 * Returns the maximum sum contiguous subarray using Kadane's Algorithm.
 *
 * Time Complexity: O(n)
 * @param a array
 * @param left left index
 * @param right right index
 * @return Triple with left index, right index and sum of the elements in this range.
 */
fun subArrayMax(a: IntArray, left: Int, right: Int): Triple {
    var l = left
    var r = left - 1
    var actualL = left

    var bestSum = 0
    var currentSum = 0

    for (i in left..right) {
        currentSum += a[i]
        if (currentSum > bestSum) {
            bestSum = currentSum
            r = i
            l = actualL
        } else if (currentSum < 0) {
            actualL = if (i == right) i else i + 1
            currentSum = 0
        }

    }
    return Triple(l, bestSum, r)
}
