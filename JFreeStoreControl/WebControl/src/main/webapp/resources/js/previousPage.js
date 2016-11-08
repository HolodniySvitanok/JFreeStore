/**
 * 
 */

function previousPage(URL) {
	$.ajax({
		url : URL,
		contentType : 'application/json; charset=UTF-8',
		dataType : 'text',
		type : "POST",
		success : function(result) {

			document.location.href = result;

		},
		error : function() {

		}
	});
}