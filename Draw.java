package example;
import java.util.List;
import java.util.Scanner;

public class Draw {
	
	
public static void menubar(){
		
		System.out.println("1- Enter participants");
		System.out.println("2- Participants list");
		System.out.println("3- Delete Person");
		System.out.println("4- Random raffle");
		System.out.println("5- Exit");
		
		
        Scanner input= new Scanner(System.in);
		
		System.out.println("Enter your choice:");
		
		int userChoice = input.nextInt();

		switch (userChoice) {
		case 1:
			Process.addPerson();	
			break;
		case 2:
			Process.writeParticipants();
			Draw.menubar();
			break;
		case 3:
			Process.deletePerson();
			break;
		case 4:
			List<Person> p = Process.randomChoice();
			System.out.println("Winner:" +p);
			Process.writeLucky(p);
			break;
		case 5:
			System.out.println("Exit!!");
			break;
		default:
			System.out.println("Wrong choice.Choose again");
			menubar();
			break;
		}
		
}

public static void isContinue() {

	Scanner input= new Scanner(System.in);
	System.out.println("1- Continue to add ");
	System.out.println("2- Menu");
	System.out.println("Enter your choice:");

	int userChoice2 = input.nextInt();
	if(userChoice2==1){
		Process.addPerson();
	}else if(userChoice2==2){
		menubar();
	}else{
		System.out.println("Wrong choice.Choose again");
	}
}
}

