// Jasarjan Singh
// 05/19/2022
// CSE 143
// TA: Himani Nijhawan
// Assesment Anagram Solver
// This program uses a dictionary to print all anagram phrases 
// of a given word or phrase using recursive backtracking

import java.util.*;

public class AnagramSolver {

    private Map<String, LetterInventory> wordMap;
    private List<String> dictionaryList;
    
    // pre: the dictionary is a non-empty collection of non-empty 
    //      sequences of letters and that it contains no duplicates
    // post: initializes a new AnagramSolver object that will use the 
    //       given list as its dictionary
    public AnagramSolver(List<String> dictionary) {
        dictionaryList = dictionary;
        processDictionary(dictionary);
    }

    // post: "pre-proccesses" each word in the given dictionary list and
    //       stores each word along with the letters that make up the word
    private void processDictionary(List<String> dictionary){
        wordMap = new HashMap<String, LetterInventory>();
        for (String word : dictionaryList) {
            wordMap.put(word, new LetterInventory(word));
        }
    }

    // pre: throws an IllegalArgumentException if max number of words is less than 0,
    //      if max == 0 there is no upper limit to the number of words in the anagram
    // post: uses recursive backtracking to find combinations of words 
    //       that have the same letters as the given string (text).
    //       Each recursive call searches the dictionary to explore each word that is 
    //       a match for the current set of letters.
    //       Words are examined in the same order in which they appear in the dictionary.
    public void print(String text, int max) {
        if (max < 0) {
            throw new IllegalArgumentException();
        }
        LetterInventory phrase = new LetterInventory(text);
        List<String> currentDictionary = new ArrayList<String>();
        for (String word : dictionaryList) {
            if (phrase.subtract(wordMap.get(word)) != null) {
			    currentDictionary.add(word);
			}
		}
        print(phrase, max, new Stack<String>(), currentDictionary);
    }

    // pre: must contain the phrase of organized letters to be anagrammed
    //      the max words in each anagram, the stack of produced anagrams,
    //      and the list of words that can be created from the user input
    // post: Prints all combinations of words from the dictionary that 
    //       are anagrams of text and that include at most max words 
    //       (or an unlimited number of words if max is 0)
    private void print(LetterInventory phrase, int max, Stack<String> stack, 
                       List<String> currentDictionary) {
        if (phrase.isEmpty()) {
            System.out.println(stack.toString());
        } 
        if (max == 0 || max != stack.size()) {
            for (String word : currentDictionary) {
                LetterInventory compare = phrase.subtract(wordMap.get(word));
                if (compare != null) {
				    stack.push(word);
					print(compare, max, stack, currentDictionary);
					stack.pop();
				}
            }
        }
    }
}
