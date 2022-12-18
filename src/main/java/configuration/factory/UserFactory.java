package configuration.factory;

import configuration.model.User;

public class UserFactory {

    public User getRandomUser() {

        User user = new User.UserBuilder()
                .firstName(FakeDataFactory.getFakeFirstName()+FakeDataFactory.getNumber())
                .password(FakeDataFactory.getPassword())
                .country(FakeDataFactory.getFakeCountry())
                .city(FakeDataFactory.getCity())
                .card(Integer.parseInt(FakeDataFactory.getNumber())).userBuilder();
        return user;
    }
}
