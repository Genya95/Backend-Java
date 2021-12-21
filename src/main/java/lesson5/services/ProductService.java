package lesson5.services;

import lesson5.dto.Product;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.ArrayList;

public interface ProductService {
    @POST("products")
    Call<Product> createProduct(@Body Product product);

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") Integer id);

    @DELETE("products/{id}")
    Call<Product> deleteProduct(@Path("id") Integer id);


}
