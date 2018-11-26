package ca.utoronto.utm.regexfsm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFSMPractice {
	public static void main(String[] args) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		try {
			while ((line = in.readLine()) != null) {
				if (recognizeSomethingRegex(line)) {
					System.out.println("recognizeSomethingRegex accepts " + line);
				} else {
					System.out.println("recognizeSomethingRegex rejects " + line);
				}
				if (recognizeSomethingFSM(line)) {
					System.out.println("recognizeSomethingFSM accepts " + line);
				} else {
					System.out.println("recognizeSomethingFSM rejects " + line);
				}
				if (recognizeWellFedRegex(line)) {
					System.out.println("recognizeWellFedRegex accepts " + line);
				} else {
					System.out.println("recognizeWellFedRegex rejects " + line);
				}
				if (recognizeWellFedFSM(line)) {
					System.out.println("recognizeWellFedFSM accepts " + line);
				} else {
					System.out.println("recognizeWellFedFSM rejects " + line);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Come up with some strings so that recognizeSomethingRegex returns true
	 * 
	 * 5aaa:xend
	 * 6aei:zwdend
	 * 9aei:z  eend
	 * 
	 * Describe what this recognizes.
	 * 
	 * The first character must be a digit from 0-9, the next three character must be vowels, 
	 * and then a colon, and then an x or y or z, and then any number of characters the user 
	 * wants to enter, and finally, it must end with the string 'end'.
	 * 
	 */
	
	public static boolean recognizeSomethingRegex(String s) {
		Pattern p = Pattern.compile("^[0-9]+[aeiou]{3}:(x|y|z)(.*)end$");
		Matcher m = p.matcher(s);
		if (m.matches()) {
			System.out.println("Groupcount: " + m.groupCount());
			System.out.println("Group 1: "+m.group(1));
			System.out.println("Group 2: "+m.group(2));
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean recognizeSomethingRegexToFSM(String s) {
		char [] c=s.toCharArray();
		int len=s.length();
		// We can now access the characters of s one at a time via c[0], c[1], ..., c[len-1]
		
		boolean retVal=true;
		
		int n=0;
		int state=0; // Start out in the initial state
		while(n<len){
			switch(state){	
				case 0:
					if ('0'<=c[n] && c[n]<='9')
						state = 1;
					else 
						state=10;
					break;
				case 1:
					if (c[n] == 'a' ||c[n] == 'e' ||c[n] == 'i' ||c[n] == 'o' ||c[n] == 'u' ) 
						state = 2;
					else 
						state=10;
					break;
				case 2:
					if (c[n] == 'a' ||c[n] == 'e' ||c[n] == 'i' ||c[n] == 'o' ||c[n] == 'u' ) 
						state = 3;
					else 
						state=10;
					break;
				case 3:
					if (c[n] == 'a' ||c[n] == 'e' ||c[n] == 'i' ||c[n] == 'o' ||c[n] == 'u' ) 
						state = 4;
					else 
						state=10;
					break;
				case 4:
					if (c[n] == ':' ) 
						state = 5;
					else 
						state=10;
					break;
				case 5:
					if(n!= len-4) 
						state = 5;
					else if (n == len -4)
						state = 6;
					else
						state = 10;
					break;
				case 6:
					if (c[n] == 'e') 
						state = 7;
					else 
						state=10;
					break;
				case 7:
					if (c[n] == 'n') 
						state = 8;
					else 
						state=10;
					break;
				case 8:
					if (c[n] == 'd') 
						state = 7;
					else 
						state=10;
					break;
				case 10:
					break;

			}
			n = n + 1;
		}
		if (state == 10)
			retVal=false;
		
		return retVal;
		
	}
	
	
	/**
	 * Come up with some strings so that recognizeSomethingFSM returns true
	 * 
	 * 12340
	 * 0
	 * 75980
	 * 000
	 * 
	 * Describe what this recognizes.
	 * 
	 * Return true iff the string s only has numbers between 0-9 numbers in it, and has a '0' at the end 
	 * 
	 */
	public static boolean recognizeSomethingFSM(String s) {
		char [] c=s.toCharArray();
		int len=s.length();
		// We can now access the characters of s one at a time via c[0], c[1], ..., c[len-1]
		
		boolean retVal=true;
		
		int n=0;
		int state=0; // Start out in the initial state
		while(n<len){
			switch(state){	
				case 0:
					if ('1'<=c[n] && c[n]<='9')
						state = 0;
					else if (c[n] == '0')
						state = 1;
					else 
						state=2;
					break;
				case 1:
					if (c[n] == '0') 
						state = 1;
					else if ('1'<=c[n] && c[n]<='9')
						state = 0;
					else 
						state=2;
					break;
				case 2:
					break;
			}
			n = n + 1;
		}
		if (state != 1)
			retVal=false;
		
		return retVal;
	}
	
    public static boolean recognizeSomethingFSMToRegex(String s) {
    	Pattern p = Pattern.compile("^[0-9]*[0]$");
		Matcher m = p.matcher(s);
		return m.matches();
    	
		
	}
	

	/**
	 * 
	 * A well-fed number is an integer in which 7s appear but 9s do not. (Why? Because seven eight nine.) 
	 * This means the number can be made up of any digits EXCEPT 9, and must include AT LEAST ONE 7.
	 * This recognizer uses a regular expression to determine if the string given is a well-fed number.
	 * 
	 * @param s
	 * @return whether s is the string representation of a well-fed number
	 */
	public static boolean recognizeWellFedRegex(String s) {
		
		// COMPLETE THIS METHOD
		
		Pattern p = Pattern.compile("^[^9]*7+[^9]*$");
		Matcher m = p.matcher(s);
		return m.matches();
	}

	/**
	 * This recognizer uses a Finite State Machine to determine whether s
	 * represents a well-fed number (as defined above)
	 * 
	 * @param s
	 * @return whether s is the string representation of a well-fed number
	 */
	public static boolean recognizeWellFedFSM(String s) {
		// COMPLETE THIS METHOD
		
		char [] c=s.toCharArray();
		int len=s.length();
		
		boolean retVal=true;
		
		int n=0;
		int state=0; // Start out in the initial state
		while(n<len){
			switch(state){	
				case 0:
					if (c[n] != 9)
						state = 0;
					else if (c[n] == '7')
						state = 1;
					else 
						state=2;
					break;
				case 1:
					if (c[n] != 9)
						state = 1;
					else 
						state=2;
					break;
				case 2:
			
					break;
			}
			n=n+1;
		}
		if (state !=1) {
			retVal = false;
		}
		return retVal;
	}
}