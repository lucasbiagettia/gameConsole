package connection;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class JarExecutor implements Runnable {

	File file;

	public JarExecutor(File theFile) {
		file = theFile;

	}

	@Override
	public void run() {
		String path = file.getAbsolutePath();

		String comando = "java -jar " + path;

		try {
			ProcessBuilder builder = new ProcessBuilder(Arrays.asList(comando.split(" ")));
			builder.start();
//			proceso.waitFor();

		} catch (IOException  e) {
			e.printStackTrace();
		}
	}
}
