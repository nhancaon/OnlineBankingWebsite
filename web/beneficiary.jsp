
<%@ page import="business.Beneficiary"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/includes/header.jsp" %>
<%@ include file="/includes/checkLogin.jsp" %>

<%    
    List<Beneficiary> beneficiaries = (List<Beneficiary>) request.getAttribute("Beneficiaries");
%>
<div class="bg-[#f0f1f1] mt-[5.2rem] pb-16">
    <div class="py-16 mx-2 md:mx-56">
        <div class="flex text-2xl">
            <form action="Profile" method="GET">
                <button><i class="fa-solid fa-chevron-left text-xl py-[0.3rem] pr-6"></i></button>
            </form>
            <div class="py-[0.2rem]">Beneficiary</div>
        </div>

        <nav class="flex pt-3 ml-[2.3rem]" aria-label="Breadcrumb">
            <ol class="inline-flex items-center space-x-1 md:space-x-3">
                <li class="inline-flex items-center">
                    <%@ include file="/includes/homeButton.jsp" %>
                </li>

                <li>
                    <div class="flex items-center">
                        <svg class="w-3 h-3 mx-1" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 9 4-4-4-4"/>
                        </svg>
                        <form class="cursor-pointer">
                            <button
                                type="text"
                                class="ml-1 text-sm font-medium text-blue-600 md:ml-2 mb-1 pointer-events-none"
                                >
                                Beneficiary
                            </button>
                        </form>
                    </div>
                </li>
            </ol>
        </nav>

        <div class="my-16 pt-8 pb-24 px-2 md:px-20 rounded-xl bg-white">
            <div class="flex justify-between items-center">
                <span>Beneficiary</span>
                <button id="createBeneficiary" class="px-4 py-2 bg-[#00bfae] rounded-2xl outline-none 
                        focus:ring transform transition hover:scale-105 duration-300 ease-in-out flex text-white" onclick="showCreateAccount()"><img src="assets/plus.svg" src="" class="mr-2">Add Beneficiary</button>
            </div>
            <%@ include file="/includes/exception.jsp" %>
            <div class="relative overflow-x-auto my-16">
                <%
                    if (beneficiaries != null && !beneficiaries.isEmpty()) {

                %>
                <table class="w-full text-sm text-left rtl:text-right text-gray-500">
                    <thead class="text-xs text-gray-700 uppercase bg-gray-50">
                        <tr>
                            <th scope="col" class="px-6 py-3">
                                Ordinal Number
                            </th>
                            <th scope="col" class="px-6 py-3">
                                Beneficiary Name
                            </th>
                            <th scope="col" class="px-6 py-3">
                                Beneficiary Bank
                            </th>
                            <th scope="col" class="px-6 py-3">
                                Beneficiary Account Number
                            </th>
                            <th scope="col" class="px-6 py-3">
                                <span class="sr-only">Edit</span>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <%                            
                            int index = 1;
                            for (Beneficiary beneficiary : beneficiaries) {
                        %>
                        <tr class="bg-white border-b cursor-pointer hover:bg-gray-200" onclick="MoveToTransfer(this)">
                            <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">
                                <%= index%>
                            </th>
                            <td class="px-6 py-4">
                                <%= beneficiary.getName()%>
                            </td>
                            <td class="px-6 py-4">
                                NND BANKING
                            </td>
                            <td class="AccNumber px-6 py-4">
                                <%= beneficiary.getAccountNumber()%>
                            </td>
                            <td class="px-6 py-4 text-right">
                                <img src="assets/dot.svg" alt="edit" class="w-6 h-6 cursor-pointer focus:ring transform transition hover:scale-105 duration-300 ease-in-out" />
                            </td>        
                        </tr>
                        <%
                                index++;
                            }
                        %>
                    </tbody>
                </table>
                <%                    
                    } else {
                %>
                <div class="text-xl mt-4 text-center">
                    You have not add any contacts yet!
                </div>
                <%      
                    }
                %>
            </div>
        </div>
    </div>
</div>

<div
    id="create-account"
    class="create-account hidden fixed top-0 left-0 w-full h-full bg-blur z-[1000] px-8 py-16 md:px-96 md:py-28"
    >
    <div class="col-span-3 my-16 p-8 md:px-20 rounded-xl bg-white">
        <div class="text-[#2a6ebe] flex justify-between">
            Add New Beneficiary
            <button
                class="focus:ring transform transition hover:scale-125 duration-300 ease-in-out"
                onclick="closeCreateAccount()"
                >
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>
        <div class="content">
            <form action="CreateBeneficiary" method="post">
                <input type="hidden" name="action" value="create" />
                <div class="relative mt-6" >
                    <div id="transferType" class="block pb-2.5 pt-4 w-full text-sm bg-transparent border-b-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer">
                        NND Banking Internal Transfer
                    </div>
                    <label
                        for="transferType"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4"
                        >Money transfer type</label
                    >
                </div>
                <div class="relative mt-6">
                    <input
                        type="text"
                        id="accountNumber"
                        name="accountNumber"
                        class="block pb-2.5 pt-4 w-full text-sm bg-transparent border-b-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=" "
                        pattern="\d{10}"
                        title="Please enter a 10-digit number."
                        maxlength="10"
                        required
                        />
                    <label
                        for="accountNumber"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4"
                        >Account Number (10 digits)</label
                    >
                </div>
                <div class="relative mt-6">
                    <input
                        type="text"
                        id="nickName"
                        name="nickName"
                        class="block pb-2.5 pt-4 w-full text-sm bg-transparent border-b-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=" "
                        required
                        />
                    <label
                        for="nickName"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4"
                        >Nick name</label
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
<form id="moveToTransfer" action="Transfer" method="get" class="absolute top-44 right-14 md:right-24 z-[1000]">
    <input type="hidden" name="action" value="show-name" />
    <input type="hidden" name="getNumber" id="getNumber" />
</form>                        
<script>
    function MoveToTransfer(row) {
        const numberValue = document.getElementsByClassName('AccNumber')[Array.from(row.parentNode.children).indexOf(row)].innerText;
        document.getElementById("getNumber").value = numberValue;
        document.getElementById('moveToTransfer').submit();
    }
</script>
<%@ include file="/includes/footer.jsp" %>
