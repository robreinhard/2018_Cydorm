var modal;

// Get the button that opens the modal
var btn;

// Get the <span> element that closes the modal
var span;

// When the user clicks the button, open the modal 

var pendingDisputes = [];
var pastDisputes = [];
var stompClient = null;

window.onload = function() {
	console.log(netID);
	var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        
        
        stompClient.subscribe('/caDispute', function(theDisputes) {
        	updateDisputeList(JSON.parse(theDisputes.body));
        });
        

    	stompClient.send("/dumpCADispute",{},JSON.stringify({'netID': netID}));
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


var result;



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

function addDispute() {
	
	var disputeName = document.getElementById('dTitle').value.toString();
	var disputeBody = document.getElementById('dMessage').value.toString();
	if (disputeName.length != 0 && disputeBody.length != 0) {
		
		stompClient.send("/addDispute",{},JSON.stringify({'disputeName': disputeName,'disputeBody' : disputeBody,'studentID': netID}));

		
	}
	closed();
}