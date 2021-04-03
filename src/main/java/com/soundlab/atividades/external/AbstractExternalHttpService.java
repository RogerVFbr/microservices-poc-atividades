package com.soundlab.atividades.external;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.soundlab.atividades.exceptions.ExternalServiceException;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public abstract class AbstractExternalHttpService<T> {
    protected T api;

    protected AbstractExternalHttpService(String baseUrl) {
        this.api = getRetrofitClient(baseUrl).create(getApiType());
    }

    protected <K> K call(Call<K> call) {
        try {
            return call.execute().body();
        } catch (IOException e) {
            throw new ExternalServiceException(getApiType().getSimpleName(), e.getMessage());
        }
    }

    private static Retrofit getRetrofitClient(String baseUrl) {
        return new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getOkHttpClient())
            .addConverterFactory(getJacksonConverterFactory())
            .build();
    }

    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build();
    }

    private static JacksonConverterFactory getJacksonConverterFactory() {
        return JacksonConverterFactory.create(
            new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        );
    }

    @SuppressWarnings("unchecked")
    private Class<T> getApiType() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
            .getActualTypeArguments()[0];
    }
}
