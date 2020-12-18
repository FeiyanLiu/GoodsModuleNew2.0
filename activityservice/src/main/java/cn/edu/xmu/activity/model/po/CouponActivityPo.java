package cn.edu.xmu.activity.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class CouponActivityPo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_activity.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_activity.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_activity.begin_time
     *
     * @mbg.generated
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime beginTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_activity.end_time
     *
     * @mbg.generated
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime endTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_activity.coupon_time
     *
     * @mbg.generated
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime couponTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_activity.state
     *
     * @mbg.generated
     */
    private Byte state;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_activity.shop_id
     *
     * @mbg.generated
     */
    private Long shopId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_activity.quantity
     *
     * @mbg.generated
     */
    private Integer quantity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_activity.valid_term
     *
     * @mbg.generated
     */
    private Byte validTerm;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_activity.image_url
     *
     * @mbg.generated
     */
    private String imageUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_activity.strategy
     *
     * @mbg.generated
     */
    private String strategy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_activity.created_by
     *
     * @mbg.generated
     */
    private Long createdBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_activity.modi_by
     *
     * @mbg.generated
     */
    private Long modiBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_activity.gmt_create
     *
     * @mbg.generated
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_activity.gmt_modified
     *
     * @mbg.generated
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_activity.quantitiy_type
     *
     * @mbg.generated
     */
    private Byte quantitiyType;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_activity.id
     *
     * @return the value of coupon_activity.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_activity.id
     *
     * @param id the value for coupon_activity.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_activity.name
     *
     * @return the value of coupon_activity.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_activity.name
     *
     * @param name the value for coupon_activity.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_activity.begin_time
     *
     * @return the value of coupon_activity.begin_time
     *
     * @mbg.generated
     */
    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_activity.begin_time
     *
     * @param beginTime the value for coupon_activity.begin_time
     *
     * @mbg.generated
     */
    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_activity.end_time
     *
     * @return the value of coupon_activity.end_time
     *
     * @mbg.generated
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_activity.end_time
     *
     * @param endTime the value for coupon_activity.end_time
     *
     * @mbg.generated
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_activity.coupon_time
     *
     * @return the value of coupon_activity.coupon_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCouponTime() {
        return couponTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_activity.coupon_time
     *
     * @param couponTime the value for coupon_activity.coupon_time
     *
     * @mbg.generated
     */
    public void setCouponTime(LocalDateTime couponTime) {
        this.couponTime = couponTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_activity.state
     *
     * @return the value of coupon_activity.state
     *
     * @mbg.generated
     */
    public Byte getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_activity.state
     *
     * @param state the value for coupon_activity.state
     *
     * @mbg.generated
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_activity.shop_id
     *
     * @return the value of coupon_activity.shop_id
     *
     * @mbg.generated
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_activity.shop_id
     *
     * @param shopId the value for coupon_activity.shop_id
     *
     * @mbg.generated
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_activity.quantity
     *
     * @return the value of coupon_activity.quantity
     *
     * @mbg.generated
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_activity.quantity
     *
     * @param quantity the value for coupon_activity.quantity
     *
     * @mbg.generated
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_activity.valid_term
     *
     * @return the value of coupon_activity.valid_term
     *
     * @mbg.generated
     */
    public Byte getValidTerm() {
        return validTerm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_activity.valid_term
     *
     * @param validTerm the value for coupon_activity.valid_term
     *
     * @mbg.generated
     */
    public void setValidTerm(Byte validTerm) {
        this.validTerm = validTerm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_activity.image_url
     *
     * @return the value of coupon_activity.image_url
     *
     * @mbg.generated
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_activity.image_url
     *
     * @param imageUrl the value for coupon_activity.image_url
     *
     * @mbg.generated
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_activity.strategy
     *
     * @return the value of coupon_activity.strategy
     *
     * @mbg.generated
     */
    public String getStrategy() {
        return strategy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_activity.strategy
     *
     * @param strategy the value for coupon_activity.strategy
     *
     * @mbg.generated
     */
    public void setStrategy(String strategy) {
        this.strategy = strategy == null ? null : strategy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_activity.created_by
     *
     * @return the value of coupon_activity.created_by
     *
     * @mbg.generated
     */
    public Long getCreatedBy() {
        return createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_activity.created_by
     *
     * @param createdBy the value for coupon_activity.created_by
     *
     * @mbg.generated
     */
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_activity.modi_by
     *
     * @return the value of coupon_activity.modi_by
     *
     * @mbg.generated
     */
    public Long getModiBy() {
        return modiBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_activity.modi_by
     *
     * @param modiBy the value for coupon_activity.modi_by
     *
     * @mbg.generated
     */
    public void setModiBy(Long modiBy) {
        this.modiBy = modiBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_activity.gmt_create
     *
     * @return the value of coupon_activity.gmt_create
     *
     * @mbg.generated
     */
    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_activity.gmt_create
     *
     * @param gmtCreate the value for coupon_activity.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_activity.gmt_modified
     *
     * @return the value of coupon_activity.gmt_modified
     *
     * @mbg.generated
     */
    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_activity.gmt_modified
     *
     * @param gmtModified the value for coupon_activity.gmt_modified
     *
     * @mbg.generated
     */
    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_activity.quantitiy_type
     *
     * @return the value of coupon_activity.quantitiy_type
     *
     * @mbg.generated
     */
    public Byte getQuantitiyType() {
        return quantitiyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_activity.quantitiy_type
     *
     * @param quantitiyType the value for coupon_activity.quantitiy_type
     *
     * @mbg.generated
     */
    public void setQuantitiyType(Byte quantitiyType) {
        this.quantitiyType = quantitiyType;
    }
}