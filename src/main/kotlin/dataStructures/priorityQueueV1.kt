package dataStructures

import randomAlgorithms.exchange
import sortAlgorithms.left
import sortAlgorithms.parent
import sortAlgorithms.right


data class User(val name: String, val userId: Int, var priority: Int)

data class PriorityQueueV1(
    val heap: Array<User?>, // min-Heap
    val positions: IntArray,
    var size: Int,
    val compare: (a: User, b:User) -> Int
)


/**
 * Add and position a new element
 * Time Complexity: O(log₂n)
 */
fun PriorityQueueV1.offer(elem: User){
    heap[size] = elem
    positions[elem.userId] = size
    decreaseKey(size)
    size++
}


/**
 * Time Complexity: O(log₂n) -> n: number of elements
 */
private fun PriorityQueueV1.decreaseKey(i: Int){
    var i = i
    while (i > 0 && compare(heap[i]!!, heap[parent(i)]!!) < 0){
        exchange(heap, i, parent(i))
        exchange(positions, heap[i]!!.userId, heap[parent(i)]!!.userId)
        i = parent(i)
    }
}


/**
 * Return the element with more priority, or null if the heap is empty
 */
fun PriorityQueueV1.peek(): User? = heap[0]


/**
 * Returns and removes the element with more priority
 */
fun PriorityQueueV1.poll(): User?{
    val elem = peek()
    if (elem != null){
        heap[0] = heap[--size]
        positions[heap[0]!!.userId] = 0
        heap[size] = null
        minHeapify(0)
    }
    return elem
}

/**
 * Time Complexity: O(log₂n)
 */
private fun PriorityQueueV1.minHeapify(i: Int){
    val left = left(i)
    val right = right(i)
    var smallest = i

    if (left < size && compare(heap[left]!!, heap[smallest]!!) < 0) smallest = left
    if (right < size && compare(heap[right]!!, heap[smallest]!!) < 0) smallest = right

    if (smallest == i) return

    exchange(heap, i, smallest)
    exchange(positions, heap[i]!!.userId, heap[smallest]!!.userId)
    minHeapify(smallest)
}


/**
 * Updates the status of a User
 */
fun PriorityQueueV1.update(newStatus: User){
    val oldStatus = heap[positions[newStatus.userId]]!!
    heap[positions[newStatus.userId]] = newStatus

    if (compare(oldStatus, newStatus) > 0){
        decreaseKey(positions[newStatus.userId])
    }
    else
        minHeapify(positions[newStatus.userId])
}
