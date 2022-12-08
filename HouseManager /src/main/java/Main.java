import dao.*;
import entity.*;

import java.util.*;

public class Main {
    public static void main(String args[]){

        //creating cleaning Companies
        CleaningCompany cleaningCompany_Domestosi = new CleaningCompany();
        cleaningCompany_Domestosi.setName("Domestosi");
        CleaningCompanyDAO.saveCleaningCompany(cleaningCompany_Domestosi);

        CleaningCompany cleaningCompany_Arieli = new CleaningCompany();
        cleaningCompany_Arieli.setName("Arieli");
        CleaningCompanyDAO.saveCleaningCompany(cleaningCompany_Arieli);


        //creating Employees
        Employee employee1 = new Employee();
        employee1.setName("Evgenia");
        employee1.setCleaningCompany(cleaningCompany_Domestosi);
        EmployeeDAO.saveEmployee(employee1);

        Employee employee2=new Employee();
        employee2.setName("Svetla");
        employee2.setCleaningCompany(cleaningCompany_Domestosi);
        EmployeeDAO.saveEmployee(employee2);

        Employee employee3=new Employee();
        employee3.setName("Stoil");
        employee3.setCleaningCompany(cleaningCompany_Arieli);
        EmployeeDAO.saveEmployee(employee3);



        //creating buildings
        Building building1=new Building();
        building1.setName("Flowerland");
        building1.setAddress("Osvobojdenie 12");
        building1.setEmployee(employee1);
        BuildingDAO.saveBuilding(building1);

        Building building2=new Building();
        building2.setName("Divine");
        building2.setAddress("Vazrajdane 2");
        building2.setEmployee(employee2);
        BuildingDAO.saveBuilding(building2);

        Building building3=new Building();
        building3.setName("Spring");
        building3.setAddress("Opalchenska 10");
        building3.setEmployee(employee3);
        BuildingDAO.saveBuilding(building3);

        //creating apartments
        Apartment apartment1 = new Apartment();
        apartment1.setArea(72.55);
        apartment1.setFloor(2);
        apartment1.setHasPet(true);
        apartment1.setBuilding(building1);
        ApartmentDAO.saveApartment(apartment1);

        Apartment apartment2=new Apartment();
        apartment2.setArea(91.40);
        apartment2.setFloor(2);
        apartment2.setHasPet(false);
        apartment2.setBuilding(building1);
        ApartmentDAO.saveApartment(apartment2);

        Apartment apartment3=new Apartment();
        apartment3.setArea(125.88);
        apartment3.setFloor(4);
        apartment3.setHasPet(true);
        apartment3.setBuilding(building2);
        ApartmentDAO.saveApartment(apartment3);

        Apartment apartment4=new Apartment();
        apartment4.setArea(103.1);
        apartment4.setFloor(5);
        apartment4.setHasPet(false);
        apartment4.setBuilding(building3);
        ApartmentDAO.saveApartment(apartment4);

        //creating People
        Person person1=new Person();
        person1.setName("Sonya");
        person1.setAge(24);
        person1.setUseLift(false);
        person1.setPersonType(PersonType.OWNER);
        person1.setApartment(apartment1);
        PersonDAO.savePerson(person1);

        Person person2=new Person();
        person2.setName("Nadya");
        person2.setAge(39);
        person2.setUseLift(true);
        person2.setPersonType(PersonType.RESIDENT);
        person2.setApartment(apartment1);
        PersonDAO.savePerson(person2);

        Person person3=new Person();
        person3.setName("Petya");
        person3.setAge(43);
        person3.setUseLift(true);
        person3.setPersonType(PersonType.RESIDENT);
        person3.setApartment(apartment1);
        PersonDAO.savePerson(person3);

        Person person4=new Person();
        person4.setName("Iliya");
        person4.setAge(66);
        person4.setUseLift(true);
        person4.setPersonType(PersonType.OWNER);
        person4.setApartment(apartment2);
        PersonDAO.savePerson(person4);

        Person person5=new Person();
        person5.setName("Preslava");
        person5.setAge(31);
        person5.setUseLift(false);
        person5.setPersonType(PersonType.OWNER);
        person5.setApartment(apartment3);
        PersonDAO.savePerson(person5);

        Person person6=new Person();
        person6.setName("Silviya");
        person6.setAge(71);
        person6.setUseLift(true);
        person6.setPersonType(PersonType.RESIDENT);
        person6.setApartment(apartment4);
        PersonDAO.savePerson(person6);

        //list of people in order to set it to apartment
        List<Person>people1 = new ArrayList<>();
        people1.add(person1);
        people1.add(person2);

        List<Person>people2 = new ArrayList<>();
        people1.add(person3);
        people2.add(person4);

        List<Person>people3=new ArrayList<>();
        people3.add(person5);

        List<Person>people4=new ArrayList<>();
        people4.add(person6);


        //setting List of People to apartment and calculating the fee
        apartment1.setPeopleList(people1);
        apartment1.setFee(apartment1.fee());
        ApartmentDAO.saveOrUpdateApartment(apartment1);

        apartment2.setPeopleList(people2);
        apartment2.setFee(apartment2.fee());
        ApartmentDAO.saveOrUpdateApartment(apartment2);

        apartment3.setPeopleList(people3);
        apartment3.setFee(apartment3.fee());
        ApartmentDAO.saveOrUpdateApartment(apartment3);

        apartment4.setPeopleList(people4);
        apartment4.setFee(apartment4.fee());
        ApartmentDAO.saveOrUpdateApartment(apartment4);


        //list of buildings to set it apartments
        // and then set it to cleaning company
        List<Building>buildings1=new ArrayList<>();
        buildings1.add(building1);
        buildings1.add(building2);

        List<Building>buildings2=new ArrayList<>();
        buildings2.add(building3);

        //list of apartments filled with apartments
        List<Apartment>apartmentList1=new ArrayList<>();
        apartmentList1.add(apartment1);
        apartmentList1.add(apartment2);

        List<Apartment>apartmentList2=new ArrayList<>();
        apartmentList2.add(apartment3);

        List<Apartment>apartmentList3=new ArrayList<>();
        apartmentList3.add(apartment4);

        //filling building with apartments
        building1.setApartmentList(apartmentList1);
        building2.setApartmentList(apartmentList2);
        building3.setApartmentList(apartmentList3);


        //Cleaning companies taking care of different list of Buildings
        cleaningCompany_Domestosi.setBuildingList(buildings1);
        cleaningCompany_Domestosi.setCapital(cleaningCompany_Domestosi.capital()); //getting the profit
        CleaningCompanyDAO.saveOrUpdateCleaningCompany(cleaningCompany_Domestosi);

        cleaningCompany_Arieli.setBuildingList(buildings2);
        cleaningCompany_Arieli.setCapital(cleaningCompany_Arieli.capital());
        CleaningCompanyDAO.saveOrUpdateCleaningCompany(cleaningCompany_Arieli);

        //query
        System.out.println("------------------Companies capital----------------");
        CleaningCompanyDAO.companiesByCapitalAsc().stream().forEach(System.out::println);

        System.out.println("---------------Employee by name-----------------");
        EmployeeDAO.employeesByNameAscAndNumberOfBuildingsAsc().stream().forEach(System.out::println);

        System.out.println("----------------People by Name asc and Age asc-----------------");
        PersonDAO.peopleByNameAscAndAgeAsc().stream().forEach(System.out::println);

        System.out.println("---------------Employee's building-----------------");
        EmployeeDAO.getEmployeeBuilding(1).stream().forEach(System.out::println);
        System.out.println("Number of buildings: " + EmployeeDAO.getEmployeeBuilding(1).size());

        System.out.println("----------------Building's apartment-----------------");
        BuildingDAO.getBuildingApartment(1).stream().forEach(System.out::println);

        System.out.println("Number of apartments: " + BuildingDAO.getBuildingApartment(1).size());

    }
}
