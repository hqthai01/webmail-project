package api;

import interfaces.ICAPath;
import interfaces.ICIA;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

import util.CAUtils;
import util.TerminalUtils;

public class CIA implements ICIA {

	private Base64 base64;

	public CIA() {
		base64 = new Base64();
	}

	@Override
	public byte[] CIAEncrypter(String message, PrivateKey priKey) {
		try {
			Key key = getKey();
			byte[] storgeHash = md5(message).getBytes();

			byte[] mBytes = encrypter(message.getBytes(), key);

			byte[] keyBytes = encrypter(key.getEncoded(), priKey);
			byte[] hashBytes = encrypter(storgeHash, priKey);

			byte[][] a = { mBytes, keyBytes, hashBytes, new byte[] { (byte) (mBytes.length / 256), (byte) mBytes.length } };

			byte[] storgeBigArr = new byte[a[0].length + a[1].length + a[2].length + a[3].length];
			copyArray(a, storgeBigArr);
			return storgeBigArr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String CIADecrypter(byte[] encryptedBytes, PublicKey pubKey) {
		try {
			int mLength = encryptedBytes[encryptedBytes.length - 1];
			int count256 = encryptedBytes[encryptedBytes.length - 2];
			int key_hash_length = 128;

			mLength = calculateLength((byte) count256, (byte) mLength);

			byte[] eMessage = new byte[mLength];
			byte[] eKey = new byte[key_hash_length];
			byte[] eHash = new byte[key_hash_length];

			copyArray(encryptedBytes, eMessage, 0, 0, mLength);
			copyArray(encryptedBytes, eKey, mLength, 0, key_hash_length);
			copyArray(encryptedBytes, eHash, mLength + key_hash_length, 0, key_hash_length);

			byte[] keys = decrypter(eKey, pubKey);
			Key key = new SecretKeySpec(keys, "AES");
			byte[] message = decrypter(eMessage, key);
			byte[] hash = decrypter(eHash, pubKey);

			if (md5(new String(message)).equals(new String(hash))) {
				return new String(message);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void CIAEncrypter(File sourceFile, File destFile, PrivateKey priKey) {
		try {
			DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(sourceFile)));
			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(destFile)));
			Key key = getKey();
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] keyBytes = encrypter(key.getEncoded(), priKey);
			byte[] hashBytes = encrypter(md5(sourceFile).getBytes(), priKey);

			dos.write(hashBytes);
			dos.write(keyBytes);
			readWriteFile(dis, dos, cipher);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void CIADecrypter(File sourceFile, File destFile, PublicKey pubKey) {
		try {
			DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(sourceFile)));
			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(destFile)));

			byte[] buff = new byte[128];
			dis.read(buff);
			String hash = new String(decrypter(buff, pubKey));
			dis.read(buff);
			byte[] keyBytes = decrypter(buff, pubKey);

			Key key = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);

			readWriteFile(dis, dos, cipher);
			if (!hash.equals(md5(destFile))) {
				System.out.println("Your file is modifed");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public String encryptPath(String path, PrivateKey priKey) {
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, priKey);
			return base64.encodeAsString(cipher.doFinal(path.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String decryptPath(String pathBase64, PublicKey pubKey) {
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, pubKey);
			return new String(cipher.doFinal(base64.decode(pathBase64)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * A quick method to read and write file data with the cipher input
	 * 
	 * @param dis
	 *             is the data input stream of the file
	 * @param dos
	 *             is the data output stream of the file
	 * @param cipher
	 *             is EncryptCipher or DecyptCipher use to (en)decrypt the file data
	 */
	private void readWriteFile(DataInputStream dis, DataOutputStream dos, Cipher cipher) {
		try {
			int dataRead;
			byte[] buffer = new byte[10240];
			while ((dataRead = dis.read(buffer)) != -1) {
				dos.write(cipher.update(buffer, 0, dataRead));
			}
			dos.write(cipher.doFinal());

			dis.close();
			dos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Split an array to smaller array
	 * 
	 * @param arr
	 *             is the large array
	 * @param subArr
	 *             is the smaller array
	 * @param srcPosition
	 *             is the position you want to get from
	 * @param destPosition
	 *             is the startPosition of the subArr
	 * @param length
	 *             is the length you want to get from arr, it's always use subArr.length
	 */
	private void copyArray(final byte[] arr, final byte[] subArr, int srcPosition, int destPosition, int length) {
		System.arraycopy(arr, srcPosition, subArr, destPosition, length);
	}

	/**
	 * Convert a matrix to an array
	 * 
	 * @param smallArrays
	 *             is the matrix you want to convert to
	 * @param bigArray
	 *             is the array you want to store the data of the matrix
	 */
	private void copyArray(final byte[][] smallArrays, final byte[] bigArray) {
		int currentOffset = 0;
		for (final byte[] currentArray : smallArrays) {
			System.arraycopy(currentArray, 0, bigArray, currentOffset, currentArray.length);
			currentOffset += currentArray.length;
		}
	}

	/**
	 * Encrypt a text with a key
	 * 
	 * @param text
	 *             is the message you want to encrypt
	 * @param key
	 *             is the secret key
	 * @return an encrypted byte array
	 */
	private byte[] encrypter(byte[] text, Key key) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			return cipher.doFinal(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Encrypt a text with a key
	 * 
	 * @param text
	 *             is the message you want to encrypt
	 * @param priKey
	 *             is the primary key
	 * @return an encrypted byte array
	 */
	private byte[] encrypter(byte[] text, PrivateKey priKey) {

		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, priKey);

			return cipher.doFinal(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method use to decrypt the encrypted text byte
	 * 
	 * @param encryptText
	 *             is an array of byte encrypted
	 * @param key
	 *             is the key that encrypt the encryptedText
	 * @return an decrypt byte array
	 */
	private byte[] decrypter(byte[] encryptText, Key key) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);

			return cipher.doFinal(encryptText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method use to decrypt the encrypted text byte
	 * 
	 * @param encryptText
	 *             is an array of byte encrypted
	 * @param pubKey
	 *             is the public key
	 * @return an decrypt byte array
	 */
	private byte[] decrypter(byte[] encryptText, PublicKey pubKey) {
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, pubKey);

			return cipher.doFinal(encryptText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get random key
	 * 
	 * @return the Secret Key
	 */
	private Key getKey() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			return keyGen.generateKey();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method used to hash a message
	 * 
	 * @param message
	 *             is the message you want to hash
	 * @return hash string
	 */
	private String md5(String message) {
		String checksum = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(message.getBytes());
			byte[] hash = md.digest();
			checksum = String.format("%032x", new BigInteger(1, hash));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return checksum;
	}

	/**
	 * Method used to hash a file
	 * 
	 * @param file
	 *             is the file you want to hash
	 * @return hash string of the file
	 */
	private String md5(File file) {
		String checksum = null;
		try {
			BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
			MessageDigest md = MessageDigest.getInstance("MD5");

			byte[] buffer = new byte[102400];
			int byteRead;
			while ((byteRead = fis.read(buffer)) != -1) {
				md.update(buffer, 0, byteRead);
			}
			fis.close();
			byte[] hash = md.digest();
			checksum = String.format("%032x", new BigInteger(1, hash));
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return checksum;
	}

	/**
	 * Method used to calculate the loops of length of the mLength <br>
	 * <b>count256</b> is the parameter that count the message length division to 256, because the length of the byte is from -128 to 127 so that if the message length is more than 256 the mLength
	 * will return the positive number, and we cannot know what the real length of it <br>
	 * For example: if the length of the message is 300 if it is the byte number it will return 44, so you the message length is wrong, so that we use the other counter to store the loop of 256 in
	 * the length of message
	 * 
	 * @param count256
	 *             is the counter of 256
	 * @param mLength
	 *             is the length of the message, it can be negative number
	 * @return
	 */
	private int calculateLength(byte count256, byte mLength) {
		if (mLength >= 0) {
			return count256 * 256 + mLength;
		}
		return count256 * 256 + (256 + mLength);
	}

	public static void main(String args[]) {
		ICIA cia = new CIA();

		TerminalUtils.removePassPhrase(ICAPath.CA_FOLDER, ICAPath.PRIKEY_KEY, ICAPath.PRIKEY_PEM, ICAPath.PRIKEY_PASS);
		TerminalUtils.convertPEMtoDER(ICAPath.CA_FOLDER, ICAPath.PRIKEY_PEM, ICAPath.PRIKEY_DER);

		PrivateKey priKey = CAUtils.getPrivateKey(ICAPath.CA_FOLDER_FULLPATH + ICAPath.PRIKEY_DER);
		PublicKey pubKey = CAUtils.getPublicKey(ICAPath.CA_FOLDER_FULLPATH + ICAPath.CA_CERT);
		
		String pathTest = "/home/hqthai01/Desktop/org";
		String base64Path;
		
		System.out.println(base64Path = cia.encryptPath(pathTest, priKey));
		System.out.println(cia.decryptPath(base64Path, pubKey));

		TerminalUtils.removeFile(ICAPath.CA_FOLDER_FULLPATH + ICAPath.PRIKEY_PEM);
		TerminalUtils.removeFile(ICAPath.CA_FOLDER_FULLPATH + ICAPath.PRIKEY_DER);

	}

}
