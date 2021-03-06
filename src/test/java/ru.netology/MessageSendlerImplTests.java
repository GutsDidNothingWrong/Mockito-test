import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSendlerImplTests {
    static MessageSenderImpl messageSenderTest;

    @BeforeAll
    public static void init() {
        System.out.println("Starting tests");
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.startsWith("172.")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(geoService.byIp(Mockito.startsWith("96.")))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        messageSenderTest = new MessageSenderImpl(geoService, localizationService);
    }

    @AfterEach
    public void afterEach() {
        System.out.println(" :Test completed");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("All tests completed");
    }

    @ParameterizedTest
    @CsvFileSource(resources = ("source.csv"))
    public void testIpTextLocalizationRu(String ip, String msg) {
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String result = messageSenderTest.send(headers);
        String expected = msg;

        Assertions.assertEquals(expected, result);
    }
}
