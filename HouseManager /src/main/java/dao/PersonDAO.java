package dao;

import configuration.SessionFactoryUtil;
import entity.Employee;
import entity.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PersonDAO {
    public static void savePerson(Person person) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(person);
            transaction.commit();
        }
    }

    public static void saveOrUpdatePerson(Person person) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(person);
            transaction.commit();
        }
    }

    public static void savePeople(List<Person> peopleList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            peopleList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<Person> readPeople() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Person a", Person.class).getResultList();
        }
    }

    public static Person getPerson(long id){
        Person person;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            person=session.get(Person.class, id);
            transaction.commit();
        }
        return person;
    }

    public static void deletePerson(Person person){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(person);
            transaction.commit();
        }
    }

    //criteria query for people' name ascending and then age ascending
    public static List<Person> peopleByNameAscAndAgeAsc() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Person> cr = cb.createQuery(Person.class);
            Root<Person> root = cr.from(Person.class);
            cr.orderBy(cb.asc(root.get("name")), cb.asc(root.get("age")));

            Query<Person> query = session.createQuery(cr);
            List<Person> people = query.getResultList();
            return people;
        }
    }
}