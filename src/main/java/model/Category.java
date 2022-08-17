package model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private CategoryName categoryName;

    public Category() {
    }

    public Category(CategoryName categoryName) {
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category category = (Category) o;
        return Objects.equals(id, category.id) && categoryName == category.categoryName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryName);
    }

    public enum CategoryName {
        FOOD,
        SPORT,
        HEALTH,
        CLOTHES,
        EDUCATION,
        ENTERTAINMENT
    }
}
