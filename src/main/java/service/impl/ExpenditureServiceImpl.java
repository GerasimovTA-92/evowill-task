package service.impl;

import model.Category;
import model.Expenditure;
import model.User;
import repository.ExpenditureRepository;
import service.ExpenditureService;
import java.time.LocalDate;
import java.util.List;

public class ExpenditureServiceImpl implements ExpenditureService {
    private final ExpenditureRepository expenditureRepository;

    public ExpenditureServiceImpl(ExpenditureRepository expenditureRepository) {
        this.expenditureRepository = expenditureRepository;
    }

    @Override
    public Expenditure save(Expenditure expenditure) {
        return expenditureRepository.save(expenditure);
    }

    @Override
    public List<Expenditure> findAllByUser(User user) {
        return expenditureRepository.findAllByUser(user);
    }

    @Override
    public List<Expenditure> findByCategory(Category.CategoryName categoryName, User user) {
        return expenditureRepository.findAllByCategoryCategoryNameAndUser(categoryName, user);
    }

    @Override
    public List<Expenditure> findByDate(LocalDate localDate, User user) {
        return expenditureRepository.findAllByDateAndUser(localDate, user);
    }

    @Override
    public List<Expenditure> findByDateAfter(LocalDate localDate, User user) {
        return expenditureRepository.findAllByDateAfterAndUser(localDate, user);
    }

    @Override
    public void clearData(User user) {
        expenditureRepository.deleteAll(user);
    }
}
