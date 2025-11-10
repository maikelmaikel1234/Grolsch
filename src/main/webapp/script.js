let stap1 = document.getElementById("stap1");
let stap2 = document.getElementById("stap2");
let stap3 = document.getElementById("stap3");
let step1Form = document.getElementById("step1Form");
let step2Form = document.getElementById("step2Form");
let naam = document.getElementById("naam");
let email = document.getElementById("email");
let postcode = document.getElementById("postcode");
let huisnummer = document.getElementById("huisnummer");
let naamResult = document.getElementById("naam_result");
let emailResult = document.getElementById("email_result");
let postcodeResult = document.getElementById("postcode_result");
let huisnummerResult = document.getElementById("huisnummer_result");
let feedback = document.getElementById('feedback');

step1Form.addEventListener("submit", function(event) {
    event.preventDefault();
    updateFeedback("Stap " + (1) + " is verzonden.");
    showStep(2);
});

step2Form.addEventListener("submit", function(event) {
    event.preventDefault();
    submitStep(2);
    naamResult.textContent = naam.value
    emailResult.textContent = email.value
    postcodeResult.textContent = postcode.value
    huisnummerResult.textContent = huisnummer.value
});

function showLoading() {
    let loading = document.getElementById('loading');
    if (loading) {
        console.log("show loading");
        loading.style.display = 'block';
    }
}

/**
 * Hide loading indicator
 */
function hideLoading() {
    let loading = document.getElementById('loading');
    if (loading) {
        loading.style.display = 'none';
    }
}

function submitStep(stepNumber) {
    showLoading();
    data = {
        naam: naam.value,
        email: email.value,
        postcode: postcode.value,
        huisnummer: huisnummer.value
    };

    fetch('http://localhost:8080/ajaxHome', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'  // JSON!
        },
        body: JSON.stringify(data)  // Convert to JSON string
    })
        .then(response => response.json())
        .then(data => {
            setTimeout(() => {
                console.log('2 seconden wachten om loading te tonen');
                this.hideLoading();
            }, 2000);
            // if (onSuccess) onSuccess(data);
        })
        .catch(error => {
            setTimeout(() => {
                console.log('2 seconden wachten om loading te tonen');
                this.hideLoading();
            }, 2000);
            // console.error('AJAX error:', error);
            // if (onError) onError(error);
        });
    showStep(++stepNumber);
    updateFeedback("Stap " + (stepNumber) + " is verzonden.");
}

function showStep(stepNumber){
    switch (stepNumber){
        case 1:
            stap1.classList.remove('invisible');
            stap2.classList.add('invisible');
            stap3.classList.add('invisible');
            console.log(stepNumber);
            break;
        case 2:
            stap1.classList.add('invisible');
            stap2.classList.remove('invisible');
            stap3.classList.add('invisible');
            console.log(stepNumber);
            break;
        case 3:
            stap1.classList.add('invisible');
            stap2.classList.add('invisible');
            stap3.classList.remove('invisible');
            console.log(stepNumber);
            break;
    }
}

function updateFeedback(mesage){
    feedback.textContent = mesage;
}

