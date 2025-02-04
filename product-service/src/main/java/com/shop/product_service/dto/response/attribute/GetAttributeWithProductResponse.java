package com.shop.product_service.dto.response.attribute;

import java.math.BigDecimal;
import java.util.Map;

public class GetAttributeWithProductResponse {
    private String productName;
    private BigDecimal price;
    private Map<String, String> attributes;

    public GetAttributeWithProductResponse() {
    }

    public GetAttributeWithProductResponse( String productName, BigDecimal price, Map<String, String> attributes) {
        this.productName = productName;
        this.price = price;
        this.attributes = attributes;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
