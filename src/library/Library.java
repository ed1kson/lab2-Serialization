package library;
import java.io.*;
import java.util.ArrayList;

public class Library implements Serializable {
    private ArrayList<Shelf> shelfs;
    private ArrayList<Book> books;
    private ArrayList<Author> authors;
    private ArrayList<Reader> readers;

    public Library() {
        shelfs = new ArrayList<>();
        books = new ArrayList<>();
        authors = new ArrayList<>();
        readers = new ArrayList<>();
    }

    public Library(ArrayList<Shelf> shelfs) {
        this();
        for ( Shelf shlf : shelfs ) {
            for ( Book book : shlf ) {
                books.add(book);
            }
        }
    }

    public Author getAuthor(String name) {
        name = name.toLowerCase();

        for ( Author author : authors ) {
            if ( author.getName().toLowerCase().contains(name) ) {
                return author;
            }
        }

        return null;
    }

    public Author getAuthor(int id) {
        for ( Author author : authors ) {
            if (author.getId() == id) {
                return author;
            }
        }

        return null;
    }

    public void addBook(Book book) {
        for ( Author author : book.getAuthors() ) {
            if ( !authors.contains(author) ) {
                authors.add(author);
            }
        }

        for ( String genre : book.getGenres() ) {
            for ( Shelf shlf : shelfs ) {
                if ( genre.equals(shlf.getGenre()) ) {
                    shlf.add(book);
                    books.add(book);
                    return;
                }
            }
        }

        String genre = book.getGenres()[0];
        Shelf newShelf = new Shelf(genre+" shelf", genre);
        newShelf.add(book);
        shelfs.add(newShelf);
    }

    public void addBook(Book book, String shelfName) {
        for ( Author author : book.getAuthors() ) {
            if ( !authors.contains(author) ) {
                authors.add(author);
            }
        }

        for ( Shelf shlf : shelfs ) {
            if ( shlf.getTitle().toLowerCase().contains(shelfName.toLowerCase()) ) {
                shlf.add(book);
                books.add(book);
            }
        }
    }

    public void addShelf(Shelf shlf) {
        shelfs.add(shlf);
    }

    public void printInfo() {
        System.out.println("Authors: ");
        for ( Author author : authors ) {
            System.out.println("   - " + author.toString());
        }

        System.out.println("\n Shelfs: ");
        for (Shelf shelf : shelfs) {
            System.out.println("   - " + shelf.toString());
            for ( Book book : shelf ) {
                System.out.println("      -" + book);
            }
        }
    }
    // public getShelfs

    public Book getBook(int id) {
        for ( Book book : books ) {
            if ( book.getId() == id ) {
                return book;
            }
        }

        return null;
    }
    public Book getBook(String title) {
        title = title.toLowerCase();
        for ( Book book : books ) {
            if ( book.getTitle().toLowerCase().contains(title) ) {
                return book;
            }
        }

        return null;
    }

    public void addReader(Reader reader) {
        for ( Book book : reader.getBooks() ) {
            if ( !book.getReaders().contains(reader)) {
                book.addReader(reader);
            }
        }

        if ( !readers.contains(reader)) {
            readers.add(reader);
        }
    }

    public void deleteReader(Reader reader) {
        for ( Book book : books ) {
            book.removeReader(reader);
        }

        readers.remove(reader);
    }

    public Reader getReader(int id) {
        for ( Reader reader : readers ) {
            if (reader.getId() == id ) return reader;
        }

        return null;
    }

    public Reader getReader(String name) {
        name = name.toLowerCase();
        for ( Reader reader : readers ) {
            if ( reader.getName().toLowerCase().contains(name) ) return reader;
        }

        return null;
    }

    public void serialize() {
        serialize("");
    }

    public void serialize(String path) {

        try {
            File dir = new File(path);
            if ( !dir.exists() ) {
                dir.mkdirs();
            }

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + "data1.dat"));
            oos.writeObject(this);

            oos.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void deserialize() {
        deserialize("");
    }

    public void deserialize(String path) {
        try ( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path + "data1.dat")) ) {
            Library lib = (Library) ois.readObject();
            this.authors = lib.authors;
            this.books = lib.books;
            this.readers = lib.readers;
            this.shelfs = lib.shelfs;
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    
}
