document.addEventListener('DOMContentLoaded', function () {
    var avatar = document.getElementById('avatar');
    var dropdown = document.getElementById('dropdown');

    avatar.addEventListener('click', function () {
        // Toggle the visibility of the dropdown
        dropdown.style.display = (dropdown.style.display === 'block') ? 'none' : 'block';
    });

    // Close the dropdown when clicking outside of it
    document.addEventListener('click', function (event) {
        if (!avatar.contains(event.target) && !dropdown.contains(event.target)) {
            dropdown.style.display = 'none';
        }
    });

    const createAccountDiv = document.getElementById("create-account");

    function handleKeyDown(event) {
        // Check if the pressed key is 'Esc' (key code 27)
        if (event.keyCode === 27) {
            // Hide the create-account div
            createAccountDiv.classList.add("hidden");
        }
    }

    // Add event listener for 'keydown' event on the document
    document.addEventListener("keydown", handleKeyDown);
});


const showCreateAccount = () => {
    const createAccountDiv = document.getElementById("create-account");

    createAccountDiv.classList.toggle("hidden");

    const createAccountContent = document.querySelector('.create-account .content');

    createAccountContent.innerHTML = `
    <form action="Transfer" method="post">
                   <input type="hidden" name="action" value="add">
                       <div class="relative mt-6">
                           <input
                               type="text"
                               id="paymentAccountNumber"
                               name="acNumber"
                               class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                               placeholder=" "
                               />
                           <label
                               for="paymentAccountNumber"
                               class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                               >Payment Account Number</label
                           >
                       </div>
                        <div class="relative mt-6">
                           <input
                               type="text"
                               id="pinNumber"
                               name="pinNumber"
                               class="block px-2.5 pb-2.5 pt-4 w-full text-sm bg-transparent rounded-lg border-2 border-black appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                               placeholder=" "
                               />
                           <label
                               for="pinNumber"
                               class="absolute text-sm bg-white text-gray-500 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
                               >PIN Number</label
                           >
                       </div>
                       <div class="flex justify-end items-center mt-10">
                           <button class="px-16 py-3 rounded-md bg-gradient-to-r from-[#00bfae] to-[#0066ad] text-white">Continue</button>
                       </div>
    </form>
    `
};

const closeCreateAccount = () => {
    const createAccountDiv = document.getElementById("create-account");
    createAccountDiv.classList.add('hidden');
}