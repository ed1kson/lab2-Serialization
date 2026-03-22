import library3.*;

public class App1 {
    static Library lib = new Library();
    public static void main(String[] args) throws Exception {
        enterdata();
        lib.serialize("data/");
        lib.deserialize("data/");
        lib.printInfo();

        String author = "asimov";
        System.out.println(author+":");
        for ( Book b : lib.getAuthor("asimov").getBooks() ) {
            System.out.println("   -"+b);
        }

        System.out.println("\bbooks: ");
        for ( Book b : lib.books ) {
            System.out.println("   -"+b);
        }

        String book = "Foundation";
        System.out.println(book);
        for ( Author a : lib.getBook(book).getAuthors()) {
            System.out.println("   -"+a);
        }

    }

    static void enterdata() {
        // lib.addShelf(new Shelf("Harry potter", "fantasy"));
        // lib.addBook(new Book(
        //     "Harry Potter and philosopher's stone",
        // new String[]{"fantasy", "children's literature"},
        // new Author[]{new Author(
        //     "Joanne",
        //     "Rowling",
        //     "J. K. Rowling",
        //     60
        // )},
        // 1997
        // ), "Harry Potter");

        // lib.addBook(new Book(
        //     "Harry Potter and the Chamber of Secrets",
        //     new String[]{"fantasy", "children's literature"},
        //     new Author[]{lib.getAuthor("Rowling")},
        //     1998
        // ), "Harry potter");

        // lib.addReader(new Reader(
        //     "Eduard",
        //     "Oplakanskyi",
        //     18,
        //     lib.getBook("Philosopher")
        // ));
        //pryklady

            // 1. Додаємо 3 нові полички (разом з існуючою "Harry potter" буде 4)
            lib.addShelf(new Shelf("Dystopian Future", "dystopia"));
            lib.addShelf(new Shelf("Science Fiction", "sci-fi"));
            lib.addShelf(new Shelf("Programming & Tech", "tech"));

            // 2. Створюємо нових авторів
            Author rowling = new Author("Joanne", "Rowling", "J. K. Rowling", 60);
            Author orwell = new Author("George", "Orwell", "Eric Arthur Blair", 46);
            Author asimov = new Author("Isaac", "Asimov", "Isaac Asimov", 72);
            Author martin = new Author("George", "Martin", "George R. R. Martin", 75);
            Author bloch = new Author("Joshua", "Bloch", "Joshua Bloch", 62);

            // 3. Додаємо 10 нових книжок (разом з HP буде 11+)
            
            // Книги для існуючих поличок
            lib.addBook(new Book("1984", new String[]{"dystopia", "classic"}, new Author[]{orwell}, 1949), "Dystopian");
            lib.addBook(new Book("Animal Farm", new String[]{"dystopia", "satire"}, new Author[]{orwell}, 1945), "Dystopian");
            
            lib.addBook(new Book("I, Robot", new String[]{"sci-fi"}, new Author[]{asimov}, 1950), "Science");
            lib.addBook(new Book("Foundation", new String[]{"sci-fi"}, new Author[]{asimov}, 1951), "Science");

            lib.addBook(new Book("Effective Java", new String[]{"tech", "programming"}, new Author[]{bloch}, 2001), "Tech");
            lib.addBook(new Book("Java Puzzlers", new String[]{"tech", "programming"}, new Author[]{bloch}, 2005), "Tech");

            // Книги, що автоматично створять або знайдуть полички за жанром
            lib.addBook(new Book("A Game of Thrones", new String[]{"fantasy"}, new Author[]{martin}, 1996));
            lib.addBook(new Book("A Clash of Kings", new String[]{"fantasy"}, new Author[]{martin}, 1998));
            
            // Книга нового жанру (автоматично створить поличку "horror shelf")
            lib.addBook(new Book("The Caves of Steel", new String[]{"sci-fi", "mystery"}, new Author[]{asimov}, 1954));
            lib.addBook(new Book("Harry Potter and the Goblet of Fire", new String[]{"fantasy"}, new Author[]{rowling}, 2000));

            // 4. Додаємо читачів
            Reader reader1 = new Reader("Eduard", "Oplakanskyi", 18);
            reader1.addBook(lib.getBook("1984"));
            reader1.addBook(lib.getBook("Effective Java"));
            lib.addReader(reader1);

            Reader reader2 = new Reader("Ivan", "Petrov", 20);
            reader2.addBook(lib.getBook("Foundation"));
            lib.addReader(reader2);
        //pryklady

        // lib.deleteReader(lib.getReader("Oplakansk"));
    }

}
