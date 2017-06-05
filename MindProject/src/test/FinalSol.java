package test;

public class FinalSol {
	private int solution[][];
    private double finalDiff;
    private int nClust;
    private int n;
    private double centroid[];

    public FinalSol(int numbEle, int p) {
        solution = new int[p][numbEle];
        this.nClust = p;
        this.n = numbEle;
        finalDiff = 0;
        centroid= new double[p];
    }

    public int[][] getSolution() {
        return solution;
    }

    public void setSolution(int[][] solution) {
        this.solution = solution;
        calcTotalDiff();
    }

    public double getFinalDiff() {
        return finalDiff;
    }

    public void setCentroid(double[] centroid) {
        this.centroid = centroid;
    }

    private void calcTotalDiff() {

        for (int i = 0; i < nClust; i++) {
            for (int j = 0; j < n; j++) {
                if (solution[i][j] != -1) {
                    finalDiff += Math.abs(solution[i][j] - centroid[i]);
                }
            }
        }

    }

    @Override
    public String toString() {
        String st = "";
        st += "Total Sum of Diff of distances for this solution: " + finalDiff;
        st += "\nThe Final Clusters By Kmeans are as follows:\n";
        for (int i = 0; i < nClust; ++i) {
            st += "K" + (i + 1) + "{ ";
            for (int j = 0; solution[i][j] != -1 && j < n - 1; ++j) {
                st += solution[i][j] + " ";
            }
            st += "}\n";
        }
        return st;
    }

}
