/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author VietBao
 */
public class Cart {
    
    private Map<String, Product> cart;
    private List<Item> items;
    
    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(Map<String, Product> cart, List<Item> items) {
        this.cart = cart;
        this.items = items;
    }    

    public Map<String, Product> getCart() {
        return cart;
    }

    public void setCart(Map<String, Product> cart) {
        this.cart = cart;
    }
    
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    private int getQuantityById(String id){
        return getItemById(id).getQuantity();
    }
    
    private Item getItemById(String id){
        for(Item i: items){
            if(i.getProduct().getId().equals(id)){
                return i;
            }
        }
        return null;
    }
    
    public void addItem(Item t){
        if(getItemById(t.getProduct().getId())!= null){
            Item t2 = getItemById(t.getProduct().getId());
            t2.setQuantity(t2.getQuantity() + t.getQuantity());
        }else{
            items.add(t);
        }
    }
    
    public void removeItem(String id){
        if(getItemById(id)!= null){
            items.remove(getItemById(id));
        }
    }
    
    public int getTotalMoney(){
        int t = 0;
        for (Item item : items) {
            t = t + (item.getQuantity()*item.getPrice());
        }
        return t;
    }

//    public void add(Product product) {
//        if(this.cart == null){
//            this.cart = new HashMap<>();
//        }
//        if(this.cart.containsKey(product.getId())){
//            int currenQuantity = this.cart.get(product.getId()).getQuantity();
//            product.setQuantity(currenQuantity+product.getQuantity());
//        }
//        this.cart.put(product.getId(), product);
//    }
//    
//    public void delete(String id){
//        boolean check = false;
//        if(this.cart == null){
//            return;
//        }
//        if(this.cart.containsKey(id)){
//            this.cart.remove(id);
//        }
//    }
//    
//    public void update(String id, Fruit newFruit){
//        if(this.cart == null){
//            return;
//        }
//        if(this.cart.containsKey(id)){
//            this.cart.replace(id, newFruit);
//        }
//    }
       
}
