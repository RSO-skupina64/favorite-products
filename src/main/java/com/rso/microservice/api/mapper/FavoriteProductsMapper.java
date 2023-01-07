package com.rso.microservice.api.mapper;

import com.rso.microservice.api.dto.FavoriteProductsArrayResponseDto;
import com.rso.microservice.entity.ProductType;
import com.rso.microservice.vao.FavoriteProductsListVAO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FavoriteProductsMapper {

    @Mapping(source = "products", target = "products")
    @Mapping(source = "count", target = "count")
    FavoriteProductsArrayResponseDto toModel(FavoriteProductsListVAO favoriteProductsListVAO);

    default String toModel(ProductType productType) {
        return productType.getName();
    }

}
