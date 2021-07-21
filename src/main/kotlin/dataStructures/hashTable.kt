package dataStructures


const val DEFAULT_TABLE_SIZE = 26


/**
 * A very specific hash table that I implemented from an University Assignment.
 *
 * Requirements:
 * - Hash function = 'simply using the last character'
 * - Hash table contains exactly 26 slots (a-z)
 * - Table slots have three different statuses: 'never used', 'tombstone', 'occupied'
 * - Table starts with never used
 */
class HashTable {

    enum class SlotStatus { NEVER_USED, TOMBSTONE, OCCUPIED }
    data class Slot(var entry: String, var status: SlotStatus)

    private val table = Array(DEFAULT_TABLE_SIZE) { Slot(entry = "", status = SlotStatus.NEVER_USED) }


    /**
     * Hash function = 'simply using the last character'.
     * @return hashcode
     */
    private fun String.hashFunction(): Int {
        return last().toInt() - 'a'.toInt()
    }


    /**
     * Searches for a entry.
     * @param key key to search
     * @return true if the key is found
     */
    fun search(key: String): Int {
        val index = key.hashFunction()
        var slot = table[index]

        var i = index

        do {
            if (i == DEFAULT_TABLE_SIZE) i = 0

            if (slot.entry == key && slot.status == SlotStatus.OCCUPIED) return i
            else if (slot.status == SlotStatus.NEVER_USED) return -1

            slot = table[++i]
        } while (i != index)

        return -1
    }


    /**
     * Inserts an entry.
     * @param key key to add
     */
    fun add(key: String) {
        val index = search(key)
        if (index != -1) return // If the key exists do nothing

        val slot = table[key.hashFunction()]
        slot.entry = key
        slot.status = SlotStatus.OCCUPIED
    }


    /**
     * Deletes an entry.
     * @key key to delete.
     */
    fun delete(key: String) {
        val index = search(key)
        if (index == -1) return // If the key doesn't exist do nothing

        val slot = table[index]
        slot.status = SlotStatus.TOMBSTONE
    }


    /**
     * Prints the table.
     */
    fun print() {
        table.forEach { slot ->
            println("${slot.entry} (${slot.status})")
        }
    }
}


/**
 * HashTable test function.
 */
fun main() {
    val table = HashTable()

    table.add("apple")
    table.add("banana")
    table.delete("banana")
    table.add("strawberry")
    table.delete("apple")
    table.add("orange")
    println(table.search("orange"))
    println(table.search("apple"))

    table.print()
}
