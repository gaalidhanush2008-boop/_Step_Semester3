/**
 * Week 5 - Problem 4 (Intermediate): Match Day Grid Analyzer
 * rowAverage() only computes one match's average; the outer loop decides Power Surge vs Normal.
 * Rows may have different lengths (jagged array).
 */
public class P4_MatchDayGridAnalyzer {

    private static double rowAverage(int[] row) {
        if (row.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int runs : row) {
            sum += runs;
        }
        return (double) sum / row.length;
    }

    static String classifyMatches(int[][] runsPerOver, int threshold) {
        StringBuilder result = new StringBuilder();

        for (int match = 0; match < runsPerOver.length; match++) {
            double average = rowAverage(runsPerOver[match]);   // called once per match
            String label = (average >= threshold) ? "Power Surge" : "Normal";

            if (match > 0) {
                result.append(" | ");
            }
            result.append("Match ").append(match).append(": ").append(label);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        int[][] grid = {
                {4, 6, 8},
                {10, 12, 14},
                {2, 3, 1}
        };
        System.out.println(classifyMatches(grid, 8)); // Match 0: Normal | Match 1: Power Surge | Match 2: Normal
    }
}
