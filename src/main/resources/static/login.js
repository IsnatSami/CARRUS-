// Ensure the DOM is fully loaded before running the script
document.addEventListener('DOMContentLoaded', function () {
    // Function to handle sign-up
    function signUp() {
        const user = {
            name: document.getElementById('signup-name').value,
            email: document.getElementById('email').value,
            password: document.getElementById('password').value,
            phone: document.getElementById('signup-phone').value,
            address: document.getElementById('signup-address').value,
            nid: document.getElementById('signup-nid').value,
            dob: document.getElementById('signup-dob').value,
            gender: document.getElementById('signup-gender').value,
            userRole: document.getElementById('signup-userrole').value,
        };

        // Debugging: Log user data to the console
        console.log("User data to be sent:", user);

        fetch('http://localhost:8080/api/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user),
        })
            .then(response => {
                if (!response.ok) {
                    console.log('Response Status:', response.status); // Log response status
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                console.log("Server response:", data);
                alert(data); // Show the response as an alert
                if (data.message === 'Sign-up successful' && data.userId) {
                    localStorage.setItem('user_id', data.userId); // Store the user ID locally
                    alert(data.message); // Show success message
                    document.getElementById('signupForm').reset(); // Reset the form
                    window.location.href = '/login.html'; // Redirect to login page
                } 
            })
            .catch(error => console.error('Error:', error));
            //alert('Sign-up failed. Please try again.');
    }

    // Function to handle login
    function login() {
        const credentials = {
            email: document.getElementById('login-email').value,
            password: document.getElementById('login-password').value,
        };

        // Debugging: Log login credentials to the console
        console.log("Login credentials:", credentials);

        fetch('http://localhost:8080/api/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(credentials),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                console.log("Server response:", data);
                alert(JSON.stringify(data));  // Show the response as an alert
                if (data.message === 'Login successful' && data.userId) {
                    localStorage.setItem('user_id', data.userId); // Store the user ID locally
                    alert(data.message); // Show success message
                    window.location.href = '/home.html'; // Redirect to home page
                }
            })
            .catch(error => console.error('Error:', error));
           // alert('Login failed. Please check your credentials.');
    }

    // Function to toggle password visibility
    function togglePasswordVisibility() {
        const passwordInput = document.getElementById('password') || document.getElementById('login-password');
        if (passwordInput.type === 'password') {
            passwordInput.type = 'text';
        } else {
            passwordInput.type = 'password';
        }
    }

    // Expose the functions globally for form submission
    window.signUp = signUp;
    window.login = login;
    window.togglePasswordVisibility = togglePasswordVisibility;
});
