let firstName = document.getElementById("inputFirstName");
let lastName = document.getElementById("inputLastName");
let password = document.getElementById("inputPassword");
let email = document.getElementById("inputEmail");
let identificationType = document.getElementById("inputIdentificationType");
let identification = document.getElementById("inputIdentification");
let phone = document.getElementById("inputPhone");
let gender = document.getElementById("inputGender");
let promotion = document.getElementById("inputPromotion");
let image = document.getElementById("inputImage");
let cambio = 0;

function removeReadOnly(){
    if(cambio == 0){
        firstName.removeAttribute("readonly", false);
        lastName.removeAttribute("readonly", false);
        password.removeAttribute("readonly", false);
        email.removeAttribute("readonly", false);
        identificationType.removeAttribute("readonly", false);
        identification.removeAttribute("readonly", false);
        phone.removeAttribute("readonly", false);
        gender.removeAttribute("readonly", false);
        promotion.removeAttribute("readonly", false);
        image.removeAttribute("readonly", false);
        cambio = 1;
    }else if(cambio == 1){
        firstName.setAttribute("readonly", true);
        lastName.setAttribute("readonly", true);
        password.setAttribute("readonly", true);
        email.setAttribute("readonly", true);
        identificationType.setAttribute("readonly", true);
        identification.setAttribute("readonly", true);
        phone.setAttribute("readonly", true);
        gender.setAttribute("readonly", true);
        promotion.setAttribute("readonly", true);
        image.setAttribute("readonly", true);
        cambio = 0;
    }

}
