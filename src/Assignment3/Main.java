package Assignment3;
public class Main {
    public static void main(String[] args) {
        Sorter sorter = new Sorter();
        Searcher searcher = new Searcher();
        Expirement experiment = new Expirement(sorter, searcher);
        experiment.runAllExperiments();
    }
}