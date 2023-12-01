package controller.AdminDashboard;

import business.SavingAccount;
import DAO.SavingAccountDAO;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/admin-dashboard/savingAccount")
public class SavingAccountServlet extends HttpServlet {

    SavingAccountDAO savingAccountDAO = new SavingAccountDAO();

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
            case "show-savingAccount" ->
                this.showSavingAccount(request, response);
            default -> {
            }
        }
        url = "/admin-dashboard/savingAccount.jsp";
        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    protected void showSavingAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<SavingAccount> savingAccounts = savingAccountDAO.findAllSavingAccount();

        request.setAttribute("savingAccounts", savingAccounts);
    }

    protected void addSavingAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountNumber = request.getParameter("accountNumber");
        String accountStatus = request.getParameter("accountStatus");
        String accountType = request.getParameter("accountType");
        String savingAmount = request.getParameter("savingAmount");
        String dateOpened = request.getParameter("dateOpened");
        String dateClosed = request.getParameter("dateClosed");
        
    }
    
       protected void updateSavingAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<SavingAccount> savingAccounts = savingAccountDAO.findAllSavingAccount();

        request.setAttribute("savingAccounts", savingAccounts);
    }

}
