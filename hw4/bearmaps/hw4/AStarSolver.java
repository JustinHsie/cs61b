package bearmaps.hw4;

import java.util.HashMap;
import java.util.List;
import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private double solutionWeight;
    private List<Vertex> solution;
    private double timeSpent;
    private ExtrinsicMinPQ<Vertex> PQ;
    private HashMap<Vertex, Double> distTo;
    private AStarGraph<Vertex> G;
    private Vertex goal;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        PQ = new ArrayHeapMinPQ<>();
        distTo = new HashMap<>();
        G = input;
        goal = end;

        PQ.add(start, G.estimatedDistanceToGoal(start, end));
        distTo.put(start, 0.0);

        while(PQ.size() != 0 || !PQ.getSmallest().equals(end) || timeSpent != timeout) {
            Vertex p = PQ.removeSmallest();
            List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(p);
            for (WeightedEdge<Vertex> e : neighborEdges) {
                distTo.putIfAbsent(e.from(), Double.POSITIVE_INFINITY);
                distTo.putIfAbsent(e.to(), Double.POSITIVE_INFINITY);
                relax(e);
            }
        }
        timeSpent = sw.elapsedTime();
    }

    private void relax(WeightedEdge<Vertex> e) {
        Vertex p = e.from();
        Vertex q = e.to();
        double w = e.weight();
        if (distTo.get(p) + w < distTo.get(q)) {
            distTo.replace(q, distTo.get(p) + w);
            if (PQ.contains(q)) {
                PQ.changePriority(q, distTo.get(q) + G.estimatedDistanceToGoal(q, goal));
            }
            else {
                PQ.add(q, distTo.get(q) + G.estimatedDistanceToGoal(q, goal));
            }
        }
    }

    @Override
    public SolverOutcome outcome() {return outcome;}

    @Override
    public List<Vertex> solution() {return solution;}

    @Override
    public double solutionWeight() {return solutionWeight;}

    @Override
    public int numStatesExplored() {return 0;}

    @Override
    public double explorationTime() {return timeSpent;}
}
