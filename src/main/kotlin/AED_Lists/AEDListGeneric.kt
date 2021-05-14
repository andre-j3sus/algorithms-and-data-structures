package AED_Lists

interface AEDList<E>{
    val size:Int
    fun contains(element: E): Boolean
    fun get(idx: Int): E
    fun isEmpty(): Boolean
}

interface AEDMutableList<E>: AEDList<E>{
    fun add(element: E): Boolean
    fun remove(element: E): Boolean
    fun add(index: Int, element: E)
    fun removeAt(index: Int): E
}

class AEDArrayList<E>(override val size: Int) : AEDMutableList<E>{
    private val elements: Array<E> = arrayOfNulls<Any?>(10) as Array<E>

    override fun get(idx:Int): E{
        return elements[idx]
    }

    override fun isEmpty(): Boolean {
        return elements.isEmpty()
    }

    override fun contains(element: E): Boolean {
        return elements.contains(element)
    }

    override fun add(element: E): Boolean {
        TODO("Not yet implemented")
    }

    override fun remove(element: E): Boolean {
        TODO("Not yet implemented")
    }

    override fun add(index: Int, element: E) {
        TODO("Not yet implemented")
    }

    override fun removeAt(index: Int): E {
        TODO("Not yet implemented")
    }
}
