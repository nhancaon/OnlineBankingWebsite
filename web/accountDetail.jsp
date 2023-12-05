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
                        <form action="Account" method="get">
                            <button
                                type="submit"
                                class="ml-1 text-sm font-medium hover:text-blue-600 md:ml-2 mb-1"
                                >
                                Account
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
                                Account Detail
                            </button>
                        </form>
                    </div>
                </li>
            </ol>
        </nav>

        <div class="flex flex-col-reverse md:grid grid-cols-6 gap-8">
            <div class="col-span-4 md:my-16 py-8 px-2 md:px-20 rounded-xl bg-white">
                <div class="w-full grid grid-cols-1">
                    <% if (transactionList != null) {
                            for (Transaction transaction
                                    : transactionList) {
                                if (transaction.getSender().getAccountNumber().equals(paymentAccount.getAccountNumber())) {%>
                    <div class="grid grid-col-2 sm:grid-cols-3 border-b-2 py-2 mb-3">
                        <div class="text-gray-500 text-sm">
                            <%= transaction.getTransactionDate().format(formatter)%>
                        </div>
                        <div class="sm:col-span-2 sm:text-end text-gray-500 text-sm">
                            Trans Id: <%= transaction.getTransactionId()%>
                        </div>
                        <div class="sm:col-span-2">
                            <%= transaction.getReceiver().getAccountNumber()%> <%=transaction.getTransactionRemark()%>
                        </div>
                        <div class="sm:text-end text-xl text-red-400">
                            - <%= formatCurrency(transaction.getAmount())%> VND
                        </div>
                    </div>
                    <% } else {%>
                    <div class="grid grid-cols-3 border-b-2 py-2 mb-3">
                        <div class="text-gray-500 text-sm">
                            <%= transaction.getTransactionDate().format(formatter)%>
                        </div>
                        <div class="col-span-2 text-end text-gray-500 text-sm">
                            Trans Id: <%= transaction.getTransactionId()%>
                        </div>
                        <div class="col-span-2">
                            <%= transaction.getReceiver().getAccountNumber()%> <%=transaction.getTransactionRemark()%>
                        </div>
                        <div class="text-end text-xl text-green-400">
                            + <%= formatCurrency(transaction.getAmount())%> VND
                        </div>
                    </div>
                    <% }
                            }
                        }%>
                </div>
            </div>
            <div class="col-span-2 mt-16 md:my-16 rounded-xl bg-white max-h-[450px]">
                <div
                    class="flex justify-between bg-gradient-to-r from-[#3caff2] to-[#2267a8] rounded-t-xl"
                    >
                    <div
                        class="flex flex-col justify-center items-center ml-8 text-white"
                        >
                        <span class="uppercase text-sm"><%= customer.getName()%></span>
                        <span class="uppercase text-sm"
                              ><%= paymentAccount.getAccountNumber()%></span
                        >
                    </div>
                    <img src="assets/thanh-toan.svg" src="wallet" class="p-4" />
                </div>
                <div class="w-full grid grid-cols-1 gap-2 p-8">
                    <div class="flex justify-between items-center">
                        <span class="text-gray-500 text-sm">Account Name</span>
                        <span class="uppercase text-sm"><%= customer.getName()%></span>
                    </div>
                    <div class="flex justify-between items-center">
                        <span class="text-gray-500 text-sm">Account Number</span>
                        <span class="uppercase text-sm"
                              ><%= paymentAccount.getAccountNumber()%></span
                        >
                    </div>
                    <div class="flex justify-between items-center">
                        <span class="text-gray-500 text-sm">Account Status</span>
                        <span class="uppercase text-sm"
                              ><%= paymentAccount.getAccountStatus()%></span
                        >
                    </div>
                    <div class="flex justify-between items-center">
                        <span class="text-gray-500 text-sm">Account Type</span>
                        <span class="uppercase text-sm"
                              ><%= paymentAccount.getAccountType()%></span
                        >
                    </div>
                    <div class="flex justify-between items-center">
                        <span class="text-gray-500 text-sm">Account Opening Date</span>
                        <span class="uppercase text-sm"
                              ><%= paymentAccount.getDateOpened()%></span
                        >
                    </div>
                    <div class="flex justify-between items-center">
                        <span class="text-gray-500 text-sm">Account Balance</span>
                        <span class="uppercase text-sm"
                              ><%= formatCurrency(paymentAccount.getCurrentBalence())%> VND</span
                        >
                    </div>
                    <div class="flex justify-between items-center">
                        <span class="text-gray-500 text-sm">Reward Point </span>
                        <span class="uppercase text-sm"
                              ><%= paymentAccount.getRewardPoint()%> RWP</span
                        >
                    </div>
                    <div class="flex justify-between items-center">
                        <span class="text-gray-500 text-sm">Registered Branch</span>
                        <span class="uppercase text-sm">NND Banking</span>
                    </div>
                    <% if (!paymentAccount.getAccountStatus().equals("Default")) {%>
                    <div class="flex justify-end items-center">
                        <form action="AccountDetail" method="post">
                            <input type="hidden" name="accountNumb" value="<%= paymentAccount.getAccountNumber()%>" />
                            <div class="flex justify-center items-center">
                                <button
                                    class="p-4 rounded-md bg-gradient-to-r from-[#00bfae] to-[#0066ad] mt-3 text-sm text-white"
                                    >
                                    Set as Default Account
                                </button>
                            </div>
                        </form>
                    </div>
                    <% }%>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/includes/footer.jsp" %>

