package aedInterfaces

interface AEDIntList{
    val size: Int
    fun contains(element: Int): Boolean
    fun get(idx: Int): Int
    fun isEmpty(): Boolean
}

class AEDIntArrayList(val elements: IntArray): AEDIntList{
    override val size: Int
        get() = elements.size

    override fun contains(element: Int): Boolean {
        return elements.contains(element) // O(n) -> pesquisa sequencial
    }

    override fun get(idx: Int): Int {
        return elements[idx]
    }

    override fun isEmpty(): Boolean {
        return elements.isEmpty()
    }
}

fun main() {
    val a = intArrayOf(1, 2, 3, 4, 5)
    val list = AEDIntArrayList(a)

    println(list.contains(0))
    println(list.size)
    println(list.get(1))

}