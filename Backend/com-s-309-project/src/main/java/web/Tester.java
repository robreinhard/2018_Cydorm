package web;

import java.util.*;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		Scanner scan = new Scanner(System.in);
		String message = "";
		
		while(!(message.toUpperCase().equals("STOP"))) {
			System.out.print("Enter message: ");
			message = scan.nextLine();
			if (message.startsWith("@item")){
				message = message.replace("@item ", "");
				if (message.isEmpty()) {
					System.out.println("ERROR: ITEM CANNOT BE NULL");
				}
				else {
					System.out.println(message);
				}
			}
			else {
				System.out.println("Message: " + message);
			}
		}
		scan.close();
	}

}
