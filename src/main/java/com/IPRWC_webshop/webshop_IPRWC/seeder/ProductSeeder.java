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
                new Product("BMW 3 Series", 45000.00, 10, "Luxury sedan", "https://www.autocar.co.uk/sites/autocar.co.uk/files/images/car-reviews/first-drives/legacy/_p_9416_0.jpg", bmwCarCategory),
                new Product("BMW 5 Series", 55000.00, 15, "Executive sedan", "https://i.ytimg.com/vi/3Aa6Y3AJJpg/maxresdefault.jpg", bmwCarCategory),
                new Product("BMW 7 Series", 80000.00, 8, "Flagship sedan", "https://hips.hearstapps.com/hmg-prod/amv-prod-cad-assets/wp-content/uploads/2017/08/E32-1.jpg", bmwCarCategory),
                new Product("BMW X3", 43000.00, 12, "Compact SUV", "https://www.arlingtoncardinal.com/wordpress/wp-content/uploads/2023/03/BMW-X3crashwithtreeMar12-2023.jpg", bmwCarCategory),
                new Product("BMW X5", 59000.00, 7, "Midsize SUV", "https://www.autocar.co.uk/sites/autocar.co.uk/files/images/car-reviews/first-drives/legacy/_p_9416_0.jpg", bmwCarCategory),
                new Product("BMW X7", 74000.00, 5, "Luxury SUV", "https://www.autocar.co.uk/sites/autocar.co.uk/files/images/car-reviews/first-drives/legacy/_p_9416_0.jpg", bmwCarCategory),
                new Product("BMW Z4", 50000.00, 20, "Sports car", "https://www.autocar.co.uk/sites/autocar.co.uk/files/images/car-reviews/first-drives/legacy/_p_9416_0.jpg", bmwCarCategory),
                new Product("BMW M3", 65000.00, 3, "Sports sedan", "https://www.autocar.co.uk/sites/autocar.co.uk/files/images/car-reviews/first-drives/legacy/_p_9416_0.jpg", bmwCarCategory),
                new Product("BMW M4", 70000.00, 2, "Sports coupe", "https://www.autocar.co.uk/sites/autocar.co.uk/files/images/car-reviews/first-drives/legacy/_p_9416_0.jpg", bmwCarCategory),
                new Product("Mini Cooper", 30000.00, 25, "Compact car", "https://images.ctfassets.net/uaddx06iwzdz/PXmQc2xGilyi4z8ylrSpa/017180b0dbf3c367fb2ef97aa19da9a3/mini-cooper-front.jpeg", miniCarCategory),
                new Product("Mini Countryman", 35000.00, 30, "Compact SUV", "https://ev-database.org/img/auto/Mini_Countryman_2024/Mini_Countryman_2024-01@2x.jpg", miniCarCategory)
        );

//        productService.createProducts(bmwCars);
        for (Product product : bmwCars) {
            productService.createProduct(product);
        }
    }
}
