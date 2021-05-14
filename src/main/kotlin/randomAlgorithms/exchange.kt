package randomAlgorithms


fun <E> exchange(a:Array<E>, idx1:Int, idx2:Int){
    val temp = a[idx1]
    a[idx1] = a[idx2]
    a[idx2] = temp
}


fun exchange(a:IntArray, idx1:Int, idx2:Int){
    val temp = a[idx1]
    a[idx1] = a[idx2]
    a[idx2] = temp
}
