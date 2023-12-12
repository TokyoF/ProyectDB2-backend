package com.skillprint.skillprintbackend.Controller;

import com.skillprint.skillprintbackend.Entity.Category;
import com.skillprint.skillprintbackend.Service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")  // /api/category/all  /api/category/1  /api/category/2  /api/category/3  /api/category/4  /api/category/5  /api/category/6  /api/category/7
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController (CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
}
