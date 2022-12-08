package entity;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Stream;

@Entity
@Table(name = "cleaning_company")
public class CleaningCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="capital", nullable = false)
    private double capital;

    @OneToMany
    private Set<Employee> employeeSet;

    @OneToMany
    private List<Building> buildingList;

    //constructors
    public CleaningCompany() {
    }

    public CleaningCompany(String name, Set<Employee> employeeSet) {
        this.name = name;
        this.employeeSet = employeeSet;
    }

    //getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public double getCapital() {
        return capital;
    }

    //setters
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    public void setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    //method - buildings serviced by particular employee
    public void employeeBuilding(Building building, Set<Employee> employeeSet){
        List<Building> buildingList = new ArrayList<>();
        ArrayList<Employee> elements = new ArrayList<>(employeeSet);
        Employee emp = new Employee();
        for(Employee employee:elements) {
            for (int i = 0; i < elements.size() - 1; i++) {
                for (int k = i + 1; k < elements.size(); k++) {
                    if (elements.get(i).getBuildingList().size() < elements.get(k).getBuildingList().size())
                        emp = elements.get(i);
                    else
                        emp=elements.get(k);
                }
            }
        }
        buildingList.add(building);
        emp.setBuildingList(buildingList);
        System.out.println(emp);
    }

    // adding paid taxes
    public double capital(){
        List<Apartment> apartmentList=new ArrayList<>();
        for(Building building:buildingList) {
            apartmentList.addAll(building.getApartmentList());
        }
        for(Apartment apartment:apartmentList)
            capital+=apartment.fee();
        return capital;
    }

    //toString
    @Override
    public String toString() {
        return "CleaningCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                //", employeeSet=" + employeeSet +
                ", capital=" + capital +
                '}';
    }
}