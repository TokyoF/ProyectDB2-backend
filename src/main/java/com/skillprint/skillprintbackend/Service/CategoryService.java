package com.skillprint.skillprintbackend.Service;

import com.skillprint.skillprintbackend.Entity.Category;
import com.skillprint.skillprintbackend.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }


    public Category createCategory(Category category){
        Category newCategory = Category.builder()
                .categoryName(category.getCategoryName())
                .baseSalary(category.getBaseSalary())
                .annualIncrease(category.getAnnualIncrease())
                .build();
        return categoryRepository.save(newCategory);
    }

    public Category updateCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
}
