let stap1 = document.getElementById("stap1");
let stap2 = document.getElementById("stap2");
let button = document.getElementById("button");

button.addEventListener("click", function(event) {
                                     event.preventDefault();

                                     let name = document.getElementById('form-name').value;
                                     let email = document.getElementById('form-email').value;

                                     let output = document.getElementById('form-output');
                                     output.textContent = '✅ Form submitted!\nNaam: ' + name + '\nEmail: ' + email;
                                     output.style.display = 'block';

                                     console.log('Form data:', {name, email});
                                 });


