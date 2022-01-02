package top.yinzsw.dics.launch.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class DBUtils {
    static String driver;
    static String url;
    static String username;
    static String password;
    Connection connection = null;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DBUtils db = new DBUtils();

    private DBUtils() {
    }

    public static DBUtils getInstance() {
        return db;
    }

    //加载配置与驱动
    static {
        Properties properties = new Properties();
        try {
            System.out.println(sdf.format(new Date()) + ":\tfile->'database.properties' Loading...");

            FileInputStream fin = new FileInputStream("sources/database.properties");
            properties.load(fin);
            fin.close();

            System.out.println(sdf.format(new Date()) + ":\tfile->'database.properties' Loading succeeded");
            driver = properties.getProperty("DRIVER");
            url = properties.getProperty("URL");
            username = properties.getProperty("USERNAME");
            password = properties.getProperty("PASSWORD");

            System.out.println(sdf.format(new Date()) + ":\tdriver->'databaseDriver' Loading...");
            Class.forName(driver);
            System.out.println(sdf.format(new Date()) + ":\tdriver->'databaseDriver' Loading succeeded\n");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    private PreparedStatement getPreparedStatement(String sql, Object... args) throws SQLException {
        System.out.println(sdf.format(new Date()) + ":\tdatabase Connection...");
        connection = createConnection();
        System.out.println(sdf.format(new Date()) + ":\tdatabase Connection succeeded");
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) preparedStatement.setObject(i + 1, args[i]);
        System.out.println(sdf.format(new Date()) + ":\t" + preparedStatement.toString());
        return preparedStatement;
    }

    public int update(String sql, Object... args) {
        int row = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getPreparedStatement(sql, args);
            row = preparedStatement.executeUpdate();
            System.out.println(sdf.format(new Date()) + ":\taffected " + row + " rows\t");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        closeAll(null, preparedStatement, connection);
        return row;
    }

    public <T> ArrayList<T> queryList(Class<T> clazz, String sql, Object... args) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<T> list = new ArrayList<>();
        try {
            preparedStatement = getPreparedStatement(sql, args);
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                T t = clazz.getConstructor().newInstance();
                System.out.print(sdf.format(new Date()) + ":\tresultSet: ");
                for (int i = 1; i <= columnCount; i++) {
                    String name = resultSetMetaData.getColumnName(i);
                    String columnTypeName = resultSetMetaData.getColumnTypeName(i);
                    Object value = resultSet.getObject(name);
                    Field field = clazz.getDeclaredField(name);
                    field.setAccessible(true);

                    System.out.print(name + "=" + resultSet.getObject(name) + " ");

                    if (value == null) continue;
                    switch (columnTypeName) {
                        case "DATETIME":
                            LocalDateTime ldt = (LocalDateTime) value;
                            Date date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
                            field.set(t, date);
                            break;
                        case "DECIMAL":
                            BigDecimal bigDecimal = (BigDecimal) value;
                            field.set(t, bigDecimal.doubleValue());
                            break;
                        default:
                            field.set(t, value);
                            break;
                    }
                }
                System.out.println();
                list.add(t);
            }
        } catch (SQLException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return list;
    }

    private static void closeAll(ResultSet rs, PreparedStatement ps, Connection co) {
        System.out.print(sdf.format(new Date()) + ":\tJDBC Resource Closing...\t");
        try {
            if (rs != null) {
                rs.close();
                System.out.print("ResultSet closed, ");
            }
            if (ps != null) {
                ps.close();
                System.out.print("PreparedStatement closed, ");
            }
            if (co != null) {
                co.close();
                System.out.print("Connection closed.\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(sdf.format(new Date()) + ":\tJDBC Resource Closing succeeded\n");
    }
}