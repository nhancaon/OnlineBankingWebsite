<%@page import="DAO.SavingAccountDAO"%> 
<%@page import="business.SavingAccount"%>
<%@ include file="/includes/header.jsp"%> 
<%@ include file="/includes/checkLogin.jsp" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="bg-[#f0f1f1] mt-[5.2rem] pb-16">
    <div class="py-16 mx-56">
        <div class="flex text-2xl">
            <a href=""><i class="fa-solid fa-chevron-left text-xl py-[0.3rem] pr-6"></i></a>
            <div class="py-[0.2rem]">Saving Account</div>
        </div>

        <nav class="flex pt-3 ml-[2.3rem]" aria-label="Breadcrumb">
            <ol class="inline-flex items-center space-x-1 md:space-x-3">
                <li class="inline-flex items-center">
                    <%@ include file="/includes/homeButton.jsp" %>
                </li>
                <li>
                    <div class="h-full flex items-center">
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
                        <a
                            href="./savingAccount.jsp"
                            class="ml-1 text-sm font-medium text-gray-600 md:ml-2 cursor-pointer"
                            >Saving Account</a
                        >
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
                                Saving Account Detail
                            </button>
                        </form>
                    </div>
                </li>
            </ol>
        </nav>

        <div class="grid grid-cols-6 gap-8">
            <div class="col-span-4 my-16 py-8 px-20 rounded-xl bg-white">
                <div>
                    <c:if test="${not empty requestScope.successMessage}">
                        <p style="color: green">${requestScope.successMessage}</p>
                    </c:if>

                    <c:if test="${not empty requestScope.errorMessage}">
                        <p style="color: red">${requestScope.errorMessage}</p>
                    </c:if>
                </div>
                <div class="w-full grid grid-cols-1">
                    <c:if test="${ savingAccount.accountStatus == 'Active' }">
                        <form action="with-draw" method="post">
                            <div class="grid grid-cols-3 border-b-2 py-2 mb-3">
                                <div class="text-gray-500 text-sm">${savingAccount.accountStatus}</div>
                                <div class="col-span-2 text-end text-gray-500 text-sm">
                                    Saving Id: ${savingAccount.savingAccountId}
                                </div>
                                <div class="col-span-2">Account Type : ${savingAccount.interestRate.savingTitle}</div>
                                <div class="text-end text-xl text-green-400"> ${savingAccount.interestRate.interestRate} %</div>
                                <div class="col-span-2">Savings Deposit : </div>
                                <div class="text-end text-xl text-green-400">${savingAccount.savingAmount}VND</div>
                                <div class="col-span-2">Expected Amount Received : </div>
                                <div class="text-end text-xl text-green-400">${savingAccount.savingAmount*(100+savingAccount.interestRate.interestRate)/100}VND</div>
                            </div>

                            <input type="hidden" name="action" value="withdraw">
                                <input type="hidden" name="accountSavingId" value="${savingAccount.savingAccountId}">
                                    <input type="hidden" name="expectedAmount" value="${savingAccount.savingAmount*(100+savingAccount.interestRate.interestRate)/100}">
                                        <div class="flex justify-end items-center mt-10">
                                            <button class="px-16 py-3 rounded-md bg-gradient-to-r from-[#00bfae] to-[#0066ad] text-white">Withdraw Money</button>
                                        </div>
                                        </form>


                                    </c:if>
                                    <c:if test="${ savingAccount.accountStatus != 'Active' }">
                                        <div class="grid grid-cols-3 border-b-2 py-2 mb-3">
                                            <div class="text-gray-500 text-sm">${savingAccount.accountStatus}</div>
                                            <div class="col-span-2 text-end text-gray-500 text-sm">
                                                Saving Id: ${savingAccount.savingAccountId}
                                            </div>
                                            <div class="col-span-2">Account Type : ${savingAccount.interestRate.savingTitle}</div>
                                            <div class="text-end text-xl text-red-400"> ${savingAccount.interestRate.interestRate} %</div>
                                            <div class="col-span-2">Savings Deposit : </div>
                                            <div class="text-end text-xl text-red-400">${savingAccount.savingAmount}VND</div>
                                            <div class="col-span-2">Expected Amount Received : </div>
                                            <div class="text-end text-xl text-red-400">${savingAccount.savingAmount*(100+savingAccount.interestRate.interestRate)/100}VND</div>
                                        </div>
                                    </c:if>
                                    </div>
                                    </div>
                                    <div class="col-span-2 my-16 rounded-xl bg-white">
                                        <div
                                            class="flex justify-between bg-gradient-to-r from-[#3caff2] to-[#2267a8] rounded-t-xl"
                                            >
                                            <div
                                                class="flex flex-col justify-center items-center ml-8 text-white"
                                                >
                                                <span class="uppercase text-sm">${savingAccount.paymentAccount.customer.name}</span>
                                                <span class="uppercase text-sm"
                                                      >${savingAccount.accountNumber}</span
                                                >
                                            </div>
                                            <img src="assets/thanh-toan.svg" src="wallet" class="p-4" />
                                        </div>
                                        <div class="w-full grid grid-cols-1 gap-2 p-8">
                                            <div class="flex justify-between items-center">
                                                <span class="text-gray-500 text-sm">Date Open</span>
                                                <span class="uppercase text-sm">${savingAccount.dateOpened}</span>
                                            </div>
                                            <div class="flex justify-between items-center">
                                                <span class="text-gray-500 text-sm">Date Close</span>
                                                <span class="uppercase text-sm"
                                                      >${savingAccount.dateClosed}</span
                                                >
                                            </div>
                                            <div class="flex justify-between items-center">
                                                <span class="text-gray-500 text-sm">Min Balance</span>
                                                <span class="uppercase text-sm"
                                                      >${savingAccount.minBalance}</span
                                                >
                                            </div>
                                            <div class="flex justify-between items-center">
                                                <span class="text-gray-500 text-sm">Registered Branch</span>
                                                <span class="uppercase text-sm">NND Banking</span>
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                    </div>
                                    </div>
                                    <%@ include file="/includes/footer.jsp" %>
