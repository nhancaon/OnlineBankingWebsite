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
                    <th scope="col" class="px-6 py-3">Loan Id</th>
                    <th scope="col" class="px-6 py-3">Account Number</th>
                    <th scope="col" class="px-6 py-3">Account Status</th>
                    <th scope="col" class="px-6 py-3">Account Type</th>
                    <th scope="col" class="px-6 py-3">Loan Amount</th>
                    <th scope="col" class="px-6 py-3">Total Loan Amount</th>
                    <th scope="col" class="px-6 py-3">Monthly Pay</th>
                    <th scope="col" class="px-6 py-3">Date Opened</th>
                    <th scope="col" class="px-6 py-3">Date Closed</th>
                    <th scope="col" class="px-6 py-3">
                        <span class="sr-only">Edit</span>
                    </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="loanLending" items="${loanLendings}">
                    <tr
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        >
                        <th
                            scope="row"
                            class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
                            >
                            ${loanLending.getLoanLendingId()}
                        </th>
                        <td class="px-6 py-4">${loanLending.getAccountNumber()}</td>
                        <td class="px-6 py-4">${loanLending.getAccountStatus()}</td>
                        <td class="px-6 py-4">${loanLending.getAccountType()}</td>
                        <td class="px-6 py-4">${loanLending.getLoanAmount()} VND</td>
                        <td class="px-6 py-4">${loanLending.getTotalLoanAmount()} VND</td>
                        <td class="px-6 py-4">${loanLending.getMonthlyPay()} VND</td>
                        <td class="px-6 py-4">${loanLending.getDateOpened()}</td>
                        <td class="px-6 py-4">${loanLending.getDateClosed()}</td>
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