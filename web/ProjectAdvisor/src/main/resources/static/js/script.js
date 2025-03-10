const container = document.getElementById('container');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');

registerBtn.addEventListener('click', () => {
    container.classList.add("active");
});

loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
});


//toast erreur message

document.addEventListener('DOMContentLoaded', function () {
    const toast = document.getElementById('errorToast');
    if (toast) {
        toast.classList.add('show'); // Show the toast

        // Hide the toast after 3 seconds
        setTimeout(() => {
            toast.classList.remove('show');
        }, 3000);
    }
});

// details display

function toggleDetails(button) {
    const details = button.nextElementSibling;
    details.style.display = details.style.display === "none" ? "block" : "none";
}
