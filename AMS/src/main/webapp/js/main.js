function clearErrors() {
    document.querySelectorAll(".error").forEach(el => el.innerText = "");
}

function showFieldError(fieldId, message) {
    const field = document.getElementById(fieldId);
    if (field) {
        let errorSpan = field.nextElementSibling;
        if (errorSpan && errorSpan.classList.contains("error")) {
            errorSpan.innerText = message;
        }
    }
}

function validateForm() {
    clearErrors();
    let isValid = true;

    const firstName = document.getElementById("firstName");
    const lastName = document.getElementById("lastName");
    const dob = document.getElementById("dob");
    const emailId = document.getElementById("emailId");
    const address1 = document.getElementById("address1");
    const phone = document.getElementById("phone");
    const city = document.getElementById("city");
    const state = document.getElementById("state");
    const country = document.getElementById("country");
    const zipCode = document.getElementById("zipCode");

    if (!firstName.value.trim()) {
        showFieldError("firstName", "First Name is required");
        isValid = false;
    }

    if (!lastName.value.trim()) {
        showFieldError("lastName", "Last Name is required");
        isValid = false;
    }

    if (!dob.value.trim()) {
        showFieldError("dob", "Date of Birth is required");
        isValid = false;
    }

    if (!emailId.value.trim()) {
        showFieldError("emailId", "Email ID is required");
        isValid = false;
    } else {
        const emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
        if (!emailPattern.test(emailId.value.trim())) {
            showFieldError("emailId", "Enter a valid email address");
            isValid = false;
        }
    }

    if (!address1.value.trim()) {
        showFieldError("address1", "Address is required");
        isValid = false;
    }

    if (!phone.value.trim()) {
        showFieldError("phone", "Contact Number is required");
        isValid = false;
    } else if (!/^\d{10}$/.test(phone.value.trim())) {
        showFieldError("phone", "Enter a valid 10-digit phone number");
        isValid = false;
    }

    if (!city.value.trim()) {
        showFieldError("city", "City is required");
        isValid = false;
    }

    if (!state.value.trim()) {
        showFieldError("state", "State is required");
        isValid = false;
    }

    if (!country.value.trim()) {
        showFieldError("country", "Country is required");
        isValid = false;
    }

    if (!zipCode.value.trim()) {
        showFieldError("zipCode", "ZIP Code is required");
        isValid = false;
    }

    return isValid;
}
