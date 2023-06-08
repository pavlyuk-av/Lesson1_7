package ru.x5.school.advanced.collections.task;
import java.util.Objects;

public class BookDescription {
    private final String title;
    private final String author;
    private final int year;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDescription that = (BookDescription) o;
        return year == that.year && title.equals(that.title) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year);
    }

    public BookDescription(String title, String author, int year) {
        if (title == null ) {
            throw new IllegalArgumentException("Title should be non null!");
        }

        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }
}
