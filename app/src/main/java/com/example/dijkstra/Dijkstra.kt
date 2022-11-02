package com.example.dijkstra

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import org.json.JSONStringer
import java.util.PriorityQueue

data class NodeCost(val n: Int,val c:Int)
data class Path(val nodes: List<Int>,val cost: Int)

class Dijkstra(val graph: Graph) {

    fun pathfind(s: Int, e: Int):Path? {

        // previous node
        val previous = MutableList(graph.nodeCount) { -1 }
        // current min cost from source
        val costs = MutableList(graph.nodeCount) { -1 }
        // distance to start from start is 0
        costs[s] = 0;

        val queue = PriorityQueue<NodeCost>(compareBy{it.c})

        queue.add(NodeCost(s,0))

        while (!queue.isEmpty()) {
            val current = queue.remove()

            for ( vertice in graph.adjacencyList[current.n] ){
                if(current.c + vertice.c > costs[vertice.n] && costs[vertice.n] != -1) continue

                costs[vertice.n] = current.c + vertice.c
                previous[vertice.n] = current.n
                queue.add(NodeCost(vertice.n,costs[vertice.n]))

            }

        }

        if(previous[e] == -1) return null

        //calculate path
        var path: MutableList<Int> = mutableListOf()
        var i = e
        do{
            path.add(i)
            i = previous[i]
        }while(i != s)
        return Path(path.reversed(),costs[e])

    }

}