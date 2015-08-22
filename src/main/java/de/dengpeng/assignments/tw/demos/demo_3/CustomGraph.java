/*
 * This is an sample class definition that extends the JGraphT SimpleDirectedWeightedGraph class, it is found in this address below:
 * http://stackoverflow.com/questions/30784655/extending-simpleweightedgraph-jgrapht
 */
package de.dengpeng.assignments.tw.demos.demo_3;

import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class CustomGraph extends SimpleWeightedGraph<Node, DefaultWeightedEdge>{

    /**
	 * 
	 */
	private static final long serialVersionUID = -7649904682690194371L;

	/**
     * Creates a new simple weighted graph with the specified edge factory.
     *
     * @param ef the edge factory of the new graph.
     */
    public CustomGraph(EdgeFactory<Node, DefaultWeightedEdge> ef)
    {
        super(ef);
    }

    /**
     * Creates a new simple weighted graph.
     *
     * @param edgeClass class on which to base factory for edges
     */
    public CustomGraph(Class<? extends DefaultWeightedEdge> edgeClass)
    {
        this(new ClassBasedEdgeFactory<Node, DefaultWeightedEdge>(edgeClass));
    }

}