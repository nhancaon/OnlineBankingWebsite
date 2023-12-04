<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="business.Transaction"%> 
<%@page import="java.util.List"%>
<%@page import="DAO.PaymentAccountDAO"%> 
<%@page import="business.PaymentAccount"%> 
<%@ include file="/includes/header.jsp"%> 
<%@ include file="/includes/checkLogin.jsp" %> 


<div class="bg-[#f0f1f1] mt-[5.2rem] pb-16">
    <div class="py-16 mx-2 md:mx-56">

        <div class="flex text-2xl">
            <form action="Profile" method="GET">
                <button><i class="fa-solid fa-chevron-left text-xl py-[0.3rem] pr-6"></i></button>
            </form>
            <div class="py-[0.2rem]">Account</div>
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
                                My Rewards
                            </button>
                        </form>
                    </div>
                </li>
            </ol>
        </nav>

        <%@ include file="/includes/exception.jsp" %>


        <c:choose>
            <c:when test="${empty rewardsOfAccount}">
                <div class="grid justify-center items-center mt-16 mb-32 py-40 md:px-20 rounded-xl bg-white">
                    <div>This Account has not redeemed any reward yet.</div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="grid my-16 py-8 md:px-20 rounded-xl bg-white">
                    <c:forEach var="reward" items="${rewardsOfAccount}">
                        <div class="grid grid-cols-11 p-8 rounded-xl my-2 shadow-md bg-gray-50 items-center">
                            <div class="h-16 w-16">
                                <img src="assets/rewards/${reward.rewardId}.jpg" class="rounded-md h-full w-full" />
                            </div>
                            <div class="col-span-10 grid grid-cols-5">
                                <div class="col-span-3 text-xl">
                                    <span>${reward.getRewardName()}</span>
                                </div>
                                <div class="flex justify-end text-lg col-span-2">
                                    Reward Code: <span class="text-blue-600 ml-1">${reward.getRewardId()}</span>
                                </div >
                                <div class="col-span-3 text-md text-blue-400">
                                    <span>${reward.getRewardType()}</span>
                                </div>
                                <div class="flex justify-end text-md text-blue-600 col-span-2"> 
                                    <span>Cost Point: ${reward.getCostPoint()} RWP</span>
                                </div>

                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
</div>


<%@ include file="/includes/footer.jsp" %>

