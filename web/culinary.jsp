<%@page import="business.PaymentAccount"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ include file="/includes/header.jsp"%> 
<%@ include file="/includes/checkLogin.jsp" %> 
<% PaymentAccount defaultPaymentAccount = (PaymentAccount) session.getAttribute("defaultPaymentAccount");%>
<div class="bg-[#f0f1f1] mt-[5.2rem] pb-16">
    <div class="py-16 mx-2 md:mx-56">
        <div class="flex text-2xl">
            <form action="Profile" method="GET">
                <button><i class="fa-solid fa-chevron-left text-xl py-[0.3rem] pr-6"></i></button>
            </form>
            <div class="py-[0.2rem]">Culinary</div>
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
                        <form action="Rewards" method="get">
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
                                Culinary
                            </button>
                        </form>
                    </div>
                </li>
            </ol>
        </nav>

        <%@ include file="/includes/exception.jsp" %>
        <div class="">
            <div class="grid my-16 py-8 md:px-20 rounded-xl bg-white">
                <div
                    class="py-3 sm:py-0 flex justify-between bg-gradient-to-r from-[#3caff2] to-[#2267a8] rounded-xl"
                    >
                    <div
                        class="flex flex-col justify-center items-center space-y-2 ml-8 text-white"
                        >
                        <span class="uppercase text-sm"><%= customer.getName()%></span>
                        <span class="uppercase text-sm"
                              >${defaultPaymentAccount.getAccountNumber()}</span
                        >
                    </div>
                    <div
                        class="flex flex-col justify-center items-center space-y-2 ml-8 text-white"
                        >
                        <span class="uppercase text-sm"
                              >Available Balance: <%=formatCurrency(defaultPaymentAccount.getCurrentBalence())%>
                            VND</span
                        >
                        <span class="uppercase text-sm"
                              >Reward Point: ${defaultPaymentAccount.getRewardPoint()} RWP</span
                        >
                    </div>
                    <div class="grid justify-end px-4 py-8 ml-16 sm:ml-4">
                        <form action="Rewards" method="get">
                            <input type="hidden" name="action" value="my-rewards" />
                            <input type="hidden" name="accountNumber" value="${defaultPaymentAccount.getAccountNumber()}" />
                            <button type="submit" class="px-2 py-1 bg-gray-200 rounded-lg focus:ring transform transition hover:scale-105 duration-300 ease-in-out">
                                My reward<span class="font-bold ml-2"><i
                                        class="fa-solid fa-chevron-right py-1"></i></span>
                            </button>
                        </form>
                    </div>
                </div>
            </div>

            <div class="grid mt-16 rounded-xl bg-white">
                <div class="md:px-10 px-6 py-6">Culinary</div>
                <div class="md:p-10 p-6 bg-gray-200 grid md:grid-cols-3 gap-8">
                    <c:forEach var="culinaryReward" items="${culinaryRewards}">
                        <div
                            class="flex flex-col rounded-md bg-white focus:ring transform transition hover:scale-105 duration-300 ease-in-out"
                            >
                            <div class="h-[300px]">
                                <img src="assets/rewards/${culinaryReward.rewardId}.jpg" class="rounded-md h-full w-full" />
                            </div>
                            <div class="grid grid-cols-2 p-4 rounded-md shadow-md flex-grow">
                                <div class="grid grid-rows-3 col-span-2 h-[96px]">
                                    <div class="row-span-2 grid grid-cols-1 text-xl font-extrabold">
                                        <span>${culinaryReward.getRewardName()}</span>                   
                                    </div>                                  
                                </div>
                                <div class="flex flex-row mt-3">
                                    <div class="flex -space-x-2 rtl:space-x-reverse">
                                        <img
                                            class="w-6 h-6 border-2 rounded-full border-gray-600"
                                            src="assets/reward-ic.svg"
                                            alt=""
                                            />
                                        <img
                                            class="w-6 h-6 border-2 rounded-full border-gray-600"
                                            src="assets/miles-ic.svg"
                                            alt=""
                                            />
                                        <img
                                            class="w-6 h-6 border-2 rounded-full border-gray-800"
                                            src="assets/cashback-ic.svg"
                                            alt=""
                                            />
                                    </div>
                                    <span class="ml-2 text-md text-blue-600"
                                          >${culinaryReward.getCostPoint()} RWP</span
                                    >
                                </div>
                                <form action="Rewards" method="post" class="flex items-end justify-end">
                                    <input type="hidden" name="rewardId" value="${culinaryReward.getRewardId()}" />                                
                                    <input type="hidden" name="currentPage" value="reward" />
                                    <button
                                        class="px-4 py-2 rounded-md bg-gradient-to-r from-[#00bfae] to-[#0066ad] mt-3 text-sm text-white"
                                        >
                                        Redeem
                                    </button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/includes/footer.jsp" %>
