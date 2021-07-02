package linkedList


/**
 * Double Linked List, circular and with sentinel
 */
class DoubleLinkedList<E> {
    private data class Node<E>(var item: E?, var next: Node<E>?, var previous: Node<E>?)

    private var head: Node<E>? = null
    var size: Int = 0

    init {
        head = Node(null, head, head)
    }

    fun isEmpty(): Boolean {
        return head!!.next == head
    }

    private fun search(element: E): Node<E>? {
        var aux = head!!.next
        while (aux != head) {
            if (aux!!.item == element) return aux
            aux = aux.next
        }
        return null
    }

    fun contains(element: E): Boolean {
        return search(element) != null
    }

    fun get(idx: Int): E {
        var count = 0
        var aux = head!!.next
        while (count < idx) {
            aux = aux!!.next
            count++
        }
        return aux!!.item!!
    }

    fun add(element: E): Boolean {
        val new = Node(element, null, null)
        new.next = head
        new.previous = head!!.previous
        head!!.previous!!.next = new
        head!!.previous = new
        size++
        return true
    }

    fun remove(element: E): Boolean {
        val toRemove = search(element) ?: return false
        toRemove.previous!!.next = toRemove.next
        toRemove.next!!.previous = toRemove.previous
        size--
        return true
    }

    fun add(index: Int, element: E) {
        val new = Node(element, null, null)
        var aux = head
        var count = 0

        while (count < index) {
            aux = aux!!.next
            count++
        }

        new.previous = aux!!.previous
        new.next = aux
        aux.previous!!.next = new
        aux.previous = new

        while (aux!!.next != head) {
            aux = aux.next
        }
        size++
    }

    fun removeAt(index: Int): E {
        var aux = head
        var count = 0
        while (count < index) {
            aux = aux!!.next
            count++
        }
        val toRemove = aux!!.item!!

        aux.previous!!.next = aux.next
        aux.next!!.previous = aux.previous
        size--

        return toRemove
    }
}

