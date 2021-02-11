package assignment1;
/**
 * @author Angelica Dick
 * CMSC 204
 */

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerSTUDENT_Test {
	
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		Throwable exception = Assertions.assertThrows(LengthException.class, new Executable() {
			public void execute() throws Throwable {
				PasswordCheckerUtility.isValidLength("TestingLength");
				PasswordCheckerUtility.isValidLength("Lengt");
			}			
		});
		assertEquals("The password must be at least 6 characters long", exception.getMessage());
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		Throwable exception = assertThrows(NoUpperAlphaException.class, new Executable() {

			public void execute() throws Throwable {
				PasswordCheckerUtility.hasUpperAlpha("TestingAlpha");
				PasswordCheckerUtility.hasUpperAlpha("testingalpha");
			}			
		});
		assertEquals("The password must contain at least one uppercase alphabetic character", exception.getMessage());
		
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		Throwable exception = assertThrows(NoLowerAlphaException.class, new Executable() {

			public void execute() throws Throwable {
				PasswordCheckerUtility.hasLowerAlpha("testinglower");
				PasswordCheckerUtility.hasLowerAlpha("11234TEST");
			}			
		});
		assertEquals("The password must contain at least one lower case alphabetic character", exception.getMessage());		
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try {
			assertTrue(PasswordCheckerUtility.isWeakPassword("Pass2021!"));
		} catch (WeakPasswordException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		Throwable exception = assertThrows(InvalidSequenceException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				//PasswordCheckerUtility.hasSameCharInSequence("Testing2021@");
				PasswordCheckerUtility.hasSameCharInSequence("AAAbb@123");
			}			
		});
		assertEquals("The password cannot contain more than two of the same character in sequence", exception.getMessage());
		
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		Throwable exception = assertThrows(NoDigitException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasDigit("12345");
				PasswordCheckerUtility.hasDigit("testingdigit");
			}			
		});
		assertEquals("The password must contain at least one digit", exception.getMessage());
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try{
			assertEquals(true,PasswordCheckerUtility.isValidPassword("ValidPass21!"));
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		} 
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> invalidPasswordsArray = new ArrayList<>();
		
		invalidPasswordsArray.add("334455BB");
		invalidPasswordsArray.add("Im2cool4U");
		invalidPasswordsArray.add("george2ZZZ");
		invalidPasswordsArray.add("4sale");
		
		
		ArrayList<String> results = new ArrayList<>();
		
		results = PasswordCheckerUtility.getInvalidPasswords(invalidPasswordsArray);
		
		assertEquals(results.size(), 4);
		assertEquals(results.get(0), "334455BB -> The password must contain at least one lower case alphabetic character");
		assertEquals(results.get(1), "Im2cool4U -> The password must contain at least one special character");
		assertEquals(results.get(2), "george2ZZZ -> The password must contain at least one special character");
		assertEquals(results.get(3), "4sale -> The password must be at least 6 characters long");
		
	
	}
	
}
