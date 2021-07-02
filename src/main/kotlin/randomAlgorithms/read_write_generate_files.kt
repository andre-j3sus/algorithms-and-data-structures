package randomAlgorithms


import java.io.BufferedReader
import java.io.FileReader
import java.io.PrintWriter


/**
 * Creates a file reader.
 * @param fileName name of the file
 * @return reader
 */
fun createReader(fileName: String): BufferedReader {
    return BufferedReader(FileReader(fileName))
}


/**
 * Creates a file writer.
 * @param fileName name of the file
 * @return writer
 */
fun createWriter(fileName: String): PrintWriter {
    return PrintWriter(fileName)
}


/**
 * Generates a random string of variable length.
 * @param length length of the string
 * @return string
 */
fun getRandomString(length: Int) : String {
    val allowedChars = 'a'..'z'
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}
