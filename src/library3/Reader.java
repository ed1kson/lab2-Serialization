package library3;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

public class Reader extends Human {
    static int nextId = 0;
    private int id;
    private ArrayList<Book> books;

    Reader() {
        super("", "", 0);
    }

    Reader(int id, String forename, String surename, int age) {
        super(forename, surename, age);
        this.id = id;
        this.books = new ArrayList<Book>();
    }

    public Reader(String forename, String surename, int age) {
        super(forename, surename, age);
        id = nextId++;
        this.books = new ArrayList<Book>();
    }

    public Reader(String forename, String surename, int age, Book[] books) {
        this(forename, surename, age);
        for ( Book book : books ) {
            this.books.add(book);
        }
    }

    public Reader(String forename, String surename, int age, Book book) {
        this(forename, surename, age);
        this.books = new ArrayList<Book>();
        books.add(book);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        this.books.add(book);
        if ( !book.getReaders().contains(this) ) {
            book.addReader(this);
        }
    }

    public void removeBook(Book book) {
        this.books.remove(book);
        book.removeReader(this);
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

    public int getId() { return id; }
    public void setId(int ID) { id = ID; }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(id);
        out.writeUTF(super.getForename());
        out.writeUTF(super.getSurename());
        out.writeInt(super.getAge());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = in.readInt();
        setForename(in.readUTF());
        setSurename(in.readUTF());
        setAge(in.readInt());

        books = new ArrayList<Book>();
        setName(getForename() + " " + getSurename());
    }
}