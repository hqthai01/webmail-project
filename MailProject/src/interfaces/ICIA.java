package interfaces;

import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

public interface ICIA {
	public byte[] CIAEncrypter(String message, PrivateKey priKey);
	public String CIADecrypter(byte[] encrytedBytes, PublicKey pubKey);
	
	public void CIAEncrypter(File sourceFile, File destFile, PrivateKey priKey);
	public void CIADecrypter(File sourceFile, File destFile, PublicKey pubKey);
	
}
