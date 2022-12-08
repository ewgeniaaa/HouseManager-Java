package entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "building")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "building")
    private List<Apartment> apartmentList;

    @ManyToOne
    //@JoinColumn(name="cleaningCompany_id")
    private CleaningCompany cleaningCompany;

    @ManyToOne(fetch = FetchType.LAZY)
    private entity.Employee employee;

    //constructors
    public Building() {
    }

    public Building(String name, String address, List<Apartment> apartmentList) {
        this.name = name;
        this.address=address;
        this.apartmentList=new ArrayList<>();
    }

    //getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Apartment> getApartmentList() {
        return apartmentList;
    }

    public Employee getEmployee() {
        return employee;
    }

    public CleaningCompany getCleaningCompany() {
        return cleaningCompany;
    }

    //setters
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setApartmentList(List<Apartment> apartmentList) {
        this.apartmentList = apartmentList;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setCleaningCompany(CleaningCompany cleaningCompany) {
        this.cleaningCompany = cleaningCompany;
    }

    //method to get people from each apartment
    public List<Person> getPeople_fromEachApartment(){
        List<Person> people=new ArrayList<>();
        for(Apartment apartment:getApartmentList()){
            people.addAll(apartment.getPeopleList());
        }
        return people;
    }

    //toString
    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                //", apartmentList=" + apartmentList +
                //", employee=" + employee +
                '}';
    }
}