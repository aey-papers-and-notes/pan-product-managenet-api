package com.aey.products.infrastructure.persistence.query;

public class ProductQuery {

    public static  final String PARAM_PRODUCT_LIMIT = "limit";
    public static  final String PARAM_PRODUCT_OFFSET = "offset";

    public static final String PAGINATION_PRODUCT = "select " +
            "product_id as productId, " +
            "p_name as name, " +
            "p_description as description, " +
            "p_stock as stock, " +
            "p_price as price, " +
            "p_image_url as imageUrl, " +
            "p_created_at as createdAt, " +
            "p_updated_at as updatedAt, " +
            "p_is_active as isActive, " +
            "p_category as category, " +
            "p_tag as tag, " +
            "p_brand as brand " +
            "from prod01_products " +
            "where (p_is_active = true) " +
            "limit coalesce(:limit, 10) " +
            "offset coalesce(:offset, 0)";

    public static final String COUNT_AVAILABLE_PRODUCTS = "select " +
            "count(*) " +
            "from prod01_products " +
            "where p_is_active = true";
}
