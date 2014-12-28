package interfaces;

import util.PropertyLoader;

/**
 * @author hqthai01 Dec 28, 2014
 */
public interface ICAPath {
	public static final String PRIKEY_KEY = PropertyLoader.getProperty("prikey_key");
	public static final String PRIKEY_PEM = PropertyLoader.getProperty("prikey_pem");
	public static final String PRIKEY_DER = PropertyLoader.getProperty("prikey_der");
	public static final String PRIKEY_PASS = PropertyLoader.getProperty("prikey_pass");

	public static final String CA_FOLDER = PropertyLoader.getProperty("ca_folder");
	public static final String CA_FOLDER_FULLPATH = PropertyLoader.getProperty("ca_folder_fullpath");
	public static final String CA_CERT = PropertyLoader.getProperty("ca_cert");
}
