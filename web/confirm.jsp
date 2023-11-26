<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ page
    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@ include
        file="/includes/header.jsp" %>

        <div class="bg-[#f0f1f1] mt-[5.2rem] pb-16">
            <div class="py-16 mx-56">
                <div class="flex text-2xl">
                    <a href=""><i class="fa-solid fa-chevron-left text-xl py-[0.3rem] pr-6"></i></a>
                    <div class="py-[0.2rem]">Transfer</div>
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
                                <a href="./transfer.jsp" class="ml-1 text-sm font-medium hover:text-blue-600 md:ml-2">Transfer</a>
                            </div>
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
                                        Confirm
                                    </button>
                                </form>
                            </div>
                        </li>
                    </ol>
                </nav>

                <div class="grid gap-8">
                    <div class="my-16 py-8 px-20 rounded-xl bg-white">
                        <div class="text-[#2a6ebe] text-center mb-12">Internal Transfer Confirmation</div>
                        <div class="grid gap-4 mx-40">
                            <div class="flex justify-between">
                                <label>Account sender:</label>
                                <span class="text-[#2a6ebe]">${sender.accountNumber}</span
                                >
                            </div>
                            <div class="flex justify-between">
                                <label>Account receiver:</label>
                                <span class="text-[#2a6ebe]">${receiver.accountNumber}</span>
                            </div>
                            <div class="flex justify-between">
                                <label>Receiver Name:</label>
                                <span class="text-[#2a6ebe]">${Name}</span>
                            </div>
                            <div class="flex justify-between">
                                <label>Receiver Bank:</label>
                                <span class="text-[#2a6ebe]">NND Bank</span>
                            </div>
                            <div class="flex justify-between">
                                <label>Amount:</label>
                                <span class="text-[#2a6ebe]">${Amount} VND</span>
                            </div>
                            <div class="flex justify-between">
                                <label>Transaction Date:</label>
                                <span class="text-[#2a6ebe]">${timeStr}</span>
                            </div>
                            <div class="flex justify-between">
                                <label>Transaction Remark: </label>
                                <span class="text-[#2a6ebe]">${Remark}</span>
                            </div>
                        </div>

                        <form action="Transfer" method="post">
                            <input type="hidden" name="action" value="confirm" />
                            <div class="flex justify-center items-center mt-14">
                                <button
                                    class="px-20 py-3 rounded-md bg-gradient-to-r from-[#00bfae] to-[#0066ad] text-white"
                                    >
                                    Confirm
                                </button>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>

        <%@ include file="/includes/footer.jsp" %>
