package com.IPRWC_webshop.webshop_IPRWC.seeder;

import com.IPRWC_webshop.webshop_IPRWC.model.Category;
import com.IPRWC_webshop.webshop_IPRWC.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
@Order(1)
public class CategorySeeder implements CommandLineRunner {

    private final CategoryService categoryService;

    @Override
    public void run(String... args) {
        seedCategories();
    }

    private void seedCategories() {
        List<Category> categories = Arrays.asList(
                new Category("BMW", "German car manufacturer"),
                new Category("Mini", "British car manufacturer")
                // Add more categories as needed...
        );

        for(Category category : categories) {
            categoryService.createCategory(category);
        }
    }
}

