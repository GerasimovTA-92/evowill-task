import exception.AuthenticationException;
import model.Category;
import model.Expenditure;
import model.User;
import repository.CategoryRepository;
import repository.ExpenditureRepository;
import repository.UserRepository;
import repository.impl.CategoryRepositoryImpl;
import repository.impl.ExpenditureRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.AuthenticationService;
import service.CategoryService;
import service.ExpenditureService;
import service.UserService;
import service.impl.AuthenticationServiceImpl;
import service.impl.CategoryServiceImpl;
import service.impl.ExpenditureServiceImpl;
import service.impl.UserServiceImpl;
import util.SessionFactoryUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Application {
    private static Scanner scanner = new Scanner(System.in);
    private static AuthenticationService authenticationService;
    private static CategoryService categoryService;
    private static ExpenditureService expenditureService;
    private static UserService userService;
    private static CategoryRepository categoryRepository;
    private static ExpenditureRepository expenditureRepository;
    private static UserRepository userRepository;
    private static User user;

    public static void main(String[] args) {
        userRepository = new UserRepositoryImpl(SessionFactoryUtil.getSessionFactory(), User.class);
        categoryRepository = new CategoryRepositoryImpl(SessionFactoryUtil.getSessionFactory(), Category.class);
        expenditureRepository = new ExpenditureRepositoryImpl(SessionFactoryUtil.getSessionFactory(), Expenditure.class);
        userService = new UserServiceImpl(userRepository);
        authenticationService = new AuthenticationServiceImpl(userService);
        categoryService = new CategoryServiceImpl(categoryRepository);
        expenditureService = new ExpenditureServiceImpl(expenditureRepository);
        run();
    }

    private static void authenticate() {
        System.out.println("Enter login: ");
        String login = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        try {
            user = authenticationService.login(login, password);
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            authenticate();
        }
    }

    private static void register() {
        System.out.println("Enter login: ");
        String login = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        user = authenticationService.register(login, password);
    }

    private static void checkStatistics() {
        String in = null;
        while (!"4".equals(in)) {
            System.out.println("Hello " + user.getLogin());
            System.out.println("Create expenditure - 1");
            System.out.println("Check statistics - 2");
            System.out.println("Clear all data - 3");
            System.out.println("Exit - 4");
            System.out.println("Back - 5");
            in = scanner.nextLine();
            switch (in) {
                case "1":
                    createExpenditure();
                    break;
                case "2":
                    getStatistics();
                    break;
                case "3":
                    expenditureService.clearData(user);
                    break;
                case "4":
                    System.exit(0);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid number!");
                    checkStatistics();
            }
        }
    }

    private static void getStatistics() {
        String in;
        do {
            System.out.println("Print by:\nCategory - 1\nDate - 2\nBack - 3");
            in = scanner.nextLine();
            switch(in) {
                case "1":
                    printStatistics(expenditureService.findByCategory(getCategory(), user));
                    return;
                case "2":
                    getStaticsByDay();
                    return;
            }
        } while(in.equals("3"));
    }

    private static LocalDate getDate() {
        System.out.println("Choose:\nNow - 1\nEnter - 2");
        if (scanner.nextLine().equals("1")) {
            return LocalDate.now();
        } else {
            System.out.println("Enter date like year - month - day: ");
            return LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }

    private static void printStatistics(List<Expenditure> data) {
        data.forEach(System.out::println);
    }

    private static void getStaticsByDay() {
        System.out.println("Per: \nDay - 1\nMonth - 2\nYear - 3");
        switch (scanner.nextLine()) {
            case "1" -> {
                printStatistics(expenditureService.findByDateAfter(LocalDate.now(), user));
            }
            case "2" -> {
                printStatistics(expenditureService.findByDateAfter(LocalDate.now().minusMonths(1L), user));
            }
            case "3" -> {
                printStatistics(expenditureService.findByDateAfter(LocalDate.now().minusYears(1L), user));
            }
        }
    }

    private static Category.CategoryName getCategory() {
        while (true) {
            System.out.println("Choose category:\nFood - 1\nSport - 2\nHealth - 3\nClothes - 4\nEducation - 5\nEntertainment - 6");
            switch (scanner.nextLine()) {
                case "1":
                    return Category.CategoryName.FOOD;
                case "2":
                    return Category.CategoryName.SPORT;
                case "3":
                    return Category.CategoryName.HEALTH;
                case "4":
                    return Category.CategoryName.CLOTHES;
                case "5":
                    return Category.CategoryName.EDUCATION;
                case "6":
                    return Category.CategoryName.ENTERTAINMENT;
                default:
                    System.out.println("Invalid number");
            }
        }
    }
    private static void createExpenditure() {
        Expenditure expenditure = new Expenditure();
        expenditure.setUser(user);
        String in;
        do {
            expenditure.setCategory(categoryService.findByCategoryName(getCategory()));
            expenditure.setDate(getDate());
            System.out.println("Enter count: ");
            expenditure.setCount(new BigDecimal(scanner.nextLine()));
            expenditureService.save(expenditure);
            System.out.println("Expenditure added! Back to main menu!");
            return;
        } while (!in.equals("7"));
    }

    public static void run() {
        String in;
        do {
            System.out.println("Hello!");
            System.out.println("Login - 1");
            System.out.println("Register - 2");
            System.out.println("Exit - 3");
            in = scanner.nextLine();
            switch (in) {
                case "1":
                    authenticate();
                    checkStatistics();
                    break;
                case "2":
                    register();
                    checkStatistics();
                    break;
            }
        } while (!in.equals("3"));
    }
}
