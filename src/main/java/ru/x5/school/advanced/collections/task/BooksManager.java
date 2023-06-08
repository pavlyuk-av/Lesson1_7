package ru.x5.school.advanced.collections.task;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BooksManager {
    /**

     * Добавляет книгу в библиотеку в количестве 1 шт..

     *

     * @param bookDescription - описание книги

     * @return - общее количество книг данного вида в наличии в библиотеке

     */

    int addBook(BookDescription bookDescription);

    /**

     * Добавляет книгу в библиотеку, в указанном количестве.

     *

     * @param bookDescription - описание книги

     * @param count - добавляемое количество книг

     * @return - общее количество книг данного вида в наличии в библиотеке

     */

    int addBook(BookDescription bookDescription, int count);

    /**

     * Возвращает количество книг данного вида в библиотеке

     * @param book - описание книги

     * @return - количество книг

     */

    int getBookAmount(BookDescription book);

    /**

     * Возвращает список книг в хранилище в порядке уменьшения количества.

     *

     * Включает в себя книги, которые в библиотеке есть, но сейчас все на руках.

     * @return - список книг

     */

    List<BookDescription> getBooksByCount();

    /**

     * Выдает книгу на руки читателю, вычитая 1 штуку из количества в библиотеке.

     * Если читатель уже взял какую-то книгу, то повторно ее же ему выдавать нельзя

     *

     * @param client - читатель.

     * @param book - выдаваемая книга

     * @return - true, если получилось выдать, false - не полуилось

     * (нет книг в хранилище, либо книгу уже была взята этим читателем)

     */

    boolean rentBook(Client client, BookDescription book);

    /**

     * Возвращает книгу в библиотеку, увеличивая количество в хранилище на 1.

     * Важно - читатель не может сдать книгу, если он ее раньше не брал.

     * @param client - читатель

     * @param book - возвращаемая книга

     * @return - true, если книга успешно возвращена,  false - возврат не удался (читатель не брал эту книгу)

     */

    boolean returnBook(Client client, BookDescription book);

    /**

     * Возвращает книги, находящиеся у читателя.

     * @param client - читатель

     * @return - книги

     */

    Collection<BookDescription> getRentedBooks(Client client);

    /**

     * Возвращает информация по количеству арендованных экземпляров разных книг.

     * Если книга не у читателей, должно быть 0

     *

     * @return - словарь с книгами и их арендованным количеством.

     */

    Map<BookDescription, Integer> getRentedBooks();

    /**

     * Возвращает читателей арендовавших экземпляры данной книги.

     *

     * @param book - книга

     * @return - читатели. Возвращаемое значение неизменяемое, при попытке его изменить выбрасывается исключение

     */

    Collection<Client> getBookRenters(BookDescription book);

    /**

     * Возвращает список из трех читателей с максимальным количество книг, отсортированным по уменьшению

     * Желательно использовать алгоритм оптимальный по скорости для данных условий. Общее число клиентов может быть любым

     * @return чемпионы по книгам

     */

    List<Client> getRentChampions();
}
