package library3;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Arrays;

public class Book implements Externalizable {
    static int nextId = 0;
    private int id;
    private String title;
    private String[] genres;
    private Author[] authors;
    private ArrayList<Reader> readers;
    private int year;

    Book() { }

    Book(int id, String title, String[] genres, Author[] authors, int year) {
        this.id = id;
        readers = new ArrayList<>();
        this.genres = genres;
        this.title = title;
        this.authors = authors;
        this.year = year;
        for ( Author a : authors ) {
            a.addBook(this);
        }
    }
    public Book(String title, String[] genres, Author[] authors, int year) {
        id = nextId++;
        readers = new ArrayList<>();
        this.genres = genres;
        this.title = title;
        this.authors = authors;
        this.year = year;
        for ( Author a : authors ) {
            a.addBook(this);
        }
    }

    public String getTitle() {
        return title;
    }

    public Author[] getAuthors() { return authors; }
    public String[] getGenres() { return genres; }
    public ArrayList<Reader> getReaders() { return readers; }

    public int getId() { return id; }
    public void setId(int ID) { id = ID; } 

    public int getYear() {
        return year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void addReader(Reader reader) {
        this.readers.add(reader);
        if ( !reader.getBooks().contains(this) ) {
            reader.addBook(this);
        }
    }

    public void removeReader(Reader reader) {
        this.readers.remove(reader);
        reader.removeBook(this);
    }

    @Override
    public String toString() {
        return "\"" + title + "\" by " + authors[0].getForename() + " " + authors[0].getSurename() + " (" + year + ")" + ", " + Arrays.toString(genres) + ", readers: " + readers.size();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(id);
        out.writeUTF(title);
        out.writeObject(genres);
        out.writeInt(year);
        out.writeInt(authors.length);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = in.readInt();
        title = in.readUTF();
        genres = (String[]) in.readObject();
        year = in.readInt();
        authors = new Author[in.readInt()];

        readers = new ArrayList<Reader>();
    }
}