package searchAlgorithms


/**
 * Naive pattern searching algorithm.
 *
 * Time complexity: O(m*(n-m+1)) -> Worst case
 */
fun searchPatternNaive(txt: String, pat: String) {
    val n = txt.length
    val m = pat.length

    for (i in 0..n - m) {
        for (j in pat.indices) {
            if (txt[i + j] == pat[j]) {
                if (j == pat.lastIndex) println("Pattern found at index $i")
            } else break
        }
    }
}


/**
 * Test function of pattern searching algorithms.
 */
fun main() {
    val text = "AABAACAADAABAAABAA"
    val pattern = "AABA"
    searchPatternNaive(text, pattern) // 0; 9; 13
}
