package trees

import java.util.*
import kotlin.Comparator
import kotlin.math.abs
import kotlin.math.max


data class Node<E>(var value: E, var left: Node<E>? = null, var right: Node<E>? = null)


fun <E> containsIterative(root: Node<E>?, cmp: Comparator<E>, elem: E): Boolean {
    var current = root
    while (current != null) {
        val c = cmp.compare(elem, current.value)
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

    val c = cmp.compare(elem, root.value)
    return when{
        c == 0 -> true
        c < 0  -> containsRecursive(root.left, cmp, elem)
        else   -> containsRecursive(root.right, cmp, elem)
    }
}


fun <E> insert(root: Node<E>?, elem: E, cmp: Comparator<E>): Node<E>{
    val toInsert = Node(elem)
    if (root == null) return toInsert

    if (cmp.compare(elem, root.value) <= 0){
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
        current = if (cmp.compare(elem, current.value) <= 0) current.left else current.right
    }
    if (cmp.compare(elem, previous!!.value) <= 0) previous.left = toInsert else previous.right = toInsert

    return root
}


fun <E> printInOrder(root: Node<E>?){
    if (root == null) return
    if (root.left != null) printInOrder(root.left)
    println(root.value)
    if (root.right != null) printInOrder(root.right)
}

fun <E> printPreOrder(root: Node<E>?){
    if (root == null) return
    println(root.value)
    if (root.left != null) printInOrder(root.left)
    if (root.right != null) printInOrder(root.right)
}

fun <E> printPostOrder(root: Node<E>?){
    if (root == null) return
    if (root.left != null) printInOrder(root.left)
    if (root.right != null) printInOrder(root.right)
    println(root.value)
}

fun <E> breadthFirstPrint(root: Node<E>?, queue: Queue<Node<E>>){
    if (root != null){
        queue.offer(root)
        while (!queue.isEmpty()){
            val current = queue.poll()
            println(current.value)
            if (current.left != null) queue.offer(current.left)
            if (current.right != null) queue.offer(current.right)
        }
    }
}


fun <E> minimum(root: Node<E>?): E?{
    if (root == null) return null
    return if (root.left != null) minimum(root.left) else root.value
}

fun <E> minimumI(root: Node<E>?): E?{
    if (root == null) return null
    var current = root
    while (current!!.left != null) current = current.left
    return current.value
}


fun <E> maximum(root: Node<E>?): E?{
    if (root == null) return null
    return if (root.right != null) minimum(root.right) else root.value
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


fun <E> equalTrees(root1: Node<E>?, root2: Node<E>?): Boolean{
    if (root1 != root2) return false
    else if (root1 == null) return true

    return equalTrees(root1.left, root2!!.left) && equalTrees(root1.right, root2.right)
}


fun <E> isBalanced(root: Node<E>?): Boolean{
    if (root == null) return true

    if (isBalanced(root.left) && isBalanced(root.right)){
        return abs(height(root.left) - height(root.right)) <= 1
    }
    return false
}


fun <E> removeGreaterThan(root: Node<E>?, x: E, cmp: Comparator<E>): Node<E>?{
    if (root == null) return root

    if (cmp.compare(x, root.value) >= 0) root.right = removeGreaterThan(root.right, x, cmp)
    else return removeGreaterThan(root.left, x, cmp)

    return root
}


fun upper(root: Node<Int>?, k:Int): Int?{
    if (root == null) return null
    val search = if (root.value < k) upper(root.right, k) else if(root.value > k) upper(root.left, k) else root.value
    if (search != null && search >= k) return search
    return root.value
}


fun lowestCommonAncestor(root: Node<Int>?, n1: Int, n2: Int): Node<Int>?{
    if (root == null) return null
    if (root.value in n1+1 until n2 || root.value in n2+1 until n1) return root
    return if (root.value < n1) lowestCommonAncestor(root.right, n1, n2)
    else lowestCommonAncestor(root.left, n1, n2)
}


fun withSum(root: Node<Int>?, sum: Int): Boolean{
    if (root == null) return sum == 0
    val newSum = sum - root.value
    return withSum(root.left, newSum) || withSum(root.right, newSum)
}


fun getDiameter(root:Node<Int>?): Int{
    if (root == null) return 0

    val lHeight = height(root.left) + 1
    val rHeight = height(root.right) + 1

    val lDiameter = getDiameter(root.left)
    val rDiameter = getDiameter(root.right)

    return max(lHeight + rHeight + 1, max(lDiameter, rDiameter))
}


fun existClosedNodes(root: Node<Int>): Boolean{
    return existClosedNodesAux(root, 1, Int.MAX_VALUE)
}

fun existClosedNodesAux(root: Node<Int>?, min: Int, max: Int): Boolean{
    if (root == null) return false
    if (root.left == null && root.right == null && root.value == min && root.value == max) return true
    return existClosedNodesAux(root.left, min, root.value - 1) || existClosedNodesAux(root.right, root.value + 1, max)
}


fun isEquals(root: Node<Int>?, ar: Array<Int>): Boolean{
    return isEqualsAux(root, ar, 0) == ar.size
}

fun isEqualsAux(root: Node<Int>?, ar: Array<Int>, idx: Int): Int{
    //if (idx >= ar.size && root != null) return -1
    if (root == null) return idx
    var newIdx = isEqualsAux(root.left, ar, idx)
    if (root.value == ar[newIdx]) newIdx++ //else return -1

    return isEqualsAux(root.right, ar, newIdx)
}

fun convertBST(root: Node<Int>?): Node<Int>? {
    convertBSTAux(root, 0)
    return root
}

fun convertBSTAux(root: Node<Int>?, acc: Int): Int {
  if (root == null) return acc
  val newAcc = convertBSTAux(root.right, acc)
  root.value = root.value + newAcc
  convertBSTAux(root.left, root.value)
  return root.value
}



fun main() {
    val root1 : Node<Int> = Node(40)
    /*insert(root1, 5)  { a, b -> a - b }
    insert(root1, 13) { a, b -> a - b }
    insert(root1, 6)  { a, b -> a - b }
    insert(root1, 14) { a, b -> a - b }
    insert(root1, 2)  { a, b -> a - b }
    insert(root1, 3)  { a, b -> a - b }
    insert(root1, 11) { a, b -> a - b }
    insert(root1, 0)  { a, b -> a - b }
    insert(root1, 7)  { a, b -> a - b }*/

    insert(root1, 20) { a, b -> a - b }
    insert(root1, 10)  { a, b -> a - b }
    insert(root1, 30) { a, b -> a - b }
    insert(root1, 25)  { a, b -> a - b }
    insert(root1, 60)  { a, b -> a - b }
    insert(root1, 50) { a, b -> a - b }
    insert(root1, 70)  { a, b -> a - b }


    println(isEquals(root1, arrayOf(10, 20, 25, 30, 40, 50, 60, 70)))

}
