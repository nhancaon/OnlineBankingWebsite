<%@page import="business.PaymentAccount"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/includes/header.jsp" %>

<%@ include file="/includes/checkLogin.jsp" %>


<%  
    PaymentAccount defaultPaymentAccount = (PaymentAccount) session.getAttribute("DefaultPaymentAccount");
 
%>


<div class="relative w-full h-[30rem] mt-[5.2rem]" >
    <img src="./assets/money.gif" class="w-full h-full" alt="alt"/>
</div>

<div id="content" class="bg-[#f0f1f1] pb-16">
    <div class="mx-56 px-4 pt-10 pb-16 rounded-md bg-[#fff]">
        <div class="grid grid-cols-2 pt-4 pb-10 justify-between">
            <div class="grid">
                <span>Current Payment Account</span>
                <span class="font-bold text-xl"><%= defaultPaymentAccount.getAccountNumber()%></span>
                <span class="font-bold text-xl"></span>
            </div>
            <div class="flex justify-end items-center">
                <div class="grid justify-end">
                    <span>Available Balance</span>
                    <span class="font-bold text-xl"><%= defaultPaymentAccount.getCurrentBalence()%> VND</span>
                </div>
                <div class="grid justify-end ml-4">
                    <form action="show-account" method="get">
                        <button type="submit" class="px-2 py-1 bg-gray-200 rounded-lg">
                            Accounts List <span class="font-bold"><i class="fa-solid fa-chevron-right py-1"></i></span>
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <div class="grid grid-cols-4 gap-8">
            <a
                href="./transfer.jsp"
                class="flex flex-col items-center justify-center rounded-md h-28 bg-[#2a6ebe] text-sm font-bold text-white cursor-pointer
                transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-110 duration-300"
                >
                <img src="./assets/transfer.png" class="w-8 mb-2" />
                Transfer
            </a>
            <form action="create-saving-account" method="get">
                <a
                    class="flex flex-col items-center justify-center rounded-md h-28 bg-[#2a6ebe] text-sm font-bold text-white cursor-pointer
                    transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-110 duration-300"
                    >
                    <img src="./assets/salary.png" class="w-8 mb-2" />
                    <button type="submit">Saving Account</button>
                </a>
            </form>
            <a
                href="./loan.jsp"
                class="flex flex-col items-center justify-center rounded-md h-28 bg-[#038883] text-sm font-bold text-white cursor-pointer
                transition ease-in-out delay-150hover:-translate-y-1 hover:scale-110 duration-300"
                >
                <img src="./assets/loan.png" class="w-8 mb-2" />
                Loan
            </a>
            <a
                href="./reward.jsp"
                class="flex flex-col items-center justify-center rounded-md h-28 bg-[#038883] text-sm font-bold text-white cursor-pointer
                transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-110 duration-300"
                >
                <img src="./assets/reward.png" class="w-8 mb-2" />
                Reward
            </a>
        </div>
    </div>
</div>


<%@ include file="/includes/footer.jsp" %>
