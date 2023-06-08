package ru.x5.school.advanced.collections.task;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class TestApp {
    static Supplier<BookManagerBasic> managerSupplier;
    static Client client1;
    static Client client2;
    static Client client3;
    static Client client4;
    static Client client5;
    static Client client6;

    static BookDescription book1;
    static BookDescription book2;
    static BookDescription book3;
    static BookDescription book4;
    static BookDescription book5;
    static BookDescription book6;


    public TestApp(Supplier<BookManagerBasic> managerSupplier) {
        this.managerSupplier = managerSupplier;
    }

    public void testRentBook() {
        var manager = managerSupplier.get();
        manager.addBook(book2, 3);

        assert(manager.rentBook(client1, book2));
        assert(manager.getBookAmount(book2) == 2);
        var clientBooks = manager.getRentedBooks(client1);
        assert(clientBooks.size() == 1);
        assert(clientBooks.contains(book2));
    }

    public void testRentBookTwice() {
        var manager = managerSupplier.get();
        manager.addBook(book1, 3);

        assert(manager.rentBook(client1, book1));
        assert(!manager.rentBook(client1, book1));
        var clientBooks = manager.getRentedBooks(client1);
        assert(clientBooks.size() == 1);
        assert(clientBooks.contains(book1));
    }

    private static void fillBooks(BooksManager manager) {
        manager.addBook(book1);
        manager.addBook(book2, 3);
        manager.addBook(book3, 5);
        manager.addBook(book4, 7);
        manager.addBook(book5, 5);
        manager.addBook(book6, 2);
    }

    private void rentBooks(BooksManager manager) {
        manager.rentBook(client1, book2);
        manager.rentBook(client1, book3);
        manager.rentBook(client1, book5);
        manager.rentBook(client1, book4);

        manager.rentBook(client2, book3);
        manager.rentBook(client2, book6);
        manager.rentBook(client2, book1);

        manager.rentBook(client3, book5);

        manager.rentBook(client4, book2);
        manager.rentBook(client4, book5);

        manager.rentBook(client5, book2);
        manager.rentBook(client5, book3);
        manager.rentBook(client5, book4);
        manager.rentBook(client5, book5);
        manager.rentBook(client5, book6);

        manager.rentBook(client6, book4);
        manager.rentBook(client6, book3);
    }

    public void testBooksList() {
        var manager = managerSupplier.get();
        fillBooks(manager);
        rentBooks(manager);
        var books = manager.getRentedBooks(client1);
        assert(books.size() == 4);
        books.add(book6);
        assert(manager.getRentedBooks(client1).size() == 4);
    }

    static Logger log = Logger.getLogger("mainTestApp");
    public static void main(String[] args) throws InterruptedException {
        log.info("Start Test");
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService getExecutor = Executors.newFixedThreadPool(cores);
        ExecutorService putExecutor = Executors.newFixedThreadPool(cores);

        client1 = new Client("Петр", "Михайлов", "320804506");
        client2 = new Client("Васисуалий", "Лоханкин", "987344234");
        client3 = new Client("Нечай", "Петров", "3422110233");
        client4 = new Client("Алевтина", "Ангилоки", "342342344");
        client5 = new Client("Ингеборга", "Дапкунайте", "2343242342");
        client6 = new Client("Стивен", "Спилберг", "530804306");

        book1 = new BookDescription("The Hitchhiker's guide to the Galaxy", "Douglas Adams", 1979);
        book2 = new BookDescription("Core Java. Volume I - Fundamentals", "Horstmann C.S.", 2019);
        book3 = new BookDescription("Core Java. Volume II - Advanced Features", "Horstmann C.S.", 2019);
        book4 = new BookDescription("Былое и думы", "А. И. Герцен", 1868);
        book5 = new BookDescription("Cryptonomicon", "Neal Stephenson", 1999);
        book6 = new BookDescription("В глубине великого кристалла", "Владислав Крапивин", 1989);

        BookManagerBasic manager = new BookManagerBasic();

        fillBooks(manager);

        BookGetter bg = new BookGetter(manager, client1, book1);
        getExecutor.execute(bg);
        System.out.println(manager.getBookAmount(book1));
        getExecutor.execute(bg);
        System.out.println(manager.getBookAmount(book1));
        getExecutor.execute(bg);
        System.out.println(manager.getBookAmount(book1));

        System.out.println("ReturnBooks");
        BookReturner br = new BookReturner(manager, client1, book1);
        putExecutor.execute(br);
        System.out.println(manager.getBookAmount(book1));
        putExecutor.execute(br);
        System.out.println(manager.getBookAmount(book1));
        putExecutor.execute(br);
        System.out.println(manager.getBookAmount(book1));
        getExecutor.shutdown();
        putExecutor.shutdown();
        System.out.println("Finished");
    }
}
