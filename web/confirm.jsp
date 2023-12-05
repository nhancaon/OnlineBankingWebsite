<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ page
    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@ include
        file="/includes/header.jsp" %>

        <div class="bg-[#f0f1f1] mt-[5.2rem] pb-16">
            <div class="py-16 mx-2 md:mx-56">
                <div class="flex text-2xl">
                    <form action="Profile" method="GET">
                        <button><i class="fa-solid fa-chevron-left text-xl py-[0.3rem] pr-6"></i></button>
                    </form>
                    <div class="py-[0.2rem]">Transfer</div>
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
                                    href="./transfer.jsp"
                                    class="ml-1 text-sm font-medium hover:text-blue-600 md:ml-2"
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
                    <div class="my-16 py-8 px-8 md:px-20 rounded-xl bg-white">
                        <%@ include file="/includes/exception.jsp" %>
                        <div class="text-[#2a6ebe] text-center mb-12">
                            Internal Transfer Confirmation
                        </div>

                        <div class="grid gap-4 md:mx-40">
                            <div class="flex justify-between">
                                <label>Account sender:</label>
                                <span class="text-[#2a6ebe]">${sender.accountNumber}</span>
                            </div>
                            <div class="flex justify-between">
                                <label>Account receiver:</label>
                                <span class="text-[#2a6ebe]">${receiver.accountNumber}</span>
                            </div>
                            <div class="flex justify-between">
                                <label>Receiver Name:</label>
                                <span class="text-[#2a6ebe]">${receiver.customer.name}</span>
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
                            <form action="Transfer" method="post">
                                <input type="hidden" name="action" value="confirm" />
                                <div class="relative mt-6">
                                    <input
                                        type="text"
                                        id="enteredOTP"
                                        name="enteredOTP"
                                        class="block pb-2.5 pt-4 w-full text-xl bg-transparent border-b-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer text-center"
                                        placeholder=" "
                                        pattern="\d{6}"
                                        title="Please enter a 6-digit number."
                                        maxlength="6"
                                        required
                                        />
                                    <label
                                        for="enteredOTP"
                                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4"
                                        >OTP (6 digits)</label
                                    >
                                </div>

                                <div class="flex justify-end items-center mt-10">
                                    <button
                                        type="submit"
                                        class="px-16 py-3 rounded-md bg-gradient-to-r from-[#00bfae] to-[#0066ad] text-white"
                                        >
                                        Confirm
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <c:if test="${show ne 'not'}">
                    <div
                        id="check-pin"
                        class="check-pin fixed top-0 left-0 w-full h-full bg-blur z-[1000] px-8 md:px-96 py-28"
                        >
                        <div class="col-span-3 my-16 py-8 px-8 md:px-20 rounded-xl bg-white">
                            <div class="text-[#2a6ebe] flex justify-between">
                                Enter your PIN
                                <form action="Transfer" method="get">
                                    <input type="hidden" name="action" value="reload" />
                                    <button
                                        class="focus:ring transform transition hover:scale-125 duration-300 ease-in-out"
                                        >
                                        <i class="fa-solid fa-xmark"></i>
                                    </button>
                                </form>
                            </div>

                            <div class="content">
                                <div class="relative mt-6">
                                    <input
                                        type="password"
                                        id="pinNumber"
                                        name="PIN"
                                        class="block pb-2.5 pt-4 w-full text-sm bg-transparent border-b-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                                        placeholder=" "
                                        pattern="\d{6}"
                                        title="Please enter a 6-digit number."
                                        maxlength="6"
                                        required
                                        />
                                    <label
                                        for="pinNumber"
                                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4"
                                        >PIN (6 digits)</label
                                    >
                                </div>
                                <div class="flex justify-end items-center mt-10">
                                    <button
                                        type="submit"
                                        class="px-16 py-3 rounded-md bg-gradient-to-r from-[#00bfae] to-[#0066ad] text-white"
                                        onclick="return validatePIN()"
                                        >
                                        Continue
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <script>
                    function validatePIN() {
                        var enteredPIN = document.getElementById("pinNumber").value;
                        var correctPIN = "<%=customer.getPinNumber()%>";

                        if (enteredPIN === correctPIN) {
                            document.forms["Mail"].submit();
                            return true;
                        } else {
                            alert("Incorrect PIN. Please try again.");
                            return false;
                        }
                    }
                    setInterval(function () {
                        document.getElementById("Mail").submit();
                    }, 120000);
                </script>
                <form id="Mail" action="Transfer" method="post">
                    <input type="hidden" name="action" value="sendMail" />
                </form>

            </div>
        </div>

        <%@ include file="/includes/footer.jsp" %>