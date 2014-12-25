package api;

import interfaces.ICA;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class CAMock implements ICA {
	private KeyPairGenerator keyGen;
	private KeyPair keyPair;

	public CAMock() {
		try {
			keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(1024);
			keyPair = keyGen.genKeyPair();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private Key genKey(boolean pubKey) {
		try {
			if (pubKey)
				return keyPair.getPublic();
			return keyPair.getPrivate();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Key[] getCertificate(String userInfo) {
		return null;
	}

	@Override
	public PrivateKey getPrivateKey() {
		return (PrivateKey)genKey(false);
	}

	@Override
	public PublicKey getPublicKey() {
		return (PublicKey)genKey(true);
	}

}
