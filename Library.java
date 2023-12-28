package VIVA3;

import java.util.*;

public class Library {
    private ArrayList<Book> books;


    public Library() {
        this.books = new ArrayList<>();
    }


    public void addBook(Book book) {
        books.add(book);
    }


    public void removeBook(String isbn) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getIsbn().equals(isbn)) {
                iterator.remove();
                break;
            }
        }
    }


    public void displayLibrary() {
        for (Book book : books) {
            displayBookInfo(book);
        }
    }


    private void displayBookInfo(Book book) {
        System.out.println("- Title: " + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nISBN: " + book.getIsbn());
    }


    public void findBooksByAuthor(String author) {
        System.out.println("Books by author '" + author + "':");
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                displayBookInfo(book);
            }
        }
    }


    public void findBooksByTitle(String title) {
        System.out.println("Books with title '" + title + "':");
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                displayBookInfo(book);
            }
        }
    }


    public boolean borrowBook(String isbn) {
        int lastTwoDigits = Integer.parseInt(isbn.substring(isbn.length() - 2));
        return !isPrime(lastTwoDigits);
    }


    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


    public void sortBooks() {
        books.sort((book1, book2) -> book1.getTitle().compareToIgnoreCase(book2.getTitle()));
        System.out.println("\nSorted Books:");
        displayLibrary();
    }
}
