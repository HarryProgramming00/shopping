package org.harry.farmer.order.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.harry.farmer.api.order.Order;
import org.harry.farmer.api.order.Product;
import org.harry.farmer.order.api.model.CreateOrder;
import org.harry.farmer.order.model.OrderDto;
import org.harry.farmer.order.model.ProductDto;
import org.harry.farmer.order.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.harry.farmer.order.api.ProductController.BASE_URI;

@RestController
@RequestMapping(BASE_URI)
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    public static final String BASE_URI = "/products";

    private final ProductRepository productRepository;

    @GetMapping
    public List<Product> getProducts() {
        log.info("Get products");
        return toProducts(productRepository.getProducts());
    }

    private List<Product> toProducts(List<ProductDto> productDtos){
        return productDtos.stream().map(p->Product.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .unitPrice(p.getUnitPrice())
                        .build())
                .collect(Collectors.toList());
    }

}
