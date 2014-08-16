package semester_iii.abstract_project;

public class Popular extends BookTemplate implements Book {

    private boolean awards;

    public Popular(boolean awards, String title, String author, String isbn, String subject) {
        super(title, author, isbn, subject);
        this.awards = awards;
    }

    public boolean hasWonAwards() {
        return awards;
    }

    public void awardAwards() {
        awards = true;
    }

    @Override
    public String getGenre() {
        return "Fiction";
    }

    @Override
    public String getType() {
        return "Paperback";
    }

    @Override
    public String toString() {
        return super.toString() + "Awards: " + awards;
    }
}