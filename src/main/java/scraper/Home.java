package scraper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor @ToString @Getter @Builder
public class Home {
    private double housePrice;
    private int houseBeds;
    private int houseBaths;
    private int houseGarages;
    private double houseArea;
}
