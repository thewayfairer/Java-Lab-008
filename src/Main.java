import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Create a scanner object
        Scanner scanner = new Scanner(System.in);

        // Write a loop that will ask the user to enter a file path to gather stats on,
        // and continue until "Q" is entered.
        String filePath;
        do {
            System.out.print("Enter a file path (or Q to quit): ");
            filePath = scanner.nextLine();

            if (!filePath.equalsIgnoreCase("Q")) {
                // Reference Java-Assignment-003 to see how to use the java.nio libraries to turn a String path into a File
                Path path = Paths.get(filePath);
                File file = path.toFile();

                // Ask the user if they would like to skip whitespace
                System.out.print("Would you like to skip whitespace (Y/N): ");
                String skipWsInput = scanner.nextLine();

                // Create a variable called skipWs that stores the user's response as a boolean
                boolean skipWs = skipWsInput.equalsIgnoreCase("Y");

                /*
                 * Within this try/catch block, which is used to handle possible errors thrown by the code in the try block,
                 * write code to get the line, word, and character count of the File object created above!
                 */
                try {
                    // You will need to create a FileStats object by passing it the File object and your skipWs variable as args
                    FileStats fs = new FileStats(file, skipWs);

                    // You will need to call the fs.read method, which you need to implement!
                    fs.read();

                    /*
                     * You will access the FileStats object's getter methods to get the file's line, word, character count and
                     * the files name. You should use a format specifier to print it all out similar to the following example:
                     *
                     * Stats: lines - 6, words - 46, chars - 237 /path/to/file/fileName.txt
                     */
                    System.out.printf("Stats: lines - %d, words - %d, chars - %d %s%n",
                            fs.getNumLines(),
                            fs.getNumWords(),
                            fs.getNumChars(),
                            file.getAbsolutePath());

                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        } while (!filePath.equalsIgnoreCase("Q"));

        scanner.close();
    }
}

