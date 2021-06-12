package dataStructures


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


class GraphStructure<I, D>: Graph<I, D> {

    private val INITIAL_CAPACITY = 10
    val vertices = HashMap<I, Graph.Vertex<I, D>>(INITIAL_CAPACITY)

    /**
     * Vértice do grafo
     * @property id idenfiticador
     * @property data dados associados
     */
    private class Vertex<I, D>(override val id: I, override var data: D) : Graph.Vertex<I, D>{
        private val adjacenciesSet = mutableSetOf<Graph.Edge<I>?>()

        /**
         * Atribui novos dados ao vértice
         * @param newData novos dados
         * @return dados antigos
         */
        override fun setData(newData: D): D {
            val oldData = data
            data = newData
            return oldData
        }

        /**
         * Devolve um conjunto de todas as arestas adjacentes ao vértice
         * @return conjunto das arestas ou conjunto vazio se não existirem
         */
        override fun getAdjacencies(): MutableSet<Graph.Edge<I>?> {
            return adjacenciesSet
        }
    }


    /**
     * Aresta adjacente a um vértice
     * @property id identificador do vértice origem
     * @property adjacent identificador do vértice destino
     */
    class Edge<I>(override val id: I, override val adjacent: I) : Graph.Edge<I>


    /**
     * Armazena o número de vértices existentes no grafo
     */
    override val size: Int
        get() {
            return vertices.size
        }

    /**
     * Adiciona um novo vértice ao grafo
     * @param id identificador do vértice
     * @param d dados do vértice
     * @return null se o vértice já existir, caso contrário os dados do vértice
     */
    override fun addVertex(id: I, d: D): D? {
        val vertex = getVertex(id)
        if (vertex != null) return null

        vertices[id] = Vertex(id, d)
        return d
    }


    /**
     * Adiciona uma nova aresta ao grafo
     * @param id identificador do vértice origem
     * @param idAdj identificador do vértice destino
     * @return null se o vértice de origem não existir, caso contrário o identificador do vértice adjacente
     */
    override fun addEdge(id: I, idAdj: I): I? {
        val source = getVertex(id) ?: return null
        if (getEdge(id, idAdj) != null) return null

        source.getAdjacencies().add(Edge(id, idAdj))
        return idAdj
    }


    /**
     * Obtém um vértice dado o seu identificador
     * @param id identificador do vértice
     * @return vértice se este existir, caso contrário null
     */
    override fun getVertex(id: I): Graph.Vertex<I, D>? {
        return vertices[id]
    }


    /**
     * Obtém uma aresta dados os identificadores do vértice origem e destino(adjacente)
     * @param id identificador do vértice origem
     * @param idAdj identificador do vértice destino
     * @return a aresta se esta existir, caso contrário null
     */
    override fun getEdge(id: I, idAdj: I): Graph.Edge<I>? {
        val source = getVertex(id) ?: return null
        source.getAdjacencies().forEach { edge ->
            if (edge!!.adjacent == idAdj) return edge
        }
        return null
    }


    /**
     * Retorna um objeto Iterator que permite a iteração dos vértices existentes no grafo
     */
    override fun iterator(): Iterator<Graph.Vertex<I, D>> {
        return object : Iterator<Graph.Vertex<I, D>>{
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


fun main(){
    val graph = GraphStructure<Int, String>()

    graph.addVertex(48280, "Jesus")
    graph.addVertex(48287, "Nyck")
    graph.addVertex(48253, "Karl")
    graph.addEdge(48280, 48287)
    graph.addEdge(48253, 48280)
    graph.addEdge(48287, 48253)

    for (vertex in graph){
        println("${vertex.id}: ${vertex.data}")
    }

}
