<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="business.Transaction"%> 
<%@page import="java.util.List"%>
<%@page import="DAO.PaymentAccountDAO"%> 
<%@page import="business.PaymentAccount"%> 
<%@ include file="/includes/header.jsp"%> 
<%@ include file="/includes/checkLogin.jsp" %> 
<%    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
    PaymentAccount paymentAccount = (PaymentAccount) request.getAttribute("paymentAccount");
    List<Transaction> transactionList = (List<Transaction>) request.getAttribute("transactionList");
%>

<div class="bg-[#f0f1f1] mt-[5.2rem] pb-16">
    <div class="py-16 mx-2 md:mx-56">

        <div class="flex text-2xl">
            <form action="Profile" method="GET">
                <button><i class="fa-solid fa-chevron-left text-xl py-[0.3rem] pr-6"></i></button>
            </form>
            <div class="py-[0.2rem]">Account</div>
        </div>

        <nav class="flex pt-3 ml-[2.3rem]" aria-label="Breadcrumb">
            <ol class="inline-flex items-center space-x-1 md:space-x-3">
                <li class="inline-flex items-center">
                    <%@ include file="/includes/homeButton.jsp" %>
                </li>
                <li>
                    <div class="flex items-center">
                        <svg
                            class="w-3 h-3 mx-1"
                            aria-hidden="true"
                            xmlns="http://www.w3.org/2000/svg"
                            fill="none"
                            viewBox="0 0 6 10"
                            >
                            <path
                                stroke="currentColor"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                stroke-width="2"
                                d="m1 9 4-4-4-4"
                                />
                        </svg>
                        <form action="rewards" method="get">
                            <input type="hidden" name="action" value="allRewards" />
                            <button
                                class="ml-1 mb-1 text-sm font-medium text-gray-600 hover:text-blue-600 md:ml-2 cursor-pointer"
                                >
                                Rewards
                            </button>
                        </form>
                    </div>
                </li>
                <li>
                    <div class="flex items-center">
                        <svg
                            class="w-3 h-3 mx-1"
                            aria-hidden="true"
                            xmlns="http://www.w3.org/2000/svg"
                            fill="none"
                            viewBox="0 0 6 10"
                            >
                            <path
                                stroke="currentColor"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                stroke-width="2"
                                d="m1 9 4-4-4-4"
                                />
                        </svg>
                        <form class="cursor-pointer">
                            <button
                                type="text"
                                class="ml-1 text-sm font-medium text-blue-600 md:ml-2 mb-1 pointer-events-none"
                                >
                                My Rewards
                            </button>
                        </form>
                    </div>
                </li>
            </ol>
        </nav>

        <div class="grid my-16 py-8 md:px-20 rounded-xl bg-white">
            <c:forEach var="reward" items="${rewardsOfAccount}">
                <div
                    class="p-8 flex justify-between rounded-xl"
                    >

                    <span>${reward.getRewardName()}</span>
                    <span class="text-sm font-light">Price: ${reward.price} VND</span>
                    <span class="ml-2 text-md text-blue-600"
                          >${reward.getCostPoint()} RWP</span>
                </div>
            </c:forEach>
        </div>
    </div>
</div>


<%@ include file="/includes/footer.jsp" %>

