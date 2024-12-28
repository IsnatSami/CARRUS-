document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('car-registration-form');
    const photoInput = document.getElementById('car-photo-upload');
  //  const availableCarsList = document.getElementById('available-cars-list'); // Assuming you have a div to list available cars

    // Fetch available cars on page load
    fetch('http://localhost:8080/api/cars/available')
        .then(response => response.json())
        .then(data => {
            if (data.length > 0) {
                availableCarsList.innerHTML = ''; // Clear any previous cars listed
                data.forEach(car => {
                    const carItem = document.createElement('li');
                    carItem.textContent = `Car ID: ${car.id}, Registration Number: ${car.registrationNumber}, Make: ${car.make}, Model: ${car.model}`;

                    // Create a button to book the car
                    const bookButton = document.createElement('button');
                    bookButton.textContent = 'Book Car';
                    bookButton.onclick = function () {
                        bookCar(car.id); // Call function to book the car
                    };

                    carItem.appendChild(bookButton);
                    availableCarsList.appendChild(carItem);
                });
            } else {
                availableCarsList.innerHTML = 'No available cars at the moment.';
            }
        })
        .catch(error => {
            console.error('Error fetching available cars:', error);
            availableCarsList.innerHTML = 'Error loading available cars.';
        });

    // Register car functionality
    form.addEventListener('submit', function (e) {
        e.preventDefault(); // Prevent form from submitting normally
        
        // Get user id
        const userId = localStorage.getItem('user_id');
        if (!userId) {
            alert('You must log in to register a car.');
            window.location.href = '/login.html';
            return;
        }

        // Create a FormData object to send both fields and file
        const formData = new FormData();
        formData.append('registration-number', document.getElementById('registration-number').value);
        formData.append('make', document.getElementById('make').value);
        formData.append('model', document.getElementById('model').value);
        formData.append('year', document.getElementById('year').value);
        formData.append('category', document.getElementById('category').value);
        formData.append('seat-capacity', parseInt(document.getElementById('seat-capacity').value)); // Convert to integer

        // Append the uploaded file (if any)
        formData.append('car-photo-upload', photoInput.files[0]);

        // Send the form data via fetch to register the car
        fetch('http://localhost:8080/api/cars/register', {
            method: 'POST',
            headers: {
                'logged-in-user-id': userId, // Include the required header
            },
            body: formData, // Send as FormData
        })
        .then(response => {
            if (response.ok) {
                return response.json().catch(() => null); // Handle empty responses
            } else {
                return response.text().then(err => {
                    throw new Error(err || `HTTP error ${response.status}`);
                });
            }
        })
        .then(data => {
            alert('Car registered successfully!');
            console.log('Car data:', data);
            window.location.href = '/home.html';
        })
        .catch(error => {
            alert('Error registering car: ' + error.message);
            console.error('Error:', error);
        });
    });

    // Function to book a car
    function bookCar(carId) {
        const userId = localStorage.getItem('user_id');
        if (!userId) {
            alert('You must log in to book a car.');
            window.location.href = '/login.html';
            return;
        }

        fetch(`http://localhost:8080/api/cars/book/${carId}`, {
            method: 'PUT',
            headers: {
                'logged-in-user-id': userId, // Include user ID in the header
            },
        })
        .then(response => {
            if (response.ok) {
                alert('Car booked successfully!');
                // Optionally, refresh the available cars list
                window.location.reload();
            } else {
                return response.text().then(err => {
                    throw new Error(err || `HTTP error ${response.status}`);
                });
            }
        })
        .catch(error => {
            alert('Error booking car: ' + error.message);
            console.error('Error:', error);
        });
    }
});
