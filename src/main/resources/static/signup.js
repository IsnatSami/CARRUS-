document.addEventListener('DOMContentLoaded', () => {
    const signupForm = document.querySelector('form');

    signupForm.addEventListener('submit', (e) => {
        e.preventDefault(); // Prevent default form submission

        try {
            // Collect form data
            const name = document.getElementById('signup-name').value.trim();
            const phone = document.getElementById('signup-phone').value.trim();
            const email = document.getElementById('email').value.trim();
            const address = document.getElementById('signup-address').value.trim();
            const nid = document.getElementById('signup-nid').value.trim();
            const dob = document.getElementById('signup-dob').value;
            const gender = document.getElementById('signup-gender').value;
            const userRole = document.getElementById('signup-userrole').value;
            const password = document.getElementById('password').value;

            // Simple validation
            if (!name || !phone || !email || !address || !nid || !dob || !gender || !userRole || !password) {
                alert('Please fill out all the fields.');
                return;
            }

            if (password.length < 8) {
                alert('Password must be at least 8 characters long.');
                return;
            }

            // Save data to the database (simulated here with localStorage)
            const user = {
                name,
                phone,
                email,
                address,
                nid,
                dob,
                gender,
                userRole,
                password
            };

            // Save user data
            const users = JSON.parse(localStorage.getItem('users')) || [];
            users.push(user);
            localStorage.setItem('users', JSON.stringify(users));

            alert('Signup successful! Redirecting to login page...');
            window.location.href = './login.html'; // Redirect to login page
        } catch (error) {
            console.error('An error occurred during signup:', error);
            alert('Something went wrong while processing your signup. Please try again.');
        }
    });
});
