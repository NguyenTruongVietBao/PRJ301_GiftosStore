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
import model.Product;

/**
 *
 * @author VietBao
 */
@WebServlet(name = "DeleteCartController", urlPatterns = {"/DeleteCartController"})
public class DeleteCartController extends HttpServlet {

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
        //xoa
        if(arr != null){
            for (Cookie cookie : arr) {
                if(cookie.getName().equals("Cart")){    
                    txt += cookie.getValue();
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        String id = request.getParameter("id");
        String ids[] = txt.split(",");
        String out = "";
        for (int i = 0; i < ids.length; i++) {
            String []s = ids[i].split(":");
            if(!s[0].equals(id)){
                if(out.isEmpty()){
                    out = ids[i];
                }else{
                    txt += "," + ids[i];
                }
            }
        }
        if(!out.isEmpty()){
            Cookie c = new Cookie("Cart", out);
            c.setMaxAge(2*24*60*60);
            response.addCookie(c);
        }
        Cart cart = new Cart(out, list);
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
