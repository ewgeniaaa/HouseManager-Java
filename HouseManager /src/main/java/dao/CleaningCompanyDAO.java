package dao;

import configuration.SessionFactoryUtil;
import entity.CleaningCompany;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CleaningCompanyDAO {
    public static void saveCleaningCompany(CleaningCompany cleaningCompany) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(cleaningCompany);
            transaction.commit();
        }
    }

    public static void saveOrUpdateCleaningCompany(CleaningCompany cleaningCompany) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(cleaningCompany);
            transaction.commit();
        }
    }

    public static void saveCleaningCompanies(List<CleaningCompany> cleaningCompaniesList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            cleaningCompaniesList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<CleaningCompany> readCleaningCompanies() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM CleaningCompany a", CleaningCompany.class).getResultList();
        }
    }

    public static CleaningCompany getCleaningCompany(long id){
        CleaningCompany cleaningCompany;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            cleaningCompany=session.get(CleaningCompany.class, id);
            transaction.commit();
        }
        return cleaningCompany;
    }

    public static void deleteCleaningCompany(CleaningCompany cleaningCompany){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(cleaningCompany);
            transaction.commit();
        }
    }

    //criteria query companies' capital ascending
    public static List<CleaningCompany> companiesByCapitalAsc() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CleaningCompany> cr = cb.createQuery(CleaningCompany.class);
            Root<CleaningCompany> root = cr.from(CleaningCompany.class);
            cr.orderBy(cb.asc(root.get("capital")));

            Query<CleaningCompany> query = session.createQuery(cr);
            List<CleaningCompany> companies = query.getResultList();
            return companies;
        }
    }

}