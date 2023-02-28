package com.posada.santiago.alphapostsandcomments.domain.values;

import co.com.sofka.domain.generic.ValueObject;

public class PersonalInformation implements ValueObject<PersonalInformation.Props> {

    private final String firstName;
    private final String lastName;

    private final int age;

    public PersonalInformation(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }


    @Override
    public Props value() {
        return new Props() {
            @Override
            public String firstName() {
                return firstName;
            }

            @Override
            public String lastName() {
                return lastName;
            }

            @Override
            public int age() {
                return age;
            }
        };
    }

    interface Props{
        String firstName();
        String lastName();

        int age();
    }
}
