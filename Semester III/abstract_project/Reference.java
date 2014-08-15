package semester_iii.abstract_project;

public class Reference extends BookTemplate implements Book {

    private boolean series = false;
    private boolean recognized = false;

    public Reference(String title, String author, String isbn, String subject, boolean series, boolean recognized) {
        super(title, author, isbn, subject);
        this.series = series;
        this.recognized = recognized;
    }

    public boolean isPartOfSeries() {
        return series;
    }

    public boolean isLargelyRecognized() {
        return recognized;
    }

    public void setAsSeries() {
        series = true;
    }

    public void setAsLargelyRecognized() {
        recognized = true;
    }

    @Override
    public String getGenre() {
        return "Non-fiction";
    }

    @Override
    public String getType() {
        return "Hardback";
    }

    @Override
    public String toString() {
        return super.toString() + "Part of series: " + series + "\nLargely recognized: " + recognized;
    }
}