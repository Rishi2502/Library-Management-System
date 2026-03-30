import java.io.*;
import java.util.ArrayList;
public class Library {
    ArrayList<Book> books = new ArrayList<>();
    public void addBook(Book b) {
        books.add(b);
        System.out.println("Book added successfully!");
    }
    public void showBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        for (Book b : books) {
            b.displayBook();
        }
    }
    public void issueBook(int id) {
        for (Book b : books) {
            if (b.id == id && !b.isIssued) {
                b.isIssued = true;
                System.out.println("Book issued!");
                return;
            }
        }
        System.out.println("Book not available.");
    }
    public void returnBook(int id) {
        for (Book b : books) {
            if (b.id == id && b.isIssued) {
                b.isIssued = false;
                System.out.println("Book returned!");
                return;
            }
        }
        System.out.println("Invalid return.");
    }
    public void saveToFile() {
        try {
            FileWriter fw = new FileWriter("books.txt");
            for (Book b : books) {
                fw.write(b.id + "," + b.title + "," + b.author + "," + b.isIssued + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }
    public void loadFromFile() {
        try {
            File file = new File("books.txt");
            if (!file.exists()) return;
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Book b = new Book(Integer.parseInt(data[0]), data[1], data[2]);
                b.isIssued = Boolean.parseBoolean(data[3]);
                books.add(b);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
    }
}