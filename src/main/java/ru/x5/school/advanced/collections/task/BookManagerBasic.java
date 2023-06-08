package ru.x5.school.advanced.collections.task;

import java.util.*;
import java.util.stream.Collectors;

public class BookManagerBasic implements BooksManager {

    Map<BookDescription, Integer> books;
    Map<Client,List<BookDescription>> rentList;

    public BookManagerBasic(){
        books = new HashMap<>();
        rentList = new HashMap<>();
    }

    @Override
    public int addBook(BookDescription bookDescription) {
        books.put(bookDescription, books.getOrDefault(bookDescription,0)+1);
        return  books.get(bookDescription);
    }

    @Override
    public int addBook(BookDescription bookDescription, int count) {
        books.put(bookDescription, books.getOrDefault(bookDescription,0)+count);
        return  books.get(bookDescription);
    }

    @Override
    public int getBookAmount(BookDescription book) {
        if (books.containsKey(book))
            return books.get(book);
        return 0;
    }

    @Override
    public List<BookDescription> getBooksByCount() {
        var booksByCount = books.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(x -> x.getKey())
                .collect(Collectors.toList());
        return booksByCount;
    }

    @Override
    public boolean rentBook(Client client, BookDescription book) {
        if (!rentList.containsKey(client))
            rentList.put(client, new ArrayList<>());

        if (!books.containsKey(book))
            return false;

        if (books.get(book) != 0 && !rentList.get(client).contains(book)) {
            books.put(book, books.getOrDefault(book,0) -1);
            rentList.get(client).add(book);
            return true;
        }
        return false;
    }

    @Override
    public boolean returnBook(Client client, BookDescription book) {
        if (!rentList.containsKey(client))
            return false;
        if (rentList.get(client).contains(book)) {
            rentList.get(client).remove(book);
            books.put(book, books.getOrDefault(book,0)+1);
            return true;
        }
        return false;
    }

    @Override
    public Collection<BookDescription> getRentedBooks(Client client) {
        return rentList.get(client);
    }

    @Override
    public Map<BookDescription, Integer> getRentedBooks() {
        Map<BookDescription, Integer> rentedBooks = new HashMap<>();
        books.forEach((book,count) -> {
            rentedBooks.put(book, getBookRenters(book).size());
        });
        return rentedBooks;
    }

    @Override
    public Collection<Client> getBookRenters(BookDescription book) {
        return rentList.entrySet().stream().filter(l -> l.getValue().contains(book)).map(x -> x.getKey()).collect(Collectors.toList());
    }

    @Override
    public List<Client> getRentChampions() {
        Map<Client,Integer> clients = new HashMap<>();
        rentList.forEach((c,b) -> {
            clients.put(c,b.size());
        });
        //rentList.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        return clients.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(3).map(x -> x.getKey()).collect(Collectors.toList());
    }
}
