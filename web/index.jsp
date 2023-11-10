<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/includes/header.jsp" %>

<div class="bg-black h-96"></div>
<div id="content" class="bg-[#f0f1f1]">
    <div class="mx-56 px-4 pt-10 pb-16 rounded-md bg-[#fff]">
        <div class="grid grid-cols-2 pt-4 pb-10 justify-between">
            <div class="grid">
                <span>Tài Khoản Thanh Toán</span>
                <span class="font-bold text-xl">8890743713</span>
            </div>
            <div class="flex justify-end items-center">
                <div class="grid justify-end">
                    <span>Số dư khả dụng</span>
                    <span class="font-bold text-xl">1,100,000 VND</span>
                </div>
                <div class="grid justify-end ml-4">
                    <button class="px-2 py-1 bg-gray-200 rounded-lg">
                        Account Lists <span class="font-bold">></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="grid grid-cols-4 gap-8">
            <a
                href="./transfer.jsp"
                class="flex flex-col items-center justify-center rounded-md h-28 bg-[#2a6ebe] text-sm font-bold text-white cursor-pointer"
                >
                <img src="./assets/transfer.png" class="w-8 mb-2" />
                Transfer
            </a>
            <a
                href="./transfer.jsp"
                class="flex flex-col items-center justify-center rounded-md h-28 bg-[#2a6ebe] text-sm font-bold text-white cursor-pointer"
                >
                <img src="./assets/salary.png" class="w-8 mb-2" />
                Saving Account
            </a>
            <a
                href="./transfer.jsp"
                class="flex flex-col items-center justify-center rounded-md h-28 bg-[#038883] text-sm font-bold text-white cursor-pointer"
                >
                <img src="./assets/loan.png" class="w-8 mb-2" />
                Loan
            </a>
            <a
                href="./transfer.jsp"
                class="flex flex-col items-center justify-center rounded-md h-28 bg-[#038883] text-sm font-bold text-white cursor-pointer"
                >
                <img src="./assets/reward.png" class="w-8 mb-2" />
                Reward
            </a>
        </div>
    </div>
</div>


<%@ include file="/includes/footer.jsp" %>
