package library;
import java.util.ArrayList;

public class Reader extends Human {
    private ArrayList<Book> books;

    public Reader(String forename, String surename, int age) {
        super(forename, surename, age);
        this.books = new ArrayList<Book>();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void printInfo() {
        System.out.println("Reader: " + this.getForename() + " " + this.getSurename() + ", age: " + this.getAge());
        System.out.println("Books:");
        for (Book book : books) {
            System.out.println("   - " + book.toString());
        }
    }

}