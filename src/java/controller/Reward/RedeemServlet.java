package controller.Reward;

import DAO.RewardDAO;
import Exception.HandleException;
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

        HttpSession session = request.getSession();
        PaymentAccount defaultPaymentAccount = (PaymentAccount) session.getAttribute("defaultPaymentAccount");
        String defaultAccountNumber = defaultPaymentAccount.getAccountNumber();

        String url = "/" + currentPage + ".jsp";

        try {
            Reward reward = rewardDAO.redeemReward(rewardId, defaultAccountNumber);
            request.setAttribute("successMessage", "You have successfully reedeem the reward " + reward.getRewardName() + " with " +
            reward.getCostPoint() + " RWP");
        } catch (HandleException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }

        servletContext.getRequestDispatcher(url).forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
