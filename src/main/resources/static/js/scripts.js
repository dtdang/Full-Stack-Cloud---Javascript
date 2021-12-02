/*!
* Start Bootstrap - Shop Homepage v5.0.3 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2021 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank

$(document).ready(function(){
	$('#form-email').submit(function(ev){
		// This prevents the form from doing its default submit behavior
		ev.preventDefault();

		// Use Javascript to post the email to the server instead of posting to a new page

		//First, get email address from form
		var form = $(ev.currentTarget);
		var email = form.find('input[name="email"]').val();

		// Send the email as JSON data
		const data = {email: email};

		fetch(form.attr('action'), {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(data),
		})
		.then(response => response.json())
		.then(data => {
			console.log(data);
			$('#staticBackdrop').modal('show')
		})
		.catch((error) => {
			console.error('Error:', error);
		});
	});
});

$(document).ready(function(){
	$('#product-form').submit(function(ev){
		// This prevents the form from doing its default submit behavior
		ev.preventDefault();

		var form = $(ev.currentTarget);
		if ($("#prodID").val() === "" || $("#prodName").val() === "" ||
			$("#prodStock").val() === ""  || $("#prodPrice").val() === "" ){
			$('#missingProductBackdrop').modal('show')
			return;
		}
		let data = {
			id: $("#prodID").val(),
			name: $("#prodName").val(),
			stock: $("#prodStock").val(),
			price: $("#prodPrice").val()
		}

		fetch(form.attr('action'), {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(data),
		})
		.then(response => response.json())
		.then($('#productBackdrop').modal('show'))
		.catch((error) => {
			console.error('Error:', error);
		});
	});
});

function openForm(){
	document.getElementById("productForm").style.display="block";
}

function closeForm(){
	document.getElementById("productForm").style.display="none";
}


