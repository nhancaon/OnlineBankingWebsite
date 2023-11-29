package controller.Reward;

import DAO.RewardDAO;
import business.PaymentAccount;
import business.Reward;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/redeem")
public class RedeemServlet extends HttpServlet {

    RewardDAO rewardDAO = new RewardDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        String rewardId = request.getParameter("rewardId");
        String currentPage = request.getParameter("currentPage");
        
        System.out.println(rewardId);

        HttpSession session = request.getSession();
        PaymentAccount defaultPaymentAccount = (PaymentAccount) session.getAttribute("defaultPaymentAccount");
        String defaultAccountNumber = defaultPaymentAccount.getAccountNumber();
        System.out.println(defaultAccountNumber);
        Reward redeemReward = rewardDAO.redeemReward(rewardId, defaultAccountNumber);
        
        String url = "/" + currentPage + ".jsp";

        servletContext.getRequestDispatcher(url).forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
