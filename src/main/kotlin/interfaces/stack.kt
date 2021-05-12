package interfaces

interface Stack<E>{
    fun empty(): Boolean
    fun push(elem: E): Boolean
    fun peek(): E?
    fun pop(): E?
}

class StackList<E>: Stack<E>{
    private data class Node<E>(var item:E?, var next:Node<E>?)
    private var head: Node<E>? = null

    override fun empty(): Boolean {
        return head == null
    }

    override fun push(elem: E): Boolean {
        head = Node(elem, head!!.next)
        return true
    }

    override fun peek(): E? {
        return head!!.item
    }

    override fun pop(): E? {
        if (empty()) return null
        val temp = head!!.item
        head = head!!.next
        return temp
    }

}
