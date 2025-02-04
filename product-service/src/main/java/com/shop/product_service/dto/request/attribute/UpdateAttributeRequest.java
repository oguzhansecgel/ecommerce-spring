package com.shop.product_service.dto.request.attribute;

public class UpdateAttributeRequest {
    private String key;
    private String value;
    private Long productId;

    public UpdateAttributeRequest() {}

    public UpdateAttributeRequest(String key, String value, Long productId) {
        this.key = key;
        this.value = value;
        this.productId = productId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
