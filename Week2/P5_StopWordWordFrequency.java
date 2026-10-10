import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Week 2 - Problem 5: Stop-Word-Filtered Word Frequency Report
 * Concepts: replace(), split("\\s+"), stop-word filtering, HashMap counting, sorting by value.
 */
public class P5_StopWordWordFrequency {

    private static final List<String> STOP_WORDS =
            List.of("the", "was", "and", "a", "is", "of", "in");

    static void printFilteredWordFrequency(String feedback) {
        // Normalize: lowercase and strip punctuation
        String cleaned = feedback.toLowerCase()
                .replace(".", "")
                .replace(",", "")
                .replace("!", "")
                .replace("?", "")
                .replace(";", "")
                .replace(":", "")
                .trim();

        // LinkedHashMap keeps first-seen order, so ties stay in a predictable order
        Map<String, Integer> counts = new LinkedHashMap<>();
        for (String word : cleaned.split("\\s+")) {
            if (word.isEmpty() || STOP_WORDS.contains(word)) {
                continue;
            }
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }

        // Sort by count, highest first (List.sort is stable, so ties keep insertion order)
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(counts.entrySet());
        entries.sort((a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<String, Integer> e : entries) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }

    public static void main(String[] args) {
        printFilteredWordFrequency("The mentor was great, the session was great and clear.");
        // great: 2
        // mentor: 1
        // session: 1
        // clear: 1
    }
}
