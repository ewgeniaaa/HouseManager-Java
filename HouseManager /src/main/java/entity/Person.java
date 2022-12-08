package entity;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private PersonType personType;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="age", nullable = false)
    private int age;

    @Column(name="useLift", nullable = false)
    private boolean useLift;

    @ManyToOne
    private Apartment apartment;

    //constructors
    public Person() {
    }

    public Person(String name, int age, boolean useLift) {
        this.name = name;
        this.age = age;
        this.useLift = useLift;
    }

    //getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isUseLift() {
        return useLift;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public PersonType getPersonType() {
        return personType;
    }

    //setters
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUseLift(boolean useLift) {
        this.useLift = useLift;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    //toString
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", useLift=" + useLift +
                '}';
    }
}
