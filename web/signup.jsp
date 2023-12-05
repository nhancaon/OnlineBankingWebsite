<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/includes/header.jsp" %>


<div class="bg-[#f0f1f1] grid items-center py-20 mt-20 md:px-56">
    <div>
        <div class="flex flex-col-reverse md:grid grid-cols-2 md:w-full bg-white md:rounded-lg shadow md:mt-0">
            <div class="w-full p-6 space-y-4 md:space-y-6 sm:p-8">
                <h1 class="text-sm font-light leading-tight tracking-tight text-gray-900">
                    Already have an account? <a href="./login.jsp" class="hover:underline text-[#007eca]">Click here</a> to login
                </h1>
                <%@ include file="/includes/exception.jsp" %>
                <form class="col-span-3 space-y-4 md:space-y-6" action="Signup" method="post">
                    <input type="hidden" name="action" value="signup"/> 
                    <ol class="relative text-gray-500 border-s border-gray-200">                  
                        <li class="mb-10 ms-6">            
                            <span class="absolute flex items-center justify-center w-8 h-8 bg-gray-100 rounded-full -start-4 ring-4 ring-white ">
                                <i class="fa-solid fa-circle-info"></i>
                            </span>
                            <h3 class="font-medium leading-tight">Personal Info</h3>
                            <div class="relative mt-6">
                                <input
                                    type="text"
                                    id="fullName"
                                    name="fullName"
                                    class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                                    placeholder=" "
                                    required
                                    />
                                <label
                                    for="fullName"
                                    class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                                    >Full Name</label
                                >
                            </div>
                            <div class="relative mt-6">
                                <input
                                    type="text"
                                    id="email"
                                    name="email"
                                    class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                                    placeholder=" "
                                    required
                                    />
                                <label
                                    for="email"
                                    class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                                    >Email</label
                                >
                            </div>
                            <div class="relative mt-6">
                                <input
                                    type="text"
                                    id="phoneNumber"
                                    name="phoneNumber"
                                    class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                                    placeholder=" "
                                    required
                                    />
                                <label
                                    for="phoneNumber"
                                    class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                                    >Phone Number</label
                                >
                            </div>
                            <div class="relative mt-6">
                                <input
                                    type="text"
                                    id="citizenIdentity"
                                    name="citizenIdentity"
                                    class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                                    placeholder=" "
                                    required
                                    />
                                <label
                                    for="citizenIdentity"
                                    class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                                    >Citizen Identity (*)</label
                                >
                            </div>
                            <div class="relative mt-6">
                                <input
                                    type="text"
                                    id="dateOfBirth"
                                    name="dateOfBirth"
                                    onfocus="(this.type = 'date')"
                                    onblur="(this.type = 'text')"
                                    class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                                    placeholder=""
                                    />
                                <label
                                    for="dateOfBirth"
                                    class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                                    >Date Of Birth</label
                                >
                            </div>
                        </li>
                        <li class="mb-10 ms-6">
                            <span class="absolute flex items-center justify-center w-8 h-8 bg-gray-100 rounded-full -start-4 ring-4 ring-white ">
                                <svg class="w-3.5 h-3.5 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 18 20">
                                    <path d="M16 1h-3.278A1.992 1.992 0 0 0 11 0H7a1.993 1.993 0 0 0-1.722 1H2a2 2 0 0 0-2 2v15a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2V3a2 2 0 0 0-2-2Zm-3 14H5a1 1 0 0 1 0-2h8a1 1 0 0 1 0 2Zm0-4H5a1 1 0 0 1 0-2h8a1 1 0 1 1 0 2Zm0-5H5a1 1 0 0 1 0-2h2V2h4v2h2a1 1 0 1 1 0 2Z"/>
                                </svg>
                            </span>
                            <h3 class="font-medium leading-tight">Address</h3>
                            <div class="relative mt-6">
                                <input
                                    type="text"
                                    id="address"
                                    name="address"
                                    class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                                    placeholder=" "
                                    required
                                    />
                                <label
                                    for="address"
                                    class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                                    >Address</label
                                >
                            </div>
                        </li>
                        <li class="ms-6">
                            <span class="absolute flex items-center justify-center w-8 h-8 bg-gray-100 rounded-full -start-4 ring-4 ring-white ">
                                <svg class="w-3.5 h-3.5 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 18 20">
                                    <path d="M16 1h-3.278A1.992 1.992 0 0 0 11 0H7a1.993 1.993 0 0 0-1.722 1H2a2 2 0 0 0-2 2v15a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2V3a2 2 0 0 0-2-2ZM7 2h4v3H7V2Zm5.7 8.289-3.975 3.857a1 1 0 0 1-1.393 0L5.3 12.182a1.002 1.002 0 1 1 1.4-1.436l1.328 1.289 3.28-3.181a1 1 0 1 1 1.392 1.435Z"/>
                                </svg>
                            </span>
                            <h3 class="font-medium leading-tight">Confirmation</h3>   
                            <div class="relative mt-6">
                                <input
                                    type="password"
                                    id="password"
                                    name="password"
                                    class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                                    placeholder=" "
                                    required
                                    />
                                <label
                                    for="password"
                                    class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                                    >Password</label
                                >
                            </div>
                            <div class="relative mt-6">
                                <input
                                    type="text"
                                    id="pinNumber"
                                    name="pinNumber"
                                    class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                                    placeholder=" "
                                    pattern="\d{6}"
                                    title="Please enter a 6-digit number."
                                    maxlength="6" 
                                    required
                                    />
                                <label
                                    for="pinNumber"
                                    class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                                    >Pin Number</label
                                >
                            </div>
                            <button type="submit" class="w-full mt-6 text-white bg-gradient-to-r from-[#00bfae] to-[#0066ad] hover:bg-gradient-to-l focus:ring-4 focus:outline-none focus:ring-primary-300 
                                    font-medium rounded-lg text-sm px-5 py-2.5 text-center">Confirm</button>
                        </li>
                    </ol>
                </form>
            </div>
            <div class="bg-gradient-to-r from-[#00bfae] to-[#0066ad] h-full md:px-10 px-2 py-24 md:rounded-r-lg">
                <img class="mb-4 w-18 h-12" src="./assets/banklogo.png" alt="logo">
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
                                    <a class="flex items-center justify-center w-10 h-10 text-xs font-medium text-white bg-[#0066ad] border-2 border-white rounded-full hover:bg-[#00bfae]" href="#">+99</a>
                                    </div>
                                    </div>


                                    </div>
                                    </div>

                                    </div>

                                    <%@ include file="/includes/footer.jsp" %>