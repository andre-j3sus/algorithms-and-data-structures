package linkedList

import sortAlgorithms.heapSort
import java.util.function.Predicate


/**
 * This file contains some algorithms that I implemented to study
 * for a college exam of "Algorithms and Data Structures" class.
 *
 * They all use linked lists.
 */


data class Node<E>(var value: E?, var next: Node<E>? = null, var prev: Node<E>? = null)


fun <E> Node<E>.addNode(elem: E) {
    val new = Node(elem, next = this, prev = this.prev)
    if (this.prev != null) this.prev!!.next = new
    this.prev = new
}


fun isPalindrome(list: Node<Char>): Boolean {
    var left = list.next
    var right = list.prev
    while (left!!.value == right!!.value) {
        if (left == right || left.next == right) return true
        left = left.next
        right = right.prev
    }
    return false
}


fun countPairs(list1: Node<Int>, list2: Node<Int>, x: Int): Int {
    var i = list1.next
    var j = list2.prev
    var count = 0

    while (i != list1 && j != list2) {
        val sum = list1.value!! + list2.value!!
        when {
            sum == x -> {
                count++
                i = i!!.next
                j = j!!.prev
            }
            sum > x -> j = j!!.prev
            else -> i = i!!.next
        }
    }
    return count
}


fun <E> filterByInterval(list: Node<E>, init: E, end: E, cmp: Comparator<E>): Node<E>? {
    var current: Node<E>? = list
    var newHead: Node<E>? = Node(null)
    while (current != null && cmp.compare(current.value, init) < 0) {
        current = current.next
    }
    if (current != null) {
        newHead = current
        newHead.prev = null
    }

    while (current != null && cmp.compare(current.value, end) <= 0) {
        current = current.next
    }
    if (current != null) {
        if (current.prev != null) current.prev!!.next = null
        else newHead = null
    }

    return newHead
}


fun <E> satisfyPredicateFirst(list: Node<E>, pred: Predicate<E>): Node<E> {
    var current: Node<E>? = list
    var newList = list
    while (current != null) {
        val temp = current.next

        if (pred.test(current.value!!) && current != newList) {
            current.prev!!.next = current.next
            if (current.next != null) current.next!!.prev = current.prev

            newList.prev = current
            current.next = newList
            current.prev = null
            newList = current
        }

        current = temp
    }

    return newList
}


fun division(list: Node<Int>, elem: Int) {
    var left = list.next
    var right = list.prev

    while (true) { // right!!.next != left && left != right

        while (left != list && left!!.value!! <= elem) {
            left = left.next
            if (left!!.value == elem) {
                left.prev!!.next = left.next
                left.next!!.prev = left.prev
            }
        }
        while (right != list && right!!.value!! >= elem) {
            right = right.prev
            if (right!!.value == elem) {
                right.prev!!.next = right.next
                right.next!!.prev = right.prev
            }
        }

        if (left.prev == right || left == right) break

        val temp = left.value
        left.value = right.value
        right.value = temp

    }
}


fun <E> hasCycle(list: Node<E>): Boolean {
    var slow: Node<E>? = list
    var fast: Node<E>? = list

    while (fast?.next != null) { // fast != null && fast.next != null
        slow = slow!!.next
        fast = fast.next!!.next

        if (fast == slow) return true
    }
    return false
}


fun <E> modularRemove(list: Node<E>, k: Int) {
    var current = list.next
    var i = 0
    while (current!! != list) {
        if (i % k == 0) {
            current.prev!!.next = current.next
            current.next!!.prev = current.prev
        }
        current = current.next
        i++
    }
}
