<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="business.Customer" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="./styles/main.css" />
        <script src="./styles/tailwindcss.js"></script>
        <link rel="icon" href="./assets/logo.png" />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" />
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
                            'bounceOne': '0.5s ease-in-out',
                            'appear': 'appear 0.5s ease-in-out',
                            'appearRight': 'appearRight ease-in-out .3s',
                            'notification': 'notification ease-in-out .3s, fadeOut 3s .3s ease-in-out forwards',
                        },
                        keyframes: {
                            'bounceOne': {
                                '0%': {transform: 'translateY(0)'},
                                '100%': {transform: 'translateY(-20px)'},

                            },
                            appear: {
                                '0%': {opacity: '0', transform: 'translateY(20%)'},
                                '100%': {opacity: '1', transform: 'translateY(0)'},
                            },
                            appearRight: {
                                '0%': {opacity: '0', transform: 'translateX(20%)'},
                                '100%': {opacity: '1', transform: 'translateX(0)'},
                            },
                            notification: {
                                '0%': {opacity: '0', transform: 'translateX(20%)'},
                                '100%': {opacity: '1', transform: 'translateX(0)'},
                            },
                            fadeOut: {'0%': {opacity: '1'}, '100%': {opacity: '0'}}
                        },
                    },
                },
            };
        </script>
        <title>Banking</title>
    </head>
    <body>

        <div id="header" class="fixed top-0 w-full shadow-xl z-[1000] bg-[#fff]">
            <div class="grid grid-cols-3 justify-center mx-56 py-4">
                <div class="w">
                    <form>
                        <div class="relative">
                            <div
                                class="absolute inset-y-0 left-0 flex items-center pointer-events-none"
                                >
                                <img src="./assets/search.svg" class="w-5 h-5" />
                            </div>
                            <input
                                type="search"
                                id="default-search"
                                class="block w-full p-4 pl-8 text-sm text-gray-90 outline-none border-gray-200"
                                placeholder="Find in SmartBanking"
                                required
                                />
                        </div>
                    </form>
                </div>
                <div class="flex items-center justify-center">
                    <a href="./profile.jsp">
                        <img src="./assets/logo.png" class="w-18 h-12" />
                    </a>
                </div>

                <%
                    // Retrieve the customer from the session
                    Customer customer = (Customer) session.getAttribute("customer");

                    // Check if the customer object exists in the session
                    if (customer != null) {
                        // Access customer properties
                        String customerId = customer.getCustomerId();
                        String fullName = customer.getName();
                        String email = customer.getEmail();
                        // ... other properties
%>
                <div class="flex items-center justify-end">
                    <div class="grid mr-2">
                        <div class="text-sm">Good morning!</div>
                        <div class="font-bold text-lg"><%= fullName%></div>
                    </div>
                    <div>
                        <div id="avatar" class="bg-black rounded-full w-10 h-10"></div>
                        <div id="dropdown" class="hidden absolute right-0 mx-56 mt-2 border-2 border-gray-200 p-4 w-48 bg-white shadow-xl rounded-md">
                            <ul>
                                <li>
                                    <form action="Logout"> 
                                        <div class="focus:ring transform transition hover:scale-105 duration-300 ease-in-out">
                                            <input type="hidden" name="action" value="logout">  
                                            <i class="fa-solid fa-arrow-right-from-bracket mr-3 text-red-800"></i>
                                            <input type="submit" value="Log out"/>
                                        </div>
                                    </form>
                                </li>
                            </ul>
                        </div>
                    </div>                 
                </div>
                <%
                } else {
                %>

                <div></div>
                <%
                    }
                %>
            </div>

        </div>