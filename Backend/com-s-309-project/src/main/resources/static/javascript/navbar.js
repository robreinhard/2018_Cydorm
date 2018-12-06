var modal;

// Get the button that opens the modal
var btn;

// Get the <span> element that closes the modal
var span;

// When the user clicks the button, open the modal 

var pendingGroceries = [];
var purchaseGroceries = [];
var toDoChores = [];
var pendingChores = [];
var pendingDisputes = [];
var pastDisputes = [];
var stompClient = null;

window.onload = function() {
	
	var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/allChores', function(theChores) {
        	
    		updateChoresList(JSON.parse(theChores.body));
     
        	
        });
        stompClient.subscribe('/allGroceries', function(theList) {
        	updateGroceryList(JSON.parse(theList.body));
        });
        
        stompClient.subscribe('/allDisputes', function(theDisputes) {
        	updateDisputeList(JSON.parse(theDisputes.body));
        });
        
        
    	stompClient.send("/dumpGrocery",{},JSON.stringify({'netID': netID}));
    	stompClient.send("/dumpChore",{},JSON.stringify({'netID': netID}));
    	stompClient.send("/dumpDispute",{},JSON.stringify({'netID': netID}));
    });
};   

function updateDisputeList(disputes) {
	
	for (var i=0; i < disputes.length;i++) {
		console.log("disputes length: " + disputes.length);
		console.log("DISPUTE LIST: ");
		console.log(disputes[i]);
		if ( disputes[i].resolved === 'F' && ( ( disputes[i].visability === 'F') && ((disputes[i].studentID === netID) || (disputes[i].visability === 'T') ) ) ) {

		  var isNotPushed = true;
		  for (var j=0; j < pendingDisputes.length;j++) {

			  if (disputes[i].dispute_id == pendingDisputes[j].dispute_id) {
				  console.log("it is false");
				  isNotPushed = false;
			  }
				  
		  }
		  if (isNotPushed) {
				  
			  pendingDisputes.push(disputes[i]);
			  console.log("DISPUTE PUSHED: ");
			  console.log(disputes[i]);
			  $( "#pendingDisputes" ).append('<div class="disputeItemContainer" id=d' + disputes[i].dispute_id + '"><h4 class="containerComponents">' + disputes[i].disputeName + '</h4><h4 class="containerComponents">'+disputes[i].studentID+'</h4><input type="checkbox" class="containerComponents floatRight">' + '<br><p class="disputeBodyComponent">' + disputes[i].disputeBody + '</p></div>');

		  }
		}
		else if ( disputes[i].resolved === 'T' && ( ( disputes[i].visability === 'F') && ((disputes[i].studentID === netID) || (disputes[i].visability === 'T') ) ) ){
			  
		  var isNotPushed = true;
		  for (var j=0; j < pastDisputes.length;j++) {
				  
			  if (disputes[i].dispute_id == pastDisputes[j].dispute_id) {
					  
			  	isNotPushed = false;
			  	
			  }
		  }
		  if (isNotPushed) {
				  
			  	pastDisputes.push(disputes[i]);

				$( "#pastDisputes" ).append('<div class="disputeItemContainer" id=d' + disputes[i].dispute_id + '"><h4 class="containerComponents">' + disputes[i].disputeName + '</h4><h4 class="containerComponents">'+disputes[i].studentID+'</h4><input type="checkbox" class="containerComponents floatRight">' + '<br><p class="disputeBodyComponent">' + disputes[i].disputeBody + '</p></div>');

		  }
		}
	}
		var allDisputes = pastDisputes.concat(pendingDisputes);
		  var toBeRemoved = allDisputes.filter(function(g) {

			for (var k=0; k < disputes.length;k++) {

			  if (disputes[k].dispute_id == g.dispute_id) {
				  return false;
			  }
			  
			}
			
			
			return true;
		  
		  });
		  

		  console.log(toBeRemoved);
			
		  for (var l=0; l < toBeRemoved.length;l++) {
			console.log(toBeRemoved[l]);
			if (toBeRemoved.length > 0) {
				
				var toRemove = "#d" + toBeRemoved[l].dispute_id.toString();
				console.log(toRemove);
				$(toRemove).remove();
			}
			

			
		  }
}

function updateChoresList(chores) {
	
	for (var i=0; i < chores.length;i++) {

		if (chores[i].completed === 'F') {
			  
		  var isNotPushed = true;
		  for (var j=0; j < toDoChores.length;j++) {
			//alert(chores[i].chore_id);	//debug
			  if (chores[i].chore_id == toDoChores[j].chore_id) {
				  
				  isNotPushed = false;
			  }
				  
		  }
		  if (isNotPushed) {
				  
			  toDoChores.push(chores[i]);
			  var date = new Date(chores[i].dueDate);
			  
			  $( "#toDoChores" ).append('<div class="choreItemContainer" id=c' + chores[i].chore_id + ' onclick="modifyRemovePromptChore('+ chores[i].chore_id + ')"><h4 class="containerComponents">' + chores[i].chore + '</h4><h4 class="containerComponents">'+ (date.getMonth()+1) + '/' + date.getDate()+'</h4><h4 class="containerComponents">'+chores[i].studentID+'</h4><input type="checkbox" class="containerComponents floatRight"></div>');

		  }
		}
		else {
			  
		  var isNotPushed = true;
		  for (var j=0; j < pendingChores.length;j++) {
				  
			  if (chores[i].chore_id == pendingChores[j].chore_id) {
					  
			  	isNotPushed = false;
			  	
			  }
		  }
		  if (isNotPushed) {
				  
			  	pendingChores.push(chores[i]);
			  	var date = new Date(chores[i].dueDate);

			  	$( "#pendingChores" ).append('<div class="choreItemContainer" id=c' + chores[i].chores_id + ' onclick="modifyRemovePromptChore('+ chores[i].chore_id + ')"><h4 class="containerComponents">' + chores[i].chore + '</h4><h4 class="containerComponents">'+ (date.getMonth()+1) + '/' + date.getDate()+'</h4><h4 class="containerComponents">'+chores[i].studentID+'</h4><input type="checkbox" checked="checked" class="containerComponents floatRight"></div>');

		  }
		}
	}
		var allChores = pendingChores.concat(toDoChores);
		console.log(allChores);
		  var toBeRemoved = allChores.filter(function(c) {

			for (var k=0; k < chores.length;k++) {
				//alert(c.chore_id);
			  if (chores[k].chore_id == c.chore_id) {
				  console.log(chores[k].chore_id + ":" + c.chore_id);
				  return false;
			  }
			  
			}
			
			
			return true;
		  
		  });
		  

		  console.log("hello");
		  console.log(toBeRemoved);
			
		  for (var l=0; l < toBeRemoved.length;l++) {
			console.log(toBeRemoved[l]);
			if (toBeRemoved.length > 0) {
				
				var toRemove = "#c" + toBeRemoved[l].chore_id.toString();
				console.log(toRemove);
				$(toRemove).remove();
			}
			

			
		  }
}


function updateGroceryList(groceries) {
	
	for (var i=0; i < groceries.length;i++) {
		console.log(groceries[i].approval);
		if (groceries[i].approval === 'F') {
			  
		  var isNotPushed = true;
		  for (var j=0; j < purchaseGroceries.length;j++) {

			  if (groceries[i].id == purchaseGroceries[j].id) {
				  
				  isNotPushed = false;
			  }
				  
		  }
		  if (isNotPushed) {
				  
			  purchaseGroceries.push(groceries[i]);
			  $( "#toPurchase" ).append('<div class="groceryItemContainer" id=g' + groceries[i].id + ' onclick="modifyRemovePromptGrocery('+ groceries[i].id + ')"><h4 class="containerComponents">' + groceries[i].groceryItem + '</h4><h4 class="containerComponents">$'+ groceries[i].groceryPrice+'</h4><h4 class="containerComponents">'+groceries[i].studentID+'</h4><input type="checkbox" class="containerComponents floatRight"></div>');

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
			  	$( "#pendingPurchase" ).append('<div class="groceryItemContainer" id=g' + groceries[i].id + ' onclick="modifyRemovePromptGrocery('+ groceries[i].id + ')"><h4 class="containerComponents">' + groceries[i].groceryItem + '</h4><h4 class="containerComponents">$'+ groceries[i].groceryPrice+'</h4><h4 class="containerComponents">'+groceries[i].studentID+'</h4><input type="checkbox" checked="checked" class="containerComponents floatRight"></div>');

		  }
		}

  }
	 
  var allGroceries = pendingGroceries.concat(purchaseGroceries);

  var toBeRemoved = allGroceries.filter(function(g) {

	for (var k=0; k < groceries.length;k++) {

	  if (groceries[k].id == g.id) {
		  
		  return false;
	  }
	  
	}
  
	return true;
  
  });
  
  console.log(toBeRemoved);
	
  for (var l=0; l < toBeRemoved.length;l++) {
	
	if (toBeRemoved.length > 0) {
		
		var toRemove = "#g" + toBeRemoved[l].id.toString();
		$(toRemove).remove();
	}
	

	
  }
	
}



function groceryAdd() {
	
	modal = document.getElementById('groceryAddModal');
	btn = document.getElementById("myBtn");
	document.getElementsByClassName("close")[0];
    modal.style.display = "block";
    
}

function choreAdd() {
	
	modal = document.getElementById('choreAddModal');
	btn = document.getElementById("myBtn");
	document.getElementsByClassName("close")[0];
    modal.style.display = "block";
    
}


function disputeAdd() {
	
	modal = document.getElementById('disputeAddModal');
	btn = document.getElementById("myBtn");
	document.getElementsByClassName("close")[0];
	modal.style.display="block";
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
		var approved;
		
		if (check1 == true) {
			
			
			approved = 'T';	
		}
		else {

			approved = 'F';	

		}
		
		stompClient.send("/addGroceryItem",{},JSON.stringify({'groceryItem': item1Text,'groceryPrice': price1,'approved' : approved,'studentID': netID}));
		
		/*
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
	    	  
		}*/
	}
	if (item2Text.length != 0 && !isNaN(price2)) {
		
		item2.groceryItem = item2Text;
		item2.groceryPrice = price2;
		var approved;

		if (check2 == true) {
			
			approved = 'T';	

			
		}
		else {

			approved = 'F';	

			
		}
		/*
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
	    	  
		}*/
		
		stompClient.send("/addGroceryItem",{},JSON.stringify({'groceryItem': item2Text,'groceryPrice': price2,'approved' : approved,'studentID': netID}));

	}
	if (item3Text.length != 0 && !isNaN(price3)) {
		
		item3.groceryItem = item3Text;
		item3.groceryPrice = price3;	
		var approved; 
		
		if (check3 == true) {
			
			approved = 'T';	
			
			
		}
		else {

			approved = 'F';	

			
		}
		/*
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
	    	  
		}*/
		stompClient.send("/addGroceryItem",{},JSON.stringify({'groceryItem': item3Text,'groceryPrice': price3,'approved' : approved,'studentID': netID}));
	
	}	

	closed();
}
var result;

function modifyRemovePromptGrocery(id) {
	
	modal = document.getElementById('groceryEditModal');
	btn = document.getElementById("myBtn");
	document.getElementsByClassName("close")[0];
    modal.style.display = "block";
    var allGroceries = pendingGroceries.concat(purchaseGroceries);
    result = allGroceries.find(obj => {
    	  return obj.id === id
	})
    document.getElementById('gItem').value = result.groceryItem;
	document.getElementById('gPrice').value = result.groceryPrice;
}

function toDeleteGrocery() {
	
	stompClient.send("/deleteGroceryItem",{},JSON.stringify({'netID': netID,'grocery_id':result.id}));

    closed();
}

function modifyGrocery() {
	
	
	var newItem =  document.getElementById('gItem').value;
	var newPrice = document.getElementById('gPrice').value;
	var url = 'http://proj309-vc-05.misc.iastate.edu:8080/addGroceryItem?';
	
	if (newItem.length != 0 && !isNaN(newPrice) && !(newItem==result.groceryItem && newPrice==result.groceryPrice)) {
		toDeleteGrocery();
	
		result.groceryItem = newItem;
		result.groceryPrice = newPrice;
		
		var str = [];
		stompClient.send("/addGroceryItem",{},JSON.stringify({'groceryItem': newItem,'groceryPrice': newPrice,'approved' : result.approval,'studentID': netID}));

		
	    
	}
}

function modifyRemovePromptChore(id) {
	
	modal = document.getElementById('choreEditModal');
	btn = document.getElementById("myBtn");
	document.getElementsByClassName("close")[0];
    modal.style.display = "block";
    var allChores = pendingChores.concat(toDoChores);
    result = allChores.find(obj => {
    	  return obj.chore_id === id
	})
	console.log(result);
    document.getElementById('cItemMod').value = result.chore;

}

function toDeleteChore() {
	
	stompClient.send("/deleteChoreItem",{},JSON.stringify({'netID': netID,'chore_id':result.chore_id}));

    closed();
}

function modifyChore() {
	
	console.log("RUN");
	var newChore =  document.getElementById('cItemMod').value;
	var newDueDate = document.getElementById('dateItemMod').value;
	console.log(newDueDate);
	if (newChore.length != 0 && !isNaN(newDueDate)) {
		
		toDeleteChore();
	
		
		var str = [];
		stompClient.send("/addChore",{},JSON.stringify({'cItem': newChore,'nextSevenDays' : nextSevenDays[newDueDate],'correspondingMonth':correspondingMonth[newDueDate],'correspondingYear':correspondingYear[newDueDate],'studentID': netID}));

		
	    
	}

}

// When the user clicks anywhere outside of the modal, close it
function addChore() {
	
	var cItem = document.getElementById('cItem').value.toString();
	var dateItem = document.getElementById('dateItem').value.toString();
	if (cItem.length != 0 && !isNaN(dateItem)) {
	
		stompClient.send("/addChore",{},JSON.stringify({'cItem': cItem,'date' : nextSevenDays[dateItem],'month':correspondingMonth[dateItem],'year':correspondingYear[dateItem],'studentID': netID}));
	
	}
	closed();
}

function addDispute() {
	
	var disputeName = document.getElementById('dTitle').value.toString();
	var disputeBody = document.getElementById('dMessage').value.toString();
	if (disputeName.length != 0 && disputeBody.length != 0) {
		
		stompClient.send("/addDispute",{},JSON.stringify({'disputeName': disputeName,'disputeBody' : disputeBody,'studentID': netID}));

		
	}
	closed();
}