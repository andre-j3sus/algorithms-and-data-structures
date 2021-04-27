package sortAlgorithms


fun insertionSort(a: IntArray){
    for (i in 1 until a.size){
        val value = a[i]
        var currentIdx = i

        while (currentIdx > 0 && a[currentIdx - 1] > value){
            a[currentIdx] = a[currentIdx - 1]
            currentIdx--
        }
        a[currentIdx] = value
    }
}



fun main() {
    val a = intArrayOf(-2, -9, 4, -3, 0, 19, 14, -20, 5, 2)
    insertionSort(a)
    println(a.asList())
}
