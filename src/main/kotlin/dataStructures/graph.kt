package dataStructures


/**
 * In a Graph, we have a set of nodes (a.k.a vertices) and these nodes are connected with each other with
 * the help of some edges. The nodes or vertices are used to store data and this data can be used further.
 *
 * A graph is a type of non-linear data structure that is used to store data in the form of nodes and edges.
 */
interface Graph<I, D> {
    interface Vertex<I, D> {
        val id: I
        val data: D
        fun setData(newData: D): D
        fun getAdjacencies(): MutableSet<Edge<I>?>
    }

    interface Edge<I> {
        val id: I
        val adjacent: I
    }

    val size: Int
    fun addVertex(id: I, d: D): D?
    fun addEdge(id: I, idAdj: I): I?
    fun getVertex(id: I): Vertex<I, D>?
    fun getEdge(id: I, idAdj: I): Edge<I>?
    operator fun iterator(): Iterator<Vertex<I, D>>
}


/**
 * Class GraphStructure implemented with Graph.
 */
class GraphStructure<I, D> : Graph<I, D> {

    private val INITIAL_CAPACITY = 10
    val vertices = HashMap<I, Graph.Vertex<I, D>>(INITIAL_CAPACITY)


    class Vertex<I, D>(override val id: I, override var data: D) : Graph.Vertex<I, D> {
        private val adjacenciesSet = mutableSetOf<Graph.Edge<I>?>()


        override fun setData(newData: D): D {
            val oldData = data
            data = newData
            return oldData
        }


        override fun getAdjacencies(): MutableSet<Graph.Edge<I>?> {
            return adjacenciesSet
        }
    }


    class Edge<I>(override val id: I, override val adjacent: I) : Graph.Edge<I>


    override val size: Int
        get() {
            return vertices.size
        }

    override fun addVertex(id: I, d: D): D? {
        val vertex = getVertex(id)
        if (vertex != null) return null

        vertices[id] = Vertex(id, d)
        return d
    }


    override fun addEdge(id: I, idAdj: I): I? {
        val source = getVertex(id) ?: return null
        if (getEdge(id, idAdj) != null) return null

        source.getAdjacencies().add(Edge(id, idAdj))
        return idAdj
    }


    override fun getVertex(id: I): Graph.Vertex<I, D>? {
        return vertices[id]
    }


    override fun getEdge(id: I, idAdj: I): Graph.Edge<I>? {
        val source = getVertex(id) ?: return null
        source.getAdjacencies().forEach { edge ->
            if (edge!!.adjacent == idAdj) return edge
        }
        return null
    }


    override fun iterator(): Iterator<Graph.Vertex<I, D>> {
        return object : Iterator<Graph.Vertex<I, D>> {
            val iterator = vertices.iterator()

            override fun hasNext(): Boolean {
                return iterator.hasNext()
            }

            override fun next(): Graph.Vertex<I, D> {
                return iterator.next().value
            }
        }
    }
}


/**
 * Test function of the GraphStructure class.
 */
fun main() {
    val graph = GraphStructure<Int, String>()

    graph.addVertex(48280, "Jesus")
    graph.addVertex(48287, "Nyck")
    graph.addVertex(48253, "Karl")
    graph.addEdge(48280, 48287)
    graph.addEdge(48253, 48280)
    graph.addEdge(48287, 48253)

    for (vertex in graph) {
        println("${vertex.id}: ${vertex.data}")
    }
}
