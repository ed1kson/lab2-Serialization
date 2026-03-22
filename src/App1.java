import library2.*;

public class App1 {
    public static void main(String[] args) throws Exception {
        Library lib = new Library();
        lib.addShelf(new Shelf("Harry potter", "fantasy"));
        lib.addBook(new Book(
            "Harry Potter and philosopher's stone",
        new String[]{"fantasy", "children's literature"},
        new Author[]{new Author(
            "Joanne",
            "Rowling",
            "J. K. Rowling",
            60
        )},
        1997
        ), "Harry Potter");

        lib.addBook(new Book(
            "Harry Potter and the Chamber of Secrets",
            new String[]{"fantasy", "children's literature"},
            new Author[]{lib.getAuthor("Rowling")},
            1998
        ), "Harry potter");

        lib.addReader(new Reader(
            "Eduard",
            "Oplakanskyi",
            18,
            lib.getBook("Philosopher")
        ));

        // lib.deleteReader(lib.getReader("Oplakansk"));

        lib.serialize("data/");
        
        lib.deserialize("data/");

        lib.printInfo();
    }

}
