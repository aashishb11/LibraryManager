package main.CapaDomini.Models;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Class Written by : Aashish Bhusal & Mario
 */

public class BooleanParser {
    /**
     * Constructor for the BooleanParser class.
     */
    public BooleanParser(){
    }

    /**
     * Preprocesses the given expression by adding spaces around parentheses and quotation marks.
     * @param expression the expression to preprocess
     * @return the preprocessed expression
     */
    public String preprocess(String expression) {
        // Add spaces around parentheses and quotation marks
        expression = expression.replace("(", "( ").replace(")", " )");
        expression = expression.replace("\"", " \" ");
        expression = expression.replace("{", "{ ").replace("}", " }");
        return expression;
    }
    /**
     * Preprocesses the given list of tokens by removing empty strings.
     * @param tokens the list of tokens to preprocess
     * @return the preprocessed list of tokens
     */
    public String[] preprocessTokens(String[] tokens) {
        // Create a list to store the non-empty tokens
        ArrayList<String> filter = new ArrayList<String>();
        // Iterate through the list of tokens
        for (String token: tokens) {
            // If the token is not empty, add it to the list
            if (!token.equals("")) filter.add(token);
        }
        // Convert the list to an array and return it
        return filter.toArray(new String[filter.size()]);
    }

    /**
     * Parses the given boolean expression and returns the result.
     * @param expression the boolean expression to parse
     * @param d the document to check for the presence of the words in the expression
     * @return the result of parsing the boolean expression
     */

    public Boolean parserBooleanExpresion(String expression, Document d) {
        // Preprocess the expression
        expression = preprocess(expression);
        // Split the expression into tokens
        String[] tokens = expression.split(" ");
        // Preprocess the tokens
        tokens = preprocessTokens(tokens);
        // Parse the "or" expression
        return parserOrExpression(tokens, d);
    }

    /**
     * Parses the given "or" expression and returns the result.
     * @param tokens the tokens of the "or" expression
     * @param d the document to check for the presence of the words in the expression
     * @return the result of parsing the "or" expression
     */
    public Boolean parserOrExpression(String[] tokens, Document d) {
        // Initialize a counter for the number of open parentheses
        int parenthesis = 0;
        // Iterate through the tokens
        for (int i = 0; i < tokens.length; ++i) {
            // If the token is an open parenthesis, increment the counter
            if (tokens[i].equals("(")) ++parenthesis;
                // If the token is a close parenthesis, decrement the counter
            else if(tokens[i].equals(")")) --parenthesis;
                // If the token is an OR operator and there are no open parentheses, parse the OR expression
            else if(tokens[i].equals("|") && parenthesis==0) {
                // Return the result of parsing the OR expression by checking if either of the two subexpressions
                // are true
                return (
                        parserOrExpression(Arrays.copyOfRange(tokens, 0, i), d) ||
                                parserOrExpression(Arrays.copyOfRange(tokens, i+1, tokens.length), d)
                );
            }
        }
        // If none of the OR operators are found, parse the AND expression instead
        return parserAndExpression(tokens, d);
    }

    /**
     * Parses the given "and" expression and returns the result.
     * @param tokens the tokens of the "and" expression
     * @param d the document to check for the presence of the words in the expression
     * @return the result of parsing the "and" expression
     */
    public Boolean parserAndExpression(String[] tokens, Document d) {
        // Initialize a counter for the number of open parentheses
        int parenthesis = 0;
        // Iterate through the tokens
        for (int i = 0; i < tokens.length; ++i) {
            // If the token is an open parenthesis, increment the counter
            if (tokens[i].equals("(")) ++parenthesis;
                // If the token is a close parenthesis, decrement the counter
            else if(tokens[i].equals(")")) --parenthesis;
                // If the token is an "and" operator and there are no open parentheses,
                // return the result of parsing the two subexpressions
            else if(tokens[i].equals("&") && parenthesis==0) {
                return (
                        parserOrExpression(Arrays.copyOfRange(tokens, 0, i), d) &&
                                parserOrExpression(Arrays.copyOfRange(tokens, i + 1, tokens.length), d)
                );
            }
        }
        // Return the result of parsing the parenthesis expression
        return parserParenthesisExpression(tokens, d);
    }
    /**
     * Parses the given parenthesis expression and returns the result.
     * @param tokens the tokens of the parenthesis expression
     * @param d the document to check for the presence of the words in the expression
     * @return the result of parsing the parenthesis expression
     */
    public Boolean parserParenthesisExpression(String[] tokens, Document d) {
        // If the first token is an open parenthesis, parse the expression within the parentheses
        if (tokens[0].equals("(")) {
            parserOrExpression(Arrays.copyOfRange(tokens, 1, tokens.length), d);
        }
        // Return the result of parsing the expression
        return parserExpression(tokens, d);
    }
    /**
     * Parses the given expression and returns the result.
     * @param tokens the tokens of the expression
     * @param d the document to check for the presence of the words in the expression
     * @return the result of parsing the expression
     */
    public Boolean parserExpression(String[] tokens, Document d) {
        // Get the first token of the expression
        String firstString = tokens[0];
        // If the expression consists of only one token, return the result of parsing the token
        if (tokens.length == 1) return parserExpression(firstString, d);
        // Depending on the first token, parse the expression differently
        switch (firstString) {
            // If the first token is a quotation mark, parse the full sentence
            case "\"":
                return parserFullSentence(String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length-1)), d);
            case "{":
                // If the first token is an open curly brace, parse the set of words
                return parserSetWords(Arrays.copyOfRange(tokens, 1, tokens.length - 1), d);
        }
        // If none of the above cases are met, return false
        return false;
    }
    /**
     * Parses the given token and returns the result.
     * @param token the token to parse
     * @param d the document to check for the presence of the word
     * @return the result of parsing the token
     */
    private Boolean parserExpression(String token, Document d) {
        // If the token starts with an exclamation mark, return the negation of the presence of the word
        // in the document
        if (token.startsWith("!")) return !d.hasToken(token.substring(1));
        // Otherwise, return the presence of the word in the document
        return d.hasToken(token);
    }

    /**
     * Parses the given full sentence and returns the result.
     * @param sentence the full sentence to parse
     * @param d the document to check for the presence of the sentence
     * @return the result of parsing the full sentence
     */

    private Boolean parserFullSentence(String sentence, Document d) {
        // Remove the quotation marks from the sentence
        sentence = sentence.replace("\"", "");
        // Return the presence of the sentence in the document
        return d.hasSentence(sentence);
    }
    /**
     * Parses the given set of words and returns the result.
     * @param words the tokens of the set of words
     * @param d the document to check for the presence of the words
     * @return the result of parsing the set of words
     */
    private Boolean parserSetWords(String[] words, Document d) {
        for (String w: words) {
            if (!d.hasToken(w)) return false;
        }
        return true;
    }
    /**
     * Parses the given set of words and returns the result.
     *
     * @param tokens the tokens of the set of words
     * @param d the document to check for the presence of the words
     * @return the result of parsing the set of words
     */
    public Boolean parserSetWordss(String[] tokens, Document d) {
        // Initialize a list of words in the set
        ArrayList<String> wordList = new ArrayList<String>();
        // Iterate through the tokens
        for (int i = 0; i < tokens.length; ++i) {
            // If the token is a comma, add the previous token to the list of words
            if (tokens[i].equals(",")) {
                wordList.add(tokens[i - 1]);
            }
            // If the token is a close curly brace, add the previous token to the list of words
            // and return the result of checking if any of the words in the list are present in the document
            else if (tokens[i].equals("}")) {
                wordList.add(tokens[i - 1]);
                return d.hasAnyWord(wordList);
            }
        }
        // If none of the above cases are met, return false
        return false;
    }

}