<%@page import="business.PaymentAccount" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/includes/header.jsp" %>

<%@ include file="/includes/checkLogin.jsp" %>


<% PaymentAccount defaultPaymentAccount = (PaymentAccount) request.getAttribute("defaultPaymentAccount");
%>


<div class="relative w-full h-[30rem] mt-[5.2rem]">
    <img src="./assets/money.gif" class="w-full h-full" alt="alt" />
</div>

                    <div id="content" class="bg-[#f0f1f1] py-16">
                        <div class="mx-56 px-8 pt-10 pb-16 rounded-md bg-[#fff]">
                            <div class="grid grid-cols-2 pt-4 pb-10 justify-between">
                                <% if (defaultPaymentAccount !=null) {%>
                                    <div class="grid">
                                        <span>Current Payment Account</span>
                                        <span class="font-bold text-xl">
                                            <%= defaultPaymentAccount.getAccountNumber()%>
                                        </span>
                                        <span class="font-bold text-xl"></span>
                                    </div>
                                    <div class="flex justify-end items-center">
                                        <div class="grid justify-end">
                                            <span>Available Balance</span>
                                            <span class="font-bold text-xl">
                                                <%= formatCurrency(defaultPaymentAccount.getCurrentBalence()) %> VND
                                            </span>
                                        </div>
                                        <div class="grid justify-end ml-4">
                                            <form action="show-account" method="get">
                                                <button type="submit" class="px-2 py-1 bg-gray-200 rounded-lg">
                                                    Accounts List <span class="font-bold"><i
                                                            class="fa-solid fa-chevron-right py-1"></i></span>
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                    <% } else { %>
                                        <div>User has not created a Payment Account yet</div>
                                        <div class="grid justify-end ml-4">
                                            <form action="show-account" method="get">
                                                <button type="submit" class="px-2 py-1 bg-gray-200 rounded-lg">
                                                    Create Payment Account <span class="font-bold"><i
                                                            class="fa-solid fa-chevron-right py-1"></i></span>
                                                </button>
                                            </form>
                                        </div>
                                        <% }%>
                            </div>
                            <div class="grid grid-cols-4 gap-8">
                                <a href="./transfer.jsp" class="flex flex-col items-center justify-center rounded-md h-28 bg-[#2a6ebe] text-sm font-bold text-white cursor-pointer
                focus:ring transform transition hover:scale-105 duration-300 ease-in-out">
                                    <img src="./assets/transfer.png" class="w-8 mb-2" />
                                    Transfer
                                </a>
                                <form action="create-saving-account" method="post" class="w-full">
                                    <button type="submit" class="w-full flex flex-col items-center justify-center rounded-md h-28 bg-[#2a6ebe] text-sm font-bold text-white cursor-pointer
                focus:ring transform transition hover:scale-105 duration-300 ease-in-out">
                                        <img src="./assets/salary.png" class="w-8 mb-2" />
                                        Saving Accounts
                                    </button>
                                </form>
                                <a href="./loan.jsp" class="flex flex-col items-center justify-center rounded-md h-28 bg-[#009893] text-sm font-bold text-white cursor-pointer
                focus:ring transform transition hover:scale-105 duration-300 ease-in-out">
                                    <img src="./assets/loan.png" class="w-8 mb-2" />
                                    Loan
                                </a>
                                <a href="./reward.jsp" class="flex flex-col items-center justify-center rounded-md h-28 bg-[#009893] text-sm font-bold text-white cursor-pointer
                focus:ring transform transition hover:scale-105 duration-300 ease-in-out">
                                    <img src="./assets/reward.png" class="w-8 mb-2" />
                                    Reward
                                </a>
                            </div>
                        </div>

    <div class="mx-56 py-16">
        <div>Settings</div>
        <div class="py-10 grid grid-cols-5 gap-5">
            <form action="show-beneficiary" method="GET">
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