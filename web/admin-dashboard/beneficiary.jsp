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
                    <th scope="col" class="px-6 py-3">Beneficiary Id</th>
                    <th scope="col" class="px-6 py-3">Name</th>
                    <th scope="col" class="px-6 py-3">Account Number</th>
                    <th scope="col" class="px-6 py-3">Customer Name</th>
                    <th scope="col" class="px-6 py-3">
                        <span class="sr-only">Edit</span>
                    </th>
                    <th scope="col" class="px-6 py-3">
                        <span class="sr-only">Delete</span>
                    </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="beneficiary" items="${beneficiaries}">
                    <tr
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        >
                        <th
                            scope="row"
                            class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
                            >
                            ${beneficiary.getBeneficiaryId()}
                        </th>
                        <td class="px-6 py-4">${beneficiary.getName()}</td>
                        <td class="px-6 py-4">${beneficiary.getAccountNumber()}</td>
                        <td class="px-6 py-4">${beneficiary.getCustomer().getName()}</td>
                        <td class="px-6 py-4 text-right">
                            <a
                                href="#"
                                class="font-medium text-blue-600 dark:text-blue-500 hover:underline"
                                onclick="showUpdateForm('${beneficiary.getBeneficiaryId()}', '${beneficiary.getAccountNumber()}', '${beneficiary.getCustomer().getName()}')"
                                >Edit
                            </a>
                        </td>
                        <td class="px-6 py-4 text-center ">
                            <form action="beneficiary" method="POST">
                                <input type="hidden" name="action" value="delete"/>
                                <input type="hidden" name="beneficiaryId" value="${beneficiary.getBeneficiaryId()}"/>
                                <button class="font-medium text-red-600 hover:underline mt-3">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>
</div>

<div id="update-account" class="update-account hidden fixed top-0 left-0 w-full h-full bg-blur z-[1000] py-16 px-96">
    <div class="col-span-3 mb-16 py-8 px-20 rounded-xl bg-white">
        <div class="text-[#2a6ebe] flex justify-between">
            Update Beneficiary
            <button class="focus:ring transform transition hover:scale-125 duration-300 ease-in-out" onclick="closeUpdateForm()">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>
        <div class="grid grid-cols-1 gap-2 mb-10">
            <form action="beneficiary" method="post" class="mt-6 grid grid-cols-2 gap-x-8">
                <input type="hidden" name="action" value="update-beneficiary"/>
                <input type="hidden" name="beneficiaryId" id="updateBeneficiaryId" value=""/>

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="beneficiaryIdUpdate"
                        name="beneficiaryIdUpdate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        readonly  
                        />
                    <label
                        for="beneficiaryIdUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Beneficiary ID
                    </label>
                </div> 

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="customerNameUpdate"
                        name="customerNameUpdate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        readonly  
                        />
                    <label
                        for="customerNameUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Customer Name</label
                    >
                </div> 
                
                <div class="relative mt-6">
                    <input
                        type="text"
                        id="accountNumberUpdate"
                        name="accountNumberUpdate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""

                        />
                    <label
                        for="accountNumberUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Account Number</label
                    >
                </div>

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="nameUpdate"
                        name="nameUpdate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""

                        />
                    <label
                        for="nameUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Beneficiary Name</label
                    >
                </div>

                <div class="flex justify-end items-center">
                    <button 
                        class="mt-4 px-16 py-3 rounded-md bg-gradient-to-r from-[#00bfae] to-[#0066ad] text-white" 
                        onclick="updateBeneficiary()">Update Beneficiary</button>
                </div>
            </form>

        </div>
    </div>
</div>

<script>
    function showUpdateForm(beneficiaryId, accountNumber, customerName) {
        // Set the beneficiaryId in the input field
        document.getElementById('beneficiaryIdUpdate').value = beneficiaryId;

        document.getElementById('accountNumberUpdate').value = accountNumber;

        document.getElementById('customerNameUpdate').value = customerName;

        // Set the beneficiaryId in the hidden field
        document.getElementById('updateBeneficiaryId').value = beneficiaryId;

        // Show the update form
        document.getElementById('update-account').classList.remove('hidden');
    }

    function closeUpdateForm() {
        // Close the update form
        document.getElementById('update-account').classList.add('hidden');
    }

    function updateBeneficiary() {
        closeUpdateForm();
    }
</script>


</body>
</html>