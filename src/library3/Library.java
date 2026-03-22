package library3;
import java.io.*;
import java.util.ArrayList;

public class Library implements Externalizable {
    private transient ArrayList<Shelf> shelfs;
    public transient ArrayList<Book> books;
    private transient ArrayList<Author> authors;
    private transient ArrayList<Reader> readers;

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

    public ArrayList<Book> getBooks() { return books; }

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
        books.add(book);
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
        System.out.println("Readers: " + readers.size());
        System.out.println("Books in the library: " + books.size());
        System.out.println("Authors: ");
        for ( Author author : authors ) {
            System.out.print("   - " + author.toString());
            System.out.printf("; %d books in the library\n", author.getBooks().size() );
        }

        System.out.println("\nShelfs: ");
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
            reader.removeBook(book);
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

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + "data3.dat"));
            writeExternal(oos);
            oos.writeInt(Book.nextId);
            oos.writeInt(Author.nextId);
            oos.writeInt(Reader.nextId);

            oos.close();
        } catch ( Exception e ) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void deserialize() {
        deserialize("");
    }

    public void deserialize(String path) {
        try ( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path + "data3.dat")) ) {
            Library lib = new Library();
            lib.readExternal(ois);
            this.authors = lib.authors;
            this.books = lib.books;
            this.readers = lib.readers;
            this.shelfs = lib.shelfs;

            Book.nextId = ois.readInt();
            Author.nextId = ois.readInt();
            Reader.nextId = ois.readInt();

        } catch ( Exception e ) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(shelfs.size()); // 1
        for ( Shelf shlf : shelfs ) { // shelfs.size() times 
            shlf.writeExternal(out); // 2
            
            out.writeInt(shlf.size()); // 3
            for (Book b : shlf) {
                b.writeExternal(out); // 4 
            }
        }

        out.writeInt(authors.size());
        for ( Author a : authors ) {
            a.writeExternal(out);
        }

        out.writeInt(readers.size());
        for ( Reader r : readers ) {
            r.writeExternal(out);
        }

        out.writeInt(books.size());
        for ( Book b : books ) {
            out.writeInt(b.getId());

            for ( Author a : b.getAuthors() ) {
                out.writeInt(a.getId());
            }

            out.writeInt(b.getReaders().size());
            for ( Reader r : b.getReaders() ) {
                out.writeInt(r.getId());
            }
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        int shelfCount = in.readInt(); // 1
        for ( int j = 0 ; j < shelfCount ; j++ ) {
            Shelf shlf = new Shelf();
            shlf.readExternal(in); // 2

            int shlfBookCount = in.readInt(); // 3 
            for ( int i = 0 ; i < shlfBookCount ; i++ ) {
                Book b = new Book();
                b.readExternal(in); // 4 
                books.add(b);
                shlf.add(b);
            }

            shelfs.add(shlf);
        }

        int authorCount = in.readInt();
        for ( int i = 0 ; i < authorCount ; i++ ) {
            Author b = new Author();
            b.readExternal(in);
            authors.add(b);
        }

        int readerCount = in.readInt();
        for ( int i = 0 ; i < readerCount ; i++ ) {
            Reader b = new Reader();
            b.readExternal(in);
            readers.add(b);
        }

        int bookCount = in.readInt();
        for ( int i = 0 ; i < bookCount ; i++ ) {
            Book b = getBook(in.readInt());
            
            for ( int j = 0 ; j<b.getAuthors().length ; j++ ) {
                Author a = getAuthor(in.readInt());
                b.getAuthors()[j] = a;
                a.addBook(b);
            }

            int bookAuthorCount = in.readInt();
            for ( int j = 0 ; j<bookAuthorCount ; j++ ) {
                b.addReader(getReader(in.readInt()));
            }
        }
        

    }
}