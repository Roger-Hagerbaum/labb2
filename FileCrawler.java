import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileCrawler {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please Enter search string!: ");
        String searchString = sc.next();
        sc.close();
        try {
            String startingFolder = ("C:\\Users\\HP\\Skola\\labb2\\testfolder");
            scan(startingFolder, searchString);
        } catch (Exception e) {
            System.out.println("Folder not found");
        }
    }

    private static void scan(String path, String searchString) {
        File[] files = new File(path).listFiles();
        assert files != null;
        for (File file : files) {
            if (file.canRead() && file.isFile()) {
                    path = (file.getAbsolutePath());
                    searchFile(path, searchString);
                } else if (file.isDirectory()) {
                    path = (file.getAbsolutePath());
                    scan(path, searchString);
                } else {
                System.err.println("File: " + file.getAbsolutePath() + " can not be read");
            }
        }
    }

    private static void searchFile(String path, String searchText) {
        File file = new File(path);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String text = (scanner.nextLine());
                boolean val = text.contains(searchText);
                if (val) {
                    System.out.println(file.getAbsolutePath());
                    return;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
