/**
 * @author Angelica Dick
 * Assignment 1 Password Checker
 */

package assignment1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

public class PasswordCheckerUtility {
	
	public PasswordCheckerUtility() {
		
	}
	/**
	 * Check if passwords are the same
	 * @throws UnmatchedException
	 */
	public static void comparePasswords(java.lang.String password,
            							java.lang.String passwordConfirm) 
            		throws UnmatchedException{
		
		if (!(password.equals(passwordConfirm))) {
			throw new UnmatchedException();
		}else {
			
		}
	}
	
	/**
	 * Confirms if passwords are the same. Case sensitive.
	 * @param password
	 * @param passwordConfirm
	 * @return
	 */
	public static boolean comparePasswordsWithReturn(java.lang.String password,
            										java.lang.String passwordConfirm) {
		
		boolean comparePasswords;
		
		if (password.equals(passwordConfirm)) {
			comparePasswords = true;
		}else {
			comparePasswords = false;
		}
		
		return comparePasswords;
	}
	
	/**
	 * Creates an array list of failed passwords and returns it with error code.
	 * 
	 * @param passwords
	 * @return
	 */
	public static java.util.ArrayList<java.lang.String> 
				getInvalidPasswords(java.util.ArrayList<java.lang.String> passwords) {
		
		ArrayList<String> invalidPasswords = new ArrayList<>();
		
		for (int i = 0; i < passwords.size(); i++) {
			
			if (passwords.get(i).length() < 6) {
			invalidPasswords.add(passwords.get(i) + " -> The password must be at least 6 characters long");
			}
			
			else if (isMatch("(.)*(\\d)(.)*", passwords.get(i))) {
				invalidPasswords.add(passwords.get(i) + " -> The password must contain at least one digit");
			}
			
			else if (isMatch("(?s).*[A-Z].*", passwords.get(i))) {
			invalidPasswords.add(passwords.get(i) + " -> The password must contain at least one uppercase alphabetic character");
			}
			
			else if (isMatch(".*[a-z].*", passwords.get(i))) {
			invalidPasswords.add(passwords.get(i) + " -> The password must contain at least one lower case alphabetic character");
			}
			
			else if (!isMatch("[a-zA-Z0-9]*", passwords.get(i))) {
			invalidPasswords.add(passwords.get(i) + " -> The password must contain at least one special character");
			}
			else if (invalidSequence(passwords.get(i))) {
			invalidPasswords.add(passwords.get(i) + " -> The password cannot contain more than two of the same character in sequence");	
			}
			
		}
		return invalidPasswords;	
	}
	
	/**
	 * Tests if password has between 6 and 9 characters. 
	 * @param password
	 * @return
	 */
	public static boolean hasBetweenSixAndNineChars(java.lang.String password) {
		boolean passwordCheck;
		
		if (password.length() < 6 || password.length() > 9) {
			passwordCheck = false;
		}else {
			passwordCheck = true;
		}
		return passwordCheck;
	}
	/**
	 * Checks if password has at least one digit
	 * @param password
	 * @return
	 * @throws NoDigitException
	 */
	public static boolean hasDigit(java.lang.String password)
            					throws NoDigitException {
		
		for (int i = 0; i < password.length(); i++) {	
			if (Character.isDigit(password.charAt(i))) {
			return true;	
			}
		}
		throw new NoDigitException("The password must contain at least one digit");
			//return true;
}
	/**
	 * Check if password has lower case requirement
	 * @param password
	 * @return
	 * @throws NoLowerAlphaException
	 */
	
	public static boolean hasLowerAlpha(java.lang.String password)
            throws NoLowerAlphaException{
		
		
		for (int i = 0; i < password.length(); i++) {
			
			if (Character.isLowerCase(password.charAt(i))) { 
			return true;
			}
		}
			throw new NoLowerAlphaException("The password must contain at least one lower case alphabetic character");
		
	}
	
	/**
	 * Checks if password has more than 2 of same character in sequence
	 * @param password
	 * @return
	 * @throws InvalidSequenceException
	 */
	public static boolean hasSameCharInSequence(java.lang.String password)
            throws InvalidSequenceException{
		
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) == password.charAt(i + 1) && 
					password.charAt(i) == password.charAt(i + 2)) {
				
				throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence");
			}
		}
		return false;
	}
	
	/**
	 * Checks if password has special character
	 * @param password
	 * @return
	 * @throws NoSpecialCharacterException
	 */
	public static boolean hasSpecialChar(java.lang.String password)
            throws NoSpecialCharacterException{
		String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
		if (password.contains(specialChars)) {
			return true;
		
		}else {
			throw new NoSpecialCharacterException("The password must contain at least one special character");
		}
		
	}
	/**
	 * Checks if password has Upper case character
	 * @param password
	 * @return
	 * @throws NoUpperAlphaException
	 */
	public static boolean hasUpperAlpha(java.lang.String password)
            throws NoUpperAlphaException{
		
			for (int i = 0; i < password.length(); i++) {
			
			if (Character.isUpperCase(password.charAt(i))) { 
			return true;
			}
			}
			throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
			
	}
	/**
	 * Checks is password is at least 6 characters
	 * @param password
	 * @return
	 * @throws LengthException
	 */
	public static boolean isValidLength(java.lang.String password)
            throws LengthException{
		
		if (password.length() >= 6) {
			return true;
		}else {
			throw new LengthException("The password must be at least 6 characters long");
		}
	}
	
	/**
	 * Return true if valid password (follows all rules from below), returns false if an invalid password
	 * @param password
	 * @return
	 * @throws LengthException
	 * @throws NoUpperAlphaException
	 * @throws NoLowerAlphaException
	 * @throws NoDigitException
	 * @throws NoSpecialCharacterException
	 * @throws InvalidSequenceException
	 */
	public static boolean isValidPassword(java.lang.String password)
            throws LengthException,
                   NoUpperAlphaException,
                   NoLowerAlphaException,
                   NoDigitException,
                   NoSpecialCharacterException,
                   InvalidSequenceException, UnmatchedException {
		
		boolean isValid;
		
		if (password.length() < 6) {
			throw new LengthException("The password must be at least 6 characters long");
		}
		else if (isMatch("(?s).*[A-Z].*", password)){
			throw new NoUpperAlphaException ("The password must contain at least one uppercase alphabetic character");
		}
		else if (isMatch(".*[a-z].*", password)) {
			throw new NoLowerAlphaException("The password must contain at least one lower case alphabetic character");
		}
		else if (isMatch("(.)*(\\d)(.)*", password)) {
			throw new NoDigitException("The password must contain at least one digit");
		}
		else if (!isMatch("[a-zA-Z0-9]*", password)) {
			throw new NoSpecialCharacterException("The password must contain at least one special character");
		}
		else if (invalidSequence(password)) {
			throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence");	
		}
		else {
			isValid = true;
		}
		
		return isValid;
		
	}
	
	/**
	 * Checks if password is valid but between 6 -9 characters
	 * @param password
	 * @return
	 * @throws WeakPasswordException
	 */
	public static boolean isWeakPassword(java.lang.String password)
            throws WeakPasswordException{
		
		boolean weekPassword = false;
		if (password.length() >= 6 && password.length() <= 9) {
			weekPassword = true;
			throw new WeakPasswordException("The password is OK but weak ");
		}
			return weekPassword;
	}
                  
	/**
	 * Method to check if password string matches low, alpha, digit and special character.
	 * @param regex
	 * @param password
	 * @return
	 */
	public static boolean isMatch (String regex, String password) {
		
		Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password); 
        
       return (!matcher.matches());
	}
	
	/**
	 * Method to check is password character is the same as two next ones.
	 * @param password
	 * @return
	 */
	public static boolean invalidSequence(String password) {
		
		char [] makeArray;
		makeArray = password.toCharArray();
		
		boolean invalidSequenceofChars = false;
		
		for (int index = 0; index < makeArray.length; index ++) {
		
		if(index > (makeArray.length - 3)) {
			break;
		}
			
		else if (makeArray[index] == makeArray[index + 1]
                    && makeArray[index] == makeArray[index + 2]) {
				invalidSequenceofChars = true;
			}
		}
		return invalidSequenceofChars;
	}
	
	
}
