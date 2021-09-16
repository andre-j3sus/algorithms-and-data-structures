package graphAlgorithms


import java.util.*
import kotlin.collections.HashMap


data class VertexData(var distance: Int, var parent: Int?)
data class Vertex(val id: Int, val neighbours: MutableList<Vertex>, var data: VertexData)


/**
 * Breadth-first search (BFS) is an algorithm for searching a tree data structure for a node that satisfies a given property.
 * It starts at the tree root and explores all nodes at the present depth prior to moving on to the nodes at the next depth level.
 */
fun bfs(graph: HashMap<Int, Vertex>, start: Vertex) {

    // Initialization of the graph
    for (vertex in graph) {
        vertex.value.data.distance = Int.MAX_VALUE
        vertex.value.data.parent = null
    }


    val queue = LinkedList<Vertex>()
    queue.add(start)
    start.data.distance = 0

    while (queue.isNotEmpty()) {
        val current = queue.poll()

        for (neighbour in current.neighbours) {
            if (neighbour.data.distance == Int.MAX_VALUE) {
                neighbour.data.distance = current.data.distance + 1
                neighbour.data.parent = current.id

                queue.add(neighbour)
            }
        }
    }

}
