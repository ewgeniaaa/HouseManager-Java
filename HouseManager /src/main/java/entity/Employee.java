package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", nullable = false)
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "employee")
    private List<Building> buildingList;

    @ManyToOne
    private CleaningCompany cleaningCompany;

    //constructors
    public Employee() {
    }

    public Employee(String name, CleaningCompany cleaningCompany) {
        this.name = name;
        this.cleaningCompany = cleaningCompany;
    }

    //getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Building> getBuildingList() {
        return buildingList;
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

    public void setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    public void setCleaningCompany(CleaningCompany cleaningCompany) {
        this.cleaningCompany = cleaningCompany;
    }

    //toString
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cleaningCompany=" + cleaningCompany +
                '}';
    }
}