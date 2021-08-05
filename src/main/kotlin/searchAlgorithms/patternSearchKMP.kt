package searchAlgorithms


/**
 * Knuth Morris Pratt pattern searching algorithm.
 *
 * Time Complexity: O(n)
 *
 * @param txt text
 * @param pat pattern to search
 */
fun searchPatternKMP(txt: String, pat: String) {
    val n = txt.length
    val m = pat.length

    val lps = generateLPS(pat)
    var i = 0 // txt iterator
    var j = 0 // pat iterator

    while (i < n) {
        if (txt[i] == pat[j]) {
            i++
            j++
        } else {
            if (j > 0) j = lps[j - 1]
            else i++
        }
        if (j == m) {
            println("Pattern found at index ${i - j}")
            j = lps[j - 1]
        }

    }
}


/**
 * Generates the longest proper prefix (lps[ i ]) of pat[0..i] which is also a suffix of pat[0..i].
 * @param pat pattern
 * @return lps
 */
fun generateLPS(pat: String): IntArray {
    val lps = IntArray(pat.length) { 0 }
    var len = 0
    var i = 1

    while (i < pat.length) {
        if (pat[i] == pat[len]) lps[i++] = ++len
        else { // pat[i] != pat[len]
            if (len != 0) len = lps[len - 1]
            else lps[i++] = len
        }
    }

    return lps
}


/**
 * Test function of KMP's pattern searching algorithm.
 */
fun main() {
    val txt = "ABABDABACDABABCABAB"
    val pat = "ABABCABAB"
    searchPatternKMP(txt, pat) // 10
}
