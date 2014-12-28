package interfaces;

import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

public interface ICIA {
	public byte[] CIAEncrypter(String message, PrivateKey priKey);

	public String CIADecrypter(byte[] encrytedBytes, PublicKey pubKey);

	/**
	 * This method will create an enrypted file from your source file
	 * @param sourceFile is the source file you want to encrypted
	 * @param destFile is destination file you want to store
	 * @param priKey is your PrivateKey
 	 */
	public void CIAEncrypter(File sourceFile, File destFile, PrivateKey priKey);

	/**
	 * This method will decrypt your source file to your destination file
	 * @param sourceFile is the file you want to encrypt
	 * @param destFile is the decrypted destination file you want to store
	 * @param pubKey is the public key
	 */
	public void CIADecrypter(File sourceFile, File destFile, PublicKey pubKey);

	/**
	 * This method will encrypt your path with your private key in base64 text format
	 * 
	 * @param path
	 *             is the absolute path you want to encrypt
	 * @param priKey
	 *             is the PrivateKey of yours
	 * @return encrypted path text in base64 format
	 */
	public String encryptPath(String path, PrivateKey priKey);

	/**
	 * This method will decrypt your encrypted base64 path to a readable path
	 * @param pathBase64 is the encrypted path in base64 format
	 * @param pubKey is the PublicKey
	 * @return readable path
	 */
	public String decryptPath(String pathBase64, PublicKey pubKey);
}
