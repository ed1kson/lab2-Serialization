package library2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Book implements Serializable {
    static int nextId = 0;
    private final int id;
    private String title;
    private String[] genres;
    private Author[] authors;
    private ArrayList<Reader> readers;
    private int year;

    Book(int id, String title, String[] genres, Author[] authors, int year) {
        this.id = id;
        readers = new ArrayList<>();
        this.genres = genres;
        this.title = title;
        this.authors = authors;
        this.year = year;
    }
    public Book(String title, String[] genres, Author[] authors, int year) {
        id = nextId++;
        readers = new ArrayList<>();
        this.genres = genres;
        this.title = title;
        this.authors = authors;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public Author[] getAuthors() { return authors; }
    public String[] getGenres() { return genres; }
    public ArrayList<Reader> getReaders() { return readers; }

    public int getId() { return id; }

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
    } 

    public void removeReader(Reader reader) {
        this.readers.remove(reader);
    }

    @Override
    public String toString() {
        return "\"" + title + "\" by " + authors[0].getForename() + " " + authors[0].getSurename() + " (" + year + ")" + ", " + Arrays.toString(genres) + ", readers: " + readers.size();
    }
}