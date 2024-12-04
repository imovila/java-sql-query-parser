import java.util.ArrayList;
import java.util.List;

public class Query {
    private List<String> columns = new ArrayList<String>();
    private List<Source> fromSources = new ArrayList<Source>();
    private List<Join> joins = new ArrayList<Join>();
    private List<WhereClause> whereClauses = new ArrayList<WhereClause>();
    private List<String> groupByColumns = new ArrayList<String>();
    private List<Sort> sortColumns = new ArrayList<Sort>();
    private Integer limit = 0;
    private Integer offset = 0;

    public Query(String query) {
        parseQuery(query);
    }

    private void parseQuery(String query) {
        String[] parts = query.split(" ");
        int index = 0;

        while (index < parts.length) {
            String part = parts[index].toUpperCase();
            switch (part) {
                case "SELECT":
                    index++;
                    while (!parts[index].equalsIgnoreCase("FROM")) {
                        columns.add(parts[index].replace(",", ""));
                        index++;
                    }
                    break;
                case "FROM":
                    index++;
                    fromSources.add(new Source(parts[index]));
                    index++;
                    break;
                case "JOIN":
                    for (int i = ++index; i < parts.length; i++) {
                        if (parts[i].equalsIgnoreCase("JOIN") || parts[i].equalsIgnoreCase("WHERE")) {
                            break;
                        }
                        if (parts[i].equalsIgnoreCase("LEFT") || parts[i].equalsIgnoreCase("RIGHT")
                                || parts[i].equalsIgnoreCase("INNER") || parts[i].equalsIgnoreCase("OUTER")) {
                            continue;
                        }
                        joins.add(new Join(parts[i]));
                        index = i;
                    }
                    index++;
                    break;
                case "WHERE":
                    index++;
                    while (index < parts.length && !parts[index].equalsIgnoreCase("GROUP")
                            && !parts[index].equalsIgnoreCase("ORDER") && !parts[index].equalsIgnoreCase("LIMIT")
                            && !parts[index].equalsIgnoreCase("OFFSET")) {
                        whereClauses.add(new WhereClause(parts[index]));
                        index++;
                    }
                    break;
                case "GROUP":
                    index += 2;
                    while (index < parts.length && !parts[index].equalsIgnoreCase("ORDER")
                            && !parts[index].equalsIgnoreCase("LIMIT") && !parts[index].equalsIgnoreCase("OFFSET")) {
                        groupByColumns.add(parts[index].replace(",", ""));
                        index++;
                    }
                    break;
                case "ORDER":
                    index += 2;
                    while (index < parts.length && !parts[index].equalsIgnoreCase("LIMIT")
                            && !parts[index].equalsIgnoreCase("OFFSET")) {
                        sortColumns.add(new Sort(parts[index]));
                        index++;
                    }
                    break;
                case "LIMIT":
                    index++;
                    limit = Integer.parseInt(parts[index]);
                    index++;
                    break;
                case "OFFSET":
                    index++;
                    offset = Integer.parseInt(parts[index]);
                    index++;
                    break;
                default:
                    index++;
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "Query [columns=" + columns + ", fromSources=" + fromSources + ", joins=" + joins + ", whereClauses="
                + whereClauses + ", groupByColumns=" + groupByColumns + ", sortColumns=" + sortColumns + ", limit="
                + limit + ", offset=" + offset + "]";
    }
}
