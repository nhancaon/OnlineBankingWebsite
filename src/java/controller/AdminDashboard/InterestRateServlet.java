package controller.AdminDashboard;
import business.InterestRate;
import DAO.InterestRateDAO;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/admin-dashboard/interestRate")
public class InterestRateServlet extends HttpServlet {

    InterestRateDAO interestRateDAO = new InterestRateDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }

        String url = "/admin-dashboard/";
        switch (action) {
            case "show-interestRate" -> this.showInterestRate(request, response);
            default -> {}
        }
        url = "/admin-dashboard/interestRate.jsp";
        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    protected void showInterestRate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<InterestRate> interestRates = interestRateDAO.findAllInterestRate();

        request.setAttribute("interestRates", interestRates);
    }

}
