package exercises;

import java.util.Scanner;


/* 
 * Write an program that takes from the standard input an expression without left parentheses and prints the equivalent
 * infix expression with the parentheses inserted. For example, given the input:
 * 1+2)*3-4)*5-6))) your program should print ((1+2)*((3-4)*(5-6)))
 * */

public class InfixExpression {

    private static final char CLOSING_PARENT = ')';
    private static final String OPENING_PARENT = "(";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the expression without left parentheses: ");
        String input = scanner.nextLine();
        scanner.close();
        
        String infixed = convertToInfix(input);
        System.out.println(infixed);
    	
      
     
    }

    public static String convertToInfix(String expression) {
    	
    	if( expression.indexOf(CLOSING_PARENT) < 0) return expression;
    	
    	
    	//total number of closing parentheses in the expression
    	int totalNumberOfClosingParent = 0;
    	
    	//counts the number of opening parentheses inserted to the front of the expression
    	int openingParenthesesInsertedToFront = 0;
    	
    	//counts the total number of opening parentheses inserted to the expression
    	int totalOpeningParentInserted = 0;
    	final int length = expression.length();//length of the expression argument
    	
    	for(int i = 0; i < expression.length(); i++)
    		if(expression.charAt(i) == CLOSING_PARENT)
    			totalNumberOfClosingParent++;
    	
    	//declares a builder of size equals the length of the correct expression
    	StringBuilder expressionBuilder = new StringBuilder(length+totalNumberOfClosingParent);//
    	expressionBuilder.append(expression);
    	
    	while(totalNumberOfClosingParent > totalOpeningParentInserted) {
    		int midInsertion = 0; //count the number of closing parentheses inserted anywhere in the expression,except the beginning of expression
    		
    		for(int index  = 0; (index + 1 < length && totalNumberOfClosingParent > totalOpeningParentInserted); index++) {
    			
    			if(index == 0) {
    				expressionBuilder.replace(index, index+1, OPENING_PARENT+(expressionBuilder.charAt(index)));
    				
    				openingParenthesesInsertedToFront++;
    				
    				totalOpeningParentInserted++;
    			}else if(expression.charAt(index) == CLOSING_PARENT && expression.charAt(index+1) != CLOSING_PARENT) {
    				
    				int nextIndexToReplace = getIndexToReplace(openingParenthesesInsertedToFront, midInsertion, index);
    				int limitOfIndexToReplace = nextIndexToReplace+1;
    				char characterToReplace = expressionBuilder.charAt(nextIndexToReplace);
    				replace(expressionBuilder, nextIndexToReplace, limitOfIndexToReplace, characterToReplace);
    				midInsertion++;
    				totalOpeningParentInserted++;
    			}
    		}
    		
    		
    		
    	}
		return expressionBuilder.toString();
    	    	    	
    	
    	    }

	private static StringBuilder replace(StringBuilder expressionBuilder, int nextIndexToReplace,
			int limitOfIndexToReplace, char characterToReplace) {
		return expressionBuilder.replace(nextIndexToReplace, limitOfIndexToReplace, characterToReplace+OPENING_PARENT);
	}

	private static int getIndexToReplace(int openingParenthesesInsertedToFront, int midInsertion, int index) {
		return index+1 + openingParenthesesInsertedToFront + midInsertion;
	}
    			
    	
    }
