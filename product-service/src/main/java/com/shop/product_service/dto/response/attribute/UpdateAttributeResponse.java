package com.shop.product_service.dto.response.attribute;

public class UpdateAttributeResponse {
    private Long id;
    private String key;
    private String value;
    private Long productId;

    public UpdateAttributeResponse() {
    }

    public UpdateAttributeResponse(Long id, String key, String value, Long productId) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
