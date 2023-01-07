package com.rso.microservice.api;

import com.rso.microservice.api.dto.ErrorDto;
import com.rso.microservice.api.dto.FavoriteProductRequestDto;
import com.rso.microservice.api.dto.FavoriteProductsArrayResponseDto;
import com.rso.microservice.api.dto.MessageDto;
import com.rso.microservice.api.mapper.FavoriteProductsMapper;
import com.rso.microservice.entity.UserFavoriteProduct;
import com.rso.microservice.service.FavoriteProductsService;
import com.rso.microservice.vao.FavoriteProductsListVAO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@OpenAPIDefinition(info = @Info(title = "Favorite Products API",
        description = "This is API documentation for Favorite Products Microservice",
        version = "0.1"))
@RequestMapping("/favorite")
@Tag(name = "Favorite Products")
public class FavoriteProductsAPI {
    private static final Logger log = LoggerFactory.getLogger(FavoriteProductsAPI.class);

    final FavoriteProductsService favoriteProductsService;

    final FavoriteProductsMapper favoriteProductsMapper;

    @Autowired

    public FavoriteProductsAPI(FavoriteProductsService favoriteProductsService,
                               FavoriteProductsMapper favoriteProductsMapper) {
        this.favoriteProductsService = favoriteProductsService;
        this.favoriteProductsMapper = favoriteProductsMapper;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add product to favorites",
            description = "Add a product to User's favorites")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of products",
                    content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<MessageDto> addFavoriteProduct(
            @Valid @RequestBody FavoriteProductRequestDto favoriteProductRequest,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt) {
        log.info("addFavoriteProduct: ENTRY");
        // todo RokM userId from jwt token
        Long userId = 1L;
        UserFavoriteProduct userFavoriteProduct = favoriteProductsService.createUserFavoriteProduct(userId,
                favoriteProductRequest.getId());
        log.info("addFavoriteProduct: EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto("addFavoriteProduct completed"));
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Remove product from favorites",
            description = "Remove product from favorites")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of products",
                    content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<MessageDto> removeFavoriteProduct(
            @Valid @RequestBody FavoriteProductRequestDto favoriteProductRequest,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt) {
        log.info("removeFavoriteProduct: ENTRY");
        // todo RokM userId from jwt token
        Long userId = 1L;
        favoriteProductsService.removeUserFavoriteProduct(userId, favoriteProductRequest.getId());
        log.info("removeFavoriteProduct: EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto("removeFavoriteProduct completed"));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get favorite products",
            description = "Get favorite products")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of products",
                    content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<FavoriteProductsArrayResponseDto> getFavoriteProducts(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt) {
        log.info("fetchProductPricesSpecificShop: ENTRY");
        // todo RokM userId from jwt token
        Long userId = 1L;
        FavoriteProductsListVAO favoriteProductsList = favoriteProductsService.getFavoriteProductsByUserId(userId);
        log.info("fetchProductPricesSpecificShop: EXIT");
        return ResponseEntity.status(HttpStatus.OK)
                .body(favoriteProductsMapper.toModel(favoriteProductsList));
    }

}
