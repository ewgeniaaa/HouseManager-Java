package dao;

import configuration.SessionFactoryUtil;
import entity.Apartment;
import entity.Building;
import entity.Employee;
import entity.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class BuildingDAO {
    public static void saveBuilding(Building building) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(building);
            transaction.commit();
        }
    }

    public static void saveOrUpdateBuilding(Building building) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(building);
            transaction.commit();
        }
    }

    public static void saveBuildings(List<Building> buildingList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            buildingList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<Building> readBuildings() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Building a", Building.class).getResultList();
        }
    }

    public static Building getBuilding(long id){
        Building building;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            building=session.get(Building.class, id);
            transaction.commit();
        }
        return building;
    }

    public static void deleteBuilding(Building building){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(building);
            transaction.commit();
        }
    }

    //criteria query for apartments in a building
    public static List<Apartment> getBuildingApartment(long id){
        Building building;
        try(Session session=configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction=session.beginTransaction();
            building=session.createQuery(
                    "select c from Building c" +
                            " join fetch c.apartmentList" +
                            " where c.id = :id",
                    Building.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return building.getApartmentList();
    }
































    //criteria query for people in a building
    /*public static List<Person> getBuildingPeople(long id){
        Building building;
        try(Session session=configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction=session.beginTransaction();
            building=session.createQuery(
                    "select c from Building c" +
                            " join fetch c.people" +
                            " where c.id = :id",
                    Building.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return building.getApartmentList();
    }*/
}