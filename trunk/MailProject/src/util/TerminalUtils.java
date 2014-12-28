package util;

/**
 * @author hqthai01 Dec 28, 2014
 */
public class TerminalUtils {
	/**
	 * Method run the input command
	 * 
	 * @param command
	 *             is the terminal command you want to run
	 */
	public static void doCommand(String command) {
		try {
			Runtime runtime = Runtime.getRuntime();
			String[] commands = new String[] { "/bin/bash", "-c", command };
			Process p = runtime.exec(commands);
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to convert private key from PEM format to DER format. Because of the DER format is java readable format of private key
	 * 
	 * @param folderPath
	 *             is the path of the folder that stores prikeyPEMPath and the output DER file
	 * @param prikey_pem
	 *             is the path of private key (NOTE: the PEM private key cannot be contain password, so if you want to use this method the first thing you need to do is remove pass phrase of private
	 *             key)
	 * @param prikey_der
	 *             is the output path of your converted format
	 */
	public static void convertPEMtoDER(String folderPath, String prikey_pem, String prikey_der) {
		String command = "cd " + folderPath + " ; openssl pkcs8 -topk8 -inform PEM -outform DER -in " + prikey_pem + " -nocrypt > " + prikey_der;
		doCommand(command);
	}

	/**
	 * This method is used to remove pass phrase from your private key and return a new private key PEM format
	 * 
	 * @param folderPath
	 *             is the path of the folder that stores your private key and the output PEM file
	 * 
	 * @param prikey_key
	 *             is the path of your private key
	 * @param prikey_pem
	 *             is the output PEM private key
	 * @param prikey_pass
	 *             is the password of private key
	 */
	public static void removePassPhrase(String folderPath, String prikey_key, String prikey_pem, String prikey_pass) {
		String command = "cd " + folderPath + " ; ";
		command += "openssl rsa -in " + prikey_key;
		command += " -out " + prikey_pem;
		command += " -passin pass:" + prikey_pass;

		doCommand(command);
	}

	/**
	 * This method is used to remove your file(s).
	 * <p/>
	 * NOTE: it also delete your directory and sub directories, if your path is an directory, so be careful when you enter your filePath
	 * 
	 * @param filePath
	 *             is the path of the file(s) you want to remove
	 */
	public static void removeFile(String filePath) {
		String command = "rm -rf " + filePath;
		doCommand(command);
	}

	private static final String JAVA_HOME = PropertyLoader.getProperty("JAVA_HOME");

	public static void getServices(String folderStore, String serviceLink) {
		String command = "mkdir -p " + folderStore + " ; ";
		command += "cd " + folderStore + " ; ";
		command += "export " + JAVA_HOME + " ; ";
		command += "$JAVA_HOME/wsimport -keep -p net.cateam.service.verify " + serviceLink + " ; ";
		command += "nemo " + folderStore;

		doCommand(command);

	}
}
