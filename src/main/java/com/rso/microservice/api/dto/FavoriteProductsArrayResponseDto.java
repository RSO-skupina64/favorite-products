package com.rso.microservice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FavoriteProductsArrayResponseDto {

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("products")
    List<ProductDto> products;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
