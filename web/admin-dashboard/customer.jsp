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
                    <th scope="col" class="px-6 py-3">Customer Id</th>
                    <th scope="col" class="px-6 py-3">Email</th>
                    <th scope="col" class="px-6 py-3">Name</th>
                    <th scope="col" class="px-6 py-3">Citizen Id</th>
                    <th scope="col" class="px-6 py-3">Phone Number</th>
                    <th scope="col" class="px-6 py-3">Date Of Birth</th>
                    <th scope="col" class="px-6 py-3">Pin Number</th>
                    <th scope="col" class="px-6 py-3">
                        <span class="sr-only">Edit</span>
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
                        <td class="px-6 py-4">${customer.getDateofBirth()}</td>
                        <td class="px-6 py-4">${customer.getPinNumber()}</td>
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