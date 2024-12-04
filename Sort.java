public class Sort {
    private String column;

    public Sort(String column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return column;
    }
}
