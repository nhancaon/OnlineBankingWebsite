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
    const PINDiv = document.getElementById("close-pin");

    function handleKeyDown(event) {
        // Check if the pressed key is 'Esc' (key code 27)
        if (event.keyCode === 27) {
            createAccountDiv.classList.add("hidden");
        }
    }

    // Add event listener for 'keydown' event on the document
    document.addEventListener("keydown", handleKeyDown);
});


const showCreateAccount = () => {
    const createAccountDiv = document.getElementById("create-account");

    createAccountDiv.classList.remove("hidden");
};


const showRecoveryPassowrd = () => {
    const forgotPasswordDiv = document.getElementById("forgot-password");

    forgotPasswordDiv.classList.remove("hidden");
};

const closeCreateAccount = () => {
    const createAccountDiv = document.getElementById("create-account");
    createAccountDiv.classList.add('hidden');
};