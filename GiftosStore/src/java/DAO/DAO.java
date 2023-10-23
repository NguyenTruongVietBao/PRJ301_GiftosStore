/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Category;
import model.Item;
import model.Product;
import model.User;
import utils.DBUtils;

/**
 *
 * @author VietBao
 */
public class DAO {
    Connection conn = null;     
    PreparedStatement ptm = null;  
    
// <-!-------LOGIN USER------------------------------------------------------------------------------------!->
    private static final String LOGIN =  "SELECT fullname, role_id, email, google_id, phone_number, address\n" 
                                        +"FROM dbo.[User] WHERE id = ? AND password = ?";
    public User checkLogin(String id, String password) throws SQLException {
        User user = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, id);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {   
                    String fullname = rs.getString("fullname");
                    String email = rs.getString("email");
                    String goole_id = rs.getString("google_id");
                    String phone_number = rs.getString("phone_number");
                    String address = rs.getString("address");
                    String role_id = rs.getString("role_id");
                    user = new User(id,fullname ,email ,goole_id ,phone_number ,address ,"",role_id );                  
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (rs != null)rs.close();           
            if (ptm != null)ptm.close();           
            if (conn != null)conn.close();       
        }
        return user;
    }    
    
// <-!-------SEARCH USER---------------------------------------------------------------------------!->    
    private static final String SEARCH = "SELECT * FROM dbo.[User]  WHERE fullname LIKE ?";
    public List<User> getListUser(String search) throws SQLException {
        List<User> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {                    
                    String id = rs.getString("id");
                    String fullname = rs.getString("fullname");
                    String email = rs.getString("email");
                    String goole_id = rs.getString("google_id");
                    String phone_number = rs.getString("phone_number");
                    String address = rs.getString("address");
                    String password = "***";
                    String role_id = rs.getString("role_id");
                    list.add(new User(id, fullname, email, goole_id, phone_number, address, password, role_id));
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }finally{
            if (rs != null)rs.close();           
            if (ptm != null)ptm.close();           
            if (conn != null)conn.close();       
        }
        return list;
    }
    
// <-!-------DELETE USER---------------------------------------------------------------------------!->    
    private static final String DELETE = "DELETE dbo.[User] WHERE id = ?";
    public boolean deleteUser(String id) throws SQLException {
        boolean check = false;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1,id);
                check= ptm.executeUpdate()>0?true:false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
            return check;
        }
    
// <-!-------UPDATE USER---------------------------------------------------------------------------!->    
    private static final String UPDATE = "UPDATE dbo.[User] SET fullname = ?, email = ?, phone_number = ?, ADDRESS = ?, google_id = ? "
                                        + "WHERE id = ?";
     public boolean updateUser(User user) throws SQLException {
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, user.getFullname());
                ptm.setString(2, user.getEmail());
                ptm.setString(3, user.getPhone_number());
                ptm.setString(4, user.getAddress());
                ptm.setString(5, user.getGoogle_id());    
                ptm.setString(6, user.getId());     
              check= ptm.executeUpdate()>0?true:false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (ptm != null)ptm.close();           
            if (conn != null)conn.close();       
        }      
        return check;
    }
     
// <-!-------INSERT USER---------------------------------------------------------------------------!->    
    private static final String INSERT = "INSERT INTO dbo.[User](id, fullname, email, google_id, phone_number, address, password, role_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    public boolean insertUser(User user) throws SQLException {
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, user.getId());
                ptm.setString(2, user.getFullname());
                ptm.setString(3, user.getEmail());
                ptm.setString(4, user.getGoogle_id());
                ptm.setString(5, user.getPhone_number());
                ptm.setString(6, user.getAddress());
                ptm.setString(7, user.getPassword());
                ptm.setString(8, user.getRole_id());
                check = ptm.executeUpdate()>0?true:false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (ptm != null)ptm.close();           
            if (conn != null)conn.close();       
        }
        return check;
    }
    
// <-!-------CHECK DUPLICATE---------------------------------------------------------------------------!->        
    private static final String CHECK_DUPLICATE = "SELECT fullname FROM dbo.[User] WHERE id=?";    
    public boolean checkDuplicate(String userID) throws SQLException{
        boolean check = false;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                if(rs.next()){
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (rs != null)rs.close();           
            if (ptm != null)ptm.close();           
            if (conn != null)conn.close();       
        }
        return check;
    }
    
    

// <-!-------GET ALL_CATEGORY------------------------------------------------------------------------------------!->
    private static final String DISPLAY_CATEGORY = "SELECT * FROM dbo.Category";
    public List<Category> getAllCategory() throws SQLException, ClassNotFoundException{        
        List<Category> list = new ArrayList<>();
        try {          
            conn= DBUtils.getConnection();     
            ptm = conn.prepareStatement(DISPLAY_CATEGORY);   
            ResultSet rs  = ptm.executeQuery();            
            while (rs.next()) { 
                Category c = new Category(  rs.getString("id"), 
                                            rs.getString("name"));
                list.add(c);                
            }
        } catch (SQLException e) {
            System.out.println(e);
        }        
        return list;
    }    
    
// <-!--------GET CATEGORY BY ID---------------------------------------------------------------------------!->
    private static final String GET_CATEGORY_BY_ID = "SELECT id, name FROM dbo.Category WHERE id = ?";
    public Category getCategoryById(String id){
        try {
            conn= DBUtils.getConnection();     
            ptm = conn.prepareStatement(GET_CATEGORY_BY_ID);   
            ptm.setString(1, id);
            ResultSet rs  = ptm.executeQuery();
            if(rs.next()){
                Category c = new Category(  rs.getString("id"),
                                            rs.getString("name"));
                return c;
            }
        } catch (Exception e) {
        }
        return null;
    }

// <-!---------GET PRODUCT BY CATEGOIRY ID------------------------------------------------------------!->   
    private static final String GET_PRODUCT_BY_CATEGORYID = "SELECT id, title,price,quantity,discount,thumbnail,description,created_at,category_id "
                                                        + "FROM [dbo].[Product] WHERE category_id LIKE ?";
    public List<Product> getProductByCategoryID(String c_id) throws ClassNotFoundException{
        List<Product> list = new ArrayList<>();
        try {          
            conn= DBUtils.getConnection();                
            ptm = conn.prepareStatement(GET_PRODUCT_BY_CATEGORYID); 
            ptm.setString(1, c_id);
            ResultSet rs  = ptm.executeQuery();            
            while (rs.next()) { 
                Product p = new Product();
                p.setId(rs.getString("id"));
                p.setTitle(rs.getString("title"));
                p.setPrice(rs.getInt("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setDiscount(rs.getInt("discount"));
                p.setThumbnail(rs.getString("thumbnail"));
                p.setDescription(rs.getString("description"));
                p.setCreated_at(rs.getString("created_at"));
                Category c = getCategoryById(rs.getString("category_id"));
                p.setCategory(c);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }        
        return list;
    }
    
// <-!-------GET ALL PRODUCT---------------------------------------------------------------------------!->
    private static final String GET_ALL_PRODUCT = "SELECT * FROM dbo.Product";
    public List<Product> getAllProduct(){
        List<Product> list = new ArrayList<>();
        try {
            conn= DBUtils.getConnection();     
            ptm = conn.prepareStatement(GET_ALL_PRODUCT);   
            ResultSet rs  = ptm.executeQuery();
            while (rs.next()) {                
                Product p = new Product();
                p.setId(rs.getString("id"));
                p.setTitle(rs.getString("title"));
                p.setPrice(rs.getInt("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setDiscount(rs.getInt("discount"));
                p.setThumbnail(rs.getString("thumbnail"));
                p.setDescription(rs.getString("description"));
                p.setCreated_at(rs.getString("created_at"));
                Category c = getCategoryById(rs.getString("category_id"));
                p.setCategory(c);
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }  
    
    // <-!-------GET PRODUCT BY ID---------------------------------------------------------------------------!->
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM dbo.Product WHERE id=?";
    public Product getProductById(String id){
        try {
            conn= DBUtils.getConnection();     
            ptm = conn.prepareStatement(GET_PRODUCT_BY_ID);   
            ptm.setString(1, id);
            ResultSet rs  = ptm.executeQuery();
            while (rs.next()) {                
                Product p = new Product();
                p.setId(rs.getString("id"));
                p.setTitle(rs.getString("title"));
                p.setPrice(rs.getInt("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setDiscount(rs.getInt("discount"));
                p.setThumbnail(rs.getString("thumbnail"));
                p.setDescription(rs.getString("description"));
                p.setCreated_at(rs.getString("created_at"));
                Category c = getCategoryById(rs.getString("category_id"));
                p.setCategory(c);
                return p;
            }
        } catch (Exception e) {
        }
        return null;
    } 
    
// <-!-------SEARCH PRODUCT---------------------------------------------------------------------------!->
    private static final String SEARCH_PRODUCT = "SELECT * FROM dbo.Product WHERE title LIKE ?";
    public List<Product> searchProduct(String search) throws SQLException {
        List<Product> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_PRODUCT);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {     
                    String id = rs.getString("id");
                    String title = rs.getString("title");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    int discount = rs.getInt("discount");
                    String thumbnail = rs.getString("thumbnail");
                    String description= rs.getString("description");
                    String created_at = rs.getString("created_at");
                    Category c = getCategoryById("category_id");
                    Product p  = new Product(id, title, price, quantity, discount, thumbnail, description, created_at, c);
                    list.add(p);
                }                
            }
        } catch (Exception e) {
            e.getStackTrace();
        }finally{
            if (rs != null)rs.close();           
            if (ptm != null)ptm.close();           
            if (conn != null)conn.close();       
        }
        return list;
    }
    
// <-!-------CHECK OUT Add to DB---------------------------------------------------------------------------!->
    public static final String ADD_ORDER = "INSERT INTO dbo.Orders VALUES (?,?,?)";
    public static final String ADD_ORDER_DETAIL = "INSERT INTO dbo.Order_Details VALUES (?,?,?,?)";
    public static final String GET_ID_ORDER = "SELECT TOP 1 id FROM dbo.Orders ORDER BY id DESC ";
    public static final String UPDATE_QUANTITY = "UPDATE dbo.Product SET quantity=quantity-? WHERE id LIKE ?";
    public void addOrder (User u, Cart cart){
        LocalDate currentDate = LocalDate.now();
        String order_date = currentDate.toString();
        try {
            //add order
            conn = DBUtils.getConnection();
            PreparedStatement ptm = conn.prepareStatement(ADD_ORDER);
            ptm.setString(1, order_date);
            ptm.setInt(2, cart.getTotalMoney());
            ptm.setString(3, u.getId());
            ptm.executeUpdate();
            // lay id cua order vua add
            PreparedStatement ptm1 = conn.prepareStatement(GET_ID_ORDER);
            ResultSet rs = ptm1.executeQuery();
            //add order detail
            if(rs.next()){
                int oid = rs.getInt("id");
                for (Item item : cart.getItems()) {
                        PreparedStatement ptm2 = conn.prepareStatement(ADD_ORDER_DETAIL);
                        ptm2.setInt(1, item.getPrice());
                        ptm2.setInt(2, item.getQuantity());
                        ptm2.setInt(3, oid);
                        ptm2.setString(4, item.getProduct().getId());
                        ptm2.executeUpdate();
                }
            }
            //Update lai quantity
            PreparedStatement ptm3 = conn.prepareStatement(UPDATE_QUANTITY);
            for (Item item : cart.getItems()) {
                ptm3.setInt(1, item.getQuantity());
                ptm3.setString(2, item.getProduct().getId());
                ptm3.executeUpdate();
            }
        } catch (Exception e) {
        }
    }

}



