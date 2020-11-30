package com.ncedu.cheetahtest.dao.genericdao;

public class Consts {

    public static final String SELECT_ROWS = "SELECT %s ";
    public static final String FROM_TABLE = "FROM %s ";
    public static final  String WHERE_STATUS_ACTIVE = " WHERE status = 'ACTIVE' ";
    public static final  String ORDER_PAGINATED = " ORDER BY id LIMIT ? OFFSET ? ";
    public static final String SELECT_COUNT_ID = "SELECT count(id) ";

    private final String selectAllRowsFromTable;
    private final String tableName;

    public Consts(String[] rows, String tableName) {
        selectAllRowsFromTable = formatArrayIntoString(rows);
        this.tableName = tableName;
    }

    private String formatArrayIntoString(String[] rows) {
        StringBuilder select = new StringBuilder();

        for(int i = 0; i < rows.length; i++) {
            select.append(rows[i]);
            if (i < rows.length - 1) {
                select.append(", ");
            }
        }

        return select.toString();
    }

    public String getActivePaginated() {
        return String.format(
                SELECT_ROWS +
                FROM_TABLE +
                WHERE_STATUS_ACTIVE +
                ORDER_PAGINATED,
                selectAllRowsFromTable, tableName);
    }

    public String getAllPaginated() {
        return String.format(
                SELECT_ROWS +
                FROM_TABLE +
                ORDER_PAGINATED,
                selectAllRowsFromTable, tableName);
    }

    public String getAmountActive() {
        return String.format(
                SELECT_COUNT_ID +
                FROM_TABLE +
                WHERE_STATUS_ACTIVE,
                tableName);
    }

    public String getAmountAll() {
        return String.format(
                SELECT_COUNT_ID +
                FROM_TABLE,
                tableName);
    }

    public String getById() {
        return String.format(
                SELECT_ROWS +
                FROM_TABLE +
                " WHERE id = ? LIMIT 1",
                selectAllRowsFromTable, tableName);
    }

    public String deactivate() {
        return String.format(
                "UPDATE %s " +
                "SET status = 'INACTIVE'::status " +
                " WHERE id = ?",
                tableName);
    }

    public String getActiveSearched() {
        return String.format(
                SELECT_ROWS +
                FROM_TABLE+
                WHERE_STATUS_ACTIVE +
                " AND title LIKE ? " +
                ORDER_PAGINATED,
                selectAllRowsFromTable, tableName);
    }

    public String getAllSearched() {
        return String.format(
                SELECT_ROWS +
                FROM_TABLE +
                " WHERE title LIKE ? " +
                ORDER_PAGINATED,
                selectAllRowsFromTable, tableName);
    }

    public String getAmountActiveSearched() {
        return String.format(
                SELECT_COUNT_ID +
                FROM_TABLE +
                WHERE_STATUS_ACTIVE +
                " AND title LIKE ? LIMIT 1",
                tableName);
    }

    public String getAmountAllSearched() {
        return String.format(
                SELECT_COUNT_ID +
                FROM_TABLE +
                "WHERE title LIKE ? LIMIT 1",
                tableName);
    }
}
