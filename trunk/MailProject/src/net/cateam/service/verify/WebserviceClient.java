package net.cateam.service.verify;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author hqthai01
 * Dec 28, 2014
 */
public class WebserviceClient {
	public static boolean verify(String filePath) {
		try {
			ImplVerifyService client = new ImplVerifyService();
			ImplVerify service = client.getImplVerifyPort();
			File file = new File(filePath);
			Path path = Paths.get(filePath);

			return service.verify(file.getName(), Files.readAllBytes(path));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
