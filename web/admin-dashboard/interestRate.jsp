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
                    <th scope="col" class="px-6 py-3">Interest Id</th>
                    <th scope="col" class="px-6 py-3">Interest Rate</th>
                    <th scope="col" class="px-6 py-3">Loan Title</th>
                    <th scope="col" class="px-6 py-3">Saving Title</th>
                    <th scope="col" class="px-6 py-3">Term</th>
                    <th scope="col" class="px-6 py-3">
                        <span class="sr-only">Edit</span>
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