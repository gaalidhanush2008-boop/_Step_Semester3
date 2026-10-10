/**
 * Week 2 - Problem 2: Word Reversal Encoder
 * Reverses every word individually while keeping the word order.
 * Concepts: split(), StringBuilder, loops, joining strings.
 */
public class P2_WordReversalEncoder {

    static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            StringBuilder reversed = new StringBuilder();
            for (int j = words[i].length() - 1; j >= 0; j--) {
                reversed.append(words[i].charAt(j));
            }
            result.append(reversed);
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseEachWord("hello club")); // olleh bulc
    }
}
