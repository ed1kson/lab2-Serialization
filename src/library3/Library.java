package library2;
import java.io.*;
import java.util.ArrayList;

public class Library implements Serializable {
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

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + "data2.dat"));
            oos.writeObject(this);
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
        try ( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path + "data2.dat")) ) {
            Library lib = (Library) ois.readObject();
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

    private void writeObject(ObjectOutputStream out ) throws IOException {
        out.defaultWriteObject();

        out.writeInt(authors.size());
        for ( Author author : authors ) {
            out.writeInt(author.getId());
            out.writeUTF(author.getForename());
            out.writeUTF(author.getSurename());
            out.writeUTF(author.getName());
            out.writeInt(author.getAge());
            
            out.writeInt(author.getBooks().size());
            System.out.println(author.getName()+": "+author.getBooks().size());
            for ( Book b : author.getBooks() ) {
                out.writeInt(b.getId());
                out.writeUTF(b.getTitle());
                out.writeObject(b.getGenres());
                // out.writeInt(b.getAuthors().length);
                out.writeInt(b.getYear());
            }
        }

        out.writeInt(shelfs.size());
        for ( Shelf shlf : shelfs ) {
            out.writeUTF(shlf.getTitle());
            out.writeUTF(shlf.getGenre());

            out.writeInt(shlf.size());
            for (Book b : shlf ) {
                out.writeInt(b.getId());
            }
        }

        out.writeInt(readers.size());
        for ( Reader reader : readers ) {
            out.writeInt(reader.getId());
            out.writeUTF(reader.getForename());
            out.writeUTF(reader.getSurename());
            out.writeInt(reader.getAge());

            out.writeInt(reader.getBooks().size());
            for ( Book b : reader.getBooks() ) {
                out.writeInt(b.getId());
            }
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        this.authors = new ArrayList<Author>();
        this.books = new ArrayList<Book>();
        this.readers = new ArrayList<Reader>();
        this.shelfs = new ArrayList<Shelf>();

        int LibAuthors = in.readInt();
        for ( int i = 0 ; i < LibAuthors ; i++ ) {
            Author a = new Author(
                in.readInt(), //id
                in.readUTF(), //forename
                in.readUTF(), //surename
                in.readUTF(), //name
                in.readInt() //age
            );

            
            int AuthorBooks = in.readInt();
            System.out.println("bookscount: " + AuthorBooks);
            for ( int j = 0 ; j<AuthorBooks ; j++ ) {
                int bookId = in.readInt();
                if ( getBook(bookId) != null ) {
                    Book b = getBook(bookId);

                    Author[] newAuthors = new Author[b.getAuthors().length + 1];
                    System.arraycopy(b.getAuthors(), 0, newAuthors, 0, b.getAuthors().length);
                    newAuthors[b.getAuthors().length] = a;
                    b.setAuthors(newAuthors);

                    // a.addBook(b);
                    in.readUTF();in.readObject();in.readInt();in.readInt();
                    continue;
                }
                Book b = new Book(
                    bookId, // id
                    in.readUTF(), // title 
                    (String[]) in.readObject(), // Genres[]
                    new Author[]{a}, // Author[in.readInt()]
                    in.readInt() // year
                );
                b.getAuthors()[0] = a;
                // a.addBook(b);
                System.out.println("boooooooook: "+ b);
                this.books.add(b);
            }

            this.authors.add(a);
        }

        int shelfs = in.readInt();
        for ( int j = 0 ; j<shelfs ; j++ ) {
            Shelf shlf = new Shelf(
                in.readUTF(),
                in.readUTF()
            );
            
            int shelfBooks = in.readInt();
            for ( int k = 0 ; k < shelfBooks ; k++ ) {
                int int1 = in.readInt();
                shlf.add(getBook(int1));
            }

            this.shelfs.add(shlf);
        }

        int readers = in.readInt();
        for ( int j = 0 ; j < readers ; j++ ) {
            Reader reader = new Reader(
                in.readInt(),
                in.readUTF(),
                in.readUTF(),
                in.readInt()
            );

            int readerBooks = in.readInt();
            for ( int k = 0 ; k < readerBooks ; k++ ) {
                reader.addBook(getBook(in.readInt()));
            }
        
            this.readers.add(reader);
        }
    }
}