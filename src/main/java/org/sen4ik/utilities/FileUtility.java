package org.sen4ik.utilities;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.tika.Tika;
import org.apache.tika.io.TikaInputStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtility {

	public static String getFilePath(File file) throws FileNotFoundException {
		if (file.exists()) {
			System.out.println("File exists: " + file.getAbsolutePath());
			return file.getAbsolutePath();
		} else {
			String msg = "File does not exist: " + file.getPath();
			System.out.println(msg);
			throw new FileNotFoundException(msg);
		}
	}

	public static ArrayList<String> readFileIntoArray(File file) throws IOException {
		ArrayList<String> result = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while (br.ready()) {
				String line = br.readLine();
				if (!line.isEmpty()) {
					result.add(line);
				}
			}
		}
		return result;
	}

	public static boolean doesFileContainsString(File file, String value) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while (br.ready()) {
				String line = br.readLine();
				if (line.contains(value)) {
					return true;
				}
			}
		}
		return false;
	}

	// https://www.baeldung.com/java-append-to-file
	public static void writeToFile(File file, String data, boolean append) throws IOException {
		FileWriter fw = new FileWriter(file, append);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(data);
		bw.newLine();
		bw.close();
	}

	public static void deleteFilesAndDirsWithPrefix(File[] files, String prefix) {
		for (File file : files) {
			if (file.isDirectory()) {
				deleteFilesAndDirsWithPrefix(file.listFiles(), prefix);
				if (file.getName().startsWith(prefix)) {
					file.delete();
				}
			} else {
				if (file.getName().startsWith(prefix)) {
					file.delete();
				}
			}
		}
	}

	public static void deleteFilesAndDirsWithPrefix(File[] files, List<String> prefixes) {
		for (File file : files) {
			if (file.isDirectory()) {
				deleteFilesAndDirsWithPrefix(file.listFiles(), prefixes);
			}

			String fileName = file.getName();
			boolean res = prefixes.stream().anyMatch(fileName::startsWith);
			if (res) {
				System.out.println(file.getAbsolutePath());
				file.delete();
			}
		}
	}

	public static void deleteFilesAndDirsFromPath(File directoryToBeDeleted) {
		File[] files = directoryToBeDeleted.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				deleteFilesAndDirsFromPath(file);
			}
			if (!file.getName().equals(".gitignore")) {
				file.delete();
			}
		}
	}

	public static File getCopyOfFile(String filePath, String copyToPath) throws IOException {
		Path fileToCopy = Paths.get(filePath);
		Path copied = Paths.get(copyToPath);
		return getCopyOfFile(fileToCopy, copied);
	}

	public static File getCopyOfFile(Path fileToCopy, Path copied) throws IOException {
		Files.copy(fileToCopy, copied, StandardCopyOption.REPLACE_EXISTING);
		return copied.toFile();
	}

	private static File createFile(File file) throws IOException {
		file.createNewFile();
		return file;
	}

	public static File createFileOfGivenSize(File file, int sizeInMegabytes) throws IOException {
		File f = createFile(file);
		try (FileOutputStream out = new FileOutputStream(f)) {
			byte[] bytes = new byte[1000000 * sizeInMegabytes];
			new SecureRandom().nextBytes(bytes);
			out.write(bytes);
		}
		return f;
	}

	public static String getMimeTypeWithTika(File file) {
		Tika tika = new Tika();
		try {
			TikaInputStream tis = TikaInputStream.get(file);
			String mimeType = tika.detect(tis);
			System.out.println("Mime for file " + file.getName() + " is " + mimeType);
			return mimeType;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean deleteFile(File file) {
		try {
			if (file.exists()) {
				file.delete();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Boolean doesFileExists(File file) {
		try {
			return file.exists();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static File findLatestFileInTheDirectory(File dir, String fileExtension) {
		File latestFile = null;
		FileFilter fileFilter = new WildcardFileFilter("*." + fileExtension);
		File[] files = dir.listFiles(fileFilter);
		if (files.length > 0) {
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			latestFile = files[0];
		} else {
			System.err.println(dir.getName() + " directory is empty");
		}
		return latestFile;
	}

}