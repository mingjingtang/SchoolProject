package project2;
import edu.duke.*;

/**
 * Created by mingjingtang on 12/4/16.
 */

public class cryptography {
	public char encrypt(char input, int key){
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
		int idx = alphabet.indexOf(input);
		if(idx != -1){
			char newChar = shiftedAlphabet.charAt(idx);
			return newChar;
		}
		else
			return ' ';
	}

	public String encryptMessage(String messageOrg, String keyFile){
		StringBuilder encrypted = new StringBuilder(messageOrg);
		for(int i = 0; i < messageOrg.length()-1; i++){
			char nc = messageOrg.charAt(i);
			char keychar = keyFile.charAt(i);
			if(nc != ' '){
				System.out.println("this character is: "+ nc);
				if(keychar != ' '){
					int key = Character.getNumericValue(keychar);
					System.out.println("the key is: " + key);
					char encryptChar = encrypt(nc,key);
					encrypted.setCharAt(i, encryptChar);
				}
				else{
					encrypted.setCharAt(i,' ');
				}
			}
			else{
				encrypted.setCharAt(i,' ');
			}
		}
		return encrypted.toString();
	}

	public String decryptMessage(String encryptMessage, String keyFile){
		StringBuilder decrypted = new StringBuilder(encryptMessage);
		for(int i = 0; i < encryptMessage.length()-1; i++){
			char nc = encryptMessage.charAt(i);
			char keychar = keyFile.charAt(i);
			if (nc != ' ') {
				System.out.println("this character is: "+ nc);
				if(keychar != ' '){
					int key = Character.getNumericValue(keychar);
					int realKey = 26 - key;
					System.out.println("the key for decryptMessage is: "+ realKey);
					char decryptChar = encrypt(nc,realKey);
					decrypted.setCharAt(i, decryptChar);
				}
				else{
					decrypted.setCharAt(i,' ');
				}
			}
			else{
				decrypted.setCharAt(i,' ');
			}
		}
		return decrypted.toString();
	}

	public void test(){
		FileResource fr1 = new FileResource();
		String messageOrg = fr1.asString();
		System.out.println("The Original Message.txt is: "+ messageOrg);

		FileResource fr2 = new FileResource();
		String keyFile = fr2.asString();
		System.out.println("The Key for the Message is: "+ keyFile);

		String encryptedMessage = encryptMessage(messageOrg,keyFile);
		System.out.println("The encrypted Message is: "+ encryptedMessage);

		String decryptMessage = decryptMessage(encryptedMessage, keyFile);
		System.out.println("The decrypted message is: "+ decryptMessage);
	}
}