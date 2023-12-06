<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ include file="/includes/header.jsp" %> 
<%@ include file="/includes/checkLogin.jsp" %>
<%@ include file="/includes/exception.jsp" %>
<div class="bg-[#f0f1f1] mt-[5.2rem] pb-16">
    <div class="py-16 mx-2 md:mx-56">
        <div class="flex text-2xl">
            <form action="Profile" method="GET">
                <button><i class="fa-solid fa-chevron-left text-xl py-[0.3rem] pr-6"></i></button>
            </form>
            <div class="py-[0.2rem]">Success</div>
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
                            class="ml-1 text-sm font-medium text-gray-700 md:ml-2 cursor-pointer hover:text-blue-600"
                            href="./transfer.jsp"
                            >Transfer</a
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
                            class="ml-1 text-sm font-medium text-gray-700 md:ml-2 cursor-pointer hover:text-blue-600"
                            href="./confirm.jsp"
                            >Confirm</a
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
                                Success
                            </button>
                        </form>
                    </div>
                </li>
            </ol>
        </nav>

        <div
            class="grid text-center my-24 py-24 px-8 md:px-20 rounded-xl bg-white"
            >
            <span class="text-[#2a6ebe] text-2xl mb-6">Transaction Success</span>
            <div class="md:px-24">
                You have successfully transferred <span class="text-[#2a6ebe]"><fmt:formatNumber value="${Amount}" pattern="#,##0" /> VND</span> to account
                <span class="text-[#2a6ebe]"> ${receiver.getAccountNumber()}/${Name}</span> at <br/> ${timeStr}. Remark: ${Remark}
            </div>

            <div class="flex justify-center items-center mt-14">
                <form action="Transfer" method="get">
                    <input type="hidden" name="action" value="reload" />
                        <button
                            class="md:px-28 px-8 py-3 rounded-md bg-gradient-to-r from-[#00bfae] to-[#0066ad] text-white"
                            >Initiate new transaction
                        </button>
                                       
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="/includes/footer.jsp" %>
