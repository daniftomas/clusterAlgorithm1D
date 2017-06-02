package test;

public class FinalSol {
	private int solution[][];
    private double finalDiff;
    private int p;
    private int n;
    private double centroid[];

    public FinalSol(int numbEle, int p) {
        solution = new int[p][numbEle];
        this.p = p;
        this.n = numbEle;
        finalDiff = 0;
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

        for (int i = 0; i < p; i++) {
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
        st += "Total Diff for this solution: " + finalDiff;
        st += "\nThe Final Clusters By Kmeans are as follows:\n";
        for (int i = 0; i < p; ++i) {
            st += "K" + (i + 1) + "{ ";
            for (int j = 0; solution[i][j] != -1 && j < n - 1; ++j) {
                st += solution[i][j] + " ";
            }
            st += "}\n";
        }
        return st;
    }

}
