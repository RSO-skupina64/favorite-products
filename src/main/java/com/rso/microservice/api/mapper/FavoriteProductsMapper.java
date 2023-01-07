package com.rso.microservice.api.mapper;

import com.rso.microservice.api.dto.FavoriteProductsArrayResponseDto;
import com.rso.microservice.api.dto.ProductDto;
import com.rso.microservice.entity.Product;
import com.rso.microservice.entity.ProductType;
import com.rso.microservice.vao.FavoriteProductsListVAO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavoriteProductsMapper {

    @Mapping(source = "products", target = "products")
    @Mapping(source = "count", target = "count")
    FavoriteProductsArrayResponseDto toModel(FavoriteProductsListVAO favoriteProductsListVAO);

    List<ProductDto> toModel(List<Product> products);

    ProductDto toModel(Product product);

    @Mapping(source = "name", target = ".")
    String toModel(ProductType productType);
}
