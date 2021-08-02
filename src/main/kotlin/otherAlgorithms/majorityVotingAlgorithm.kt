package otherAlgorithms


/**
 * The Boyer-Moore majority voting algorithm is one of the popular optimal algorithms which is used to find the
 * majority element among the given elements that have more than N/2 occurrences.
 *
 * Returns the majority element or -1 if it doesn't exist.
 *
 * Time complexity: O(n)
 *
 * Space complexity: O(1)
 */
fun findingMajority(a: IntArray): Int {
    var candidate = -1
    var votes = 0

    // Finding majority candidate
    for (i in a.indices) {
        if (votes == 0) {
            candidate = a[i]
        }
        if (a[i] == candidate) votes++ else votes--
    }

    // Checking if the candidate has more than N/2 occurrences.
    var count = 0
    for (i in a.indices) {
        if (a[i] == candidate) count++
    }

    return if (count > a.size / 2) candidate else -1
}


/**
 * Test function for Boyer-Moore algorithm.
 */
fun main() {
    val a = intArrayOf(1, 1, 1, 1, 2, 1, 1, 2, 3, 4, 4, 4, 4, 3, 1)
    println(findingMajority(a)) // -1

    val b = intArrayOf(1, 1, 1, 1, 2, 1, 1, 4, 4, 3, 1)
    println(findingMajority(b)) // 1
}
