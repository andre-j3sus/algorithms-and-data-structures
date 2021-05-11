package sortAlgorithms


/**
 * Time Complexity: Θ(n)
 *
 * Implemented by me and my colleague Nyckollas
 */
fun countingSort(a:IntArray, left:Int, right:Int): IntArray {
    val sortedA = IntArray(a.size)
    var max = a[0]
    var min = a[0]

    // Searching min and max elements
    for (i in left..right){
        if (a[i] > max) max = a[i]
        if (a[i] < min) min = a[i]
    }

    // Counting for positive and negative elements
    val counts = IntArray(max+1)
    val negativeCounts = IntArray(-min + 1)
    for (i in a.indices){
        val elem = a[i]
        if (elem >= 0) counts[elem]++
        else negativeCounts[-elem]++
    }

    for (j in negativeCounts.lastIndex - 1 downTo 0){
        negativeCounts[j] += negativeCounts[j+1]
    }

    counts[0] += negativeCounts[0]
    for (j in 1..counts.lastIndex){
        counts[j] += counts[j-1]
    }

    // Building sorted array
    for (j in right downTo left ){
        val elem = a[j]
        if (elem >= 0) {
            sortedA[counts[elem] - 1] = elem
            counts[elem]--
        }
        else {
            sortedA[negativeCounts[-elem] - 1] = elem
            negativeCounts[-elem]--
        }
    }
    return sortedA
}


fun main() {
    val a = intArrayOf(-20, -2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    val b = countingSort(a, 0, a.lastIndex)
    println(b.asList())
}
