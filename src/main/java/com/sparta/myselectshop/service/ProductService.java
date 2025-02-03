package com.sparta.myselectshop.service;

import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.entity.Product;
import com.sparta.myselectshop.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public static final int MIN_MY_PRICE = 100;

    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = productRepository.save(
            new Product(requestDto)
        );

        log.info("상품 저장 완료");
        return new ProductResponseDto(product);
    }

    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductMypriceRequestDto requestDto) {
        int myPrice = requestDto.getMyprice();

        if (myPrice < MIN_MY_PRICE) {
            throw new IllegalArgumentException("유효하지 않은 관심 가격입니다. 최소 " + MIN_MY_PRICE + "원 이상으로 설정해주세요");
        }

        Product product = productRepository.findById(id)
            .orElseThrow(
                () -> new NullPointerException("해당 상품을 찾을 수 없습니다.")
        );

        product.update(requestDto);
        log.info("상품 정보 수정 완료");
        return new ProductResponseDto(product);
    }

    public List<ProductResponseDto> getProducts() {
        return productRepository.findAll().stream().map(
            ProductResponseDto::new
        ).toList();
    }
}