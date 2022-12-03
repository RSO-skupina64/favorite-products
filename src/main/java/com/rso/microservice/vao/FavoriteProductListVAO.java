package com.rso.microservice.vao;


import com.rso.microservice.entity.Product;

import java.util.List;

public class FavoriteProductListVAO {

    private final Integer count;
    private final List<Product> products;

    public FavoriteProductListVAO(int count, List<Product> products) {
        this.count = count;
        this.products = products;
    }

    public Integer getCount() {
        return count;
    }

    public List<Product> getProducts() {
        return products;
    }
}
