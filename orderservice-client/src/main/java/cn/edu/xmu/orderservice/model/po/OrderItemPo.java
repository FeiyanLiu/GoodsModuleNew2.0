package cn.edu.xmu.orderservice.model.po;

import java.time.LocalDateTime;

public class OrderItemPo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.order_id
     *
     * @mbg.generated
     */
    private Long orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.goods_sku_id
     *
     * @mbg.generated
     */
    private Long goodsSkuId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.quantity
     *
     * @mbg.generated
     */
    private Integer quantity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.price
     *
     * @mbg.generated
     */
    private Long price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.discount
     *
     * @mbg.generated
     */
    private Long discount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.coupon_activity_id
     *
     * @mbg.generated
     */
    private Long couponActivityId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.be_share_id
     *
     * @mbg.generated
     */
    private Long beShareId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.gmt_create
     *
     * @mbg.generated
     */
    private LocalDateTime gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.gmt_modified
     *
     * @mbg.generated
     */
    private LocalDateTime gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.id
     *
     * @return the value of order_item.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.id
     *
     * @param id the value for order_item.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.order_id
     *
     * @return the value of order_item.order_id
     *
     * @mbg.generated
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.order_id
     *
     * @param orderId the value for order_item.order_id
     *
     * @mbg.generated
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.goods_sku_id
     *
     * @return the value of order_item.goods_sku_id
     *
     * @mbg.generated
     */
    public Long getGoodsSkuId() {
        return goodsSkuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.goods_sku_id
     *
     * @param goodsSkuId the value for order_item.goods_sku_id
     *
     * @mbg.generated
     */
    public void setGoodsSkuId(Long goodsSkuId) {
        this.goodsSkuId = goodsSkuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.quantity
     *
     * @return the value of order_item.quantity
     *
     * @mbg.generated
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.quantity
     *
     * @param quantity the value for order_item.quantity
     *
     * @mbg.generated
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.price
     *
     * @return the value of order_item.price
     *
     * @mbg.generated
     */
    public Long getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.price
     *
     * @param price the value for order_item.price
     *
     * @mbg.generated
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.discount
     *
     * @return the value of order_item.discount
     *
     * @mbg.generated
     */
    public Long getDiscount() {
        return discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.discount
     *
     * @param discount the value for order_item.discount
     *
     * @mbg.generated
     */
    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.name
     *
     * @return the value of order_item.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.name
     *
     * @param name the value for order_item.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.coupon_activity_id
     *
     * @return the value of order_item.coupon_activity_id
     *
     * @mbg.generated
     */
    public Long getCouponActivityId() {
        return couponActivityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.coupon_activity_id
     *
     * @param couponActivityId the value for order_item.coupon_activity_id
     *
     * @mbg.generated
     */
    public void setCouponActivityId(Long couponActivityId) {
        this.couponActivityId = couponActivityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.be_share_id
     *
     * @return the value of order_item.be_share_id
     *
     * @mbg.generated
     */
    public Long getBeShareId() {
        return beShareId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.be_share_id
     *
     * @param beShareId the value for order_item.be_share_id
     *
     * @mbg.generated
     */
    public void setBeShareId(Long beShareId) {
        this.beShareId = beShareId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.gmt_create
     *
     * @return the value of order_item.gmt_create
     *
     * @mbg.generated
     */
    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.gmt_create
     *
     * @param gmtCreate the value for order_item.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.gmt_modified
     *
     * @return the value of order_item.gmt_modified
     *
     * @mbg.generated
     */
    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.gmt_modified
     *
     * @param gmtModified the value for order_item.gmt_modified
     *
     * @mbg.generated
     */
    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }
}