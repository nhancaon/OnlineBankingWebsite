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

    const createAccountBtn = document.getElementById("createAccountBtn");
    const createAccountDiv = document.getElementById("create-account");

    createAccountBtn.addEventListener("click", function () {
        // Toggle the visibility of the create-account div
        createAccountDiv.classList.toggle("hidden");
    });

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


const toggleCreateAccount = () => {
    const createAccountDiv = document.getElementById("create-account");

    // Toggle the visibility of the create-account div
    createAccountDiv.classList.toggle("hidden");
};

