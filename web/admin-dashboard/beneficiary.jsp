<%@ include file="index.jsp"%> 

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
                        <td class="px-6 py-4 text-center">
                            <form action="beneficiary" method="POST">
                                <input type="hidden" name="action" value="update"/>
                                <button class="font-medium text-blue-600 hover:underline">Edit</button>
                            </form>
                        </td>
                        <td class="px-6 py-4 text-center ">
                            <form action="beneficiary" method="POST">
                                <input type="hidden" name="action" value="delete"/>
                                <input type="hidden" name="beneficiaryId" value="${beneficiary.getBeneficiaryId()}"/>
                                <button class="font-medium text-red-600 hover:underline">Delete</button>
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
        <img src="./assets/plus.svg" src="" class="mr-2"></img>Add Beneficiary</button>
</div>


<div id="create-account" class="create-account hidden fixed top-0 left-0 w-full h-full bg-blur z-[1000] px-[450px] py-10">
    <div class="col-span-3 mb-16 py-8 px-20 rounded-xl bg-white">
        <div class="text-[#2a6ebe] flex justify-between">Add Beneficiary
            <button class="focus:ring transform transition hover:scale-125 duration-300 ease-in-out" 
                    onclick="closeCreateAccount()">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>
        <div class="grid grid-cols-1 gap-2 mb-10">           
            <form action="beneficiary" method="post" class="mt-6">
                <input type="hidden" name="action" value="add-beneficiary"/>
                <div class="relative mt-6">
                    <input
                        type="text"
                        id="beneficiaryId"
                        name="beneficiaryId"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        required
                        />
                    <label
                        for="beneficiaryId"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Beneficiary Id</label
                    >
                </div> 

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="name"
                        name="name"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        required
                        />
                    <label
                        for="name"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Name</label
                    >
                </div> 

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="accountNumber"
                        name="accountNumber"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        required
                        />
                    <label
                        for="accountNumber"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Account Number</label
                    >
                </div> 

                <div class="flex justify-end items-center">
                    <button class="mt-4 px-16 py-3 rounded-md bg-gradient-to-r from-[#00bfae] to-[#0066ad] text-white" 
                            onclick="showCreateCustomAccount()">Add Beneficiary</button>
                </div> 
            </form>

        </div>
    </div>
</div>


</body>
</html>