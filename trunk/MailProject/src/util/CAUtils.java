package util;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @author hqthai01 Dec 28, 2014
 */
public class CAUtils {
	public static PublicKey getPublicKey(String filePath) {
		try {
			CertificateFactory factory = CertificateFactory.getInstance("X.509");
			Certificate cert = factory.generateCertificate(new FileInputStream(new File(filePath)));
			return cert.getPublicKey();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static PrivateKey getPrivateKey(String path) {
		try {
			Path path2 = Paths.get(path);
			byte[] prikeyBytes = Files.readAllBytes(path2);

			KeyFactory kf = KeyFactory.getInstance("RSA");
			PrivateKey pri = kf.generatePrivate(new PKCS8EncodedKeySpec(prikeyBytes));
			return pri;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
