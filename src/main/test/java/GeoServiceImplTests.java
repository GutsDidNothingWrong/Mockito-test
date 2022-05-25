import org.junit.jupiter.api.*;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceImplTests {
    static GeoServiceImpl sut = new GeoServiceImpl();

    @BeforeAll
    public static void init() {
        System.out.println("Starting tests");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("Test completed");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("All tests completed");
    }

    @Test
    public void testByIpIsInstanceLocationClass() {
        String ip = "172.0.0.1";
        Location result = sut.byIp(ip);
        Assertions.assertInstanceOf(Location.class, result);
    }

    @Test
    public void testByIpRussia() {
        String ip = "172.0.0.1";
        Country expected = Country.RUSSIA;
        Country result = sut.byIp(ip).getCountry();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testByIpUSA() {
        String ip = "96.0.0.1";
        Country expected = Country.USA;
        Country result = sut.byIp(ip).getCountry();
        Assertions.assertEquals(expected, result);
    }

}
