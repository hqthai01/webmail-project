package interfaces;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

public interface ICA {
	public Key[] getCertificate(String userInfo);//mang key chua private key va public key
	public PrivateKey getPrivateKey();
	public PublicKey getPublicKey();
}
