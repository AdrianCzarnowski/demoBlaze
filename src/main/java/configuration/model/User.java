package configuration.model;


import lombok.Data;

@Data
public class User {
    private String name;
    private String password;
    private String country;
    private String city;
    private int card;

    public static final class UserBuilder {
        private String firstName;
        private String password;
        private String country;
        private String city;
        private int card;

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder country(String country) {
            this.country = country;
            return this;
        }

        public UserBuilder city(String city) {
            this.city = city;
            return this;
        }

        public UserBuilder card(int card) {
            this.card = card;
            return this;
        }

        public User userBuilder() {
            if (firstName.isEmpty()) {
                throw new IllegalStateException("Firstname cannot be empty");
            }
            if (password.isEmpty()) {
                throw new IllegalStateException("password cannot be empty");

            }
            if (country.isEmpty()) {
                throw new IllegalStateException("country cannot be empty");
            }
            if (city.isEmpty()) {
                throw new IllegalStateException("city cannot be empty");
            }
            User user = new User();
            user.name = this.firstName;
            user.password = this.password;
            user.country = this.country;
            user.city = this.city;
            user.card = this.card;
            return user;
        }
    }

}
