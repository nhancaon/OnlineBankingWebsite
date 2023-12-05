<%@ include file="sidebar.jsp"%> 

<div class="mt-20 p-4 sm:ml-64">
    <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
        <table
            class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400"
            >
            <thead
                class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400"
                >
                <tr>
                    <th scope="col" class="px-6 py-3">Interest Id</th>
                    <th scope="col" class="px-6 py-3">Interest Rate</th>
                    <th scope="col" class="px-6 py-3">Loan Title</th>
                    <th scope="col" class="px-6 py-3">Saving Title</th>
                    <th scope="col" class="px-6 py-3">Term</th>
                    <th scope="col" class="px-6 py-3">
                        <span class="sr-only">Edit</span>
                    </th>
                    <th scope="col" class="px-6 py-3">
                        <span class="sr-only">Delete</span>
                    </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="interestRate" items="${interestRates}">
                    <tr
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        >
                        <th
                            scope="row"
                            class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
                            >
                            ${interestRate.getInterestId()}
                        </th>
                        <td class="px-6 py-4">${interestRate.getInterestRate()} %</td>
                        <td class="px-6 py-4">${interestRate.getLoanTitle()}</td>
                        <td class="px-6 py-4">${interestRate.getSavingTitle()}</td>
                        <td class="px-6 py-4">${interestRate.getTerm()}</td>

                        <td class="px-6 py-4 text-right">
                            <a
                                href="#"
                                class="font-medium text-blue-600 dark:text-blue-500 hover:underline"
                                onclick="showUpdateForm('${interestRate.getInterestId()}')"
                                >Edit</a
                            >
                        </td>
                        <td class="px-6 py-4 text-center ">
                            <form action="interestRate" method="POST">
                                <input type="hidden" name="action" value="delete"/>
                                <input type="hidden" name="interestRateId" value="${interestRate.getInterestId()}"/>
                                <button class="font-medium text-red-600 hover:underline mt-3">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div class="flex justify-end my-10 mx-3">
    <button id="createAccountBtn" class="px-4 py-2 bg-[#00bfae] rounded-2xl outline-none 
            focus:ring transform transition hover:scale-105 duration-300 ease-in-out flex text-white" 
            onclick="showCreateAccount()">
        <img src="./assets/plus.svg" src="" class="mr-2"></img>Add Interest Rate</button>
</div>

<div id="create-account" class="create-account hidden fixed top-0 left-0 w-full h-full bg-blur z-[1000] px-96 py-28">
    <div class="col-span-3 my-16 py-8 px-20 rounded-xl bg-white">
        <div class="text-[#2a6ebe] flex justify-between">Add Interest Rate
            <button class="focus:ring transform transition hover:scale-125 duration-300 ease-in-out" 
                    onclick="closeCreateAccount()">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>

        <div class="grid grid-cols-1 gap-2 mb-10">           
            <form action="interestRate" method="post" class="mt-6">
                <input type="hidden" name="action" value="add-interestRate"/>
                <div class="relative mt-6">
                    <input
                        type="text"
                        id="interestRate"
                        name="interestRate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        required
                        />
                    <label
                        for="interestRate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Interest Rate</label
                    >
                </div> 
                <div class="relative mt-6">
                    <input
                        type="text"
                        id="loanTitle"
                        name="loanTitle"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        required
                        />
                    <label
                        for="loanTitle"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Loan Title</label
                    >
                </div> 
                <div class="relative mt-6">
                    <input
                        type="text"
                        id="savingTitle"
                        name="savingTitle"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        required
                        />
                    <label
                        for="savingTitle"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Saving Title</label
                    >
                </div> 
                <div class="relative mt-6">
                    <input
                        type="text"
                        id="term"
                        name="term"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        required
                        />
                    <label
                        for="term"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Term</label
                    >
                </div> 
                <div class="flex justify-end items-center">
                    <button class="mt-4 px-16 py-3 rounded-md bg-gradient-to-r from-[#00bfae] to-[#0066ad] text-white" 
                            onclick="showCreateCustomAccount()">Add Interest Rate</button>
                </div> 
            </form> 
        </div>
    </div>
</div>

<div id="update-account" class="update-account hidden fixed top-0 left-0 w-full h-full bg-blur z-[1000] px-[450px] py-10">
    <div class="col-span-3 mb-16 py-8 px-20 rounded-xl bg-white">
        <div class="text-[#2a6ebe] flex justify-between">
            Update Interest Rate
            <button class="focus:ring transform transition hover:scale-125 duration-300 ease-in-out" onclick="closeUpdateForm()">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>
        <div class="grid grid-cols-1 gap-2 mb-10">
            <form action="interestRate" method="post" class="mt-6">
                <input type="hidden" name="action" value="update-interestRate"/>
                <input type="hidden" name="interestRateId" id="updateInterestRateId" value=""/>
                <div class="relative mt-6">
                    <input
                        type="text"
                        id="interestRateIdUpdate"
                        name="interestRateIdUpdate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        readonly  
                        />
                    <label
                        for="interestRateIdUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Interest Rate Id</label
                    >
                </div> 

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="interestRateUpdate"
                        name="interestRateUpdate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        />
                    <label
                        for="interestRateUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Interest Rate</label
                    >
                </div> 

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="loanTitleUpdate"
                        name="loanTitleUpdate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        />
                    <label
                        for="loanTitleUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Loan Title</label
                    >
                </div> 

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="savingTitleUpdate"
                        name="savingTitleUpdate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
  
                        />
                    <label
                        for="savingTitleUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Saving Title</label
                    >
                </div> 

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="termUpdate"
                        name="termUpdate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
  
                        />
                    <label
                        for="termUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Term</label
                    >
                </div> 

                <div class="flex justify-end items-center">
                    <button 
                        class="mt-4 px-16 py-3 rounded-md bg-gradient-to-r from-[#00bfae] to-[#0066ad] text-white" 
                        onclick="updateInterestRate()">Update Interest Rate</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    function showUpdateForm(interestRateId) {
        // Set the interestRateId in the input field
        document.getElementById('interestRateIdUpdate').value = interestRateId;

        // Set the interestRateId in the hidden field
        document.getElementById('updateInterestRateId').value = interestRateId;

        // Show the update form
        document.getElementById('update-account').classList.remove('hidden');
    }

    function closeUpdateForm() {
        // Close the update form
        document.getElementById('update-account').classList.add('hidden');
    }

    function updateInterestRate() {
        closeUpdateForm();
    }
</script>

</body>
</html>