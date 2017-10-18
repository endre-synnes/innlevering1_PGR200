//class Nikita{
//    /**
//     * Create String query - to show all info
//     * I use (SELECT *) only to get access to metaData of table
//     * After create a new query to select each column name
//     * return new query
//     */
//    private String getQueryShowTable(String tableName)
//    {
//        StringBuilder sb = new StringBuilder("SELECT ");
//        // Test query to db to get meta
//        try (Connection connection = getConnection();
//             PreparedStatement selectAll = connection.prepareStatement(
//                     "SELECT * FROM " + tableName, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//             ResultSet rs = selectAll.executeQuery()
//        )
//        {
//            // Build new query based on ResultSet to take all columns name
//            ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
//            for (int i = 1; i < metaData.getColumnCount(); i++)
//            {
//                sb.append(metaData.getColumnName(i) + ", ");
//            }
//            sb.append(metaData.getColumnName(metaData.getColumnCount()) + " FROM " + tableName);
//
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//
//        return sb.toString();
//    }
//
//
//    /**
//     * Create ResultSet
//     * Fill rs with all data from table
//     * !!! return CachedRowSetImpl (safety return of rs) only read
//     * source: http://stackoverflow.com/questions/14853508/returning-a-resultset
//     * javaDocSource: https://docs.oracle.com/javase/7/docs/api/javax/sql/rowset/CachedRowSet.html#populate(java.sql.ResultSet)
//     */
//    public ResultSet getAllFromTable(String tableName)
//    {
//        String queryShowTable = getQueryShowTable(tableName);
//        //System.out.println(queryShowTable);
//        CachedRowSetImpl crs = null;
//
//        try (Connection connection = getConnection();
//             PreparedStatement selectAll = connection.prepareStatement(
//                     queryShowTable, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//             ResultSet rs = selectAll.executeQuery()
//        )
//        {
//            crs = new CachedRowSetImpl();
//            crs.populate(rs);
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
//        return crs;
//    }
//}
//
//
//
//
//public String toString()
//    {
//        ArrayList<String[]> data = getOnlyData();
//        String line = (char) 27 + "[34m### showTable (toString method) " + this.getName() + (char) 27 + "[0m" + "\n";
//        return line + data.stream()
//                .map(s -> Arrays.asList(s).toString())
//                .collect(Collectors.joining("\n"));
//    }
//}