package scraper;

public class Main {
    public static void main(String[] args) {
        CacheScraper cacheScraper = new CacheScraper();
        String url = "https://www.newhomesource.com/plan/the-campania-pacesetter-homes-texas-san-marcos-tx/1924431";
        Home house = cacheScraper.parse(url);
        System.out.println(house.toString());
    }
}
