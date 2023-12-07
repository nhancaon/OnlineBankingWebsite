<%@ page import="DAO.SavingAccountDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="business.InterestRate"%>
<%@ page import="business.SavingAccount"%>
<%@ page import="DAO.InterestRateDAO"%>
<%@ page import="DAO.PaymentAccountDAO"%> 
<%@ page import="business.PaymentAccount"%> 
<%@ include file="/includes/header.jsp" %>
<%@ include file="/includes/checkLogin.jsp" %>                  

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
                    <div class="inline-flex items-center text-sm font-medium text-gray-700 hover:text-blue-600">
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
                                Saving Account
                            </button>
                        </form>
                    </div>
                </li>
            </ol>
        </nav>
        <div class="mt-16 mb-36 pt-8 pb-24 px-2 md:px-20 rounded-xl bg-white">
            <div class="flex justify-between items-center">
                <span>Saving Account</span>
                <button
                    id="createAccountBtn"
                    class="px-4 py-2 bg-[#00bfae] rounded-2xl outline-none 
                    focus:ring transform transition hover:scale-105 duration-300 ease-in-out flex text-white" onclick="showCreateAccount()"><img src="assets/plus.svg" src="" class="mr-2"></img>Add Saving Account</button>
            </div>
            <div class="grid grid-cols-1 gap-10 my-8">
                <c:choose>
                    <c:when test="${empty savingAccounts}">
                        <p class="text-center mt-5">No saving accounts found for the specified customer.</p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="savingAccount" items="${savingAccounts}">
                            <a href="saving-detail?accountId=${savingAccount.savingAccountId}" class="flex justify-between p-4 rounded-xl bg-gray-300 
                                focus:ring transform transition hover:scale-105 duration-300 ease-in-out">
                                 <div>
                                     <i class="fa-regular fa-copy mr-2"></i>
                                     ${savingAccount.accountNumber}
                                 </div>

                                 <div>
                                     <span class="text-sm text-gray-600 mr-2">Interest Rate</span>
                                     ${savingAccount.interestRate.interestRate} %
                                 </div>
                                 <i class="fa-solid fa-chevron-right py-1"></i>
                             </a>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<%@ include file="/includes/exception.jsp" %> 
<div
    id="create-account"
    class="create-account hidden fixed top-0 left-0 w-full h-full bg-blur z-[1000] px-2 py-8 md:px-96 md:py-28"
    >
    <div class="col-span-3 my-16 py-8 px-8 md:px-20 rounded-xl bg-white">
        <div class="text-[#2a6ebe] flex justify-between">
            Add Saving Account
            <button
                class="focus:ring transform transition hover:scale-125 duration-300 ease-in-out"
                onclick="closeCreateAccount()"
                >
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>
        <div class="content">
            <form action="create-saving-account" method="get">
                <input type="hidden" name="action" value="create" />
                <div class="relative mt-6" >
                    <select name="acNumber" id="savingAccountNumber" class="block pb-2.5 pt-4 w-full text-sm bg-transparent border-b-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer">
                        <option value="" disabled selected>Select Account Number</option>
                        <c:forEach var="paymentAccount" items="${paymentAccounts}">                           
                            <option value="${paymentAccount.getAccountNumber()}">
                                ${paymentAccount.getAccountNumber()}
                            </option>
                        </c:forEach>
                    </select>
                    <label
                        for="savingAccountNumber"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4"
                        >Saving Account Number</label
                    >
                </div>

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="currentBalance"
                        name="currentBalance"
                        class="block pb-2.5 pt-4 w-full text-sm bg-transparent border-b-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=" "
                        readonly
                        />
                    <label
                        for="currentBalance"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4"
                        >Current Balance</label
                    >
                </div>

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="savingAmount"
                        name="savingAmount"
                        class="block pb-2.5 pt-4 w-full text-sm bg-transparent border-b-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=" "
                        pattern="\d{7,10}"
                        title="Amount must be higher than 1000000."
                        maxlength="10"
                        required
                        />
                    <label
                        for="savingAmount"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4"
                        >Saving amount</label
                    >
                </div>
                <div class="relative mt-6" >
                    <select name="typeOfSaving" id="typeOfSaving" class="block pb-2.5 pt-4 w-full text-sm bg-transparent border-b-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer">
                        <c:forEach var="rate" items="${interestRates}">
                            <option value="${rate.savingTitle}">
                                ${rate.savingTitle} | ${rate.interestRate}% Interest
                            </option>
                        </c:forEach>
                    </select>
                    <label
                        for="typeOfSaving"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4"
                        >Choose Type of Saving</label
                    >
                </div>
                <div class="flex justify-end items-center mt-10">
                    <button
                        class="px-16 py-3 rounded-md bg-gradient-to-r from-[#00bfae] to-[#0066ad] text-white"
                        >
                        Continue
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
    $(document).ready(function () {
    $("#savingAccountNumber").on("input", function () {
        var savingAccountNumber = $(this).val();

        // Check if savingAccountNumber is empty
        if (!savingAccountNumber) {
            $("#currentBalance").val("");
            return;
        }

        $.ajax({
            type: "GET",
            url: "create-saving-account",
            data: { action: "get-savingAccountNumber", acNumber: savingAccountNumber },
            success: function (response) {
                console.log("AJAX Response:", response);

                // Check if the response starts with "CURRENT_BALANCE:"
                if (response.startsWith("CURRENT_BALANCE:")) {
                    // Extract the balance from the response
                    var currentBalance = response.substring(16);

                    // Format the currentBalance to round to 2 decimal places
                    currentBalance = parseFloat(currentBalance).toFixed(2);

                    // Set the currentBalance in the input field
                    $("#currentBalance").val(currentBalance);
                } else {
                    console.log("Unexpected response format");
                }
            },
            error: function (xhr, status, error) {
                console.log("Error communicating with the server. Status: " + status + ", Error: " + error);
            }
        });
    });
});
</script>

<%@ include file="/includes/footer.jsp" %>