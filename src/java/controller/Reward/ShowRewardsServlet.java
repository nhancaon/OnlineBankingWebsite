/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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

@WebServlet(name = "ShowRewardsServlet", urlPatterns = {"/show-rewards"})
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
        request.setAttribute("defaultPaymentAccount", defaultPaymentAccount);

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

        List<Reward> allRewards = rewardDAO.getAllRewards();
        request.setAttribute("allRewards", allRewards);

    }

    protected void showShoppingRewards(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rewardType = "Shopping";
        List<Reward> shoppingRewards = rewardDAO.getRewardsByType(rewardType);
        request.setAttribute("shoppingRewards", shoppingRewards);
    }

    protected void showCulinaryRewards(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rewardType = "Culinary";
        List<Reward> culinaryRewards = rewardDAO.getRewardsByType(rewardType);
        request.setAttribute("culinaryRewards", culinaryRewards);
    }

}
