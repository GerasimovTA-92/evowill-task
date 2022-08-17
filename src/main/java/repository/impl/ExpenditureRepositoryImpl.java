package repository.impl;

import model.Category;
import model.Expenditure;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.AbstractRepository;
import repository.ExpenditureRepository;
import java.time.LocalDate;
import java.util.List;

public class ExpenditureRepositoryImpl extends AbstractRepository<Expenditure>
        implements ExpenditureRepository {
    public ExpenditureRepositoryImpl(SessionFactory factory, Class<Expenditure> clazz) {
        super(factory, clazz);
    }

    @Override
    public List<Expenditure> findAllByDateAfterAndUser(LocalDate date, User user) {
        try (Session session = factory.openSession()) {
            Query<Expenditure> query = session.createQuery("from Expenditure e "
                            + "where e.user = :user and e.date > :date",
                    Expenditure.class);
            query.setParameter("user", user);
            query.setParameter("date", date);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Not found list of expenditures", e);
        }
    }

    @Override
    public List<Expenditure> findAllByCategoryCategoryNameAndUser(Category.CategoryName categoryName, User user) {
        try (Session session = factory.openSession()) {
            Query<Expenditure> query = session.createQuery("from Expenditure e "
                    + "where e.user = :user and e.category.categoryName = :categoryName",
                    Expenditure.class);
            query.setParameter("user", user);
            query.setParameter("categoryName", categoryName);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Not found list of expenditures", e);
        }
    }

    @Override
    public List<Expenditure> findAllByDateAndUser(LocalDate date, User user) {
        try (Session session = factory.openSession()) {
            Query<Expenditure> query = session.createQuery("from Expenditure e "
                            + "where e.user = :user and e.date = :date",
                    Expenditure.class);
            query.setParameter("user", user);
            query.setParameter("date", date);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Not found list of expenditures", e);
        }
    }

    @Override
    public List<Expenditure> findAllByUser(User user) {
        try (Session session = factory.openSession()) {
            Query<Expenditure> query = session.createQuery("from Expenditure e "
                            + "where e.user = :user",
                    Expenditure.class);
            query.setParameter("user", user);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Not found list of expenditures", e);
        }
    }

    @Override
    public void deleteAll(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete Expenditure e where e.user = :user");
            query.setParameter("user", user);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't delete expenditures by user", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
