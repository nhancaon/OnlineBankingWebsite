package controller.Reward;

import java.io.IOException;
import DAO.RewardDAO;
import DAO.PaymentAccountDAO;
import business.Customer;
import business.PaymentAccount;
import business.Reward;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/show-rewards")
public class ShowRewardsServlet extends HttpServlet {

    RewardDAO rewardDAO = new RewardDAO();
    PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();

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
            default -> {
            }
        }

        servletContext.getRequestDispatcher(url).forward(request, response);
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

}
