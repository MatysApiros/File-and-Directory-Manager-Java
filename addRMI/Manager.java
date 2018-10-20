import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Manager extends UnicastRemoteObject implements FSInterface {

	public Manager() throws RemoteException {}

	@Override
	public File[] ls(String path) throws RemoteException {
		File file = new File(path);
		File[] directoryFiles = file.listFiles();
		if (directoryFiles.length > 0) {
			System.out.println("Files found in the given directory!");
			return directoryFiles;
		} else {
			System.out.println("No files were found in the given directory!");
			return new File[]{};
		}
	}

	@Override
	public void mkdir(String path) throws RemoteException {
		File file = new File(path);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}
	}

	@Override
	public void mkdirs(String path) throws RemoteException {
		File file = new File(path);
		if (!file.exists()) {
			if (file.mkdirs()) {
				System.out.println("Multiple directories are created!");
			} else {
				System.out.println("Failed to create multiple directories!");
			}
		}
	}

	@Override
	public void create(String path) throws RemoteException {
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
				System.out.println("New file created!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void unlink(String path) throws RemoteException {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
			System.out.println("File/Directory was deleted!");
		}  else {
			System.out.println("Failed to delete file/directory!");
		}
	}

	@Override
	public void write(byte[] data, String path) throws IOException {
		if (data != null && path != null) {
			Files.write(Paths.get(path), data);
			System.out.println("File was updated!");
		} else {
			System.out.println("Failed to update file!");
		}
	}

	@Override
	public void append(byte[] data, String path) throws RemoteException {
		try {
			Files.write(Paths.get(path), data, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String read(String path) throws IOException {
		String content;
		content = new String(Files.readAllBytes(Paths.get(path)));
		return content;
	}
}
