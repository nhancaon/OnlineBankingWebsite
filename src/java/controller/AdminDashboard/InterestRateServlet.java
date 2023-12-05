package controller.AdminDashboard;

import business.Customer;
import business.InterestRate;
import DAO.InterestRateDAO;
import Exception.HandleException;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/interestRate")
public class InterestRateServlet extends HttpServlet {

    InterestRateDAO interestRateDAO = new InterestRateDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "join"; // default action
        }

        String url = "/admin-dashboard/";
        switch (action) {
            case "add-interestRate" -> {
                this.addInterestRate(request, response);
                this.showInterestRate(request, response);
            }
            case "update-interestRate" -> {
                this.updateInterestRate(request, response);
                this.showInterestRate(request, response);
            }
            case "delete" -> {
                this.deleteInterestRate(request, response);
                this.showInterestRate(request, response);
            }
            default -> {
            }
        }
        url = "/admin-dashboard/interestRate.jsp";
        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }

        String url = "/admin-dashboard/";
        switch (action) {
            case "show-interestRate" ->
                this.showInterestRate(request, response);
            default -> {
            }
        }
        url = "/admin-dashboard/interestRate.jsp";
        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    protected void showInterestRate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<InterestRate> interestRates = interestRateDAO.findAllInterestRate();
        request.setAttribute("interestRates", interestRates);
    }

    protected void addInterestRate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String interestRate = request.getParameter("interestRate");
        String loanTitle = request.getParameter("loanTitle");
        String savingTitle = request.getParameter("savingTitle");
        String term = request.getParameter("term");
        try {
            interestRateDAO.addInterestRate(interestRate, loanTitle, savingTitle, term);
            request.setAttribute("successMessage", "The interest rate has been added successfully.");

        } catch (HandleException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }
    }

    protected void updateInterestRate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String interestRateId = request.getParameter("interestRateIdUpdate");
        InterestRate interestRate = interestRateDAO.findInterestRateByInterestId(interestRateId);

        String rate;
        String loanTitle;
        String savingTitle;
        String term;

        if(!request.getParameter("interestRateUpdate").isEmpty()){
            rate = request.getParameter("interestRateUpdate");
        }else{
            rate = String.valueOf(interestRate.getInterestRate());
        }

        if(!request.getParameter("loanTitleUpdate").isEmpty()){
            loanTitle = request.getParameter("loanTitleUpdate");
        }else{
            loanTitle = interestRate.getLoanTitle();
        }

        if(!request.getParameter("savingTitleUpdate").isEmpty()){
            savingTitle = request.getParameter("savingTitleUpdate");
        }else{
            savingTitle = interestRate.getSavingTitle();
        }

        if(!request.getParameter("termUpdate").isEmpty()){
            term = request.getParameter("termUpdate");
        }else{
            term = String.valueOf(interestRate.getTerm());
        }

        try {
            interestRateDAO.checkUpdateInterestRate(interestRateId, rate, loanTitle, savingTitle, term);
            request.setAttribute("successMessage", "The interest rate has been updated successfully.");

        } catch (HandleException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }

    }

    protected void deleteInterestRate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String interestRateId = request.getParameter("interestRateId");
        interestRateDAO.delete(interestRateId);
    }

}
