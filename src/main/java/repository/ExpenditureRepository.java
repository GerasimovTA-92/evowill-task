package repository;

import model.Category;
import model.Expenditure;
import model.User;
import java.time.LocalDate;
import java.util.List;

public interface ExpenditureRepository {
    List<Expenditure> findAllByDateAfterAndUser(LocalDate date, User user);

    List<Expenditure> findAllByCategoryCategoryNameAndUser(Category.CategoryName categoryName, User user);

    List<Expenditure> findAllByDateAndUser(LocalDate date, User user);

    Expenditure save(Expenditure expenditure);

    List<Expenditure> findAllByUser(User user);

    void deleteAll(User user);
}
