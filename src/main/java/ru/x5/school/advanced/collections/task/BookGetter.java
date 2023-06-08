package ru.x5.school.advanced.collections.task;

public class BookGetter implements Runnable {
    BookManagerBasic bookManager;
    Client client;
    BookDescription book;

    public BookGetter (BookManagerBasic bookManager, Client client, BookDescription book) {
        this.bookManager = bookManager;
        this.client = client;
        this.book = book;
    }
    public void ChangeClientAndBook(Client client, BookDescription book) {
        this.client = client;
        this.book = book;
    }
    public void ChangeClient(Client client) {
        this.client = client;
    }
    public void ChangeBook(BookDescription book) {
        this.book = book;
    }

    public void run(){
        bookManager.rentBook(client, book);
    }
}
