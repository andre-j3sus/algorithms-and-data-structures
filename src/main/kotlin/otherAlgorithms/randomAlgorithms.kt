package otherAlgorithms

import dataStructures.buildMinHeap
import dataStructures.minHeapify
import kotlin.math.abs


/**
 * This file contains some algorithms that I implemented to study
 * for a college exam of "Algorithms and Data Structures" class.
 *
 * They all use arrays and some basic algorithms like partition, sliding window or heap algorithms.
 */


fun countIncreasingSubArrays(v: IntArray): Int {
    var count = 0
    var size = 1
    for (i in 1 until v.size) {
        if (v[i] > v[i - 1]) count += size++
        else size = 1
    }
    return count
}


fun max(a: IntArray, l: Int, r: Int): Int {
    var l = l
    var r = r
    var max = a[l]
    while (l <= r) {
        val mid = l + (r - l) / 2
        if (a[mid] > max) max = a[mid]

        if (a[mid] > a[mid + 1]) r = mid - 1
        else if (a[mid] < a[mid + 1]) l = mid + 1
        else {
            var mostL = mid
            var mostR = mid
            while (mostL > l && a[mostL - 1] == a[mid]) mostL--
            while (mostR < r && a[mostR + 1] == a[mid]) mostR++


            if (mostL > l && a[mostL - 1] < a[mid]) l = mostR + 1
            else r = mostL - 1
        }
    }
    return max
}


fun countDistinct(v: IntArray): Int {
    if (v.isEmpty()) return 0
    var count = v.size
    var l = 0
    var r = v.lastIndex

    while (l < r) {
        while (l < r && v[l + 1] == v[l]) l++; count--
        while (r > l && v[r - 1] == v[r]) r--; count--

        if (l == r) break

        when {
            abs(v[l]) == abs(v[r]) -> {
                count--
                l++
                r--
            }
            abs(v[l]) > abs(v[r]) -> l++
            else -> r--
        }
    }
    return count
}


data class Point(val x: Int, val y: Int)

fun countEquals(points1: Array<Point>, points2: Array<Point>, cmp: Comparator<Point>): Int {
    var count = 0
    var i = 0
    var j = 0
    if (points1.isNotEmpty() && points2.isNotEmpty()) {
        while (i < points1.size && j < points2.size) {
            val comp = cmp.compare(points1[i], points2[j])
            when {
                comp == 0 -> {
                    count++
                    i++
                    j++
                }
                comp > 0 -> j++
                else -> i++
            }
        }
    }
    return count
}


fun find(points1: Array<Point>, points2: Array<Point>): Array<Point>? {
    if (points1.isEmpty() || points2.isEmpty()) return null
    val p = arrayOf(points1[0], points2[0])
    var minDif = abs(points1[0].x - points2[0].x)
    var i = 0
    var j = 0
    while (i < points1.size && j < points2.size) {
        val dif = abs(points1[i].x - points2[j].x)
        if (dif < minDif) {
            p[0] = points1[i]
            p[1] = points2[j]
            minDif = dif
        }

        if (dif == 0) return p

        if (points1[i].x > points2[j].x) j++ else i++
    }
    return p
}


data class SubArrayPair(var first: Int, var second: Int)

fun minSubArrayWithSum(v: IntArray, l: Int, r: Int, s: Int): SubArrayPair {
    var left = l
    var right = l

    var bestSize = v.size
    val bestPair = SubArrayPair(left, right)

    var size = 1
    var currentSum = v[l]

    if (currentSum >= s) return bestPair

    while (right < r) {
        currentSum += v[++right]
        size++

        if (currentSum >= s) {
            while (currentSum - v[left] >= s) {
                size--
                currentSum -= v[left++]
            }
            if (size < bestSize) {
                bestPair.first = left
                bestPair.second = right
                bestSize = size
                left = right
                size = 1
                currentSum = v[left]
            }
            if (bestSize == 1) return bestPair
        }
    }

    return bestPair
}


fun kSmallest(v: IntArray, k: Int): Int {
    if (v.isEmpty() || k > v.size) return -1
    var size = v.size
    buildMinHeap(v)
    for (i in 0 until k - 1) {
        exchange(v, 0, --size)
        minHeapify(v, 0, size)
    }
    return v[0]
}


/**
 * Returns the k smallest element.
 *
 * Time complexity: O(n)
 */
fun quickSelect(v: IntArray, k: Int, left: Int, right: Int): Int {
    if (left == right) return v[left]
    val partition = sortAlgorithms.partition(v, left, right)
    return when {
        partition == k - 1 -> v[partition]
        partition > k - 1 -> quickSelect(v, k, left, partition - 1)
        else -> quickSelect(v, k, partition + 1, right)
    }
}


fun undoRotate(v: IntArray, l: Int, r: Int) {
    val oldV = v.copyOf()
    var shift = 0
    while (v[shift + 1] > v[shift++]);
    for (i in l..r) {
        v[i] = oldV[if (i + shift > r) i + shift - v.size else i + shift]
    }
}


fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = HashMap<Int, Int>(nums.size)

    for (i in nums.indices) {
        val complement = target - nums[i]
        if (complement in map) return intArrayOf(i, map[complement]!!)
        map[nums[i]] = i
    }
    return intArrayOf(-1)
}
