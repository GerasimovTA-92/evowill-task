package repository.impl;

import model.Category;
import model.Expenditure;
import model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.CategoryRepository;
import repository.ExpenditureRepository;
import repository.UserRepository;
import util.SessionFactoryUtil;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExpenditureRepositoryImplTest {
    private static ExpenditureRepository expenditureRepository;
    private static UserRepository userRepository;
    private static CategoryRepository categoryRepository;
    private static User user;

    @BeforeAll
    public static void setUp() {
        expenditureRepository = new ExpenditureRepositoryImpl(SessionFactoryUtil.getSessionFactory(), Expenditure.class);
        userRepository = new UserRepositoryImpl(SessionFactoryUtil.getSessionFactory(), User.class);
        categoryRepository = new CategoryRepositoryImpl(SessionFactoryUtil.getSessionFactory(), Category.class);
        user = new User();
        userRepository.save(user);
    }

    @Test
    public void findAllByDateAfterAndUser_correctData_Ok() {
        Expenditure expenditure = new Expenditure();
        expenditure.setDate(LocalDate.now());
        expenditure.setUser(user);
        expenditure.setCategory(categoryRepository.findByCategoryName(Category.CategoryName.FOOD));
        expenditureRepository.save(expenditure);
        assertEquals(expenditure,
                expenditureRepository.findAllByDateAfterAndUser(LocalDate.now().minusMonths(1L), user).get(0));
    }

    @Test
    public void findAllByCategoryCategoryNameAndUser_Ok() {
        Expenditure expenditure = new Expenditure();
        expenditure.setUser(user);
        expenditure.setDate(LocalDate.now());
        expenditure.setCategory(categoryRepository.findByCategoryName(Category.CategoryName.FOOD));
        expenditureRepository.save(expenditure);
        assertEquals(expenditure,
                expenditureRepository.findAllByCategoryCategoryNameAndUser(Category.CategoryName.FOOD, user).get(0));
    }

    @Test
    public void findAllByDateAndUser_Ok() {
        Expenditure expenditure = new Expenditure();
        expenditure.setUser(user);
        expenditure.setDate(LocalDate.now());
        expenditure.setCategory(categoryRepository.findByCategoryName(Category.CategoryName.FOOD));
        expenditureRepository.save(expenditure);
        assertEquals(expenditure,
                expenditureRepository.findAllByDateAndUser(LocalDate.now(), user).get(0));
    }

    @Test
    public void findAllByUser_Ok() {
        Expenditure expenditure = new Expenditure();
        expenditure.setUser(user);
        expenditure.setDate(LocalDate.now());
        expenditure.setCategory(categoryRepository.findByCategoryName(Category.CategoryName.FOOD));
        expenditureRepository.save(expenditure);
        assertEquals(expenditure,
                expenditureRepository.findAllByUser(user).get(0));
    }

    @BeforeEach
    public void cleanExpenditures() {
        expenditureRepository.deleteAll(user);
    }
}