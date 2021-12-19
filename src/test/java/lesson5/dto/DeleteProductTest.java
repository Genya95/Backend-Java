package lesson5.dto;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteProductTest {
    static ProductService productService;
    Product product;
    Faker faker = new Faker();
    int id;
    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withPrice((int) (Math.random() * 10000))
                .withId(3);
    }

    @Test
    @SneakyThrows
    void deleteProductTest() {
        Response<ResponseBody> response = productService.deleteProduct(3)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
