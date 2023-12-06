<%@ page import="DAO.LoanLendingDAO"%> 
<%@ page import="business.LoanLending"%>
<%@ include file="/includes/header.jsp"%> 
<%@ include file="/includes/checkLogin.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="bg-[#f0f1f1] mt-[5.2rem] pb-16">
    <div class="py-16 mx-2 md:mx-56">
        <div class="flex text-2xl">
            <form action="Profile" method="GET">
                <button><i class="fa-solid fa-chevron-left text-xl py-[0.3rem] pr-6"></i></button>
            </form>
            <div class="py-[0.2rem]">Loan Lending</div>
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
                        <a
                            href="./loanLending.jsp"
                            class="ml-1 text-sm font-medium text-gray-600 md:ml-2 cursor-pointer"
                            >Loan Lending</a
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
                        <a
                            class="ml-1 text-sm font-medium text-blue-600 md:ml-2 cursor-pointer"
                            >Loan Detail</a
                        >
                    </div>
                </li>
            </ol>
        </nav>

        <%@ include file="/includes/exception.jsp" %>

        <div class="flex flex-col-reverse md:grid grid-cols-6 gap-8">
            <div class="col-span-4 md:my-16 py-8 px-8 md:px-20 rounded-xl bg-white">
                <div class="w-full grid grid-cols-1">
                    <c:if test="${ loanLending.accountStatus == 'In progress' }">
                        <div class="grid grid-cols-2 md:grid-cols-3 border-b-2 py-2 mb-3">
                            <div class="text-gray-500 text-sm">
                                ${loanLending.accountStatus}
                            </div>
                            <div class="md:col-span-2 text-end text-gray-500 text-sm">
                                Loan Id: ${loanLending.loanLendingId}
                            </div>
                            <div class="md:col-span-2">
                                Loan Type : ${loanLending.interestRate.loanTitle}
                            </div>
                            <div class="text-end text-xl text-green-400">
                                ${loanLending.interestRate.interestRate} %
                            </div>
                            <div class="md:col-span-2">Monthly Liability :</div>
                            <div class="text-end text-xl text-green-400">
                                ${loanLending.monthlyPay} VND
                            </div>
                            <div class="md:col-span-2">Total Liability :</div>
                            <div class="text-end text-xl text-green-400">
                                ${loanLending.totalLoanAmount} VND
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="col-span-2 mt-16 md:my-16 rounded-xl bg-white">
                <div
                    class="flex justify-between bg-gradient-to-r from-[#3caff2] to-[#2267a8] rounded-t-xl"
                    >
                    <div
                        class="flex flex-col justify-center items-center ml-8 text-white"
                        >
                        <span class="uppercase text-sm">${loanLending.customer.name}</span>
                        <span class="uppercase text-sm">${loanLending.accountNumber}</span>
                    </div>
                    <img src="assets/thanh-toan.svg" src="wallet" class="p-4" />
                </div>
                <div class="w-full grid grid-cols-1 gap-2 p-8">
                    <div class="flex justify-between items-center">
                        <span class="text-gray-500 text-sm">Date Open</span>
                        <span class="uppercase text-sm">${loanLending.dateOpened}</span>
                    </div>
                    <div class="flex justify-between items-center">
                        <span class="text-gray-500 text-sm">Date Close</span>
                        <span class="uppercase text-sm">${loanLending.dateClosed}</span>
                    </div>
                    <div class="flex justify-between items-center">
                        <span class="text-gray-500 text-sm">Principal Loan</span>
                        <span class="uppercase text-sm">${loanLending.loanAmount} VND</span>
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
