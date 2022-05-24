import org.junit.jupiter.api.*;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class LocalizationServiceImplTests {
    static LocalizationServiceImpl sut;

    @BeforeAll
    public static void init() {
        System.out.println("Starting tests");
        sut = new LocalizationServiceImpl();
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
    public void test_locale_Russia() {
        String expected = "Добро пожаловать";
        String result = sut.locale(Country.RUSSIA);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_locale_USA() {
        String expected = "Welcome";
        String result = sut.locale(Country.USA);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_locale_NotEquals() {
        String expected = "Welcome";
        String result = sut.locale(Country.RUSSIA);
        assertThat(result, not(expected));
    }
}
