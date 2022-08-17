package service;

import model.Category;
import model.Expenditure;
import model.User;
import java.time.LocalDate;
import java.util.List;

public interface ExpenditureService {
    Expenditure save(Expenditure expenditure);

    List<Expenditure> findAllByUser(User user);

    List<Expenditure> findByCategory(Category.CategoryName categoryName, User user);

    List<Expenditure> findByDate(LocalDate localDate, User user);

    List<Expenditure> findByDateAfter(LocalDate localDate, User user);

    void clearData(User user);
}
