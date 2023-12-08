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
                    <th scope="col" class="px-6 py-3">Saving Account Id</th>
                    <th scope="col" class="px-6 py-3">Account Number</th>
                    <th scope="col" class="px-6 py-3">Account Status</th>
                    <th scope="col" class="px-6 py-3">Account Type</th>
                    <th scope="col" class="px-6 py-3">Initial Saving</th>
                    <th scope="col" class="px-6 py-3">Current Saving</th>
                    <th scope="col" class="px-6 py-3">Date Opened</th>
                    <th scope="col" class="px-6 py-3">Date Closed</th>
                    <th scope="col" class="px-6 py-3">
                        <span class="sr-only">Edit</span>
                    </th>
                    <th scope="col" class="px-6 py-3">
                        <span class="sr-only">Delete</span>
                    </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="savingAccount" items="${savingAccounts}">
                    <tr
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        >
                        <th
                            scope="row"
                            class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
                            >
                            ${savingAccount.getSavingAccountId()}
                        </th>
                        <td class="px-6 py-4">${savingAccount.getAccountNumber()}</td>
                        <td class="px-6 py-4">${savingAccount.getAccountStatus()}</td>
                        <td class="px-6 py-4">${savingAccount.getAccountType()}</td>
                        <td class="px-6 py-4">${savingAccount.getSavingInitialAmount()} VND</td>
                        <td class="px-6 py-4">${savingAccount.getSavingCurrentAmount()} VND</td>
                        <td class="px-6 py-4">${savingAccount.getDateOpened()}</td>
                        <td class="px-6 py-4">${savingAccount.getDateClosed()}</td>
                        <td class="px-6 py-4 text-right">
                            <a
                                href="#"
                                class="font-medium text-blue-600 dark:text-blue-500 hover:underline"
                                onclick="showUpdateForm('${savingAccount.getSavingAccountId()}')"
                                >Edit</a
                            >
                        </td>
                        <td class="px-6 py-4 text-center ">
                            <form action="savingAccount" method="POST">
                                <input type="hidden" name="action" value="delete"/>
                                <input type="hidden" name="savingAccountId" value="${savingAccount.getSavingAccountId()}"/>
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
        <img src="./assets/plus.svg" src="" class="mr-2"></img>Add Saving Account</button>
</div>

<div id="create-account" class="create-account hidden fixed top-0 left-0 w-full h-full bg-blur z-[1000] px-96 py-10">
    <div class="col-span-3 my-16 py-8 px-20 rounded-xl bg-white">
        <div class="text-[#2a6ebe] flex justify-between">Add Saving Account
            <button class="focus:ring transform transition hover:scale-125 duration-300 ease-in-out" 
                    onclick="closeCreateAccount()">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>

        <div class="grid grid-cols-1 gap-2 mb-10">           
            <form action="savingAccount" method="post" class="mt-6">
                <input type="hidden" name="action" value="add-savingAccount"/>
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
                        >Customer Citizen ID</label
                    >
                </div> 

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="customerId"
                        name="customerId"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        readonly
                        />
                    <label
                        for="customerId"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Customer ID</label
                    >
                </div> 

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="name"
                        name="name"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        readonly
                        />
                    <label
                        for="name"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Customer Name</label
                    >
                </div> 

                <div class="relative mt-6">
                    <select name="accountNumber" id="accountNumber" class="block pb-2.5 pt-4 w-full text-sm bg-transparent border-b-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer" required>
                        <option value="" disabled selected>Select Account Number</option>
                    </select>
                    <label
                        for="accountNumber"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Account Number</label
                    >
                </div>

                <div class="relative mt-6" >
                    <select name="savingTitle" id="savingTitle" class="block pb-2.5 pt-4 w-full text-sm bg-transparent border-b-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer" required>
                        
                    </select>
                    <label
                        for="savingTitle"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4"
                        >Choose Type of Saving</label
                    >
                </div>
                
                <div class="relative mt-6">
                    <input
                        type="text"
                        id="initialAmount"
                        name="initialAmount"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        pattern="\d{7,10}"
                        title="Amount must be higher than 1000000."
                        maxlength="10"
                        required
                        />
                    <label
                        for="initialAmount"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Saving Amount</label
                    >
                </div> 
                
                <div class="flex justify-end items-center">
                    <button class="mt-4 px-16 py-3 rounded-md bg-gradient-to-r from-[#00bfae] to-[#0066ad] text-white" 
                        onclick="showCreateCustomAccount()">
                        Add Saving Account
                    </button>
                </div> 
            </form> 
        </div>
    </div>
</div>

<div id="update-account" class="update-account hidden fixed top-0 left-0 w-full h-full bg-blur z-[1000] py-16 px-96">
    <div class="col-span-3 mb-16 py-8 px-20 rounded-xl bg-white">
        <div class="text-[#2a6ebe] flex justify-between">
            Update Saving Account
            <button class="focus:ring transform transition hover:scale-125 duration-300 ease-in-out" onclick="closeUpdateForm()">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>
        <div class="grid grid-cols-1 gap-2 mb-10">
            <form action="savingAccount" method="post" class="mt-6 grid grid-cols-2 gap-x-8">
                <input type="hidden" name="action" value="update-savingAccount"/>
                <input type="hidden" name="savingAccountId" id="updateSavingAccountId" value=""/>
                <div class="relative mt-6">
                    <input
                        type="text"
                        id="savingAccountIdUpdate"
                        name="savingAccountIdUpdate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        readonly  
                        />
                    <label
                        for="savingAccountIdUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Saving Account Id</label
                    >
                </div> 

                <div class="relative mt-6">
                    <select name="accountStatusUpdate" id="accountStatusUpdate" class="block pb-2.5 pt-4 w-full text-sm bg-transparent border-b-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer">
                        <option value="Active">Active</option>
                        <option value="Inactive">Inactive</option>
                    </select>
                    <label
                        for="accountStatusUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Account Status</label
                    >
                </div> 

                <div class="relative mt-6">
                    <select name="accountTypeUpdate" id="accountTypeUpdate" class="block pb-2.5 pt-4 w-full text-sm bg-transparent border-b-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer">
                        <option value="12-Month">12-Month</option>
                        <option value="6-Month">6-Month</option>
                        <option value="3-Month">3-Month</option>
                    </select>
                    <label
                        for="accountTypeUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Account Type</label
                    >
                </div> 

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="initialAmountUpdate"
                        name="initialAmountUpdate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""

                        />
                    <label
                        for="initialAmountUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Initial Amount</label
                    >
                </div> 

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="dateOpenedUpdate"
                        name="dateOpenedUpdate"
                        onfocus="(this.type = 'date')"
                        onblur="(this.type = 'text')"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        />
                    <label
                        for="dateOpenedUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Date Opened</label
                    >
                </div>

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="dateClosedUpdate"
                        name="dateClosedUpdate"
                        onfocus="(this.type = 'date')"
                        onblur="(this.type = 'text')"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        />
                    <label
                        for="dateClosedUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Date Closed</label
                    >
                </div>

                <div class="flex justify-end items-center">
                    <button 
                        class="mt-4 px-16 py-3 rounded-md bg-gradient-to-r from-[#00bfae] to-[#0066ad] text-white" 
                        onclick="updateSavingAccount()">Update Saving Account</button>
                </div>
            </form>

        </div>
    </div>
</div>

<script>
    function showUpdateForm(savingAccountId) {
        // Set the savingAccountId in the input field
        document.getElementById('savingAccountIdUpdate').value = savingAccountId;

        // Set the savingAccountId in the hidden field
        document.getElementById('updateSavingAccountId').value = savingAccountId;

        // Show the update form
        document.getElementById('update-account').classList.remove('hidden');
    }

    function closeUpdateForm() {
        // Close the update form
        document.getElementById('update-account').classList.add('hidden');
    }

    function updateSavingAccount() {
        closeUpdateForm();
    }
</script>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
    $(document).ready(function () {
        $("#citizenId").on("input", function () {
            var citizenId = $(this).val();

            // Check if citizenId is empty
            if (!citizenId) {
                // Clear values for name and customerId
                $("#name").val("");
                $("#customerId").val("");
                // Clear options in the select
                $("#accountNumber").empty();
                $("#savingTitle").val("");
                return;
            }

            $.ajax({
                type: "POST",
                url: "savingAccount",
                data: { action: "get-citizen-id", citizenId: citizenId },
                success: function (response) {
                    console.log("Citizen ID from servlet: " + citizenId);
                    console.log("Customer ID from servlet: " + response);

                    // Split the response by newline
                    var lines = response.trim().split('\n');

                    // Extract customer ID
                    var customerIdLine = lines.find(line => line.startsWith("CUSTOMER_ID:"));
                    var customerId = customerIdLine ? customerIdLine.substring(12) : "";
                    
                    // Extract customer Name
                    var customerNameLine = lines.find(line => line.startsWith("CUSTOMER_NAME:"));
                    var customerName = customerNameLine ? customerNameLine.substring(14) : "";

                    // Set the customer ID in the input field
                    $("#customerId").val(customerId);

                    // Set the customer Name in the input field
                    $("#name").val(customerName);


                    // Extract account numbers
                    var accountNumbers = lines.filter(line => line.startsWith("ACCOUNT_NUMBER:")).map(line => line.substring(15));

                    // Clear existing options in the select
                    $("#accountNumber").empty();

                    // Add the default "Select Account Number" option
                    $("#accountNumber").append('<option value="" disabled selected>Select Account Number</option>');

                    // Populate the select dropdown with account numbers
                    for (var i = 0; i < accountNumbers.length; i++) {
                        $("#accountNumber").append('<option value="' + accountNumbers[i] + '">' + accountNumbers[i] + '</option>');
                    }

                    // Extract interest rate
                    var savingTitles = lines.filter(line => line.startsWith("INTEREST_RATE_TITLE:"))
                           .map(line => line.substring(20));

                    // Clear existing options in the select
                    $("#savingTitle").empty();

                    
                    $("#savingTitle").append('<option value="" disabled selected>Select Saving Title</option>');

                    // Populate the select dropdown with account numbers
                    for (var i = 0; i < savingTitles.length; i++) {
                        $("#savingTitle").append('<option value="' + savingTitles[i] + '">' + savingTitles[i] + '</option>');
                    }
                },
                error: function (xhr, status, error) {
                    console.log("Error communicating with the server. Status: " + status + ", Error: " + error);
                }
            });
        });
        $("#accountNumber").on("input", function () {
            // Set the selected accountNumber in the hidden field
            $("#accountNumber").val($(this).val());

            // Make an additional AJAX request to send the selected accountNumber to the servlet
            $.ajax({
                type: "POST",
                url: "savingAccount",
                data: {
                    action: "get-citizen-id",
                    citizenId: $("#citizenId").val(),
                    accountNumber: $(this).val()  // Include the accountNumber parameter
                },
                success: function (response) {
                    console.log("Selected accountNumber sent to servlet: " + response);
                },
                error: function (xhr, status, error) {
                    console.log("Error communicating with the server. Status: " + status + ", Error: " + error);
                }
            });
        });
        $("#savingTitle").on("input", function () {
            // Set the selected accountNumber in the hidden field
            $("#savingTitle").val($(this).val());

            // Make an additional AJAX request to send the selected accountNumber to the servlet
            $.ajax({
                type: "POST",
                url: "savingAccount",
                data: {
                    action: "get-citizen-id",
                    citizenId: $("#citizenId").val(),
                    savingTitle: $(this).val()  // Include the accountNumber parameter
                },
                success: function (response) {
                    console.log("Selected accountNumber sent to servlet: " + response);
                },
                error: function (xhr, status, error) {
                    console.log("Error communicating with the server. Status: " + status + ", Error: " + error);
                }
            });
        });
    });
</script>

</body>
</html>