package controller.Reward;

import java.io.IOException;
import DAO.RewardDAO;
import DAO.PaymentAccountDAO;
import Exception.HandleException;
import business.Customer;
import business.PaymentAccount;
import business.Reward;
import java.security.SecureRandom;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Rewards")
public class RewardsServlet extends HttpServlet {

    RewardDAO rewardDAO = new RewardDAO();
    PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        String rewardId = request.getParameter("rewardId");
        String currentPage = request.getParameter("currentPage");

        HttpSession session = request.getSession();
        PaymentAccount defaultPaymentAccount = (PaymentAccount) session.getAttribute("defaultPaymentAccount");
        String defaultAccountNumber = defaultPaymentAccount.getAccountNumber();

        String url = "/" + currentPage + ".jsp";

        try {
            Reward reward = rewardDAO.redeemReward(rewardId, defaultAccountNumber);
            request.setAttribute("successMessage", "You have successfully reedeem the reward " + reward.getRewardName() + " with "
                    + reward.getCostPoint() + " RWP");
            this.showRewardsOfUser(request, response, defaultAccountNumber);
            url = "/rewardDetail.jsp";
        } catch (HandleException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }

        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        String customerId = customer.getCustomerId();
        PaymentAccount defaultPaymentAccount = paymentAccountDAO.findDefaultPaymentAccount(customerId);
        session.setAttribute("defaultPaymentAccount", defaultPaymentAccount);

        String url = "/";
        switch (action) {
            case "allRewards" -> {
                this.showAllRewards(request, response);
                url = "/reward.jsp";
            }
            case "shopping" -> {
                this.showShoppingRewards(request, response);
                url = "/shopping.jsp";
            }
            case "culinary" -> {
                this.showCulinaryRewards(request, response);
                url = "/culinary.jsp";
            }
            case "my-rewards" -> {
                String accountNumber = (String) request.getParameter("accountNumber");
                this.showRewardsOfUser(request, response, accountNumber);
                url = "/rewardDetail.jsp";
            }
            default -> {
            }
        }

        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    protected void showUserRewards(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Reward> allRewards = rewardDAO.getAllRewards();
        session.setAttribute("allRewards", allRewards);

    }

    protected void showAllRewards(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Reward> allRewards = rewardDAO.getAllRewards();
        session.setAttribute("allRewards", allRewards);

    }

    protected void showShoppingRewards(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String rewardType = "Shopping";
        List<Reward> shoppingRewards = rewardDAO.getRewardsByType(rewardType);
        session.setAttribute("shoppingRewards", shoppingRewards);
    }

    protected void showCulinaryRewards(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String rewardType = "Culinary";
        List<Reward> culinaryRewards = rewardDAO.getRewardsByType(rewardType);
        session.setAttribute("culinaryRewards", culinaryRewards);
    }

    protected void showRewardsOfUser(HttpServletRequest request, HttpServletResponse response, String accountNumber)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
//        String accountNumber = (String) request.getParameter("accountNumber");
        List<Reward> rewardsOfAccount = paymentAccountDAO.findRewardOfAccount(accountNumber);
        session.setAttribute("rewardsOfAccount", rewardsOfAccount);

    }

}
