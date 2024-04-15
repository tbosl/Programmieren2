import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class Utf8Checker {

    public static void main(String[] args) {
        String directory = "D:\\Studium\\2. Semester\\Programmieren 2\\Abgabe_5";
        List<File> javaFiles = findJavaFiles(directory);

        for (File file : javaFiles) {
            checkFile(file);
        }

        System.out.println("Finished checking all Java files.");
    }

    private static List<File> findJavaFiles(String directory) {
        List<File> javaFiles = new ArrayList<>();
        File dir = new File(directory);

        if (!dir.isDirectory()) {
            System.out.println("Invalid directory: " + directory);
            return javaFiles;
        }

        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                javaFiles.addAll(findJavaFiles(file.getAbsolutePath()));
            } else if (file.getName().endsWith(".java")) {
                javaFiles.add(file);
            }
        }

        return javaFiles;
    }

    private static void checkFile(File file) {
        try (BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (!isValidUTF8(line)) {
                    System.out.println("Non-UTF-8 character found in " + file.getAbsolutePath() + " at line " + lineNumber);
                }else{
                    System.out.println("All good at " + file.getAbsolutePath());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + file.getAbsolutePath());
            e.printStackTrace();
        }
    }

    private static boolean isValidUTF8(String input) {
        return Charset.forName("UTF-8").newEncoder().canEncode(input);
    }
}