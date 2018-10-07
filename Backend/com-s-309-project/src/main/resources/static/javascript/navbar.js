var modal;

// Get the button that opens the modal
var btn;

// Get the <span> element that closes the modal
var span;

// When the user clicks the button, open the modal 


function theTest() {
	
	modal = document.getElementById('myModal');
	btn = document.getElementById("myBtn");
	document.getElementsByClassName("close")[0];
    modal.style.display = "block";
    
}

// When the user clicks on <span> (x), close the modal
function closed() {
	
	document.getElementById('gItem1').value = '';
	document.getElementById('gItem2').value = '';
 	document.getElementById('gItem3').value = '';
	document.getElementById("gCheck1").checked = false;
	document.getElementById("gCheck2").checked = false;
	document.getElementById("gCheck3").checked = false;
    modal.style.display = "none";
    
}

function addGroceryItems() {
	
	var item1Text = document.getElementById('gItem1').value;
	var item2Text = document.getElementById('gItem2').value;
	var item3Text = document.getElementById('gItem3').value;
	var check1 = document.getElementById("gCheck1").checked;
	var check2 = document.getElementById("gCheck2").checked;
	var check3 = document.getElementById("gCheck3").checked;

	if (item1Text.length != 0) {
		
		if (check1 == true) {
			
			$( "#pendingPurchase" ).append('<div class="groceryItemContainer"><h4 class="containerComponents">' + item1Text+ '</h4> <input type="checkbox" checked="checked" class="containerComponents floatRight"></div>');

		}
		else {
			$( "#toPurchase" ).append('<div class="groceryItemContainer"><h4 class="containerComponents">' + item1Text+ '</h4> <input type="checkbox" class="containerComponents floatRight"></div>');

		}
	}
	if (item2Text.length != 0) {
		
		if (check2 == true) {
			
			$( "#pendingPurchase" ).append('<div class="groceryItemContainer"><h4 class="containerComponents">' + item2Text+ '</h4> <input type="checkbox" checked="checked" class="containerComponents floatRight"></div>');

		}
		else {
			$( "#toPurchase" ).append('<div class="groceryItemContainer"><h4 class="containerComponents">' + item2Text+ '</h4> <input type="checkbox" class="containerComponents floatRight"></div>');

		}
	}
	if (item3Text.length != 0) {
		
		if (check3 == true) {
			
			$( "#pendingPurchase" ).append('<div class="groceryItemContainer"><h4 class="containerComponents">' + item3Text+ '</h4> <input type="checkbox" checked="checked" class="containerComponents floatRight"></div>');

		}
		else {
			$( "#toPurchase" ).append('<div class="groceryItemContainer"><h4 class="containerComponents">' + item3Text+ '</h4> <input type="checkbox" class="containerComponents floatRight"></div>');

		}
	}	

	closed();
}

// When the user clicks anywhere outside of the modal, close it
