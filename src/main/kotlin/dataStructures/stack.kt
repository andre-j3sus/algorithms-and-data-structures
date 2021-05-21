package dataStructures

interface Stack<E>{
    fun empty(): Boolean
    fun push(elem: E): Boolean
    fun peek(): E?
    fun pop(): E?
}


/**
 * Implemented with Simple Linked List -> remove and add to the head
 */
class StackList<E>: Stack<E>{
    private data class Node<E>(var item:E?, var next:Node<E>?)
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
