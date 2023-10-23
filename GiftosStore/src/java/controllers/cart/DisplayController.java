/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import DAO.DAO;
import javax.servlet.http.Cookie;
import model.Cart;
import model.Item;
import model.Product;

/**
 *
 * @author VietBao
 */
@WebServlet(name = "DisplayController", urlPatterns = {"/DisplayController"})
public class DisplayController extends HttpServlet {

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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
            response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO();
//        HIỂN THỊ Category
        List<Category> listCategory = dao.getAllCategory();
        request.setAttribute("LIST_CATEGORY", listCategory);
        
//        HIỂN THỊ PRODUCT          
        List<Product> listProduct = dao.getAllProduct();
        Cookie arr[] = request.getCookies();
        String txt="";
        if(arr!= null){
            for (Cookie cookie : arr) {
                if(cookie.getName().equals("Cart")){
                    txt+=cookie.getValue();
                }
            }
        }
        Cart cart = new Cart(txt, listProduct);
        List<Item> listItem = cart.getItems();
        int n;
        if(listItem != null){
            n = listItem.size();
        }else
            n = 0;
            request.setAttribute("sizeCart", n);
            request.setAttribute("LIST_PRODUCT", listProduct);
            request.getRequestDispatcher("shop.jsp").forward(request, response);
        
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
