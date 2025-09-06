package com.ams.servlet;

import com.ams.dao.CarrierDAO;
import com.ams.model.Carrier;
import com.ams.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AddCarrierServlet")
public class AddCarrierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CarrierDAO carrierDAO = new CarrierDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        
        if (user == null || !"Admin".equals(user.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        try {
            // Get form parameters
            String carrierName = request.getParameter("carrierName");
            int discount30Days = Integer.parseInt(request.getParameter("discount30Days"));
            int discount60Days = Integer.parseInt(request.getParameter("discount60Days"));
            int discount90Days = Integer.parseInt(request.getParameter("discount90Days"));
            int bulkDiscount = Integer.parseInt(request.getParameter("bulkDiscount"));
            int silverDiscount = Integer.parseInt(request.getParameter("silverDiscount"));
            int goldDiscount = Integer.parseInt(request.getParameter("goldDiscount"));
            int platinumDiscount = Integer.parseInt(request.getParameter("platinumDiscount"));
            int refund2Days = Integer.parseInt(request.getParameter("refund2Days"));
            int refund10Days = Integer.parseInt(request.getParameter("refund10Days"));
            int refund20Days = Integer.parseInt(request.getParameter("refund20Days"));
            
            // Create carrier object
            Carrier carrier = new Carrier(
                carrierName, discount30Days, discount60Days, discount90Days,
                refund2Days, refund10Days, refund20Days,
                bulkDiscount, silverDiscount, goldDiscount, platinumDiscount
            );
            
            // Save carrier
            boolean success = carrierDAO.addCarrier(carrier);
            
            if (success) {
                request.setAttribute("success", "Carrier Information Saved Successfully in the System");
                request.getRequestDispatcher("manageCarriers.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Encountered issue while saving Carrier Information. Please check the data and try again");
                request.getRequestDispatcher("addCarrier.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Encountered issue while saving Carrier Information. Please check the data and try again");
            request.getRequestDispatcher("addCarrier.jsp").forward(request, response);
        }
    }
}