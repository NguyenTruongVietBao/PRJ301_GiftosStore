/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DAO.DAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author VietBao
 */
public class Cart {
    
    private List<Item> items;
    
    public Cart() {
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public int getQuantityById(String id){
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
            Item m = getItemById(t.getProduct().getId());
            m.setQuantity(m.getQuantity() + t.getQuantity());
        }else
            items.add(t);        
    }
    
    public void removeItem(String id){
        if(getItemById(id)!= null){
            items.remove(getItemById(id));
        }
    }
    
    public int getTotalMoney(){
        int t = 0;
        for (Item item : items) {
            t +=  (item.getQuantity()*item.getPrice());
        }
        return t;
    }
    
    private Product getProductById(String id, List<Product> list){
        for (Product i : list) {
            if(i.getId().equals(id))
                return i;           
        }
        return null;
    }
    
    public Cart (String txt, List<Product> list){
        items = new ArrayList<>();        
        if(txt!=null && txt.length()!=0){
            String s[] = txt.split(",");
            for (String i : s) {
                String[] n = i.split(":");
                String id = n[0];
                int quantity = Integer.parseInt(n[1]);
                Product p = getProductById(id, list);
                Item t = new Item(p, quantity, p.getPrice());
                addItem(t);
            }
        }
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
