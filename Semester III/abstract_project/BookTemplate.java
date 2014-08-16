package semester_iii.abstract_project;

public class BookTemplate {

    private String title;
    private String author;
    private String isbn;
    private String subject;

    public BookTemplate(String title, String author, String isbn, String subject) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return title + " by" + author + "\nISBN: " + isbn + "\nSubject: " + subject;
    }
}