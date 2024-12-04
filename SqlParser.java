public class SqlParser {
    public static void main(String[] args) {
        var query = "SELECT * FROM table1 LEFT JOIN table2 ON table1.id = table2.id LEFT JOIN table3 ON table1.id = table3.id WHERE table1.id = 1 ORDER BY table1.id ASC";
        var queryObj = new Query(query);
        System.out.println(queryObj.toString());
    }
}