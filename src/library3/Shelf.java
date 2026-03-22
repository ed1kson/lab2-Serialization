package library3;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

public class Shelf extends ArrayList<Book> implements Externalizable{
    private String genre, title;
    
    Shelf() { super(); }
    
    public Shelf(String title, String genre, ArrayList<Book> books) {
        super(books);
        this.genre = genre;
        this.title = title;
    }
    
    public Shelf (String title, String genre) {
        super();
        this.genre = genre;
        this.title = title;
    }

    public void printInfo() {
        System.out.println(this.toString());
        for ( Book b : this ) {
            System.out.println(b);
        }
    }

    public String getGenre() { return genre; }
    public String getTitle() { return title; }
    public void setTitle(String t) { title = t; } 
    // public void setGenre(String g) { genre = g; }

    @Override
    public String toString() {
        return "Shelf " + title + ", genre: " + genre;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(title);
        out.writeUTF(genre);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        title = in.readUTF();
        genre = in.readUTF();
    }
}
