<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ include file="/includes/header.jsp" %> 
<%@ include file="/includes/checkLogin.jsp" %>

<div class="bg-[#f0f1f1] mt-[5.2rem] pb-16">
    <div class="py-16 mx-2 md:mx-56">
        <div class="flex text-2xl">
            <form action="Profile" method="GET">
                <button><i class="fa-solid fa-chevron-left text-xl py-[0.3rem] pr-6"></i></button>
            </form>
            <div class="py-[0.2rem]">Change Password</div>
        </div>

        <nav class="flex pt-3 ml-[2.3rem]" aria-label="Breadcrumb">
            <ol class="inline-flex items-center space-x-1 md:space-x-3">
                <li class="inline-flex items-center"><%@ include file="/includes/homeButton.jsp" %></li>
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
                                Change Password
                            </button>
                        </form>
                    </div>
                </li>
            </ol>
        </nav>

        <div class="bg-white my-16 px-2 py-16 md:p-16">
            <%@ include file="/includes/exception.jsp" %>
            <div class="grid text-xs gap-2">
                <span class="text-gray-500">Password must satisfy the following conditions:</span>
                <ul class="grid gap-2 list-disc px-4">
                    <li>
                        <span>The length must be from 8 to 20 characters</span>
                    </li>
                    <li>
                        <span>Contains at least 01 digit, 01 letter and 01 special character</span>
                    </li>
                </ul>
                <span>For example: b&123456; B&123456; &B123456</span>
            </div>

            <form class="col-span-3 space-y-4 md:space-y-6" action="ChangePassword" method="post">
                <input type="hidden" name="action" value="changePassword" />
                <div class="relative mt-6">
                    <input
                        type="password"
                        id="currentPassword"
                        name="currentPassword"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=" "
                        required
                        />
                    <label
                        for="currentPassword"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Current Password</label
                    >
                </div>
                <div class="relative mt-6">
                    <input
                        type="password"
                        id="newPassword"
                        name="newPassword"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=" "
                        required
                        />
                    <label
                        for="newPassword"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >New Password</label
                    >
                </div>
                <div class="relative mt-6">
                    <input
                        type="password"
                        id="confirmPassword"
                        name="confirmPassword"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=" "
                        required
                        />
                    <label
                        for="confirmPassword"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Confirm New Password</label
                    >
                </div>
                <div class="flex justify-end">
                    <button
                        id="changePasswordBtn"
                        class="text-white py-4 px-8 md:px-12 bg-gradient-to-r from-[#00bfae] to-[#0066ad] rounded-xl outline-none focus:ring transform transition hover:scale-105 duration-300 ease-in-out"
                        >
                        Change Password
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="/includes/footer.jsp" %>
