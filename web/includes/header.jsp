<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="business.Customer" %>
<%@ page import="business.Employee" %>
<%@ page import="java.time.LocalTime" %>
<%@ include file="FormatCurrency.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
    Customer customer = (Customer) session.getAttribute("customer");
    LocalTime currentTime = LocalTime.now();
    int hour = currentTime.getHour();
    String greeting = "";

    if (hour >= 5 && hour < 12) {
        greeting = "Good morning";
    } else if (hour >= 12 && hour < 18) {
        greeting = "Good afternoon";
    } else {
        greeting = "Good evening";
    }
%>
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
            rel="stylesheet"/>
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
                            'notification': 'notification ease-in-out .3s, fadeOut 3s .5s ease-in-out forwards',
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
        <div id="header" class="fixed top-0 w-full shadow-xl z-[100000] bg-[#fff]">
            <div class="grid grid-cols-2 md:grid-cols-3 justify-center md:mx-56 py-4">
                <div class="hidden sm:block">
                    <form action="Beneficiary" method="POST">
                        <input type="hidden" name="action" value="findContacts"/>
                        <div class="relative">
                            <div class="absolute inset-y-0 left-0 flex items-center pointer-events-none">
                                <img src="./assets/search.svg" class="w-5 h-5" />
                            </div>

                            <input
                                type="search"
                                id="default-search"
                                name="parameter"
                                class="block w-full p-4 pl-8 text-sm text-gray-90 outline-none border-gray-200"
                                placeholder="Find Contacts in NND Banking"
                                required/>
                        </div>
                    </form>
                </div>

                <div class="flex items-center justify-center">
                    <% 
                        if (customer != null) {
                    %>
                    <form action="Profile" method="GET">
                        <button>
                            <img src="./assets/banklogo.png" class="w-40 h-12" />
                        </button>
                    </form>

                    <%
                    } else {
                    %>

                    <a href="./profile.jsp"><img src="./assets/banklogo.png" class="w-40 h-12" /></a>

                    <% } %>
                </div>

                <%
                    if (customer != null) {
                        String fullName = customer.getName();
                %>

                <div class="flex items-center justify-end mr-4 sm:mr-0">
                    <div class="hidden sm:block grid mr-2">
                        <div class="text-sm"><%= greeting %>!</div>
                        <div class="font-bold text-lg"><%= fullName%></div>
                    </div>

                    <div>
                        <div id="avatar" class="bg-black rounded-full w-10 h-10"></div>
                        <div id="dropdown" class="hidden absolute right-0 mx-2 md:mx-56 mt-2 border-2 border-gray-200 p-4 w-48 bg-white shadow-xl rounded-md">
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