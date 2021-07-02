package trees

import java.util.*
import kotlin.Comparator
import kotlin.math.abs
import kotlin.math.max


/**
 * Generic Binary Search Tree Node.
 * @property value value associated to the node
 * @property left left child
 * @property right right child
 */
data class Node<E>(var value: E, var left: Node<E>? = null, var right: Node<E>? = null)


/**
 * Returns true if the BST contains the element - Iterative.
 * @param root root of the BST
 * @param cmp generic comparator
 * @param elem elem to search
 * @return true if the BST contains the element
 */
fun <E> containsIterative(root: Node<E>?, cmp: Comparator<E>, elem: E): Boolean {
    var current = root
    while (current != null) {
        val c = cmp.compare(elem, current.value)
        current = when {
            c == 0 -> return true
            c < 0  -> current.left
            else   -> current.right
        }
    }
    return false
}


/**
 * Returns true if the BST contains the element - Recursive.
 * @param root root of the BST
 * @param cmp generic comparator
 * @param elem elem to search
 * @return true if the BST contains the element
 */
fun <E> containsRecursive(root: Node<E>?, cmp: Comparator<E>, elem: E): Boolean {
    if (root == null) return false

    val c = cmp.compare(elem, root.value)
    return when {
        c == 0 -> true
        c < 0  -> containsRecursive(root.left, cmp, elem)
        else   -> containsRecursive(root.right, cmp, elem)
    }
}


/**
 * Inserts an element in the BST - Recursive.
 * @param root root of the BST
 * @param cmp generic comparator
 * @param elem elem to insert
 * @return the root of the BST
 */
fun <E> insert(root: Node<E>?, elem: E, cmp: Comparator<E>): Node<E> {
    val toInsert = Node(elem)
    if (root == null) return toInsert

    if (cmp.compare(elem, root.value) <= 0) {
        if (root.left == null) root.left = toInsert
        else insert(root.left, elem, cmp)
    } else {
        if (root.right == null) root.right = toInsert
        else insert(root.right, elem, cmp)
    }
    return root
}


/**
 * Inserts an element in the BST - Iterative.
 * @param root root of the BST
 * @param cmp generic comparator
 * @param elem elem to insert
 * @return the root of the BST
 */
fun <E> insertIterative(root: Node<E>?, elem: E, cmp: Comparator<E>): Node<E> {
    val toInsert = Node(elem)
    if (root == null) return toInsert
    var current = root
    var previous: Node<E>? = null

    while (current != null) {
        previous = current
        current = if (cmp.compare(elem, current.value) <= 0) current.left else current.right
    }
    if (cmp.compare(elem, previous!!.value) <= 0) previous.left = toInsert else previous.right = toInsert

    return root
}


/**
 * Prints the elements of a BST in order.
 * @param root root of the BST
 */
fun <E> printInOrder(root: Node<E>?) {
    if (root == null) return
    if (root.left != null) printInOrder(root.left)
    println(root.value)
    if (root.right != null) printInOrder(root.right)
}


/**
 * Prints the elements of a BST in preorder.
 * @param root root of the BST
 */
fun <E> printPreOrder(root: Node<E>?) {
    if (root == null) return
    println(root.value)
    if (root.left != null) printInOrder(root.left)
    if (root.right != null) printInOrder(root.right)
}


/**
 * Prints the elements of a BST in postorder.
 * @param root root of the BST
 */
fun <E> printPostOrder(root: Node<E>?) {
    if (root == null) return
    if (root.left != null) printInOrder(root.left)
    if (root.right != null) printInOrder(root.right)
    println(root.value)
}


/**
 * Prints the elements of a BST in order of a BFS.
 * @param root root of the BST
 * @param queue BFS queue
 */
fun <E> breadthFirstPrint(root: Node<E>?, queue: Queue<Node<E>>) {
    if (root != null) {
        queue.offer(root)
        while (!queue.isEmpty()) {
            val current = queue.poll()
            println(current.value)
            if (current.left != null) queue.offer(current.left)
            if (current.right != null) queue.offer(current.right)
        }
    }
}


/**
 * Returns the minimum element in a BST.
 * @param root root of the BST
 * @return the minimum element
 */
fun <E> minimum(root: Node<E>?): E? {
    if (root == null) return null
    return if (root.left != null) minimum(root.left) else root.value
}


/**
 * Returns the maximum element in a BST.
 * @param root root of the BST
 * @return the maximum element
 */
fun <E> maximum(root: Node<E>?): E? {
    if (root == null) return null
    return if (root.right != null) maximum(root.right) else root.value
}


/**
 * Returns the height of a BST.
 * @param root root of the BST
 * @return height of the BST
 */
fun <E> height(root: Node<E>?): Int {
    if (root == null) return -1
    val l = height(root.left)
    val r = height(root.right)
    return 1 + max(l, r)
}


/**
 * Returns true if the BST is complete:
 * A Binary Tree is a Complete Binary Tree if all the levels are completely filled.
 *
 * @param root root of the BST
 * @return true if the BST is complete
 */
fun <E> isComplete(root: Node<E>?): Boolean {
    return isCompleteAux(root) != -1
}

/**
 * Auxiliar function to isComplete.
 */
fun <E> isCompleteAux(root: Node<E>?): Int {
    if (root == null) return 0
    val l = isCompleteAux(root.left)
    val r = isCompleteAux(root.right)

    if (l == -1 || r == -1) return -1
    if (l == r) return l + 1
    return -1
}


/**
 * Returns true if the BSTs are equal.
 *
 * @param root1 root of the first BST
 * @param root2 root of the second BST
 * @return true if the BSTs are equal
 */
fun <E> equalTrees(root1: Node<E>?, root2: Node<E>?): Boolean {
    if (root1 != root2) return false
    else if (root1 == null) return true

    return equalTrees(root1.left, root2!!.left) && equalTrees(root1.right, root2.right)
}


/**
 * Returns true if the BST is balanced:
 * - Left subtree of T is balanced
 * - Right subtree of T is balanced
 * - The difference between heights of left subtree and right subtree is not more than 1.
 *
 * @param root root of the BST
 * @return true if the BST is balanced
 */
fun <E> isBalanced(root: Node<E>?): Boolean {
    if (root == null) return true

    if (isBalanced(root.left) && isBalanced(root.right)) {
        return abs(height(root.left) - height(root.right)) <= 1
    }
    return false
}


/**
 * Removes all elements greater than [x].
 *
 * @param root root of the BST
 * @param x element to compare with others
 * @param cmp generic comparator
 * @return the root of the new BST
 */
fun <E> removeGreaterThan(root: Node<E>?, x: E, cmp: Comparator<E>): Node<E>? {
    if (root == null) return root

    if (cmp.compare(x, root.value) >= 0) root.right = removeGreaterThan(root.right, x, cmp)
    else return removeGreaterThan(root.left, x, cmp)

    return root
}


/**
 * Returns the minimum element greater or equal to [k].
 *
 * @param root root of the BST
 * @param k elemen to compare with others
 * @return the minimum element greater or equal to [k]
 */
fun upper(root: Node<Int>?, k: Int): Int? {
    if (root == null) return null
    val search = if (root.value < k) upper(root.right, k) else if (root.value > k) upper(root.left, k) else root.value
    if (search != null && search >= k) return search
    return root.value
}


/**
 * Returns the lowest common ancestor of the nodes [n1] and [n2].
 *
 * @param root root of the BST
 * @param n1 first value
 * @param n2 second value
 * @return the lowest common ancestor of the nodes [n1] and [n2]
 */
fun lowestCommonAncestor(root: Node<Int>?, n1: Int, n2: Int): Node<Int>? {
    if (root == null) return null
    if (root.value in n1 .. n2 || root.value in n2 .. n1) return root
    return if (root.value < n1) lowestCommonAncestor(root.right, n1, n2)
    else lowestCommonAncestor(root.left, n1, n2)
}


/**
 * Returns true if there's a path between the root and one leaf that the sum of all elements is equal to [sum].
 *
 * @param root root of the BST
 * @param sum
 * @return true if there's a path between the root and one leaf that the sum of all elements is equal to [sum]
 */
fun withSum(root: Node<Int>?, sum: Int): Boolean {
    if (root == null) return sum == 0
    val newSum = sum - root.value
    return withSum(root.left, newSum) || withSum(root.right, newSum)
}


/**
 * Returns the diameter of a BST
 *
 * @param root root of the BST
 * @return the diameter of a BST
 */
fun getDiameter(root: Node<Int>?): Int {
    if (root == null) return 0

    val lHeight = height(root.left) + 1
    val rHeight = height(root.right) + 1

    val lDiameter = getDiameter(root.left)
    val rDiameter = getDiameter(root.right)

    return max(lHeight + rHeight + 1, max(lDiameter, rDiameter))
}


/**
 * Returns true if there are closed nodes in the BST.
 *
 * @param root root of the BST
 * @return true if there are closed nodes in the BST
 */
fun existClosedNodes(root: Node<Int>): Boolean {
    return existClosedNodesAux(root, 1, Int.MAX_VALUE)
}

/**
 * Auxiliary function to existClosedNodes.
 */
fun existClosedNodesAux(root: Node<Int>?, min: Int, max: Int): Boolean {
    if (root == null) return false
    if (root.left == null && root.right == null && root.value == min && root.value == max) return true
    return existClosedNodesAux(root.left, min, root.value - 1) || existClosedNodesAux(root.right, root.value + 1, max)
}


/**
 * Returns true if the elements in the BST in order are equal to the elements of the array.
 *
 * @param root root of the BST
 * @param ar array
 * @return true if the elements in the BST in order are equal to the elements of the array
 */
fun isEquals(root: Node<Int>?, ar: Array<Int>): Boolean {
    return isEqualsAux(root, ar, 0) == ar.size
}

/**
 * Auxiliary function to isEquals.
 */
fun isEqualsAux(root: Node<Int>?, ar: Array<Int>, idx: Int): Int {
    if (idx >= ar.size && root != null) return -1
    if (root == null) return idx
    var newIdx = isEqualsAux(root.left, ar, idx)
    if (root.value == ar[newIdx]) newIdx++ else return -1

    return isEqualsAux(root.right, ar, newIdx)
}


/**
 * Test function of the BST algorithms.
 */
fun main() {
    val root1: Node<Int> = Node(40)
    insert(root1, 20) { a, b -> a - b }
    insert(root1, 10) { a, b -> a - b }
    insert(root1, 30) { a, b -> a - b }
    insert(root1, 25) { a, b -> a - b }
    insert(root1, 60) { a, b -> a - b }
    insert(root1, 50) { a, b -> a - b }
    insert(root1, 70) { a, b -> a - b }

    printPreOrder(root1)
    printInOrder(root1)
    printPostOrder(root1)

    println(isEquals(root1, arrayOf(10, 20, 25, 30, 40, 50, 60, 70))) // true
    println(equalTrees(root1, root1)) // true

    println(lowestCommonAncestor(root1, 60, 70)?.value) // 60
    println(withSum(root1, 70)) // true
    println(existClosedNodes(root1)) // false
    println(upper(root1, 32)) // 40
    println(containsRecursive(root1, { a, b -> a - b }, 50)) // true

    println(height(root1)) // 3
    println(getDiameter(root1)) // 6
    println(maximum(root1)) // 70
    println(minimum(root1)) // 10

    println(isBalanced(root1)) // true
    println(isComplete(root1)) // false
}
