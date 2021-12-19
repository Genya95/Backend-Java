package lesson5.dto;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;

public class GetCategoryTest {
    static CategoryService categoryService;
    @BeforeAll
    static void beforeAll() {
        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);
    }

    @SneakyThrows
    @Test
    void getCategoryByIdPositiveTest() {
        Response<ResponseBody> response = categoryService.getCategory(3).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
    @SneakyThrows
    @Test
    void getCategoryByIdNegativeTest() {
        Response<ResponseBody> response = categoryService.getCategory(0).execute();
        assertThat(response.errorBody(),CoreMatchers.is(true));
    }
}
