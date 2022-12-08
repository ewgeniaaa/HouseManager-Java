package dao;

import configuration.SessionFactoryUtil;
import entity.Apartment;
import entity.Building;
import entity.Employee;
import entity.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ApartmentDAO {
    public static void saveApartment(Apartment apartment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(apartment);
            transaction.commit();
        }
    }

    public static void saveOrUpdateApartment(Apartment apartment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(apartment);
            transaction.commit();
        }
    }

    public static void saveApartments(List<Apartment> apartmentList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            apartmentList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<Apartment> readApartments() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Apartment a", Apartment.class).getResultList();
        }
    }

    public static Apartment getApartment(long id){
        Apartment apartment;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            apartment = session.get(Apartment.class, id);
            transaction.commit();
        }
        return apartment;
    }

    public static void deleteApartment(Apartment apartment){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(apartment);
            transaction.commit();
        }
    }

    //criteria query for people in apartment
    public static List<Person> getApartmentPeople(long id){
        Apartment apartment;
        try(Session session=configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction=session.beginTransaction();
            apartment = session.createQuery(
                    "select c from Apartment c" +
                            " join fetch c.peopleList" +
                            " where c.id = :id",
                    Apartment.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return apartment.getPeopleList();
    }
}