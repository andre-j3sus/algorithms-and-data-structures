package graphAlgorithms


import kotlin.collections.HashMap


data class DFSVertexData(var distance: Int, var parent: Int?, var f: Int)
data class DFSVertex(val id: Int, val neighbours: MutableList<DFSVertex>, var data: DFSVertexData)


var time = 0


/**
 * Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures.
 * The algorithm starts at the root node (selecting some arbitrary node as the root node in the case of a graph) and
 * explores as far as possible along each branch before backtracking.
 */
fun dfs(graph: HashMap<Int, DFSVertex>) {
    time = 0

    // Initialization of the graph
    for (vertex in graph) {
        vertex.value.data.parent = null
    }

    for (vertex in graph) {
        if (vertex.value.data.parent == null) {
            dfsVisit(vertex.value)
        }
    }

}


fun dfsVisit(vertex: DFSVertex) {
    vertex.data.distance = ++time
    for (neighbour in vertex.neighbours) {
        if (neighbour.data.parent == null) {
            neighbour.data.parent = vertex.id
            dfsVisit(neighbour)
        }
    }
    vertex.data.f = ++time // Vertex is finished
}
