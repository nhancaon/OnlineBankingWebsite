<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/includes/header.jsp" %>

<div class="bg-[#f0f1f1] mt-[5.2rem] pb-16">
    <div class="py-16 mx-56">
        <div class="flex text-2xl">
            <a href="./index.jsp"><i class="fa-solid fa-chevron-left text-xl py-[0.3rem] pr-6"></i></a>
            <div class="py-[0.2rem]">Transfer</div>
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
                        <a class="ml-1 text-sm font-medium text-blue-600 md:ml-2 cursor-pointer">Transfer</a>
                    </div>
                </li>
            </ol>
        </nav>
        
        <div class="grid grid-cols-5 gap-8">
            <div class="col-span-3 my-16 py-8 px-20 rounded-xl bg-white">
                <span class="text-[#2a6ebe]">Internal Transfer</span>
                <p class="text-[#2a6ebe]"><i>${message}</i></p>
                <form action="Transfer" method="post">
                    <input type="hidden" name="action" value="add">
                    <div class="relative mt-6">
                        <input
                            type="text"
                            id="accountNumber"
                            name="acNumber"
                            class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                            placeholder=" "
                            />
                        <label
                            for="accountNumber"
                            class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                            >Account Number</label
                        >
                    </div>
                    <div class="relative mt-8">
                        <input
                            type="text"
                            id="name"
                            name="acName"
                            class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                            placeholder=" "
                            />

                        <label
                            for="name"
                            class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                            >Account Name</label
                        >
                    </div>
                    <div class="relative mt-8">
                        <input
                            type="text"
                            id="amount"
                            name="acAmount"
                            class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                            placeholder=" "
                            />
                        <label
                            for="amount"
                            class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                            >Amount</label
                        >
                    </div>
                    <div class="relative mt-8">
                        <input
                            type="text"
                            id="transactionRemark"
                            name="transRemark"
                            class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                            placeholder=" "
                            />
                        <label
                            for="transactionRemark"
                            class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                            >Transaction Remark</label
                        >
                    </div>
                    <div class="flex justify-end items-center mt-10">
                        <button class="px-16 py-3 rounded-md bg-gradient-to-r from-[#00bfae] to-[#0066ad] text-white">Continue</button>
                    </div>
                </form>
            </div>
            <div class="col-span-2 my-16 p-8 rounded-xl bg-white">
                <div class="w-full text-sm font-medium text-center text-gray-500 border-b border-gray-200 dark:text-gray-400 dark:border-gray-700">
                    <ul class="grid grid-cols-3">
                        <li class="">
                            <a href="#" class="inline-block p-4 border-b-2 border-transparent rounded-t-lg hover:text-gray-600 hover:border-gray-300 ">Contacts</a>
                        </li>
                        <li class="">
                            <a href="#" class="inline-block p-4 text-blue-600 border-b-2 border-blue-600 rounded-t-lg active" aria-current="page">Recent</a>
                        </li>
                        <li class="">
                            <a href="#" class="inline-block p-4 border-b-2 border-transparent rounded-t-lg hover:text-gray-600 hover:border-gray-300 dark:hover:text-gray-300">Saved</a>
                        </li>          
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/includes/footer.jsp" %>
