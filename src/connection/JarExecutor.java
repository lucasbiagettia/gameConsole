package connection;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class JarExecutor {

	File file;

	public JarExecutor(File theFile) {
		file = theFile;

	}

	public void execute() {
		String path = file.getAbsolutePath();

		String comando = "java -jar " + path;

		try {
			ProcessBuilder builder = new ProcessBuilder(Arrays.asList(comando.split(" ")));
			Process proceso = builder.start();
			proceso.waitFor();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
