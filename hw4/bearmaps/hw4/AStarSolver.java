package bearmaps.hw4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.DoubleMapPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private double solutionWeight;
    private List<Vertex> solution;
    private int numStatesExplored;
    private double timeSpent;
    private ExtrinsicMinPQ<Vertex> PQ;
    private HashMap<Vertex, Double> distTo;
    private HashMap<Vertex, WeightedEdge<Vertex>> edgeTo;
    private AStarGraph<Vertex> G;
    private Vertex start;
    private Vertex goal;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        PQ = new ArrayHeapMinPQ<>();
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        solution = new ArrayList<>();
        solutionWeight = 0.0;
        G = input;
        this.start = start;
        goal = end;

        PQ.add(start, G.estimatedDistanceToGoal(start, end));
        distTo.put(start, 0.0);
        edgeTo.put(start, null);

        while(true) {
            if (PQ.size() == 0) {
                outcome = SolverOutcome.UNSOLVABLE;
                solution.clear();
                solutionWeight = 0.0;
                return;
            }
            Vertex p = PQ.removeSmallest();
            timeSpent = sw.elapsedTime();
            if (p.equals(end)) {
                outcome = SolverOutcome.SOLVED;
                solution(p);
                return;
            }
            if (timeSpent >= timeout) {
                outcome = SolverOutcome.TIMEOUT;
                solution.clear();
                solutionWeight = 0.0;
                return;
            }

            List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(p);
            for (WeightedEdge<Vertex> e : neighborEdges) {
                distTo.putIfAbsent(e.to(), Double.POSITIVE_INFINITY);
                edgeTo.putIfAbsent(e.to(), e);
                relax(e);
            }
            numStatesExplored++;
        }
    }

    private void relax(WeightedEdge<Vertex> e) {
        Vertex p = e.from();
        Vertex q = e.to();
        double w = e.weight();
        if (distTo.get(p) + w < distTo.get(q)) {
            distTo.replace(q, distTo.get(p) + w);
            edgeTo.replace(q, e);
            if (PQ.contains(q)) {
                PQ.changePriority(q, distTo.get(q) + G.estimatedDistanceToGoal(q, goal));
            }
            else {
                PQ.add(q, distTo.get(q) + G.estimatedDistanceToGoal(q, goal));
            }
        }
    }

    private void solution(Vertex p) {
        if (p.equals(start)) {
            solution.add(start);
            return;
        }
        solution(edgeTo.get(p).from());
        solution.add(p);
        solutionWeight += edgeTo.get(p).weight();
    }

    @Override
    public SolverOutcome outcome() {return outcome;}

    @Override
    public List<Vertex> solution() {return solution;}

    @Override
    public double solutionWeight() {return solutionWeight;}

    @Override
    public int numStatesExplored() {return numStatesExplored;}

    @Override
    public double explorationTime() {return timeSpent;}
}
