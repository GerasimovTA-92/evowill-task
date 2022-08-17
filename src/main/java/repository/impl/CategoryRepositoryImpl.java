package repository.impl;

import model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import repository.AbstractRepository;
import repository.CategoryRepository;

public class CategoryRepositoryImpl extends AbstractRepository<Category>
        implements CategoryRepository {
    public CategoryRepositoryImpl(SessionFactory factory, Class<Category> clazz) {
        super(factory, clazz);
    }

    @Override
    public Category findByCategoryName(Category.CategoryName categoryName) {
        try (Session session = factory.openSession()) {
            Query<Category> query = session.createQuery("from Category c where c.categoryName = :categoryName", Category.class);
            query.setParameter("categoryName", categoryName);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Can't get category by " + categoryName, e);
        }
    }
}
