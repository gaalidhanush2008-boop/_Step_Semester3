import java.util.Arrays;

/**
 * Week 5 - Problem 1 (Easy): Fantasy Team Score Multiplier
 * Arrays are passed by reference, so the method changes the caller's array directly.
 */
public class P1_FantasyScoreMultiplier {

    static void applyMultipliers(double[] playerScores, int captainIndex, int viceCaptainIndex) {
        playerScores[captainIndex] = playerScores[captainIndex] * 2;          // Captain: 2x
        playerScores[viceCaptainIndex] = playerScores[viceCaptainIndex] * 1.5; // Vice-Captain: 1.5x
    }

    public static void main(String[] args) {
        double[] scores = {40, 55, 30, 62};
        applyMultipliers(scores, 1, 3);
        System.out.println(Arrays.toString(scores)); // [40.0, 110.0, 30.0, 93.0]
    }
}
