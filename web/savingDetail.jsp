<%@ page import="DAO.SavingAccountDAO"%> 
<%@ page import="business.SavingAccount"%>
<%@ page import="controller.SavingAccount.CreateAccountServlet"%>
<%@ include file="/includes/header.jsp"%> 
<%@ include file="/includes/checkLogin.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="bg-[#f0f1f1] mt-[5.2rem] pb-16">
    <div class="py-16 mx-2 md:mx-56">
        <div class="flex text-2xl">
            <form action="Profile" method="GET">
                <button><i class="fa-solid fa-chevron-left text-xl py-[0.3rem] pr-6"></i></button>
            </form>
            <div class="py-[0.2rem]">Saving Account</div>
        </div>

        <nav class="flex pt-3 ml-[2.3rem]" aria-label="Breadcrumb">
            <ol class="inline-flex items-center space-x-1 md:space-x-3">
                <li class="inline-flex items-center">
                    <%@ include file="/includes/homeButton.jsp" %>
                </li>

                <li>
                    <form action="create-saving-account" method="get">
                        <button
                            type="submit"
                            class="inline-flex items-center text-sm font-medium text-gray-700 hover:text-blue-600"
                            >
                            <svg
                                class="w-3 h-3 mx-1"
                                aria-hidden="true"
                                xmlns="http://www.w3.org/2000/svg"
                                fill="none"
                                viewBox="0 0 6 10">
                                <path
                                    stroke="currentColor"
                                    stroke-linecap="round"
                                    stroke-linejoin="round"
                                    stroke-width="2"
                                    d="m1 9 4-4-4-4"/>
                            </svg>
                            Saving Account
                        </button>
                    </form>
                </li>

                <li>
                    <div class="flex items-center">
                        <svg
                            class="w-3 h-3 mx-1"
                            aria-hidden="true"
                            xmlns="http://www.w3.org/2000/svg"
                            fill="none"
                            viewBox="0 0 6 10">
                            <path
                                stroke="currentColor"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                stroke-width="2"
                                d="m1 9 4-4-4-4"/>
                        </svg>

                        <form class="cursor-pointer">
                            <button
                                type="text"
                                class="ml-1 text-sm font-medium text-blue-600 md:ml-2 mb-1 pointer-events-none">
                                Saving Account Detail
                            </button>
                        </form>
                    </div>
                </li>
            </ol>
        </nav>

        <div class="flex flex-col-reverse md:grid grid-cols-6 gap-8">
            <div class="col-span-4 md:my-16 py-8 px-2 md:px-20 rounded-xl bg-white">
                <%@ include file="/includes/exception.jsp" %> 
                <div class="w-full grid grid-cols-1">
                    <c:if test="${savingAccount.accountStatus == 'Active'}">
                        <div class="grid grid-cols-2 md:grid-cols-3 border-b-2 py-2 mb-3">
                        <div class="text-gray-500 text-sm">${savingAccount.accountStatus}</div>
                        <div class="md:col-span-2 text-end text-gray-500 text-sm">Saving Id: ${savingAccount.savingAccountId}</div>
                        <div class="md:col-span-2">Account Type : ${savingAccount.interestRate.savingTitle}</div>
                        <div class="text-end text-xl text-green-400">${savingAccount.interestRate.interestRate} %</div>
                        <div class="md:col-span-2">Initial Saving Amount : </div>
                        <div class="text-end text-xl text-green-400"><%= formatCurrency(Double.valueOf(request.getAttribute("initialString").toString()))%> VND</div>
                        <div class="md:col-span-2">Current Saving Amount : </div>
                        <div class="text-end text-xl text-green-400"><%= formatCurrency(Double.valueOf(request.getAttribute("currentSavingAmount").toString()))%> VND</div>
                        <div class="md:col-span-2">Expected Total Received : </div>
                         <div class="text-end text-xl text-green-400"><%= formatCurrency(Double.valueOf(request.getAttribute("expectedAmount").toString()))%> VND</div>
                        <div class="md:col-span-2">Expected Monthly Amount : </div>
                        <div class="text-end text-xl text-green-400"><%= formatCurrency(Double.valueOf(request.getAttribute("monthlyAmount").toString()))%> VND</div>
                    </c:if>
                    <c:if test="${savingAccount.accountStatus != 'Active'}">
                        <div class="grid grid-cols-2 md:grid-cols-3 border-b-2 py-2 mb-3">
                        <div class="text-gray-500 text-sm">${savingAccount.accountStatus}</div>
                        <div class="md:col-span-2 text-end text-gray-500 text-sm">Saving Id: ${savingAccount.savingAccountId}</div>
                        <div class="md:col-span-2">Account Type : ${savingAccount.interestRate.savingTitle}</div>
                        <div class="text-end text-xl text-red-400">${savingAccount.interestRate.interestRate} %</div>
                        <div class="md:col-span-2">Initial Saving Amount : </div>
                        <div class="text-end text-xl text-red-400"><%= formatCurrency(Double.valueOf(request.getAttribute("initialString").toString()))%> VND</div>
                        <div class="md:col-span-2">Current Saving Amount : </div>
                        <div class="text-end text-xl text-red-400"><%= formatCurrency(Double.valueOf(request.getAttribute("currentSavingAmount").toString()))%> VND</div>
                        <div class="md:col-span-2">Expected Total Received : </div>
                         <div class="text-end text-xl text-red-400"><%= formatCurrency(Double.valueOf(request.getAttribute("expectedAmount").toString()))%> VND</div>
                        <div class="md:col-span-2">Expected Monthly Amount : </div>
                        <div class="text-end text-xl text-red-400"><%= formatCurrency(Double.valueOf(request.getAttribute("monthlyAmount").toString()))%> VND</div>
                    </c:if>
                </div>
                <div>
                    <form action="with-draw" method="post">
                        <input type="hidden" name="action" value="withdraw">
                        <input type="hidden" name="accountSavingId" value="${savingAccount.savingAccountId}">
                        <input type="hidden" name="expectedAmount" value="${savingAccount.savingCurrentAmount}">
                        <input type="hidden" name="initialString" value=<%=request.getAttribute("initialString")%>>
                        <input type="hidden" name="currentSavingAmount" value=<%=request.getAttribute("currentSavingAmount")%>>
                        <input type="hidden" name="expectedAmount" value=<%=request.getAttribute("expectedAmount")%>>
                        <input type="hidden" name="monthlyAmount" value=<%=request.getAttribute("monthlyAmount")%>>
                        <div class="flex justify-end items-center mt-10">
                            <button class="px-16 py-3 rounded-md bg-gradient-to-r from-[#00bfae] to-[#0066ad] text-white">Withdraw Money</button>
                        </div>
                    </form>
                    <form action="saving-detail" method="get" >
                        <input type="hidden" name="action">
                        <input type="hidden" name="accountId" value="${savingAccount.savingAccountId}"/>
                        <div class="relative mt-6">
                            <input 
                                type="text"
                                id="checkDate"
                                name="checkDate"
                                onfocus="(this.type = 'date')"
                                onblur="(this.type = 'text')"
                                class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                                placeholder=""/>
                           <label
                                for="checkDate"
                                class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                                >Future Calculation
                            </label>
                        </div>

                        <button type="submit" class="mt-4 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                            Submit
                        </button>
                    </form>
                </div>      
            </div>
        </div>
        <div class="col-span-2 mt-16 md:my-16 rounded-xl bg-white">
            <div class="flex justify-between bg-gradient-to-r from-[#3caff2] to-[#2267a8] rounded-t-xl">
                <div class="flex flex-col justify-center items-center ml-8 text-white">
                    <span class="uppercase text-sm">${savingAccount.paymentAccount.customer.name}</span>
                    <span class="uppercase text-sm">${savingAccount.accountNumber}</span>
                </div>
                <img src="assets/thanh-toan.svg" src="wallet" class="p-4"/>
            </div>
                <div class="w-full grid grid-cols-1 gap-2 p-8">
                    <div class="flex justify-between items-center">
                        <span class="text-gray-500 text-sm">Date Open</span>
                        <span class="uppercase text-sm">${savingAccount.dateOpened}</span>
                    </div>
                <div class="flex justify-between items-center">
                    <span class="text-gray-500 text-sm">Date Close</span>
                    <span class="uppercase text-sm">${savingAccount.dateClosed}</span>
                </div>
                <div class="flex justify-between items-center">
                    <span class="text-gray-500 text-sm">Min Balance</span>
                    <span class="uppercase text-sm">${savingAccount.minBalance} VND</span>
                </div>
                <div class="flex justify-between items-center">
                    <span class="text-gray-500 text-sm">Registered Branch</span>
                    <span class="uppercase text-sm">NND Banking</span>
                </div>
            </div>
        </div>                    
    </div>
</div>         
<%@ include file="/includes/footer.jsp"%>