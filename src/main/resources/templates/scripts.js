// Toggle Hamburger Menu
function toggleMenu() {
    const menu = document.getElementById("menu");
    menu.style.display = menu.style.display === "block" ? "none" : "block";
}

// Show Login Form
function showLogin() {
    document.getElementById("signup-form").style.display = "none";
    document.getElementById("login-form").style.display = "block";
    document.getElementById("form-title").textContent = "Login";
}

// Show Sign-Up Form
function showSignUp() {
    document.getElementById("login-form").style.display = "none";
    document.getElementById("signup-form").style.display = "block";
    document.getElementById("form-title").textContent = "Sign Up";
}

// Redirect to After Payment Page
function redirectToAfterPayment(event) {
    event.preventDefault(); // Prevent default form submission
    window.location.href = "after_payment.html"; // Redirect to after_payment.html
}

// Animate Thank You Text (optional for after_payment.html)
function animateText() {
    const animatedText = document.querySelector(".animated-text");
    if (animatedText) {
        animatedText.style.transition = "opacity 2s";
        animatedText.style.opacity = 1;
    }
}

// Event Listener for Payment Form Submission (Payment Page)
document.addEventListener("DOMContentLoaded", function () {
    const paymentForm = document.querySelector("form");
    if (paymentForm) {
        paymentForm.addEventListener("submit", function (event) {
            const paymentMethod = document.querySelector("select").value;
            const amount = document.getElementById("amount").value;
            const transactionId = document.getElementById("transactionId").value;
            
            // Check if all fields are filled before redirecting
            if (!paymentMethod || !amount || (paymentMethod !== "credit-card" && !transactionId)) {
                alert("Please fill in all required fields.");
                return;
            }

            redirectToAfterPayment(event); // Call the function to handle the redirection
        });
    }

    // Call animation for the Thank You page
    animateText();
});

// Event listener for file input to preview the uploaded image (for the car photo input)
document.getElementById("car-photo").addEventListener("change", function (event) {
    const file = event.target.files[0];
    const previewContainer = document.getElementById("image-preview");
    previewContainer.innerHTML = ""; // Clear previous preview

    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            const img = document.createElement("img");
            img.src = e.target.result;
            img.alt = "Car Photo Preview";
            img.style.maxWidth = "100%";
            img.style.maxHeight = "150px";
            img.style.borderRadius = "10px";
            img.style.boxShadow = "0 0 5px rgba(0, 0, 0, 0.3)";
            previewContainer.appendChild(img);
        };
        reader.readAsDataURL(file);
    } else {
        previewContainer.innerHTML = "<p>No image selected</p>";
    }
});

// Preview Image (Generic for driver and car photos)
function previewPhoto(event) {
    const fileInputId = event.target.id; // Get the ID of the input field
    let photoId = 'driver-photo'; // Default to driver photo

    if (fileInputId === 'car-photo-upload') {
        photoId = 'car-photo-preview'; // Switch to car photo if the car input is used
    }

    const photo = document.getElementById(photoId);
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            photo.src = e.target.result; // Set the image source to the uploaded file
        };
        reader.readAsDataURL(file); // Read the file as a data URL
    }
}
