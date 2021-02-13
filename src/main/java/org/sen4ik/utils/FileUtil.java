package org.sen4ik.utils;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
public class FileUtil {

    public static String getFilePath(String relativePath) throws FileNotFoundException {
        log.info("CALLED: getFilePath(\"" + relativePath + "\")");
        File f = new File(relativePath);
        if (f.exists()) {
            log.info("File exists: " + f.getAbsolutePath());
            return f.getAbsolutePath();
        } else {
            String msg = "File does not exist: " + relativePath;
            log.error(msg);
            throw new FileNotFoundException(msg);
        }
    }

    public static ArrayList<String> readFileIntoArray(String fileName) throws IOException {
        log.info("CALLED: readFileIntoArray(\"" + fileName + "\")");
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (br.ready()) {
                String line = br.readLine();
                if(!line.isEmpty()){
                    result.add(line);
                }
            }
        }
        return result;
    }

    public static boolean doesFileContainsString(String fileName, String str) throws IOException {
        log.info("CALLED: doesFileContainsString()");
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (br.ready()) {
                String line = br.readLine();
                if(line.contains(str)){
                    return true;
                }
            }
        }
        return false;
    }

    // https://www.baeldung.com/java-append-to-file
    public static void writeToFile(String fileName, String data, boolean append) throws IOException {
        log.info("CALLED: writeToFile()");
        FileWriter fw = new FileWriter(fileName, append);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(data);
        bw.newLine();
        bw.close();
    }

    public static void deleteFilesAndDirsWithPrefix(File[] files, String prefix) {
        log.info("CALLED: deleteFilesAndDirsWithPrefix(\"" + files.toString() + "\", \"" + prefix + "\")");
        for (File file : files) {
            if (file.isDirectory()) {
                deleteFilesAndDirsWithPrefix(file.listFiles(), prefix);
                if(file.getName().startsWith(prefix)){
                    file.delete();
                }
            } else {
                if(file.getName().startsWith(prefix)){
                    file.delete();
                }
            }
        }
    }

    public static void deleteFilesAndDirsWithPrefix(File[] files, List<String> prefixes) {
        log.info("CALLED: deleteFilesAndDirsWithPrefix()");
        for (File file : files) {
            if (file.isDirectory()) {
                deleteFilesAndDirsWithPrefix(file.listFiles(), prefixes);
            }

            String fileName = file.getName();
            boolean res = prefixes.stream().anyMatch(fileName::startsWith);
            if(res){
                log.info(file.getAbsolutePath());
                file.delete();
            }
        }
    }

    public static void deleteFilesAndDirsFromPath(File directoryToBeDeleted) {
        log.info("CALLED: deleteFilesAndDirsFromPath()");

        File[] files = directoryToBeDeleted.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                deleteFilesAndDirsFromPath(file);
            }
            log.info(file.getAbsolutePath());
            if(!file.getName().equals(".gitignore")){
                file.delete();
            }
        }
    }

    public static void deleteFilesAndDirsFromPath(String path) {
        deleteFilesAndDirsFromPath(new File(path));
    }

    public static File getCopyOfFile(String filePathToCopy, String copyToPath) throws IOException {
        Path fileToCopy = Paths.get(filePathToCopy);
        Path copied = Paths.get(copyToPath);
        return getCopyOfFile(fileToCopy, copied);
    }

    public static File getCopyOfFile(Path fileToCopy, Path copied) throws IOException {
        log.info("CALLED: getCopyOfFile()");
        Files.copy(fileToCopy, copied, StandardCopyOption.REPLACE_EXISTING);
        return copied.toFile();
    }

    private static File createFile(final String filename) throws IOException {
        log.info("CALLED: createFile(" + filename + ")");
        File file = new File(filename);
        file.createNewFile();
        return file;
    }

    public static File createFileOfGivenSize(final String filename, int sizeInMegabytes) throws IOException {
        log.info("CALLED: createFileOfGivenSize()");
        File file = createFile(filename);
        try (FileOutputStream out = new FileOutputStream(file)) {
            byte[] bytes = new byte[1000000 * sizeInMegabytes];
            new SecureRandom().nextBytes(bytes);
            out.write(bytes);
        }
        return file;
    }

    public static String getMimeTypeWithTika(File file){
        log.info("CALLED: getMimeTypeWithTika(" + file.getAbsolutePath() + ")");
        Tika tika = new Tika();
        try {
            TikaInputStream tis = TikaInputStream.get(file);
            String mimeType = tika.detect(tis);
            log.info("Mime for file " + file.getName() + " is " + mimeType);
            return mimeType;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteFile(String path){
        log.info("CALLED: deleteFile()");
        try {
            File file = new File(path);
            if(file.exists()){
                file.delete();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean doesFileExists(String path){
        log.info("CALLED: doesFileExists()");
        try {
            File file = new File(path);
            return file.exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File findLatestFileInTheDirectory(String dirPath, String fileExtension) {
        log.info("CALLED: findLatestFileInTheDirectory()");

        File latestFile = null;

        File dir = new File(dirPath);
        FileFilter fileFilter = new WildcardFileFilter("*." + fileExtension);
        File[] files = dir.listFiles(fileFilter);

        if (files.length > 0) {
            Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            latestFile = files[0];
            log.info("latestFile: " + latestFile);
        }
        else {
            log.warn(dirPath + " directory is empty");
        }

        return latestFile;
    }
}
