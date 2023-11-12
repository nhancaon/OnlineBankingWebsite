<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/includes/header.jsp" %>
<%@ include file="/includes/checkLogin.jsp" %>

<div class="bg-[#f0f1f1] mt-[5.2rem] pb-16">
    <div class="py-16 mx-56">
        <div class="flex text-2xl">
            <a href="./index.jsp"><i class="fa-solid fa-chevron-left text-xl py-[0.3rem] pr-6"></i></a>
            <div class="py-[0.2rem]">Account</div>
        </div>

        <nav class="flex pt-3 ml-[2.3rem]" aria-label="Breadcrumb">
            <ol class="inline-flex items-center space-x-1 md:space-x-3">
                <li class="inline-flex items-center">
                    <a
                        href="./profile.jsp"
                        class="inline-flex items-center text-sm font-medium text-gray-700 hover:text-blue-600"
                        >
                        <svg
                            class="w-3 h-3 mr-2.5"
                            aria-hidden="true"
                            xmlns="http://www.w3.org/2000/svg"
                            fill="#000"
                            viewBox="0 0 20 20"
                            >
                            <path
                                d="m19.707 9.293-2-2-7-7a1 1 0 0 0-1.414 0l-7 7-2 2a1 1 0 0 0 1.414 1.414L2 10.414V18a2 2 0 0 0 2 2h3a1 1 0 0 0 1-1v-4a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v4a1 1 0 0 0 1 1h3a2 2 0 0 0 2-2v-7.586l.293.293a1 1 0 0 0 1.414-1.414Z"
                                />
                        </svg>
                        Home
                    </a>
                </li>
                <li>
                    <div class="flex items-center">
                        <svg class="w-3 h-3 mx-1" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 9 4-4-4-4"/>
                        </svg>
                        <a class="ml-1 text-sm font-medium text-blue-600 md:ml-2 cursor-pointer">Account</a>
                    </div>
                </li>
            </ol>
        </nav>

        <div class="my-16 py-8 px-20 rounded-xl bg-white">
            <div class="flex justify-between items-center">
                <span>Payment Accounts</span>
                <button id="createAccountBtn" class="p-4 bg-gradient-to-r from-[#00bfae] to-[#0066ad] rounded-2xl outline-none 
                        focus:ring transform transition hover:scale-105 duration-300 ease-in-out" onclick="showCreateAccount()">Add Payment Account</button>
            </div>
            <div class="grid grid-cols-1 gap-10 my-8">
                <a class="flex justify-between p-4 rounded-xl bg-gray-300 
                   focus:ring transform transition hover:scale-105 duration-300 ease-in-out" href="./accountDetail.jsp">
                    <div>
                        <i class="fa-regular fa-copy mr-2"></i>
                        8890743713
                    </div>
                    <div>
                        <span class="text-sm text-gray-600 mr-2">Available Balance</span>
                        1,100,000 VND
                    </div>
                    <i class="fa-solid fa-chevron-right py-1"></i>
                </a>
                <a class="flex justify-between p-4 rounded-xl bg-gray-300 
                   focus:ring transform transition hover:scale-105 duration-300 ease-in-out" href="./accountDetail.jsp">
                    <div>
                        <i class="fa-regular fa-copy mr-2"></i>
                        8890743713
                    </div>
                    <div>
                        <span class="text-sm text-gray-600 mr-2">Available Balance</span>
                        1,100,000 VND
                    </div>
                    <i class="fa-solid fa-chevron-right py-1"></i>
                </a>
            </div>
        </div>
    </div>
</div>


<div id="create-account" class="create-account hidden fixed top-0 left-0 w-full h-full bg-blur z-[1000] px-96 py-28">
    <div class="col-span-3 my-16 py-8 px-20 rounded-xl bg-white">
        <div class="text-[#2a6ebe] flex justify-between">Add Payment Account
            <button class="focus:ring transform transition hover:scale-125 duration-300 ease-in-out" onclick="closeCreateAccount()">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>
        <div class="content">
           
        </div>
    </div>
</div>


<%@ include file="/includes/footer.jsp" %>
