/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cart;

import DAO.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;
import model.Item;
import model.Product;

/**
 *
 * @author VietBao
 */
@WebServlet(name = "UpdateCartController", urlPatterns = {"/UpdateCartController"})
public class UpdateCartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO();
        List<Product> list = dao.getAllProduct();
        Cookie arr[] = request.getCookies();
        String txt="";
        if(arr != null){
            for (Cookie cookie : arr) { 
                if(cookie.getName().equals("Cart")){    
                    txt += cookie.getValue();
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        Cart cart = new Cart(txt, list);
        String num_raw = request.getParameter("numQuantity");
        String id = request.getParameter("id");
        int num = 0;
        try {
            Product p = dao.getProductById(id);
            int numStore = p.getQuantity();
            num = Integer.parseInt(num_raw);
            if(num == -1 && (cart.getQuantityById(id) <= 1)){
                cart.removeItem(id);
            }else{
                if((num == 1) && cart.getQuantityById(id) >= numStore){
                    num = 0;
                }
                
                int price = p.getPrice();
                Item t = new Item(p, num, price);
                cart.addItem(t);
            }
        } catch (Exception e) {
            
        }
        List<Item> items = cart.getItems();
        txt="";
        if(items.size() > 0){
            txt = items.get(0).getProduct().getId() + ":" +  items.get(0).getQuantity();
            for(int i=1; i<items.size(); i++){
                txt += ","+ items.get(i).getProduct().getId() + ":" +  items.get(i).getQuantity();
            }
        }
        Cookie  c = new Cookie("Cart", txt);
        c.setMaxAge(2*24*60*60);    //2 ngay
        response.addCookie(c);     // add vao browser  
        request.setAttribute("Cart", cart);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
