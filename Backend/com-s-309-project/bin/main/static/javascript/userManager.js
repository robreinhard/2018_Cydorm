allUsers = [];
var loadedTable = [];
window.onload = function() {
	
	var requestURL = 'http://localhost:8080/allUsers';
	var request = new XMLHttpRequest();
    request.open('GET', requestURL);
    request.responseType = 'json';
    request.send();

    request.onload = function() {
    	
    	  users = request.response;
    	  for (var i=0; i < users.length;i++) {
    		  
    		  var firstName = "<tr><td contenteditable>" + users[i].firstName + "</td>";
    	      var lastName = "<td contenteditable>" + users[i].lastName + "</td>";
    	      var netID = "<td contenteditable>" + users[i].netID + "</td>" ;
    	      var role = "<td contenteditable>" + users[i].role + "</td>";
    	      var location = "<td contenteditable>" + users[i].location + "</td>";
    	      var sublocation = "<td contenteditable>" + users[i].sublocation + "</td>";
    	      var address = "<td contenteditable>" + users[i].address + "</td></tr>";
    		  
    		  var toBuild = firstName + lastName + netID + role + location + sublocation + address;
    		  loadedTable.push(new Array(users[i].firstName,users[i].lastName,users[i].netID,users[i].role,users[i].location,users[i].sublocation,users[i].address,users[i].user_id));
    		  console.log(toBuild);
    		  $('#userTable tbody:last-child').append(toBuild);
    		  
    		  
    	  }
    	  

    }
	
};   



$(document).on('keypress', 'td', function(evt) {
	
	if(evt.which == 13){
        evt.preventDefault();
        var cellindex = $(this).index()
        // get next row, then select same cell index
        var rowindex = $(this).parents('tr').index() + 1
        $(this).parents('tbody').find('tr:eq('+rowindex+') td:eq('+cellindex+')').focus()
    }
	else {
		
		if (this.innerHTML+evt.key != loadedTable[this.parentNode.rowIndex-1][this.cellIndex]) {
			
			console.log(this.innerHTML+evt.key);
			console.log(loadedTable[this.parentNode.rowIndex-1][this.cellIndex])
			$(this).css( "background-color","yellow");

		}
		else if (this.innerHTML+evt.key === loadedTable[this.parentNode.rowIndex-1][this.cellIndex]){
			
			$(this).css( "background-color","initial");

		}
	}
	

});

$(document).on('keydown', 'td', function(evt) {
	
	
	if (evt.which == 8) {

		if (this.innerText.substring(0,this.innerText.length-1) != loadedTable[this.parentNode.rowIndex-1][this.cellIndex]) {
			
			console.log(this.innerText.substring(0,this.innerText.length-1));
			console.log(loadedTable[this.parentNode.rowIndex-1][this.cellIndex])
			$(this).css( "background-color","yellow");
	
		}
		else if (this.innerText.substring(0,this.innerText.length-1) === loadedTable[this.parentNode.rowIndex-1][this.cellIndex]){
			
			$(this).css( "background-color","initial");
	
		}
		
	}
	

});

function searchFilter() {
	  // Declare variables 
	  var input, filter, table, tr, td, i, txtValue, select,selected;
	  input = document.getElementById("myInput");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("userTable");
	  tr = table.getElementsByTagName("tr");
	  select = document.getElementById("searchBy");
	  selected = select.options[select.selectedIndex].value;
	  console.log(selected);
	  // Loop through all table rows, and hide those who don't match the search query
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[selected];
	    if (td) {
	      txtValue = td.textContent || td.innerText;
	      if (txtValue.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    } 
	  }
	}



