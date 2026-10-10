import java.util.Arrays;

/**
 * Week 5 - Problem 5 (Advanced): Fantasy League Auto-Draft Ranking Engine
 * Topics: method overloading, static methods, Comparable + Arrays.sort, encapsulation.
 *
 * Draft rules (thresholds chosen by us):
 *   - Established player: 10 or more matches played  -> draftable regardless of fitness
 *   - Everyone else: at least 5 matches AND not injured
 * Ranking: fantasy points (batting average) in descending order.
 */
public class P5_AutoDraftRankingEngine {

    static final int ESTABLISHED_MATCHES = 10;
    static final int MIN_MATCHES_WITH_FITNESS = 5;

    // Overload 1: experience alone
    static boolean isDraftable(int matchesPlayed) {
        return matchesPlayed >= ESTABLISHED_MATCHES;
    }

    // Overload 2: experience + fitness
    static boolean isDraftable(int matchesPlayed, boolean injured) {
        return matchesPlayed >= MIN_MATCHES_WITH_FITNESS && !injured;
    }

    static String draftAndRank(Player[] players) {
        // Count draftable players first so the array can be sized exactly
        int count = 0;
        for (Player p : players) {
            if (isDraftable(p.getMatchesPlayed()) || isDraftable(p.getMatchesPlayed(), p.isInjured())) {
                count++;
            }
        }

        Player[] draftable = new Player[count];
        int index = 0;
        for (Player p : players) {
            if (isDraftable(p.getMatchesPlayed()) || isDraftable(p.getMatchesPlayed(), p.isInjured())) {
                draftable[index++] = p;
            }
        }

        Arrays.sort(draftable);   // uses Player.compareTo -> descending by fantasy points

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < draftable.length; i++) {
            if (i > 0) {
                result.append(" | ");
            }
            result.append(i + 1).append(". ").append(draftable[i].getName());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Player[] players = {
                new Player("Virat", 15, 48.0, false),
                new Player("Rahul", 7, 55.0, false),
                new Player("Sameer", 3, 60.0, false),
                new Player("Dev", 12, 20.0, true)
        };
        System.out.println(draftAndRank(players)); // 1. Rahul | 2. Virat | 3. Dev
    }
}

class Player implements Comparable<Player> {
    private final String name;
    private final int matchesPlayed;
    private final double battingAverage;   // used as the fantasy points score
    private final boolean injured;

    public Player(String name, int matchesPlayed, double battingAverage, boolean injured) {
        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.battingAverage = battingAverage;
        this.injured = injured;
    }

    public String getName() { return name; }
    public int getMatchesPlayed() { return matchesPlayed; }
    public double getBattingAverage() { return battingAverage; }
    public boolean isInjured() { return injured; }

    @Override
    public int compareTo(Player other) {
        // Reversed arguments => descending order
        return Double.compare(other.battingAverage, this.battingAverage);
    }
}
