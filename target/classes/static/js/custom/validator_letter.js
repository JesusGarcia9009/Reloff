$(document).ready(function() {
	$('#clientTable').dataTable({
		"paging":   true,
        "ordering": false,
        "info":     false
	});
});

$(".floats").on('keypress', function(e) {
	var theEvent = e || window.event;
	var key = theEvent.keyCode || theEvent.which;
	key = String.fromCharCode(key);
	var regex = /^[0-9]|\.$/;
	if (!regex.test(key)) {
		theEvent.returnValue = false;
		if (theEvent.preventDefault)
			theEvent.preventDefault();
	}
});

$("#CreateletterSave").click(
		function() {

			var checked = [];
			$("input[name='checkbox']:checked").each(function() {
				checked.push(($(this).attr("value")));
			});

			console.log(checked.toString());

			$("#clients").val(checked.toString());

			var location = $("#location").val();
			var price = $('#price').val() == '' ? 0 : parseInt($('#price')
					.val(), 10);
			var loanAmount = $('#loanAmount').val() == '' ? 0 : parseInt($(
					'#loanAmount').val(), 10);
			var taxes = $('#taxes').val() == '' ? 0 : parseFloat($('#taxes')
					.val());
			var loanTerm = $('#loanTerm').val() == '' ? 0 : parseInt($(
					'#loanTerm').val(), 10);
			var ltv = $('#ltv').val() == '' ? 0 : parseFloat($('#ltv').val());
			var maxPay = $('#maxPay').val() == '' ? 0 : parseFloat($('#maxPay')
					.val());
			var HOA = $('#HOA').val() == '' ? 0 : parseFloat($('#HOA').val());

			var interest = $('#interest').val() == '' ? 0 : parseFloat($(
					'#interest').val());
			var mi = $('#mi').val() == '' ? 0 : parseFloat($('#mi').val());
			var insurance = $('#insurance').val() == '' ? 0 : parseFloat($(
					'#insurance').val());

			
			var loancombo = $("#loancombo").val();
			var comboRealtor = $("#comboRealtor").val();

			var errores = false;

			$("#loanAmountSmall").text('');
			$("#TaxesSmall").text('');
			$("#LTVSmall").text('');
			$("#loanTermSmall").text('');
			$("#locationSmall").text('');
			$("#priceSmall").text('');
			$("#LoanTypeSmall").text('');
			$("#MaxPaySmall").text('');
			$("#clientSmall").text('');
			$("#interestSmall").text('');
			$("#insuranceSmall").text('');

			if (price < loanAmount) {
				$("#loanAmountSmall").text(
						"The Loan Amount can not be greater than the price");
				$("#loanAmountSmall").show();
				errores = true;
			}
			if (price < taxes) {
				$("#TaxesSmall").text(
						"The Taxes can not be greater than the price");
				$("#TaxesSmall").show();
				errores = true;
			}
			if (price < ltv) {
				$("#LTVSmall")
						.text("The LTV can not be greater than the price");
				$("#LTVSmall").show();
				errores = true;
			}
			if (price < loanTerm) {
				$("#loanTermSmall").text(
						"The Loan Term can not be greater than the price");
				$("#loanTermSmall").show();
				errores = true;
			}
			// if(price < HOA){
			// $("#HOASmall").text("The HOA can not be greater than the price");
			// $("#HOASmall").show();
			// errores = true;
			// }
			if (price < maxPay) {
				$("#MaxPaySmall").text(
						"The Max Pay can not be greater than the price");
				$("#MaxPaySmall").show();
				errores = true;
			}
			if (location == '') {
				$("#locationSmall").text("The Location is required");
				$("#locationSmall").show();
				errores = true;
			}
			if (price == 0) {
				$("#priceSmall").text("The price is required");
				$("#priceSmall").show();
				errores = true;
			}

			if (loanAmount == 0) {
				$("#loanAmountSmall").text("The Loan Amount is required");
				$("#loanAmountSmall").show();
				errores = true;
			}

			if (taxes == 0.0) {
				$("#TaxesSmall").text("The Taxes are required");
				$("#TaxesSmall").show();
				errores = true;
			}

			if (ltv <= 0) {
				$("#LTVSmall").text("The LTV is required");
				$("#LTVSmall").show();
				errores = true;
			}

			if (loanTerm <= 0) {
				$("#loanTermSmall").text("The Loan Term is required");
				$("#loanTermSmall").show();
				errores = true;
			}

			if (maxPay == 0.0) {
				$("#MaxPaySmall").text("The Max Pay is required");
				$("#MaxPaySmall").show();
				errores = true;
			}

			if (loancombo == "default") {
				$("#LoanTypeSmall").text("The Loan Type is required");
				$("#LoanTypeSmall").show();
				errores = true;
			}

			if (checked.toString() == "") {
				$("#clientSmall").text("At least a client is required");
				$("#clientSmall").show();
				errores = true;
			}
			// ultimos campos
			if (interest <= 0) {
				$("#interestSmall").text("The Interest is required");
				$("#interestSmall").show();
				errores = true;
			}

			if (mi <= 0) {
				$("#miSmall").text("       ");
				$("#miSmall").show();
				// errores = false;
			}

			if (insurance <= 0) {
				$("#insuranceSmall").text("The Insurance is required");
				$("#insuranceSmall").show();
				errores = true;
			}

			if (errores == true) {
				event.preventDefault()
			}

		});
