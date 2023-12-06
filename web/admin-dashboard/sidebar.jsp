<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String root = request.getContextPath();%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%

    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

    if (session.getAttribute("email") == null) {
        response.sendRedirect("../login.jsp");
    }
%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="./styles/main.css" />
        <script src="./styles/tailwindcss.js"></script>
        <link rel="icon" href="./assets/banklogo.png" />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
            />
        <link
            href="https://fonts.googleapis.com/css2?family=Merriweather+Sans:wght@400;500;600;700&family=Ubuntu:wght@400;500;700&display=swap"
            rel="stylesheet"
            />
        <script>
            tailwind.config = {
                theme: {
                    extend: {
                        colors: {
                            primary: "#9333ea",
                            backdrop: "#f0f1f1",
                            blueSky: "#2a6ebe",
                            greenBtn: "#038883",
                            blur: "rgba(0, 0, 0, 0.5)",
                        },
                        animation: {
                            bounceOne: "0.5s ease-in-out",
                            appear: "appear 0.5s ease-in-out",
                            appearRight: "appearRight ease-in-out .3s",
                            notification:
                                    "notification ease-in-out .3s, fadeOut 3s .3s ease-in-out forwards",
                        },
                        keyframes: {
                            bounceOne: {
                                "0%": {transform: "translateY(0)"},
                                "100%": {transform: "translateY(-20px)"},
                            },
                            appear: {
                                "0%": {opacity: "0", transform: "translateY(20%)"},
                                "100%": {opacity: "1", transform: "translateY(0)"},
                            },
                            appearRight: {
                                "0%": {opacity: "0", transform: "translateX(20%)"},
                                "100%": {opacity: "1", transform: "translateX(0)"},
                            },
                            notification: {
                                "0%": {opacity: "0", transform: "translateX(20%)"},
                                "100%": {opacity: "1", transform: "translateX(0)"},
                            },
                            fadeOut: {"0%": {opacity: "1"}, "100%": {opacity: "0"}},
                        },
                    },
                },
            };
        </script>
        <script src="./styles/script.js"></script>
        <title>Banking</title>
    </head>
    <body>
        <nav
            class="fixed top-0 z-50 w-full bg-white border-b border-gray-200"
            >
            <div class="px-3 py-3 lg:px-5 lg:pl-3">
                <div class="flex items-center justify-between">
                    <div class="flex items-center justify-start rtl:justify-end">
                        <button
                            data-drawer-target="logo-sidebar"
                            data-drawer-toggle="logo-sidebar"
                            aria-controls="logo-sidebar"
                            type="button"
                            class="inline-flex items-center p-2 text-sm text-gray-500 rounded-lg sm:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200"
                            >
                            <span class="sr-only">Open sidebar</span>
                            <svg
                                class="w-6 h-6"
                                aria-hidden="true"
                                fill="currentColor"
                                viewBox="0 0 20 20"
                                xmlns="http://www.w3.org/2000/svg"
                                >
                            <path
                                clip-rule="evenodd"
                                fill-rule="evenodd"
                                d="M2 4.75A.75.75 0 012.75 4h14.5a.75.75 0 010 1.5H2.75A.75.75 0 012 4.75zm0 10.5a.75.75 0 01.75-.75h7.5a.75.75 0 010 1.5h-7.5a.75.75 0 01-.75-.75zM2 10a.75.75 0 01.75-.75h14.5a.75.75 0 010 1.5H2.75A.75.75 0 012 10z"
                                ></path>
                            </svg>
                        </button>
                        <a href="" class="flex ms-2 md:me-24">
                            <img
                                src="./assets/banklogo.png"
                                class="h-12 me-3"
                                alt="Logo"
                                />
                        </a>
                    </div>
                    <form action="Logout"> 
                        <div class="focus:ring transform transition hover:scale-105 duration-300 ease-in-out">
                            <input type="hidden" name="action" value="logout">  
                            <i class="fa-solid fa-arrow-right-from-bracket mr-3 text-red-800"></i>
                            <input type="submit" value="Log out"/>
                        </div>
                    </form>
                </div>
            </div>
        </nav>
        <%@ include file="/includes/exception.jsp" %>
        <aside
            id="logo-sidebar"
            class="fixed top-0 left-0 z-40 w-64 h-screen pt-20 transition-transform -translate-x-full bg-white border-r border-gray-200 sm:translate-x-0"
            aria-label="Sidebar"
            >
            <div class="h-full px-3 pb-4 overflow-y-auto">
                <ul class="space-y-2 font-medium">
                    <li class="focus:bg-gray-200">
                        <form action="employee" method="GET">
                            <input type="hidden" name="action" value="show-employee">
                            <button
                                type="submit"
                                class="flex items-center p-2 rounded-lg hover:bg-gray-200 group"
                                >
                                <svg xmlns="http://www.w3.org/2000/svg" height="16" width="20" viewBox="0 0 448 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2023 Fonticons, Inc.--><path d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z"/></svg>
                                <span class="ms-3">Employee</span>
                            </button>
                        </form>
                    </li>

                    <li>
                        <form action="customer" method="GET">
                            <input type="hidden" name="action" value="show-customer">
                            <button
                                type="submit"
                                class="flex items-center p-2 rounded-lg hover:bg-gray-200 group"
                                >
                                <svg xmlns="http://www.w3.org/2000/svg" height="16" width="20" viewBox="0 0 640 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2023 Fonticons, Inc.--><path d="M144 0a80 80 0 1 1 0 160A80 80 0 1 1 144 0zM512 0a80 80 0 1 1 0 160A80 80 0 1 1 512 0zM0 298.7C0 239.8 47.8 192 106.7 192h42.7c15.9 0 31 3.5 44.6 9.7c-1.3 7.2-1.9 14.7-1.9 22.3c0 38.2 16.8 72.5 43.3 96c-.2 0-.4 0-.7 0H21.3C9.6 320 0 310.4 0 298.7zM405.3 320c-.2 0-.4 0-.7 0c26.6-23.5 43.3-57.8 43.3-96c0-7.6-.7-15-1.9-22.3c13.6-6.3 28.7-9.7 44.6-9.7h42.7C592.2 192 640 239.8 640 298.7c0 11.8-9.6 21.3-21.3 21.3H405.3zM224 224a96 96 0 1 1 192 0 96 96 0 1 1 -192 0zM128 485.3C128 411.7 187.7 352 261.3 352H378.7C452.3 352 512 411.7 512 485.3c0 14.7-11.9 26.7-26.7 26.7H154.7c-14.7 0-26.7-11.9-26.7-26.7z"/></svg>
                                <span class="ms-3">Customer</span>
                            </button>
                        </form>
                    </li>

                    <li>
                        <form action="paymentAccount" method="GET">
                            <input type="hidden" name="action" value="show-paymentAccount">
                            <button
                                type="submit"
                                class="flex items-center p-2 rounded-lg hover:bg-gray-200 group"
                                >
                                <svg xmlns="http://www.w3.org/2000/svg" height="16" width="20" viewBox="0 0 576 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2023 Fonticons, Inc.--><path d="M64 32C28.7 32 0 60.7 0 96v32H576V96c0-35.3-28.7-64-64-64H64zM576 224H0V416c0 35.3 28.7 64 64 64H512c35.3 0 64-28.7 64-64V224zM112 352h64c8.8 0 16 7.2 16 16s-7.2 16-16 16H112c-8.8 0-16-7.2-16-16s7.2-16 16-16zm112 16c0-8.8 7.2-16 16-16H368c8.8 0 16 7.2 16 16s-7.2 16-16 16H240c-8.8 0-16-7.2-16-16z"/></svg>
                                <span class="ms-3">Payment Account</span>
                            </button>
                        </form>
                    </li>

                    <li>
                        <form action="savingAccount" method="GET">
                            <input type="hidden" name="action" value="show-savingAccount">
                            <button
                                type="submit"
                                class="flex items-center p-2 rounded-lg hover:bg-gray-200 group"
                                >
                                <svg xmlns="http://www.w3.org/2000/svg" height="16" width="20" viewBox="0 0 576 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2023 Fonticons, Inc.--><path d="M400 96l0 .7c-5.3-.4-10.6-.7-16-.7H256c-16.5 0-32.5 2.1-47.8 6c-.1-2-.2-4-.2-6c0-53 43-96 96-96s96 43 96 96zm-16 32c3.5 0 7 .1 10.4 .3c4.2 .3 8.4 .7 12.6 1.3C424.6 109.1 450.8 96 480 96h11.5c10.4 0 18 9.8 15.5 19.9l-13.8 55.2c15.8 14.8 28.7 32.8 37.5 52.9H544c17.7 0 32 14.3 32 32v96c0 17.7-14.3 32-32 32H512c-9.1 12.1-19.9 22.9-32 32v64c0 17.7-14.3 32-32 32H416c-17.7 0-32-14.3-32-32V448H256v32c0 17.7-14.3 32-32 32H192c-17.7 0-32-14.3-32-32V416c-34.9-26.2-58.7-66.3-63.2-112H68c-37.6 0-68-30.4-68-68s30.4-68 68-68h4c13.3 0 24 10.7 24 24s-10.7 24-24 24H68c-11 0-20 9-20 20s9 20 20 20H99.2c12.1-59.8 57.7-107.5 116.3-122.8c12.9-3.4 26.5-5.2 40.5-5.2H384zm64 136a24 24 0 1 0 -48 0 24 24 0 1 0 48 0z"/></svg>
                                <span class="ms-3">Saving Account</span>
                            </button>
                        </form>
                    </li>

                    <li>
                        <form action="loan" method="GET">
                            <input type="hidden" name="action" value="show-loan">
                            <button
                                type="submit"
                                class="flex items-center p-2 rounded-lg hover:bg-gray-200 group"
                                >
                                <svg xmlns="http://www.w3.org/2000/svg" height="16" width="20" viewBox="0 0 512 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2023 Fonticons, Inc.--><path d="M240.1 4.2c9.8-5.6 21.9-5.6 31.8 0l171.8 98.1L448 104l0 .9 47.9 27.4c12.6 7.2 18.8 22 15.1 36s-16.4 23.8-30.9 23.8H32c-14.5 0-27.2-9.8-30.9-23.8s2.5-28.8 15.1-36L64 104.9V104l4.4-1.6L240.1 4.2zM64 224h64V416h40V224h64V416h48V224h64V416h40V224h64V420.3c.6 .3 1.2 .7 1.8 1.1l48 32c11.7 7.8 17 22.4 12.9 35.9S494.1 512 480 512H32c-14.1 0-26.5-9.2-30.6-22.7s1.1-28.1 12.9-35.9l48-32c.6-.4 1.2-.7 1.8-1.1V224z"/></svg>
                                <span class="ms-3">Loan Lending</span>
                            </button>
                        </form>
                    </li>

                    <li>
                        <form action="interestRate" method="GET">
                            <input type="hidden" name="action" value="show-interestRate">
                            <button
                                type="submit"
                                class="flex items-center p-2 rounded-lg hover:bg-gray-200 group"
                                >
                                <svg xmlns="http://www.w3.org/2000/svg" height="16" width="20" viewBox="0 0 640 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2023 Fonticons, Inc.--><path d="M256 64H384v64H256V64zM240 0c-26.5 0-48 21.5-48 48v96c0 26.5 21.5 48 48 48h48v32H32c-17.7 0-32 14.3-32 32s14.3 32 32 32h96v32H80c-26.5 0-48 21.5-48 48v96c0 26.5 21.5 48 48 48H240c26.5 0 48-21.5 48-48V368c0-26.5-21.5-48-48-48H192V288H448v32H400c-26.5 0-48 21.5-48 48v96c0 26.5 21.5 48 48 48H560c26.5 0 48-21.5 48-48V368c0-26.5-21.5-48-48-48H512V288h96c17.7 0 32-14.3 32-32s-14.3-32-32-32H352V192h48c26.5 0 48-21.5 48-48V48c0-26.5-21.5-48-48-48H240zM96 448V384H224v64H96zm320-64H544v64H416V384z"/></svg>
                                <span class="ms-3">Interest Rate</span>
                            </button>
                        </form>
                    </li>

                    <li>
                        <form action="reward" method="GET">
                            <input type="hidden" name="action" value="show-reward">
                            <button
                                type="submit"
                                class="flex items-center p-2 rounded-lg hover:bg-gray-200 group"
                                >
                                <svg xmlns="http://www.w3.org/2000/svg" height="16" width="20" viewBox="0 0 512 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2023 Fonticons, Inc.--><path d="M190.5 68.8L225.3 128H224 152c-22.1 0-40-17.9-40-40s17.9-40 40-40h2.2c14.9 0 28.8 7.9 36.3 20.8zM64 88c0 14.4 3.5 28 9.6 40H32c-17.7 0-32 14.3-32 32v64c0 17.7 14.3 32 32 32H480c17.7 0 32-14.3 32-32V160c0-17.7-14.3-32-32-32H438.4c6.1-12 9.6-25.6 9.6-40c0-48.6-39.4-88-88-88h-2.2c-31.9 0-61.5 16.9-77.7 44.4L256 85.5l-24.1-41C215.7 16.9 186.1 0 154.2 0H152C103.4 0 64 39.4 64 88zm336 0c0 22.1-17.9 40-40 40H288h-1.3l34.8-59.2C329.1 55.9 342.9 48 357.8 48H360c22.1 0 40 17.9 40 40zM32 288V464c0 26.5 21.5 48 48 48H224V288H32zM288 512H432c26.5 0 48-21.5 48-48V288H288V512z"/></svg>
                                <span class="ms-3">Reward</span>
                            </button>
                        </form>
                    </li>

                    <li>
                        <form action="transaction" method="GET">
                            <input type="hidden" name="action" value="show-transaction">
                            <button
                                type="submit"
                                class="flex items-center p-2 rounded-lg hover:bg-gray-200 group"
                                >
                                <svg xmlns="http://www.w3.org/2000/svg" height="16" width="20" viewBox="0 0 640 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2023 Fonticons, Inc.--><path d="M535 41c-9.4-9.4-9.4-24.6 0-33.9s24.6-9.4 33.9 0l64 64c4.5 4.5 7 10.6 7 17s-2.5 12.5-7 17l-64 64c-9.4 9.4-24.6 9.4-33.9 0s-9.4-24.6 0-33.9l23-23L384 112c-13.3 0-24-10.7-24-24s10.7-24 24-24l174.1 0L535 41zM105 377l-23 23L256 400c13.3 0 24 10.7 24 24s-10.7 24-24 24L81.9 448l23 23c9.4 9.4 9.4 24.6 0 33.9s-24.6 9.4-33.9 0L7 441c-4.5-4.5-7-10.6-7-17s2.5-12.5 7-17l64-64c9.4-9.4 24.6-9.4 33.9 0s9.4 24.6 0 33.9zM96 64H337.9c-3.7 7.2-5.9 15.3-5.9 24c0 28.7 23.3 52 52 52l117.4 0c-4 17 .6 35.5 13.8 48.8c20.3 20.3 53.2 20.3 73.5 0L608 169.5V384c0 35.3-28.7 64-64 64H302.1c3.7-7.2 5.9-15.3 5.9-24c0-28.7-23.3-52-52-52l-117.4 0c4-17-.6-35.5-13.8-48.8c-20.3-20.3-53.2-20.3-73.5 0L32 342.5V128c0-35.3 28.7-64 64-64zm64 64H96v64c35.3 0 64-28.7 64-64zM544 320c-35.3 0-64 28.7-64 64h64V320zM320 352a96 96 0 1 0 0-192 96 96 0 1 0 0 192z"/></svg>
                                <span class="ms-3">Transaction</span>
                            </button>
                        </form>
                    </li>

                    <li>
                        <form action="beneficiary" method="GET">
                            <input type="hidden" name="action" value="show-beneficiary">
                            <button
                                type="submit"
                                class="flex items-center p-2 rounded-lg hover:bg-gray-200 group"
                                >
                                <svg xmlns="http://www.w3.org/2000/svg" height="16" width="20" viewBox="0 0 512 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2023 Fonticons, Inc.--><path d="M96 0C60.7 0 32 28.7 32 64V448c0 35.3 28.7 64 64 64H384c35.3 0 64-28.7 64-64V64c0-35.3-28.7-64-64-64H96zM208 288h64c44.2 0 80 35.8 80 80c0 8.8-7.2 16-16 16H144c-8.8 0-16-7.2-16-16c0-44.2 35.8-80 80-80zm-32-96a64 64 0 1 1 128 0 64 64 0 1 1 -128 0zM512 80c0-8.8-7.2-16-16-16s-16 7.2-16 16v64c0 8.8 7.2 16 16 16s16-7.2 16-16V80zM496 192c-8.8 0-16 7.2-16 16v64c0 8.8 7.2 16 16 16s16-7.2 16-16V208c0-8.8-7.2-16-16-16zm16 144c0-8.8-7.2-16-16-16s-16 7.2-16 16v64c0 8.8 7.2 16 16 16s16-7.2 16-16V336z"/></svg>
                                <span class="ms-3">Beneficiary</span>
                            </button>
                        </form>
                    </li>

                </ul>
            </div>
        </aside>
    </body>
</html>
