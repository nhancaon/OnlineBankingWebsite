<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/includes/header.jsp" %>


<div class="bg-[#f0f1f1] grid items-center py-20 mt-20 px-56">
    <div>
        <div class="grid grid-cols-2 w-full bg-white rounded-lg shadow md:mt-0">
            <div class="w-full p-6 space-y-4 md:space-y-6 sm:p-8">
                <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
                    Sign in to your account
                </h1>
                <div class="w-full h-32 rounded-xl bg-[#ebf7ff]">

                </div>
                <form class="space-y-4 md:space-y-6" action="Login" method="post">
                    <input type="hidden" name="action" value="login">    
                    <div>
                        <label for="email" class="block mb-2 text-sm font-medium text-gray-900">Your email</label>
                        <input type="email" name="email" id="email" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="name@company.com" required="">
                    </div>
                    <div>
                        <label for="password" class="block mb-2 text-sm font-medium text-gray-900">Password</label>
                        <input type="password" name="password" id="password" placeholder="••••••••" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm 
                               rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 " required="">
                    </div>
                    <div class="flex items-center justify-between">
                        <div class="flex items-start">
                            <div class="flex items-center h-5">
                                <input id="remember" aria-describedby="remember" type="checkbox" class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3
                                       focus:ring-primary-300">
                            </div>
                            <div class="ml-3 text-sm">
                                <label for="remember" class="text-gray-500">Remember me</label>
                            </div>
                        </div>
                        <a href="#" class="text-sm font-medium text-primary-600 hover:underline dark:text-primary-500">Forgot password?</a>
                    </div>
                    <button type="submit" class="w-full text-white bg-gradient-to-r from-[#00bfae] to-[#0066ad] hover:bg-gradient-to-l focus:ring-4 focus:outline-none focus:ring-primary-300 
                            font-medium rounded-lg text-sm px-5 py-2.5 text-center">Sign in</button>
                    <p class="text-sm font-light text-gray-700">
                        Don’t have an account yet? <a href="./signup.jsp" class="font-medium hover:underline text-[#007eca]">Sign up</a>
                    </p>
                </form>

            </div>
            <div class="bg-gradient-to-r from-[#00bfae] to-[#0066ad] h-full px-10 py-24 rounded-r-lg">
                <img class="mb-4 w-18 h-12" src="./assets/logo.png" alt="logo">
                <span class="flex items-center mb-6 text-2xl font-semibold text-white">
                    Welcome to NNDBank online banking services.   
                </span>
                <span class="flex items-center mb-6 text-md font-light text-gray-300">
                    Join millions of users worldwide in experiencing the epitome of online banking through NNDBank - the gateway to the most advanced and secure digital financial solutions. Dive into the future of banking convenience and access a spectrum of cutting-edge financial services from top institutions and professionals.    
                </span>

                <div class="flex -space-x-4 rtl:space-x-reverse">
                    <img class="w-10 h-10 border-2 border-white rounded-full dark:border-gray-800" src="assets/caonhan.jpg" alt="">
                    <img class="w-10 h-10 border-2 border-white rounded-full dark:border-gray-800" src="assets/dat.jpg" alt="">
                    <img class="w-10 h-10 border-2 border-white rounded-full dark:border-gray-800" src="assets/hiennhan.jpg" alt="">
                    <a class="flex items-center justify-center w-10 h-10 text-xs font-medium text-white bg-gray-700 border-2 border-white rounded-full hover:bg-gray-600 dark:border-gray-800" href="#">+99</a>
                </div>
            </div>
        </div>
    </div>

</div>


<%@ include file="/includes/footer.jsp" %>