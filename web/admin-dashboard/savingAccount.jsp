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
                    <th scope="col" class="px-6 py-3">Saving Account Id</th>
                    <th scope="col" class="px-6 py-3">Account Number</th>
                    <th scope="col" class="px-6 py-3">Account Status</th>
                    <th scope="col" class="px-6 py-3">Account Type</th>
                    <th scope="col" class="px-6 py-3">Saving Amount</th>
                    <th scope="col" class="px-6 py-3">Date Opened</th>
                    <th scope="col" class="px-6 py-3">Date Closed</th>
                    <th scope="col" class="px-6 py-3">
                        <span class="sr-only">Edit</span>
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
                        <td class="px-6 py-4">${savingAccount.getSavingAmount()} VND</td>
                        <td class="px-6 py-4">${savingAccount.getDateOpened()}</td>
                        <td class="px-6 py-4">${savingAccount.getDateClosed()}</td>
                        <td class="px-6 py-4 text-right">
                            <a
                                href="#"
                                class="font-medium text-blue-600 dark:text-blue-500 hover:underline"
                                >Edit</a
                            >
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>