public class Join {
    private String JoinType;

    public Join(String JoinType) {
        this.JoinType = JoinType;
    }

    @Override
    public String toString() {
        return JoinType;
    }
}