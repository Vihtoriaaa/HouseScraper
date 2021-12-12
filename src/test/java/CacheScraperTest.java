import org.junit.jupiter.api.Test;
import scraper.*;
import static org.junit.jupiter.api.Assertions.*;


public class CacheScraperTest {
    @Test
    public void testParseFirstHouse() {
        String url1 = "https://www.newhomesource.com/plan/the-campania-pacesetter-homes-texas-san-marcos-tx/1924431";
        CacheScraper cacheScraper = new CacheScraper();
        Home home = cacheScraper.parse(url1);
        String result = home.toString();
        String expectedResult = "Home(housePrice=393900.0, houseBeds=4, houseBaths=3, houseGarages=2, houseArea=2118.0)";
        assertEquals(result, expectedResult);
    }

    @Test
    public void testParseSecondHouse() {
        String url2 = "https://www.newhomesource.com/plan/colinas-ii-drees-custom-homes-dripping-springs-tx/1860359";
        CacheScraper cacheScraper = new CacheScraper();
        Home home = cacheScraper.parse(url2);
        String result = home.toString();
        String expectedResult = "Home(housePrice=844900.0, houseBeds=5, houseBaths=45, houseGarages=3, houseArea=4414.0)";
        assertEquals(result, expectedResult);
    }
}
