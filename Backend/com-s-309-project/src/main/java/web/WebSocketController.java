package web;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class WebSocketController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ResidencyService residencyService;
    
    @Autowired
    private GroceryService groceryService;
    
    @Autowired
    private ChoreService choreService;
    
    @Autowired
    private DisputeService disputeService;
    
    @MessageMapping("/addGroceryItem")
    @SendTo("/allGroceries")
    public Set<Grocery> addGroceryItem(Grocery grocery) throws Exception {

    	 System.out.println(grocery.toString());
    	 grocery.setApproval('F');
         User user = userService.findUserByNetID(grocery.getstudentID());
         groceryService.saveGrocery(grocery);
         System.out.println(grocery);
         groceryService.saveAddressGrocery(user.getAddress(), grocery);
         
         return user.getAddress().getGroceries();
    	
    }
    
	@MessageMapping("/dumpGrocery")
    @SendTo("/allGroceries")
    public Set<Grocery> dumpGrocery(String jsonData) throws Exception {

    	 ObjectMapper objectMapper = new ObjectMapper();
    	 JsonNode rootNode = objectMapper.readTree(jsonData);
    	 JsonNode netID = rootNode.path("netID");
         User user = userService.findUserByNetID(netID.asText());
         
         return user.getAddress().getGroceries();
    	
    }
    
    @MessageMapping("/deleteGroceryItem")
    @SendTo("/allGroceries")
    public Set<Grocery> deleteGrocery(String jsonData) throws Exception {
    	
    	ObjectMapper objectMapper = new ObjectMapper();
   	 	JsonNode rootNode = objectMapper.readTree(jsonData);
   	 	JsonNode netID = rootNode.path("netID");
   	 	User user = userService.findUserByNetID(netID.asText());
   	 	JsonNode groceryID = rootNode.path("grocery_id");
   	 	Grocery grocery = groceryService.findGroceryByID(groceryID.asInt());
   	 	groceryService.deleteGrocery(user.getAddress(),grocery);
   	 	user = userService.findUserByNetID(netID.asText());
   	 	return user.getAddress().getGroceries();
    	
    }
    
    @MessageMapping("/addChore")
    @SendTo("/allChores")
    public Set<Chore> addChore(String jsonData) throws Exception {

    	 ObjectMapper objectMapper = new ObjectMapper();
   	 	 JsonNode rootNode = objectMapper.readTree(jsonData);
   	 	 JsonNode studentID = rootNode.path("studentID");
   	 	 JsonNode choreName = rootNode.path("cItem");
   	 	 JsonNode correspondingMonth = rootNode.path("year");
   	 	 JsonNode correspondingYear = rootNode.path("month");
   	 	 JsonNode nextSevenDays = rootNode.path("day");
   	 	 System.out.println("YEAR:" + correspondingYear.asInt());
   	 	 Calendar c = Calendar.getInstance();
   	 	 c.set(correspondingYear.asInt(), correspondingMonth.asInt()-1,nextSevenDays.asInt(),0,0);
   	 	 Date date = c.getTime();
   	 	 Chore chore = new Chore(choreName.asText(),studentID.asText(), date);
    	 chore.setCompleted('F');
         User user = userService.findUserByNetID(chore.getStudentID());
         choreService.saveChore(chore);
         choreService.saveAddressChore(user.getAddress(), chore);
         
         return user.getAddress().getChores();
         
    }
    
    @MessageMapping("/dumpChore")
    @SendTo("/allChores")
    public Set<Chore> dumpChore(String jsonData) throws Exception {

    	 ObjectMapper objectMapper = new ObjectMapper();
    	 JsonNode rootNode = objectMapper.readTree(jsonData);
    	 JsonNode netID = rootNode.path("netID");
         User user = userService.findUserByNetID(netID.asText());
         
         return user.getAddress().getChores();
    	
    }
    
    @MessageMapping("/deleteChoreItem")
    @SendTo("/allChores")
    public Set<Chore> deleteChore(String jsonData) throws Exception {
    	
    	ObjectMapper objectMapper = new ObjectMapper();
   	 	JsonNode rootNode = objectMapper.readTree(jsonData);
   	 	JsonNode netID = rootNode.path("netID");
   	 	User user = userService.findUserByNetID(netID.asText());
   	 	JsonNode choreID = rootNode.path("chore_id");
   	 	/*
   	 	Grocery grocery = groceryService.findGroceryByID(groceryID.asInt());
   	 	groceryService.deleteGrocery(user.getAddress(),grocery);
   	 	*/
   	 	Chore chore = choreService.findChoreByID(choreID.asInt());
   	 	choreService.deleteChore(user.getAddress(), chore);
   	 	user = userService.findUserByNetID(netID.asText());
   	 	return user.getAddress().getChores();
    	
    }
    
    @MessageMapping("/addDispute")
    @SendTo("/allDisputes")
    public Set<Dispute> addDispute(Dispute dispute) throws Exception {
    	
        User user = userService.findUserByNetID(dispute.getStudentID());
        disputeService.saveDispute(dispute);
        disputeService.saveAddressDispute(user.getAddress(), dispute);
        
        return user.getAddress().getDisputes();
    }
    
    @MessageMapping("/dumpDispute")
    @SendTo("/allDisputes")
    public Set<Dispute> dumpDispute(String jsonData) throws Exception {

    	 ObjectMapper objectMapper = new ObjectMapper();
    	 JsonNode rootNode = objectMapper.readTree(jsonData);
    	 JsonNode netID = rootNode.path("netID");
         User user = userService.findUserByNetID(netID.asText());
         
         return user.getAddress().getDisputes();
    	
    }
    
    @MessageMapping("/deleteDispute")
    @SendTo("/allDisputes")
    public Set<Dispute> deleteDispute(String jsonData) throws Exception {
    	
    	ObjectMapper objectMapper = new ObjectMapper();
   	 	JsonNode rootNode = objectMapper.readTree(jsonData);
   	 	JsonNode netID = rootNode.path("netID");
   	 	User user = userService.findUserByNetID(netID.asText());
   	 	JsonNode disputeID = rootNode.path("dispute_id");
   	 	Dispute dispute = disputeService.findDisputeByID(disputeID.asInt());
   	 	disputeService.deleteDispute(user.getAddress(),dispute);
   	 	user = userService.findUserByNetID(netID.asText());
   	 	return user.getAddress().getDisputes();
    	
    }
}
