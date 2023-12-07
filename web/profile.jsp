<%@ page import="business.PaymentAccount" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/includes/header.jsp" %>
<%@ include file="/includes/checkLogin.jsp" %>

<% 
    PaymentAccount defaultPaymentAccount = (PaymentAccount) request.getAttribute("defaultPaymentAccount");
%>


<div class="relative w-full md:h-[36rem] mt-[5.2rem]">
    <img src="./assets/money.gif" class="w-full h-full" alt="Background" />
</div>

<div id="content" class="bg-[#f0f1f1] py-16">
    <div class="md:mx-56 px-8 pt-10 pb-16 rounded-md bg-[#fff]">
        <div class="grid grid-cols-1 md:grid-cols-2 md:pt-4 pb-10 justify-between px-2 sm:px-0">
            <% if (defaultPaymentAccount != null) {%>
            <div class="grid items-center justify-center text-center sm:items-start sm:justify-start sm:text-left ">
                <span>Current Payment Account</span>
                <span class="font-bold text-xl">
                    <%= defaultPaymentAccount.getAccountNumber()%>
                </span>
                <span class="font-bold text-xl"></span>
            </div>
            <div class="flex sm:justify-end items-center mt-3 sm:mt-0">
                <div class="grid justify-end">
                    <span>Available Balance</span>
                    <span class="font-bold text-xl">
                        <%= formatCurrency(defaultPaymentAccount.getCurrentBalence())%> VND
                    </span>
                </div>
                <div class="grid justify-end ml-16 sm:ml-4">
                    <form action="Account" method="get">
                        <button type="submit" class="px-2 py-1 bg-gray-200 rounded-lg focus:ring transform transition hover:scale-105 duration-300 ease-in-out">
                            Accounts List 
                            <span class="font-bold">
                                <i class="fa-solid fa-chevron-right py-1"></i>
                            </span>
                        </button>
                    </form>
                </div>
            </div>
            <% } else { %>
            <div>User has not created a Payment Account yet</div>
            <div class="grid justify-end ml-4">
                <form action="Account" method="get">
                    <button type="submit" class="px-2 py-1 bg-gray-200 rounded-lg">
                        Create Payment Account <span class="font-bold"><i
                                class="fa-solid fa-chevron-right py-1"></i></span>
                    </button>
                </form>
            </div>
            <% }%>
        </div>
        <div class="grid grid-cols-2 md:grid-cols-4 gap-4 md:gap-8 px-2 md:px-0">
            <form action="Transfer" method="get" class="w-full">
                <button type="submit" class="w-full flex flex-col items-center justify-center rounded-md h-28 bg-[#2a6ebe] text-sm font-bold text-white cursor-pointer
                        focus:ring transform transition hover:scale-105 duration-300 ease-in-out">
                    <img src="./assets/transfer.png" class="w-8 mb-2" />
                    Transfer
                </button>
            </form>
            <form action="create-saving-account" method="get" class="w-full">
                <input type="hidden" name="action" value="reload">
                <button type="submit" class="w-full flex flex-col items-center justify-center rounded-md h-28 bg-[#009893] text-sm font-bold text-white cursor-pointer
                        focus:ring transform transition hover:scale-105 duration-300 ease-in-out">
                    <img src="./assets/salary.png" class="w-8 mb-2" />
                    Saving Accounts
                </button>
            </form>
            <a href="./loanLending.jsp" class="flex flex-col items-center justify-center rounded-md h-28 bg-[#009893] text-sm font-bold text-white cursor-pointer
               focus:ring transform transition hover:scale-105 duration-300 ease-in-out">
                <img src="./assets/loan.png" class="w-8 mb-2" />
                Loan
            </a>
            <form action="Rewards" method="get" class="w-full">
                <input type="hidden" name="action" value="allRewards">
                <button type="submit" class="w-full flex flex-col items-center justify-center rounded-md h-28 bg-[#009893] text-sm font-bold text-white cursor-pointer
                        focus:ring transform transition hover:scale-105 duration-300 ease-in-out">
                    <img src="./assets/reward.png" class="w-8 mb-2" />
                    Reward
                </button>
            </form>

        </div>
    </div>

    <div class="md:mx-56 mx-2 py-16">
        <div>Settings</div>
        <div class="py-10 grid grid-cols-2 md:grid-cols-5 gap-5">
            <form action="Beneficiary" method="GET">
                <button type="submit" class="w-full grid justify-center items-center bg-white h-40 rounded-xl
                        focus:ring transform transition hover:scale-105 duration-300 ease-in-out">
                    <span class="flex flex-col items-center justify-center">
                        <img src="assets/quan-ly-danh-ba.svg" alt="beneficiary" class="w-12 h-12 mb-2">
                        Beneficiary
                    </span>
                </button>
            </form>
            <a href="./changePassword.jsp" class="grid justify-center items-center bg-white h-40 rounded-xl
               focus:ring transform transition hover:scale-105 duration-300 ease-in-out">
                <span class="flex flex-col items-center justify-center">
                    <img src="assets/doi-mk.svg" alt="changepassword" class="w-12 h-12 mb-2">
                    Change password
                </span>
            </a>
        </div>
    </div>
</div>


<%@ include file="/includes/footer.jsp" %>