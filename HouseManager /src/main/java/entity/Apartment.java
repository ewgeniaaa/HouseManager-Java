package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="apartment")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="floor", nullable = false)
    private int floor;

    @Column(name="area", nullable = false)
    private double area;

    @Column(name="pet", nullable = false)
    private boolean hasPet;

    @Column(name="fee", nullable = false)
    private double fee ;

    @OneToMany(mappedBy = "apartment")
    private List<Person> peopleList;

    @ManyToOne
    private Building building;

    //constructors
    public Apartment() {
    }

    public Apartment(int floor, double area, boolean hasPet) {
        this.floor = floor;
        this.area = area;
        this.hasPet=hasPet;
    }

    //getters
    public long getId() {
        return id;
    }

    public int getFloor() {
        return floor;
    }

    public double getArea() {
        return area;
    }

    public boolean isHasPet() {
        return hasPet;
    }

    public List<Person> getPeopleList() {
        return peopleList;
    }

    public Building getBuilding() {
        return building;
    }

    //setters
    public void setId(long id) {
        this.id = id;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setHasPet(boolean hasPet) {
        this.hasPet = hasPet;
    }

    public void setPeopleList(List<Person> peopleList) {
        this.peopleList = peopleList;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    //fee method
    public double fee(){
        if(area<=65)
            fee=20;
        else if(area>65 && area<=80)
            fee=35;
        else if(area>80 && area<=140)
            fee=40;
        else if(area>140)
            fee=55;
        else fee=0;

        for(Person person:peopleList){
            if(person.getAge()>7 && person.isUseLift()==true)
                fee+=6;
        }

        if(hasPet==true)
            fee+=2;

        return fee;
    }

    //toString
    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", floor=" + floor +
                ", area=" + area +
                ", hasPet=" + hasPet +
                //", peopleList=" + peopleList +
                ", building=" + building +
                ", fee=" + fee +
                '}';
    }
}