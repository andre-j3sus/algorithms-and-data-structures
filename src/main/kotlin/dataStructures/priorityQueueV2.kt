package dataStructures

interface KeyExtractor<E> {
    fun getKey(e: E): Int
}

private interface PriorityQueue<E, P> {
    fun add(elem: E, prio: P)
    fun peek(): E?
    fun poll(): E?
    fun update(key: Int, prio: P)
    fun remove(key: Int)
    fun empty(): Boolean
}

class PriorityQueueV2<E, P>(maxCapacity: Int, private val cmp: Comparator<P>, private val keyExtractor: KeyExtractor<E>) :
    PriorityQueue<E, P> {

    data class User<E, P>(val elem: E, val key: Int, var prio: P)

    private val heap = Array<User<E, P>?>(maxCapacity) { null }
    var size = 0
    private var positions = HashMap<Int, Int>(maxCapacity)


    operator fun P.compareTo(p: P): Int = cmp.compare(this, p)

    private fun left(i: Int) = i * 2 + 1
    private fun right(i: Int) = i * 2 + 2
    private fun parent(i: Int) = (i - 1) / 2


    private fun <E, P> exchange(a: Array<User<E, P>?>, idx1: Int, idx2: Int) {
        val temp = a[idx1]
        a[idx1] = a[idx2]
        a[idx2] = temp
    }


    private fun exchange(a: HashMap<Int, Int>, idx1: Int, idx2: Int) {
        val temp = a[idx1]
        a[idx1] = a[idx2]!!
        a[idx2] = temp!!
    }


    override fun add(elem: E, prio: P) {
        val key = keyExtractor.getKey(elem)
        heap[size] = User(elem, key, prio)
        positions[key] = size
        minHeapifyUp(size++)
    }


    private fun minHeapifyUp(idx: Int) {
        var i = idx
        while (i > 0 && heap[i]!!.prio < heap[parent(i)]!!.prio) {
            exchange(heap, i, parent(i))
            exchange(positions, heap[i]!!.key, heap[parent(i)]!!.key)
            i = parent(i)
        }
    }


    override fun peek(): E? =
        if (empty()) null else heap[0]!!.elem


    override fun poll(): E? {
        val toRemove = peek()
        if (toRemove != null) {
            heap[0] = heap[--size]
            heap[size] = null
            minHeapifyDown(0)
        }

        return toRemove
    }


    private fun minHeapifyDown(parent: Int) {
        val left = left(parent)
        val right = right(parent)
        var smallest = parent

        if (left < size && heap[left]!!.prio < heap[smallest]!!.prio) smallest = left
        if (right < size && heap[right]!!.prio < heap[smallest]!!.prio) smallest = right

        if (smallest == parent) return
        exchange(heap, parent, smallest)
        exchange(positions, heap[parent]!!.key, heap[smallest]!!.key)

        minHeapifyDown(smallest)
    }


    override fun update(key: Int, prio: P) {
        val position = positions[key]!!
        val oldPrio = heap[position]!!.prio
        heap[position]!!.prio = prio

        if (oldPrio < prio) minHeapifyDown(position)
        else if (oldPrio > prio) minHeapifyUp(position)
    }


    override fun remove(key: Int) {
        val position = positions[key]!!
        val oldPrio = heap[position]!!.prio

        heap[position] = heap[--size]
        positions[heap[size]!!.key] = position
        positions[key] = -1
        heap[size] = null

        if (oldPrio < heap[position]!!.prio) minHeapifyDown(position)
        else if (oldPrio > heap[position]!!.prio) minHeapifyUp(position)
    }


    override fun empty(): Boolean =
        size == 0

}
