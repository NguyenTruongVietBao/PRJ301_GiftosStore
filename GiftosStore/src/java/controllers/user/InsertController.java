/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.user;

import DAO.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import model.UserError;

/**
 *
 * @author VietBao
 */
@WebServlet(name = "InsertController", urlPatterns = {"/InsertController"})
public class InsertController extends HttpServlet {

    private static final String ERROR = "insert.jsp";
    private static final String SUCCESS = "admin.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         String url= ERROR;
        try {
            String id = request.getParameter("id");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String google_id = request.getParameter("google_id");
            String phone_number = request.getParameter("phone_number");
            String address = request.getParameter("address");
            String password = request.getParameter("password");
            String role_id = request.getParameter("role_id");
            String confirm = request.getParameter("confirm");
            UserError userError = new UserError();
            boolean checkValidation = true;
            DAO dao = new DAO();
            boolean checkDuplicate = dao.checkDuplicate(id);
//id               
            if(checkDuplicate){
                checkValidation= false;
                userError.setId("Duplicate userID");
            }
//name               
            if(fullname.length()>20 || fullname.length()<5){
                checkValidation= false;
                userError.setFullname("fullName must be in 5 - 20");
            }
//email     
            String patternEmail = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
            if(!email.matches(patternEmail)){
                checkValidation= false;
                userError.setEmail("Wrong email");
            }

//phone
            String patternPhone = "0[0-9]{9,10}";   
            if(!phone_number.matches(patternPhone)){
                checkValidation= false;
                userError.setPhone_number("Phone start with 0");
            }
//address               
            if(address.length()>200){
                checkValidation= false;
                userError.setAddress("address dai qua fen");
            }
//password            
            if(!password.equals(confirm)){
                checkValidation= false;
                userError.setConfirmError("Password confirm not match");               
            }
            
            if(checkValidation){
                User user= new User(id, fullname, email, google_id, phone_number, address, password, role_id);
                boolean checkInsert = dao.insertUser(user);
                if(checkInsert){
                    url= SUCCESS;
                }
            }else{
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
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
