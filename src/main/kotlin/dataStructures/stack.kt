package dataStructures


/**
 * Stack is an abstract data type that serves as a collection of elements,
 * with two main principal operations:
 * - Push - which adds an element to the collection;
 * - Pop - which removes the most recently added element that was not yet removed.
 *
 * Is implemented with a LIFO (Last In, First Out) architecture.
 */
interface Stack<E> {
    fun empty(): Boolean
    fun push(elem: E): Boolean
    fun peek(): E?
    fun pop(): E?
}


/**
 * Stack implemented with Simple Linked List -> remove and add to the head
 */
class StackList<E> : Stack<E> {
    private data class Node<E>(var item: E?, var next: Node<E>?)

    private var head: Node<E>? = null

    override fun empty(): Boolean {
        return head == null
    }

    override fun push(elem: E): Boolean {
        val new = Node(elem, head)
        head = new
        return true
    }

    override fun peek(): E? {
        return head!!.item
    }

    override fun pop(): E? {
        if (empty()) return null
        val toRemove = head!!.item
        head = head!!.next
        return toRemove
    }
}


/**
 * Test function of the StackList class.
 */
fun main() {
    val stack = StackList<Int>()
    stack.push(2)
    stack.push(3)
    stack.push(4)
    stack.push(5)

    println(stack.pop())
    println(stack.pop())
    println(stack.pop())
    println(stack.pop())
    println(stack.pop())
}
