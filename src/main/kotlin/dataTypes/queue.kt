package dataTypes

interface Queue<E>{
    fun empty(): Boolean
    fun peek(): E?
    fun offer(elem: E): Boolean
    fun poll(): E?
}

class ListQueue<E>: Queue<E>{
    private data class Node<E>(var item:E?, var next:Node<E>?)
    private var head: Node<E>? = null
    private var tail: Node<E>? = null

    override fun empty(): Boolean {
        return head == tail
    }

    override fun peek(): E? {
        return tail!!.item
    }

    override fun offer(elem: E): Boolean {
        head = Node(elem, head!!.next)
        return true
    }

    override fun poll(): E? {
        TODO("Not yet implemented -> como atualizar a tail")
    }

}
