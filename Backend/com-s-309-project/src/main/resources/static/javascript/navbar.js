var modal;

// Get the button that opens the modal
var btn;

// Get the <span> element that closes the modal
var span;

// When the user clicks the button, open the modal 
var groceries;
var pendingGroceries = [];
var purchaseGroceries = [];

window.onload = function() {

    setInterval(function(){

	var requestURL = 'http://proj309-vc-05.misc.iastate.edu:8080/allGroceries';
	    var request = new XMLHttpRequest();
	    request.open('GET', requestURL);
	    request.responseType = 'json';
	    request.send();
	
	    request.onload = function() {
	    	
	    	  groceries = request.response;
	    	  for (var i=0; i < groceries.length;i++) {
	    		  
	    		  if (groceries[i].approval === 'F') {
	    			  
	    			  var isNotPushed = true;
	    			  for (var j=0; j < purchaseGroceries.length;j++) {

	    				  if (groceries[i].id == purchaseGroceries[j].id) {
	    					  isNotPushed = false;
	    				  }
	    				  
	    			  }
	    			  if (isNotPushed) {
	    				  
	    				  purchaseGroceries.push(groceries[i]);
	    				  $( "#toPurchase" ).append('<div class="groceryItemContainer" id=' + groceries[i].id + '><h4 class="containerComponents">' + groceries[i].groceryItem + '</h4><h4 class="containerComponents">$'+ groceries[i].groceryPrice+'</h4><h4 class="containerComponents">'+groceries[i].firstName+'</h4><input type="checkbox" class="containerComponents floatRight"></div>');

	    			  }
	    		  }
	    		  else {
	    			  
	    			  var isNotPushed = true;
	    			  for (var j=0; j < pendingGroceries.length;j++) {
	    				  
	    				  if (groceries[i].id == pendingGroceries[j].id) {
	    					  
	    					  isNotPushed = false;
	    				  }
	    			  }
	    			  if (isNotPushed) {
	    				  
	    				  pendingGroceries.push(groceries[i]);
	    				  $( "#pendingPurchase" ).append('<div class="groceryItemContainer" id=' + groceries[i].id + '><h4 class="containerComponents">' + groceries[i].groceryItem + '</h4><h4 class="containerComponents">$'+ groceries[i].groceryPrice+'</h4><h4 class="containerComponents">'+groceries[i].firstName+'</h4><input type="checkbox" checked="checked" class="containerComponents floatRight"></div>');

	    			  }
	    		  }
	
	    	  }
		}
    }, 1000);
    
};


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
 	document.getElementById('pItem1').value = '';
	document.getElementById('pItem2').value = '';
 	document.getElementById('pItem3').value = '';
	document.getElementById("gCheck1").checked = false;
	document.getElementById("gCheck2").checked = false;
	document.getElementById("gCheck3").checked = false;
    modal.style.display = "none";
    
    
}

function addGroceryItems() {
	
	var item1Text = document.getElementById('gItem1').value.toString();
	var item2Text = document.getElementById('gItem2').value.toString();
	var item3Text = document.getElementById('gItem3').value.toString();
	var price1 = document.getElementById('pItem1').value;
	var price2 = document.getElementById('pItem2').value;
	var price3 = document.getElementById('pItem3').value;
	var check1 = document.getElementById("gCheck1").checked;
	var check2 = document.getElementById("gCheck2").checked;
	var check3 = document.getElementById("gCheck3").checked;
	var url = 'http://proj309-vc-05.misc.iastate.edu:8080/addGroceryItem?';

	var item1= {
			
			groceryItem:null,
			groceryPrice:0,
			approved:null,
			firstName:'Rob',
			lastName:'Reinhard'
				
	};
	var item2= {
			
			groceryItem:null,
			groceryPrice:0,
			approved:null,
			firstName:'Rob',
			lastName:'Reinhard'
				
	};
	var item3= {
			
			groceryItem:null,
			groceryPrice:0,
			approved:null,
			firstName:'Rob',
			lastName:'Reinhard'
				
	};
	
	if (item1Text.length != 0 && !isNaN(price1)) {
		
		item1.groceryItem = item1Text;
		item1.groceryPrice = price1;
		
		
		if (check1 == true) {
			
			
			item1.approved = 'T';	
		}
		else {

			item1.approved = 'F';	

		}
		
		
		var str = [];
		for (var p in item1)
		if (item1.hasOwnProperty(p)) {
	      str.push(encodeURIComponent(p) + "=" + encodeURIComponent(item1[p]));
		}
		var encoded =  str.join("&");
		
		var requestURL = url + encoded;
	    var request = new XMLHttpRequest();
	    request.open('GET', requestURL);
	    request.send();
	
	    request.onload = function() {
	    	
	    	  console.log("Success");
	    	  
		}
	}
	if (item2Text.length != 0 && !isNaN(price2)) {
		
		item2.groceryItem = item2Text;
		item2.groceryPrice = price2;
		
		if (check2 == true) {
			
			item2.approved = 'T';	

			
		}
		else {

			item2.approved = 'F';	

			
		}
		
		var str = [];
		for (var p in item2)
		if (item2.hasOwnProperty(p)) {
	      str.push(encodeURIComponent(p) + "=" + encodeURIComponent(item2[p]));
		}
		var encoded =  str.join("&");
		
		var requestURL = url + encoded;
	    var request = new XMLHttpRequest();
	    request.open('GET', requestURL);
	    request.send();
	
	    request.onload = function() {
	    	
	    	  console.log("Success");
	    	  
		}
	}
	if (item3Text.length != 0 && !isNaN(price3)) {
		
		item3.groceryItem = item3Text;
		item3.groceryPrice = price3;	
		
		if (check3 == true) {
			
			item3.approved = 'T';	
			
			
		}
		else {

			item3.approved = 'F';	

			
		}
		var str = [];
		for (var p in item3)
		if (item3.hasOwnProperty(p)) {
	      str.push(encodeURIComponent(p) + "=" + encodeURIComponent(item3[p]));
		}
		var encoded =  str.join("&");
		
		var requestURL = url + encoded;
	    var request = new XMLHttpRequest();
	    request.open('GET', requestURL);
	    request.send();
	
	    request.onload = function() {
	    	
	    	  console.log("Success");
	    	  
		}
	}	

	closed();
}

// When the user clicks anywhere outside of the modal, close it
