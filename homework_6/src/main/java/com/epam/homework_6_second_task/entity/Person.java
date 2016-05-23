package com.epam.homework_6_second_task.entity;

public class Person {
    private String name;
    private int age;
    private EnumSex sex;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public EnumSex getSex() {
        return sex;
    }

    public static PersonBuilder builder() {
        return new Person().new PersonBuilder();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name = " + name +
                ", age = " + age +
                ", sex = " + sex.toString().toLowerCase() +
                '}';
    }

    public class PersonBuilder {

        private PersonBuilder() {
        }

        public PersonBuilder age(int age) {
            Person.this.age = age;
            return this;
        }

        public PersonBuilder name(String name) {
            Person.this.name = name;
            return this;
        }

        public PersonBuilder sex(EnumSex sex) {
            Person.this.sex = sex;
            return this;
        }

        public Person build() {
            return Person.this;
        }
    }

}
