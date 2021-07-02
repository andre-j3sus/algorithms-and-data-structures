package linkedList


/**
 * Single Linked List, non circular and without sentinel
 */
class SingleLinkedList<E> {
    private data class Node<E>(var item: E, var next: Node<E>?)

    private var head: Node<E>? = null

    var size: Int = 0

    fun isEmpty(): Boolean {
        return head == null
    }

    fun contains(element: E): Boolean {
        var aux = head
        while (aux != null) {
            if (aux.item == element) return true
            aux = aux.next
        }
        return false
    }

    fun get(idx: Int): E { // O(n)
        var count = 0
        var aux = head
        while (count < idx) {
            aux = aux!!.next
            count++
        }
        return aux!!.item
    }

    fun add(element: E): Boolean {
        val new = Node(element, null)
        var prev: Node<E>? = null
        var aux = head

        while (aux != null) {
            prev = aux
            aux = aux.next
        }
        if (prev == null) head = new // if size = 0
        else prev.next = new
        size++
        return true
    }

    fun remove(element: E): Boolean {
        var aux = head
        var prev: Node<E>? = null

        while (aux != null) {
            if (aux.item!! == element) {
                if (prev == null) head = head!!.next
                else prev.next = aux.next
                size--
                return true
            }
            prev = aux
            aux = aux.next
        }
        return false
    }

    fun add(index: Int, element: E) {
        val new = Node(element, null)
        var prev: Node<E>? = null
        var aux = head

        var count = 0
        while (count < index) {
            prev = aux
            aux = aux!!.next
            count++
        }
        if (prev == null) head = new
        else {
            while (aux != null) {
                aux = aux.next
            }
            prev.next = new
        }
        size++
    }

    fun removeAt(index: Int): E {
        var prev: Node<E>? = null
        var aux = head

        var count = 0
        while (count < index) {
            prev = aux
            aux = aux!!.next
            count++
        }
        val element: E = aux!!.item
        if (prev == null) head = head!!.next
        else prev.next = aux.next
        size--
        return element
    }
}
