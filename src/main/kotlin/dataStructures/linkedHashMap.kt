package dataStructures

interface MutableMap<K,V> {
    interface MutableEntry<K, V>{
        val key: K
        val value:V
        fun setValue(newValue: V): V
    }
    var size: Int
    fun put(key: K, value: V): V?
    fun remove(key: K): V?
    operator fun get(key: K): V?
    fun iterator(): Iterator<MutableEntry<K, V>>
}

class LinkedHashMap<K, V>(capacity: Int = 10) : MutableMap<K, V> {

    class HashNode<K, V>(override val key: K, override var value: V) : MutableMap.MutableEntry<K, V>{

        /**
         * Change the value of a HashNode
         * @param newValue 
         * @return the old value
         */
        override fun setValue(newValue: V): V {
            val lastValue = value
            value = newValue
            return lastValue
        }

        var next : HashNode<K, V>? = null
        var previous : HashNode<K, V>? = null

        var nextOrdered: HashNode<K, V>? = null     
        var previousOrdered: HashNode<K, V>? = null 
    }

    private var table: Array<HashNode<K, V>?> = Array(capacity){ null }

    private var headOrdered : HashNode<K, V>? = null 
    private var tailOrdered : HashNode<K, V>? = null 

    override var size: Int = 0           
    private var dimTable: Int = capacity 

    
    override fun put(key: K, value: V): V? {
        if ((size / dimTable.toDouble()) > 0.75) resize()

        val index = index(key)
        var elem = table[index]

        while (elem != null){
            if (elem.key == key){
                val lastValue = elem.value
                elem.setValue(value)

                // Ordered list
                removeFromOrdered(elem)
                addToOrdered(elem)

                return lastValue
            }

            if (elem.next == null) break

            elem = elem.next
        }

        val new = HashNode(key, value)

        // Add element to bucket
        new.previous = elem
        if (elem == null) table[index] = new
        else elem.next = new

        // Add element to insertion order list
        addToOrdered(new)

        size++

        return null
    }

    
    override fun remove(key: K): V? {
        var elem = table[index(key)]

        while (elem != null){
            if (elem.key == key){

                // Remove element from bucket
                if (elem.previous != null) elem.previous!!.next = elem.next
                else table[index(key)] = elem.next

                if (elem.next != null) elem.next!!.previous = elem.previous

                // Remove element from insertion order list
                removeFromOrdered(elem)

                size--

                return elem.value
            }

            elem = elem.next
        }
        return null
    }

    
    private fun addToOrdered(elem: HashNode<K, V>) {
        if (headOrdered == null && tailOrdered == null){
            headOrdered = elem
            tailOrdered = elem
        }
        else{
            elem.previousOrdered = tailOrdered!!
            tailOrdered!!.nextOrdered = elem
            elem.nextOrdered = null

            tailOrdered = elem
        }
    }

    
    private fun removeFromOrdered(elem: HashNode<K, V>){
        when (elem) {
            headOrdered -> headOrdered = elem.nextOrdered
            tailOrdered -> {
                tailOrdered = elem.previousOrdered
                tailOrdered!!.nextOrdered = null
            }
            else -> {
                elem.previousOrdered!!.nextOrdered = elem.nextOrdered
                elem.nextOrdered!!.previousOrdered = elem.previousOrdered
            }
        }
    }

    
    override operator fun get(key: K): V? {
        var elem = table[index(key)]

        while (elem != null){
            if (elem.key == key) return elem.value

            elem = elem.next
        }

        return null
    }

    
    private fun index(key: K) : Int{
        val hashCode = key.hashCode()
        return hashCode % dimTable + if (hashCode % dimTable < 0) dimTable else 0
    }

    
    private fun resize(){
        dimTable *= 2
        val newTable = Array<HashNode<K, V>?>(dimTable){ null }

        var current: HashNode<K, V>?

        for (i in table.indices){
            current = table[i]
            while (current != null){
                table[i] = table[i]!!.next
                val newPosition = index(current.key)

                current.next = newTable[newPosition]
                current.previous = null
                if (newTable[newPosition] != null) newTable[newPosition]!!.previous = current
                newTable[newPosition] = current

                if (table[i] != null) table[i]!!.previous = null

                current = table[i]
            }
        }

        table = newTable
    }

    
    override operator fun iterator(): Iterator<MutableMap.MutableEntry<K, V>> {
        return object : Iterator<MutableMap.MutableEntry<K, V>>{
            var element = headOrdered
            override fun hasNext(): Boolean {
                return element != null
            }

            override fun next(): MutableMap.MutableEntry<K, V> {
                if (!hasNext()) throw NoSuchElementException()

                val x = element!!
                element = element!!.nextOrdered
                return x
            }
        }
    }

    
    override fun toString(): String {
        var str = ""

        for (elem in this){
            str += "'${elem.key}': ${elem.value}, "
        }
        return "{ ${str.dropLast(2)} }"
    }

    
    operator fun set(k: K, value: V) =
        put(k, value)
    
}
