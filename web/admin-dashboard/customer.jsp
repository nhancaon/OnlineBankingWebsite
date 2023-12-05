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
                    <th scope="col" class="px-6 py-3">Customer Id</th>
                    <th scope="col" class="px-6 py-3">Email</th>
                    <th scope="col" class="px-6 py-3">Name</th>
                    <th scope="col" class="px-6 py-3">Citizen Id</th>
                    <th scope="col" class="px-6 py-3">Phone Number</th>
                    <th scope="col" class="px-6 py-3">Address</th>
                    <th scope="col" class="px-6 py-3">Date Of Birth</th>
                    <th scope="col" class="px-6 py-3">Pin Number</th>
                    <th scope="col" class="px-6 py-3">
                        <span class="sr-only">Edit</span>
                    </th>
                    <th scope="col" class="px-6 py-3">
                        <span class="sr-only">Delete</span>
                    </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="customer" items="${customers}">
                    <tr
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        >
                        <th
                            scope="row"
                            class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
                            >
                            ${customer.getCustomerId()}
                        </th>
                        <td class="px-6 py-4">${customer.getEmail()}</td>
                        <td class="px-6 py-4">${customer.getName()}</td>
                        <td class="px-6 py-4">${customer.getCitizenId()}</td>
                        <td class="px-6 py-4">${customer.getPhoneNumber()}</td>
                        <td class="px-6 py-4">${customer.getAddress()}</td>
                        <td class="px-6 py-4">${customer.getDateofBirth()}</td>
                        <td class="px-6 py-4">${customer.getPinNumber()}</td>                     
                        <td class="px-6 py-4 text-right">
                            <a
                                href="#"
                                class="font-medium text-blue-600 dark:text-blue-500 hover:underline"
                                onclick="showUpdateForm('${customer.getCustomerId()}')"
                                >Edit</a
                            >
                        </td>
                        <td class="px-6 py-4 text-center ">
                            <form action="customer" method="POST">
                                <input type="hidden" name="action" value="delete"/>
                                <input type="hidden" name="customerId" value="${customer.getCustomerId()}"/>
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
        <img src="./assets/plus.svg" src="" class="mr-2"></img>Add Customer</button>
</div>

<div id="create-account" class="create-account hidden fixed top-0 left-0 w-full h-full bg-blur z-[1000] py-16 px-96">
    <div class="col-span-3 mb-16 py-8 px-20 rounded-xl bg-white">
        <div class="text-[#2a6ebe] flex justify-between">Add Customer
            <button class="focus:ring transform transition hover:scale-125 duration-300 ease-in-out" 
                    onclick="closeCreateAccount()">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>
        <div class="grid grid-cols-1 gap-2 mb-10">           
            <form action="customer" method="post" class="mt-6 grid grid-cols-2 gap-x-8">
                <input type="hidden" name="action" value="add-customer"/>
                <div class="relative mt-6">
                    <input
                        type="text"
                        id="email"
                        name="email"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
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
                        id="citizenId"
                        name="citizenId"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        required
                        />
                    <label
                        for="citizenId"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Citizen ID</label
                    >
                </div> 
                <div class="relative mt-6">
                    <input
                        type="text"
                        id="phoneNumber"
                        name="phoneNumber"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
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
                        id="address"
                        name="address"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        required
                        />
                    <label
                        for="address"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Address</label
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
                <div class="relative mt-6">
                    <input
                        type="text"
                        id="pinNumber"
                        name="pinNumber"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        required
                        />
                    <label
                        for="pinNumber"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >PIN Number</label
                    >
                </div> 
                <div class="relative mt-6">
                    <input
                        type="text"
                        id="password"
                        name="password"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        required
                        />
                    <label
                        for="password"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Password</label
                    >
                </div> 
                <div class="flex justify-end items-center">
                    <button class="mt-4 px-16 py-3 rounded-md bg-gradient-to-r from-[#00bfae] to-[#0066ad] text-white" 
                            onclick="showCreateCustomAccount()">Add Customer</button>
                </div> 
            </form> 
        </div>     
    </div>
</div>

<div id="update-account" class="update-account hidden fixed top-0 left-0 w-full h-full bg-blur z-[1000] py-16 px-96">
    <div class="col-span-3 mb-16 py-8 px-20 rounded-xl bg-white">
        <div class="text-[#2a6ebe] flex justify-between">
            Update Customer
            <button class="focus:ring transform transition hover:scale-125 duration-300 ease-in-out" onclick="closeUpdateForm()">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>
        <div class="grid grid-cols-1 gap-2 mb-10">
            <form action="customer" method="post" class="mt-6 grid grid-cols-2 gap-x-8">
                <input type="hidden" name="action" value="update-customer"/>
                <input type="hidden" name="customerId" id="updateCustomerId" value=""/>
                <div class="relative mt-6">
                    <input
                        type="text"
                        id="customerIdUpdate"
                        name="customerIdUpdate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        readonly  
                        />
                    <label
                        for="customerIdUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Customer Id</label
                    >
                </div> 

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="emailUpdate"
                        name="emailUpdate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        />
                    <label
                        for="emailUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Email</label
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
                        >Name</label
                    >
                </div> 

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="citizenIdUpdate"
                        name="citizenIdUpdate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""

                        />
                    <label
                        for="citizenIdUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Citizen ID</label
                    >
                </div> 

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="phoneNumberUpdate"
                        name="phoneNumberUpdate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""

                        />
                    <label
                        for="phoneNumberUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Phone Number</label
                    >
                </div> 

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="addressUpdate"
                        name="addressUpdate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""

                        />
                    <label
                        for="addressUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Address</label
                    >
                </div> 

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="dateOfBirthUpdate"
                        name="dateOfBirthUpdate"
                        onfocus="(this.type = 'date')"
                        onblur="(this.type = 'text')"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        />
                    <label
                        for="dateOfBirthUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Date Of Birth</label
                    >
                </div>

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="pinNumberUpdate"
                        name="pinNumberUpdate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""

                        />
                    <label
                        for="pinNumberUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >PIN Number</label
                    >
                </div> 

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="passwordUpdate"
                        name="passwordUpdate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""

                        />
                    <label
                        for="passwordUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Password</label
                    >
                </div> 

                <div class="flex justify-end items-center">
                    <button 
                        class="mt-4 px-16 py-3 rounded-md bg-gradient-to-r from-[#00bfae] to-[#0066ad] text-white" 
                        onclick="updateCustomer()">Update Customer</button>
                </div>
            </form>

        </div>
    </div>
</div>

<script>
    function showUpdateForm(customerId) {
        // Set the customerId in the input field
        document.getElementById('customerIdUpdate').value = customerId;

        // Set the customerId in the hidden field
        document.getElementById('updateCustomerId').value = customerId;

        // Show the update form
        document.getElementById('update-account').classList.remove('hidden');
    }

    function closeUpdateForm() {
        // Close the update form
        document.getElementById('update-account').classList.add('hidden');
    }

    function updateCustomer() {
        closeUpdateForm();
    }
</script>

</body>
</html>