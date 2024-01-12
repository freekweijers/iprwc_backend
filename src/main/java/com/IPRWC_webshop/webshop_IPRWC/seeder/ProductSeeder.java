package com.IPRWC_webshop.webshop_IPRWC.seeder;

import com.IPRWC_webshop.webshop_IPRWC.model.Category;
import com.IPRWC_webshop.webshop_IPRWC.model.Product;
import com.IPRWC_webshop.webshop_IPRWC.service.CategoryService;
import com.IPRWC_webshop.webshop_IPRWC.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
@Order(2)
public class ProductSeeder implements CommandLineRunner {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Override
    public void run(String... args) {
        seedBMWCarProducts();
    }

    private void seedBMWCarProducts() {
//        Category carCategory = new Category("BMW", "German car manufacturer");
        Category bmwCarCategory = categoryService.getCategoryById(1L).orElse(null);
        Category miniCarCategory = categoryService.getCategoryById(2L).orElse(null);


        List<Product> bmwCars = Arrays.asList(
                new Product("BMW 3 Series", 45000.00, 10, "Luxury sedan", "image-url-1", bmwCarCategory),
                new Product("BMW 5 Series", 55000.00, 15, "Executive sedan", "image-url-2", bmwCarCategory),
                new Product("BMW 7 Series", 80000.00, 8, "Flagship sedan", "image-url-3", bmwCarCategory),
                new Product("BMW X3", 43000.00, 12, "Compact SUV", "image-url-4", bmwCarCategory),
                new Product("BMW X5", 59000.00, 7, "Midsize SUV", "image-url-5", bmwCarCategory),
                new Product("BMW X7", 74000.00, 5, "Luxury SUV", "image-url-6", bmwCarCategory),
                new Product("BMW Z4", 50000.00, 20, "Sports car", "image-url-7", bmwCarCategory),
                new Product("BMW M3", 65000.00, 3, "Sports sedan", "image-url-8", bmwCarCategory),
                new Product("BMW M4", 70000.00, 2, "Sports coupe", "image-url-9", bmwCarCategory),
                new Product("Mini Cooper", 30000.00, 25, "Compact car", "image-url-10", miniCarCategory),
                new Product("Mini Countryman", 35000.00, 30, "Compact SUV", "image-url-11", miniCarCategory)
        );

//        productService.createProducts(bmwCars);
        for (Product product : bmwCars) {
            productService.createProduct(product);
        }
    }
}
