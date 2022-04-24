package ru.whitegray.exercise_1;

public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private String age;
    private String gender;

    private Person(PersonBuilder personBuilder) {
        firstName = personBuilder.firstName;
        lastName = personBuilder.lastName;
        middleName = personBuilder.middleName;
        country = personBuilder.country;
        address = personBuilder.address;
        phone = personBuilder.phone;
        age = personBuilder.age;
        gender = personBuilder.gender;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Person ").append(firstName).append(" ").append(lastName).append(" ").append(middleName)
                .append(", пол ").append(gender).append(", возраст ").append(age)
                .append(", из страны ").append(country).append(", по адресу ").append(address)
                .append(", тел.номер ").append(phone);
        return builder.toString();
    }

    public static class PersonBuilder {
        public String firstName;
        public String lastName;
        public String phone;

        public String middleName = "отчество не известно";
        public String country = "не известно";
        public String address = "не известно";
        public String age = "не известно";
        public String gender = "не известно";

        public PersonBuilder(String firstName, String lastName, String phone) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
        }

        public PersonBuilder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public PersonBuilder country(String country) {
            this.country = country;
            return this;
        }

        public PersonBuilder address(String address) {
            this.address = address;
            return this;
        }

        public PersonBuilder age(String age) {
            this.age = age;
            return this;
        }

        public PersonBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }


        public Person build() {
            return new Person(this);
        }
    }
}
