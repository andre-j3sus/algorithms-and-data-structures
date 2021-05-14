package AED_Lists

interface AEDIntMutableList: AEDIntList{
    fun add(element: Int): Boolean
    fun add(index: Int, element: Int)
    fun remove(element: Int): Boolean
    fun removeAt(index: Int): Int
}


class AEDIntArrayListMutable: AEDIntMutableList{
    private var elements: IntArray

    constructor(){elements = IntArray(10)}
    constructor(initialCapacity: Int){elements = IntArray(initialCapacity) }

    override var size: Int = 0

    override fun contains(element: Int): Boolean {
        return elements.contains(element)
    }

    override fun get(idx: Int): Int {
        return elements[idx]
    }

    override fun isEmpty(): Boolean {
        return elements.isEmpty()
    }

    override fun add(element: Int): Boolean {
        if (size == elements.size) increaseCapacity()
        elements[size++] = element
        return true
    }

    private fun increaseCapacity(){
        val newArray = IntArray(2*elements.size)
        System.arraycopy(elements, 0, newArray, 0, size)
        elements = newArray
    }

    override fun add(index: Int, element: Int) {
		if (size == elements.size) increaseCapacity()
        System.arraycopy(elements, index, elements, index+1, size-index)
        size++
        elements[index] = element
    }

    override fun remove(element: Int): Boolean {
        for (i in elements.indices){
            if (elements[i] == element){
                System.arraycopy(elements, i+1, elements, i, size-i-1)
                size--
                return true
            }
        }
        return false
    }

    override fun removeAt(index: Int): Int {
        val element = elements[index]
        System.arraycopy(elements, index+1, elements, index, size-index)
        size--
        return element
    }
}
