package dataTypes

class HashSet<E>(override var size: Int) : MutableSet<E>{
    data class Node <E>(val item: E, var next: Node<E>? = null, var previous: Node<E>? = null)

    private var table: Array<Node<E>?>? = null
    private var dimTable = 0
    constructor() : this(0){
        table = arrayOfNulls<Node<E>?>(10) // as Array<HashSet.Node<E>?>
        dimTable = 10
    }


    /**
     * Returns the index of the element
     */
    private fun index(elem: E): Int{
        val pos = elem.hashCode() % dimTable
        return if (pos < 0) pos + dimTable else pos
    }


    /**
     * Returns the node in the position idx with the item equal to elem, or null
     */
    private fun search(elem: E, idx: Int): Node<E>?{
        var current = table!![idx]
        while (current != null){
            if (current.item == elem) return current
            current = current.next
        }
        return null
    }


    /**
     * Adds a element to the HashSet
     *
     * If the element already exists, returns false
     */
    override fun add(element: E): Boolean {
        var pos = index(element)
        val node = search(element, pos)
        if (node != null) return false

        if ((size / dimTable).toDouble() > 0.75) resize()

        pos = index(element)
        val new = Node(element, table!![pos])
        if (table!![pos] != null) table!![pos]!!.previous = new
        table!![pos] = new

        size++
        return true
    }


    private fun resize(){
        dimTable *= 2
        table = table!!.copyOf(size)
        for (i in 0..dimTable){
            val elem = table!![i]
            if (elem != null) table!![index(elem.item)] = elem
        }
    }

    override fun addAll(elements: Collection<E>): Boolean {
        elements.forEach { elem ->
            if (!add(elem)) return false
        }
        return true
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun iterator(): MutableIterator<E> {
        TODO("Not yet implemented")
    }

    override fun remove(element: E): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeAll(elements: Collection<E>): Boolean {
        TODO("Not yet implemented")
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        TODO("Not yet implemented")
    }

    override fun contains(element: E): Boolean {
        return search(element, index(element)) != null
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        elements.forEach{ elem -> if (!contains(elem)) return false }
        return true
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }


}