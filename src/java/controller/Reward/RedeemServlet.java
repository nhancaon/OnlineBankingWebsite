package controller.Reward;

import business.Customer;
import business.PaymentAccount;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/redeem")
public class RedeemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        String rewardId = request.getParameter("rewardId");
        String currentPage = request.getParameter("currentPage");
            

        String url = "/" + currentPage + ".jsp";

        servletContext.getRequestDispatcher(url).forward(request, response);
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
