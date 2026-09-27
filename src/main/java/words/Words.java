package words;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;


/*
 * The method converts the sentence to lowercase, splits it into words using a regular expression,
 * and returns a list of words in order of their first appearance.
*/
public class Words {
    public static List<String> getUniqueWordsFromSentence(String sentence) {
        
        String[] allWords = sentence.toLowerCase().split("[^a-zA-Z]+");
        
        // LinkedHashSet removes duplicates while preserving the original order.
        return new ArrayList<>(new LinkedHashSet<>(Arrays.asList(allWords)));
    }
}
