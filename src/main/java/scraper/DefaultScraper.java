package scraper;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class DefaultScraper implements Scraper {
    public static final String PRICE_SELECTOR = ".detail__info-xlrg";
    public static final String BEDS_SELECTOR = ".nhs_BedsInfo";
    public static final String BATHS_SELECTOR = ".nhs_BathsInfo";
    public static final String GARAGE_SELECTOR = ".nhs_GarageInfo";
    public static final String AREA_SELECTOR = ".nhs_SqFtInfo";

    @Override @SneakyThrows
    public Home parse(String url) {
        Document doc = Jsoup.connect(url).get();
        double housePrice = getPrice(doc);
        int houseBeds = getBed(doc);
        int houseGarages = getGarage(doc);
        int houseBaths = getBath(doc);
        double houseArea = getArea(doc);
        return new Home(housePrice, houseBeds, houseBaths, houseGarages, houseArea);
    }

    private static int getPrice(Document doc) {
        Element priceTag = doc.selectFirst(PRICE_SELECTOR);
        String priceText = priceTag.text().replaceAll("[^0-9]", "");
        return Integer.parseInt(priceText);
    }

    private static int getBed(Document doc) {
        Element bedsTag = doc.selectFirst(BEDS_SELECTOR);
        String bedsText = bedsTag.text().replaceAll("[^0-9]", "");
        return Integer.parseInt(bedsText);
    }

    private static int getBath(Document doc) {
        Element bathTag = doc.selectFirst(BATHS_SELECTOR);
        String bathText = bathTag.text().replaceAll("[^0-9]", "");
        return Integer.parseInt(bathText);
    }

    private static int getGarage(Document doc) {
        Element garageTag = doc.selectFirst(GARAGE_SELECTOR);
        String garageText = garageTag.text().replaceAll("[^0-9]", "");
        return Integer.parseInt(garageText);
    }

    private static double getArea(Document doc) {
        Element garageTag = doc.selectFirst(AREA_SELECTOR);
        String garageText = garageTag.text().replaceAll("[^0-9]", "");
        return Double.parseDouble(garageText);
    }
}
