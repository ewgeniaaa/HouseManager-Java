package dao;

import configuration.SessionFactoryUtil;
import entity.Apartment;
import entity.Building;
import entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EmployeeDAO {
    public static void saveEmployee(Employee employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    public static void saveOrUpdateEmployee(Employee employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
        }
    }

    public static void saveEmployees(List<Employee> employeeList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employeeList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<Employee> readEmployees() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Employee a", Employee.class).getResultList();
        }
    }

    public static Employee getEmployee(long id) {
        Employee employee;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employee = session.get(Employee.class, id);
            transaction.commit();
        }
        return employee;
    }

    public static void deleteEmployee(Employee employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }

    //criteria query for which building by which employee
    public static List<Building> getEmployeeBuilding(long id){
        Employee employee;
        try(Session session=configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction=session.beginTransaction();
            employee=session.createQuery(
                    "select c from Employee c" +
                            " join fetch c.buildingList" +
                            " where c.id = :id",
                    Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return employee.getBuildingList();
    }

    //criteria query for employees'name
    public static List<Employee> employeesByNameAscAndNumberOfBuildingsAsc() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
            Root<Employee> root = cr.from(Employee.class);
            cr.orderBy(cb.asc(root.get("name")));

            Query<Employee> query = session.createQuery(cr);
            List<Employee> employees = query.getResultList();
            return employees;
        }
    }
}