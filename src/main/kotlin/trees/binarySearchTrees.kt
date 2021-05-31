package trees

import java.util.*
import kotlin.Comparator
import kotlin.math.max

data class Node<E>(val item: E, var left: Node<E>? = null, var right: Node<E>? = null)


fun <E> containsIterative(root: Node<E>?, cmp: Comparator<E>, elem: E): Boolean {
    var current = root
    while (current != null) {
        val c = cmp.compare(elem, current.item)
        current = when {
            c == 0 -> return true
            c < 0 -> current.left
            else -> current.right
        }
    }
    return false
}


fun <E> containsRecursive(root: Node<E>?, cmp: Comparator<E>, elem: E): Boolean {
    if (root == null) return false

    val c = cmp.compare(elem, root.item)
    return when{
        c == 0 -> true
        c < 0  -> containsRecursive(root.left, cmp, elem)
        else   -> containsRecursive(root.right, cmp, elem)
    }
}


fun <E> insert(root: Node<E>?, elem: E, cmp: Comparator<E>): Node<E>{
    val toInsert = Node(elem)
    if (root == null) return toInsert

    if (cmp.compare(elem, root.item) <= 0){
        if (root.left == null) root.left = toInsert
        else insert(root.left, elem, cmp)
    }
    else{
        if (root.right == null) root.right = toInsert
        else insert(root.right, elem, cmp)
    }
    return root
}

fun <E> insertIterative(root: Node<E>?, elem: E, cmp: Comparator<E>): Node<E>{
    val toInsert = Node(elem)
    if (root == null) return toInsert
    var current = root
    var previous: Node<E>? = null

    while (current != null){
        previous = current
        current = if (cmp.compare(elem, current.item) <= 0) current.left else current.right
    }
    if (cmp.compare(elem, previous!!.item) <= 0) previous.left = toInsert else previous.right = toInsert

    return root
}


fun <E> printInOrder(root: Node<E>?){
    if (root == null) return
    if (root.left != null) printInOrder(root.left)
    println(root.item)
    if (root.right != null) printInOrder(root.right)
}

fun <E> printPreOrder(root: Node<E>?){
    if (root == null) return
    println(root.item)
    if (root.left != null) printInOrder(root.left)
    if (root.right != null) printInOrder(root.right)
}

fun <E> printPostOrder(root: Node<E>?){
    if (root == null) return
    if (root.left != null) printInOrder(root.left)
    if (root.right != null) printInOrder(root.right)
    println(root.item)
}

fun <E> breadthFirstPrint(root: Node<E>?, queue: Queue<Node<E>>){
    if (root != null){
        queue.offer(root)
        while (!queue.isEmpty()){
            val current = queue.poll()
            println(current.item)
            if (current.left != null) queue.offer(current.left)
            if (current.right != null) queue.offer(current.right)
        }
    }
}


fun <E> minimum(root: Node<E>?): E?{
    if (root == null) return null
    return if (root.left != null) minimum(root.left) else root.item
}

fun <E> minimumI(root: Node<E>?): E?{
    if (root == null) return null
    var current = root
    while (current!!.left != null) current = current.left
    return current.item
}


fun <E> maximum(root: Node<E>?): E?{
    if (root == null) return null
    return if (root.right != null) minimum(root.right) else root.item
}


fun <E> height(root: Node<E>?): Int{
    if (root == null) return -1
    val l = height(root.left)
    val r = height(root.right)
    return 1 + max(l, r)
}


fun <E> isComplete(root: Node<E>?): Boolean{
    return isCompleteAux(root) != -1
}

fun <E> isCompleteAux(root: Node<E>?): Int{
    if (root == null) return 0
    val l = isCompleteAux(root.left)
    val r = isCompleteAux(root.right)

    if (l == -1 || r == -1) return -1
    if (l == r) return l + 1
    return -1
}


fun main() {
    val newTree = Node(10)
    insert(newTree, 5) { a, b -> a - b }
    insert(newTree, 13) { a, b -> a - b }
    insert(newTree, 6) { a, b -> a - b }
    insert(newTree, 14) { a, b -> a - b }
    insert(newTree, 2) { a, b -> a - b }
    insert(newTree, 3) { a, b -> a - b }
    insert(newTree, 11) { a, b -> a - b }
    insert(newTree, 0) { a, b -> a - b }
    printInOrder(newTree)
    println("---------------")

    println("Height = ${height(newTree)}")
    println("---------------")

    println("Minimum = ${minimum(newTree)}")
    println("Maximum = ${maximum(newTree)}")
}
