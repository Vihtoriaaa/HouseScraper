package scraper;

import lombok.SneakyThrows;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CacheScraper implements Scraper {

    @Override @SneakyThrows
    public Home parse(String url) {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
        Statement statement = connection.createStatement();
        String query = String.format("select count(*) as count from homes where url='%s'", url);
        System.out.println("Connecting to DataBase...");
        ResultSet result = statement.executeQuery(query);
        int counter = result.getInt("count");
        if (counter == 0) {
            DefaultScraper defaultParser = new DefaultScraper();
            Home home = defaultParser.parse(url);
            insertHomeIntoDB(statement, home, url);
            return home;
        } else {
            return selectHomeFromDB(statement, url);
        }
    }

    @SneakyThrows
    private void insertHomeIntoDB(Statement statement, Home home, String url) {
        String insertedQuery = String.format("insert into homes(url, price, beds, baths, garages, area) values('%s', '%f', '%d', '%d', '%d', '%f')", url, home.getHousePrice(), home.getHouseBeds(), home.getHouseBaths(), home.getHouseGarages(), home.getHouseArea());
        statement.executeUpdate(insertedQuery);
        System.out.println("Retrieving information from website...");
    }

    @SneakyThrows
    private Home selectHomeFromDB(Statement statement, String url) {
        String selectQuery = String.format("select * from homes where url='%s'", url);
        ResultSet result = statement.executeQuery(selectQuery);
        Home home = new Home(result.getDouble("price"), Integer.parseInt(result.getString("beds").replaceAll(",", ".")), Integer.parseInt(result.getString("baths").replaceAll(",", ".")), Integer.parseInt(result.getString("garages").replaceAll(",", ".")), Double.parseDouble(result.getString("area").replaceAll(",", ".")));
        System.out.println("Retrieving information from DataBase...");
        return home;
    }
}
