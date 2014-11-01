$(document).ready(function () {
	$("#loading").hide();
	$("#resultsSection").hide();
	$('.form-control').change(function(){
		console.log("Validating input params");
		var budget = $('#budgetFieldId').val();
		var numOfItems = $('#numOfItemsFieldId').val();
		console.log("numOfItems :" + numOfItems);
		console.log("budget :" + budget);
		if($.isNumeric(budget) && $.isNumeric(numOfItems)){
			//Clear previous results
			$('#tableColumnsFieldId').html("");
			$("#resultsRowParentFieldId").html("");
			
			$('#tableColumnsFieldId').append("<th>Price</th>");
		 	for (var i =  0; i < numOfItems; i++) {
		 		$('#tableColumnsFieldId').append("<th> Product" + (i+1) + "</th>");
		 		console.log("i :"+i);
		 	};
		 	$("#resultsSection").show();
		 	$("#loading").show();
			$.get( "suggestedResults/10/99.99", function( data ) {
			  console.log( data );
			  $.each(data, function( index, result ) {
				  var idForTR = "row"+index;
				  $("#resultsRowParentFieldId").append("<tr id="+idForTR+"></tr>");
				  $("#"+idForTR).append("<td>"+result.price.toFixed(2)+"</td>");
				  $.each(result.products, function( productIndex, product ) {
					  $("#"+idForTR).append("<td><a href="+product.productUrl+" target='_blank'>"+product.name+"</a></td>");
				  });
			  });
			  $("#loading").hide();
			});		 	
		}
	});
});	