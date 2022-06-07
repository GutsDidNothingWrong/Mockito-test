import org.junit.jupiter.api.*;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class LocalizationServiceImplTests {
    static LocalizationServiceImpl localizationServiceTest = new LocalizationServiceImpl();

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
    public void testLocaleRussia() {
        String expected = "Добро пожаловать";
        String result = localizationServiceTest.locale(Country.RUSSIA);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testLocaleUSA() {
        String expected = "Welcome";
        String result = localizationServiceTest.locale(Country.USA);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testLocaleNotEquals() {
        String expected = "Welcome";
        String result = localizationServiceTest.locale(Country.RUSSIA);
        assertThat(result, not(expected));
    }
}
