package controller.SavingAccount;

import business.SavingAccount;
import DAO.SavingAccountDAO;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/saving-detail")
public class ShowAccountDetailServlet extends HttpServlet {

    SavingAccountDAO savingAccountDAO = new SavingAccountDAO();
    SavingAccount savingAccount = new SavingAccount();
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        String url = "/savingDetail.jsp";
        String accountNumber = request.getParameter("accountNumber");
        savingAccount = savingAccountDAO.findByAccountNumber(accountNumber);
        request.setAttribute("savingAccount", savingAccount);
        
        servletContext.getRequestDispatcher(url)
                .forward(request, response);
    }
}
