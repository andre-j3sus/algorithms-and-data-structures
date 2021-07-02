package dataStructures


/**
 * A Queue is a linear structure which follows a particular order in which the operations are performed.
 *
 * The order is First In, First Out (FIFO).
 */
interface Queue<E> {
    fun empty(): Boolean
    fun peek(): E?
    fun offer(elem: E): Boolean
    fun poll(): E?
}


/**
 * Queue implemented with Simple Linked List -> remove to the head and add to the tail
 */
class QueueList<E> : Queue<E> {
    private data class Node<E>(var item: E?, var next: Node<E>?)

    private var tail: Node<E> = Node(null, null)
    private var head: Node<E> = tail

    override fun empty(): Boolean {
        return head.item == null
    }

    override fun peek(): E? {
        return tail.item
    }

    override fun offer(elem: E): Boolean {
        val new = Node(elem, null)
        tail.next = new
        tail = new
        if (head.item == null) head = new
        return true
    }

    override fun poll(): E? {
        if (empty()) return null
        val toRemove = head.item
        if (head.next != null) head = head.next!! else head.item = null
        return toRemove
    }
}


/**
 * Test function of the QueueList class.
 */
fun main() {
    val queue = QueueList<String>()
    queue.offer("Jesus")
    queue.offer("Nyck")
    queue.offer("Páscoa")
    queue.offer("Carlos")

    println(queue.empty())

    println("First: ${queue.poll()}")
    println("Second: ${queue.poll()}")
    println("Third: ${queue.poll()}")
    println("Fourth: ${queue.poll()}")

    println(queue.empty())
    queue.offer("Jesus")
    queue.offer("Nyck")
    println("Fifth: ${queue.poll()}")
    println("Sixth: ${queue.poll()}")
}
