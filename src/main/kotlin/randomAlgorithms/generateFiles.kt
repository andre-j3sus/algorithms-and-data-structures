package randomAlgorithms


import java.io.BufferedReader
import java.io.FileReader
import java.io.PrintWriter

fun createReader(fileName: String): BufferedReader {
    return BufferedReader(FileReader(fileName))
}

fun createWriter(fileName: String?): PrintWriter {
    return PrintWriter(fileName)
}

fun getRandomString(length: Int) : String {
    val allowedChars = 'a'..'z'
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}


/**
 * Generates random files that I used to test a priority queue on a college project
 * @param numberOfElems number of Users in priority queue
 * @param fileName
 */
fun generatePQFile(numberOfElems: Int, fileName: String){
    val writer = createWriter(fileName)
    repeat(numberOfElems){
        val name = getRandomString((3..10).random())
        writer.println("1;$name;${(0..4).random()}")
    }
    repeat(numberOfElems){
        writer.println("2")
    }
    writer.println("4")
    writer.close()
}

fun main() {
    generatePQFile(100000, "test_100000.txt")
}
