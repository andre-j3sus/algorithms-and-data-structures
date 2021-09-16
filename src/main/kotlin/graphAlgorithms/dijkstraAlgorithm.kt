package graphAlgorithms

import java.util.*
import kotlin.collections.HashMap


/**
 * Dijkstra's algorithm is an algorithm for finding the shortest paths between nodes in a graph, which may represent,
 * for example, road networks.
 *
 * It's used in weighted graphs.
 */
fun dijkstra(graph: HashMap<Int, Vertex>, start: Vertex) {
    val pq = PriorityQueue<Vertex>()

    for (entry in graph) {
        entry.value.data.distance = if (entry.value == start) 0 else Int.MAX_VALUE
        entry.value.data.parent = null

        pq.add(entry.value)
    }

    while (pq.isNotEmpty()) {
        val current = pq.poll()

        for (neighbour in current.neighbours) {
            val calculatedCost = current.data.distance + getDistance(neighbour, current)
            if (neighbour.data.distance > calculatedCost) {
                neighbour.data.distance = calculatedCost
                pq.remove(neighbour); pq.add(neighbour) // Update the vertex priority
            }
        }
    }
}


fun getDistance(v1: Vertex, v2: Vertex): Int{
    // TODO("To be implemented")
    return (0..10).random()
}
