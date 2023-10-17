/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author VietBao
 */
public class Product {
    private String id;
    private String title;
    private int price ;
    private int quantity ;
    private int discount ;
    private String thumbnail;
    private String description ;
    private String created_at ;
    private Category category;

    public Product() {
    }

    public Product(String id, String title, int price, int quantity, int discount, String thumbnail, String description, String created_at, Category category) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.thumbnail = thumbnail;
        this.description = description;
        this.created_at = created_at;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category_id) {
        this.category = category_id;
    }

   
    
}
