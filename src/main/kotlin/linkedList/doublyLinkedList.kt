package linkedList


// Double Linked List,  circular and with sentinel

/*class LinkedListV2<E>() : AEDMutableList<E> {
    private data class Node<E>(var item:E?, var next:Node<E>?, var previous:Node<E>?)
    private var head: Node<E>? = null
    override var size: Int = 0

    init {
        head = Node<E>(null, head, head)
    }

    override fun isEmpty(): Boolean {
        return head!!.next == head
    }

    private fun search(element: E): Node<E>?{
        var aux = head!!.next
        while (aux != head){
            if (aux!!.item == element) return aux
            aux = aux.next
        }
        return null
    }

    override fun contains(element: E): Boolean {
        return search(element) != null
    }

    override fun get(idx: Int): E {
        var count = 0
        var aux = head!!.next
        while (count < idx){
            aux = aux!!.next
            count++
        }
        return aux!!.item!!
    }

    override fun add(element: E): Boolean {
        val new = Node<E>(element, null, null)
        new.next = head
        new.previous = head!!.previous
        head!!.previous!!.next = new
        head!!.previous = new
        size++
        return true
    }

    override fun remove(element: E): Boolean {
        val toRemove = search(element) ?: return false
        toRemove.previous!!.next = toRemove.next
        toRemove.next!!.previous = toRemove.previous
        size--
        return true
    }

    override fun add(index: Int, element: E) {
        val new = Node<E>(element, null, null)
        var aux = head
        var count = 0

        while (count < index){
            aux = aux!!.next
            count++
        }

        new.previous = aux!!.previous
        new.next = aux
        aux.previous!!.next = new
        aux.previous = new

        while (aux!!.next != head){
            aux = aux.next
        }
        size++
    }

    override fun removeAt(index: Int): E {
        var aux = head
        var count = 0
        while (count < index){
            aux = aux!!.next
            count++
        }
        val toRemove = aux!!.item!!

        aux.previous!!.next = aux.next
        aux.next!!.previous = aux.previous
        size--

        return toRemove
    }
}*/

