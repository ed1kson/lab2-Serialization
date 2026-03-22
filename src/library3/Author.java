package library3;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;


public class Author extends Human {
    static int nextId = 0;
    private int id;
    private ArrayList<Book> books;
    private String name;

    Author() {
        super(null, null, 0);
    }

    Author(int id, String forename, String surename, String name, int age) {
        super(forename, surename, age);
        this.books = new ArrayList<>();
        this.name = name;
        this.id = id;
    }

    public Author(String forename, String surename, String name, int age) {
        super(forename, surename, age);
        this.books = new ArrayList<>();
        this.name = name;
        id = nextId++;
    }

    public Author(String forename, String surename, String name, int age, ArrayList<Book> books) {
        super(forename, surename, age);
        this.books = books;
        this.name = name;
        id = nextId++;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getId() { return id; }
    public void setId(int ID) { id = ID; }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(id);
        out.writeUTF(super.getForename());
        out.writeUTF(super.getSurename());
        out.writeUTF(getName());
        out.writeInt(super.getAge());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = in.readInt();
        setForename(in.readUTF());
        setSurename(in.readUTF());
        setName(in.readUTF());
        setAge(in.readInt());

        books = new ArrayList<Book>();
    }

}
