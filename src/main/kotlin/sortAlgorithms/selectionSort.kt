package sortAlgorithms


fun selectionSort(a: IntArray){
    var currentIdx = 0

    for (i in a.indices){
        exchange(a, currentIdx++, minIdx(a, i, a.lastIndex))
    }
}


fun minIdx(a:IntArray, l:Int, r:Int):Int{
    var minIdx = l
    for (i in l..r){
        if (a[i] < a[minIdx]) minIdx = i
    }
    return minIdx
}


fun exchange(a:IntArray, idx1:Int, idx2:Int){
    val temp = a[idx1]
    a[idx1] = a[idx2]
    a[idx2] = temp
}



fun main() {
    val a = intArrayOf(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    selectionSort(a)
    println(a.asList())
}
