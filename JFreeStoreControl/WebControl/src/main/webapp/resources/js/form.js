function sendUser(Context, URL) {
	var login = $('#login').val();
	var password = $('#password').val();

	password = hex_md5(password);

	var user = JSON.stringify({
		login : login,
		password : password

	});

	$.ajax({
		url : URL,
		contentType : 'application/json; charset=UTF-8',
		dataType : 'text',

		type : "POST",
		data : user,
		success : function(result) {

			if (result === "ok") {
				document.location.href = Context;
			} else {
				$("#error").html(
						'<div class="alert alert-danger"><strong>' + result
								+ '</strong></div>')
			}

		},
		error : function() {

		}
	});

}

function actualExchangerate(URL) {

	$.ajax({
		url : URL,
		contentType : 'application/json; charset=UTF-8',
		dataType : 'text',

		type : "POST",

		success : function(result) {

			$("#sellExchange").val(result)

		},
		error : function() {

		}
	});

}
