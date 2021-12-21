package org.example;

import com.github.javafaker.Faker;
import lesson5.dto.Category;
import lesson5.enums.CategoryType;
import lesson5.services.CategoryService;
import lesson5.dto.Product;
import lesson5.services.ProductService;
import lesson5.utils.PrettyLogger;
import lesson5.utils.RetrofitUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class ProductTests {
    static Retrofit client;
    static ProductService productService;
    static CategoryService categoryService;
    Faker faker =new Faker();
    Product product;

    @BeforeAll
    static void beforeAll(){
        client = RetrofitUtils.getRetrofit();
        productService = client.create(ProductService.class);
        categoryService = client.create(CategoryService.class);

    }

    @BeforeEach
    void setup(){
        product = new Product()
                .withTitle(faker.food().fruit())
                .withPrice((int) (Math.random()+1)*1000)
                .withCategoryTitle(CategoryType.FOOD.getTitle());

    }

    @Test
    void postProductTest() throws IOException{
        Response<Product> response = productService.createProduct(product).execute();
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
    }

    @Test
    void deleteProductTest() throws IOException{
        Response<Product> response = productService.deleteProduct(product.id).execute();
        assertThat(response.code(), equalTo(200));

    }

    @Test
    void getCategoryByIdTest() throws IOException {
        Integer id = CategoryType.ELECTRONIC.getId();
        Response<Category> response = categoryService
                .getCategory(id)
                .execute();

        PrettyLogger.DEFAULT.log(response.body().toString());
        assertThat(response.body().getTitle(),equalTo(CategoryType.FOOD.getTitle()));
        assertThat(response.body().getId(), equalTo(CategoryType.FOOD.getId()));
    }

}
