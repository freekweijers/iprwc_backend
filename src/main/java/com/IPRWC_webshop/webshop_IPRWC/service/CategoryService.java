package com.IPRWC_webshop.webshop_IPRWC.service;

import com.IPRWC_webshop.webshop_IPRWC.exception.CategoryNotFoundException;
import com.IPRWC_webshop.webshop_IPRWC.model.Category;
import com.IPRWC_webshop.webshop_IPRWC.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public Optional<Category> getCategoryByName(String categoryName) {
        return categoryRepository.findByName(categoryName);
    }

    public Category createCategory(Category category) {
        // Additional logic before saving (if needed)
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long categoryId, Category updatedCategory) throws CategoryNotFoundException {
        // Check if the category with the given ID exists
        if (categoryRepository.existsById(categoryId)) {
            updatedCategory.setId(categoryId); // Ensure the ID is set for update
            return categoryRepository.save(updatedCategory);
        } else {
            throw new CategoryNotFoundException("Category not found with ID: " + categoryId);
        }
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
