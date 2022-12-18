package configuration.factory;

import com.github.javafaker.Faker;

public class FakeDataFactory {

    static Faker faker = new Faker();

    public static String getFakeFirstName() {
        return faker.name().firstName();
    }

    public static String getFakeCountry() {
        return faker.country().name();
    }

    public static String getPassword() {
        return faker.internet().password(8, 24, true, true);
    }

    public static String getCity() {
        return faker.address().cityName();
    }

    public static String getNumber() {
        return faker.numerify("#########");
    }

}


