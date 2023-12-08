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
                    <th scope="col" class="px-6 py-3">Transaction Id</th>
                    <th scope="col" class="px-6 py-3">Amount</th>
                    <th scope="col" class="px-6 py-3">Transaction Date</th>
                    <th scope="col" class="px-6 py-3">Transaction Remark</th>
                    <th scope="col" class="px-6 py-3">Receiver ID</th>
                    <th scope="col" class="px-6 py-3">Sender ID</th>
                    <th scope="col" class="px-6 py-3">
                        <span class="sr-only">Edit</span>
                    </th>
                    <th scope="col" class="px-6 py-3">
                        <span class="sr-only">Delete</span>
                    </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="transaction" items="${transactions}">
                    <tr
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        >
                        <th
                            scope="row"
                            class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
                            >
                            ${transaction.getTransactionId()}
                        </th>
                        <td class="px-6 py-4">${transaction.getAmount()} VND</td>
                        <td class="px-6 py-4">${transaction.getTransactionDate()}</td>
                        <td class="px-6 py-4">${transaction.getTransactionRemark()}</td>
                        <td class="px-6 py-4">${transaction.getReceiver().getCustomer().getName()}</td>
                        <td class="px-6 py-4">${transaction.getSender().getCustomer().getName()}</td>
                        <td class="px-6 py-4 text-right">
                            <a
                                href="#"
                                class="font-medium text-blue-600 dark:text-blue-500 hover:underline"
                                onclick="showUpdateForm('${transaction.getTransactionId()}')"
                                >Edit</a
                            >
                        </td>
                        <td class="px-6 py-4 text-center ">
                            <form action="transaction" method="POST">
                                <input type="hidden" name="action" value="delete"/>
                                <input type="hidden" name="transactionId" value="${transaction.getTransactionId()}"/>
                                <button class="font-medium text-red-600 hover:underline mt-3">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>
</div>

<div id="update-account" class="update-account hidden fixed top-0 left-0 w-full h-full bg-blur z-[1000] px-[450px] py-10">
    <div class="col-span-3 mb-16 py-8 px-20 rounded-xl bg-white">
        <div class="text-[#2a6ebe] flex justify-between">
            Update Transaction
            <button class="focus:ring transform transition hover:scale-125 duration-300 ease-in-out" onclick="closeUpdateForm()">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>
        <div class="grid grid-cols-1 gap-2 mb-10">
            <form action="transaction" method="post" class="mt-6">
                <input type="hidden" name="action" value="update-transaction"/>
                <input type="hidden" name="transactionId" id="updateTransactionId" value=""/>
                <div class="relative mt-6">
                    <input
                        type="text"
                        id="transactionIdUpdate"
                        name="transactionIdUpdate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        readonly  
                        />
                    <label
                        for="transactionIdUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Transaction Id</label
                    >
                </div> 

                <div class="relative mt-6">
                    <input
                        type="text"
                        id="transactionRemarkUpdate"
                        name="transactionRemarkUpdate"
                        class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                        placeholder=""
                        />
                    <label
                        for="transactionRemarkUpdate"
                        class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                        >Transaction Remark</label
                    >
                </div>

                <div class="flex justify-end items-center">
                    <button 
                        class="mt-4 px-16 py-3 rounded-md bg-gradient-to-r from-[#00bfae] to-[#0066ad] text-white" 
                        onclick="updateTransaction()">Update Transaction</button>
                </div>
            </form>

        </div>
    </div>
</div>

<script>
    function showUpdateForm(transactionId) {
        // Set the transactionId in the input field
        document.getElementById('transactionIdUpdate').value = transactionId;

        // Set the customerId in the hidden field
        document.getElementById('updateTransactionId').value = transactionId;

        // Show the update form
        document.getElementById('update-account').classList.remove('hidden');
    }

    function closeUpdateForm() {
        // Close the update form
        document.getElementById('update-account').classList.add('hidden');
    }

    function updateTransaction() {
        closeUpdateForm();
    }
</script>

</body>
</html>