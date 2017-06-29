package example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Process {

	public static void main(String[] args) {
		Draw.menubar();
	}
	public static void addPerson(){

		Scanner input = new Scanner(System.in);

		System.out.println("Enter name:");
		String name= input.nextLine();

		System.out.println("Enter surname:");
		String surname=input.nextLine();

		System.out.println("Enter id:");
		int id=input.nextInt();

		writeToList(name,surname,id);
		Draw.isContinue();

	}

	public static void writeToList(String name,String surname,int id){
		String file = "C:\\Users\\gmbim23\\Desktop\\Participants_List.txt";

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			HashMap<Integer, Person> map=readFromList();
			if(map.containsKey(id)){
				throw new CustomException("Please enter a different id");					
			}

			String content = id+"/"+name+"/"+surname;

			fw = new FileWriter(file,true);
			bw = new BufferedWriter(fw);
			bw.append(content);
			bw.newLine();

		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (CustomException e) {
			System.out.println(e.getMessage());
			Draw.menubar();
		}
		catch (Exception e) {
			throw e;
		}
		finally {

			try {
				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}

	public static HashMap<Integer, Person> readFromList(){

		HashMap<Integer, Person> map=new HashMap<>();
		File file = new File("C:\\Users\\gmbim23\\Desktop\\Participants_List.txt");
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null&& !line.isEmpty()) {
				String[] fields = line.split("/");
				Person person=new Person(Integer.valueOf(fields[0]), fields[1], fields[2]);
				map.put(person.id, person);
			}

		}
		catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		catch (IOException e) {	
			e.printStackTrace();
		}
		return map;
	}


	public static List<Person> randomChoice(){

		HashMap<Integer, Person> map=readFromList();
		List<Person> randomList=new ArrayList<>();

		System.out.println("How many lucky person do you want to choose?:");

		Scanner input = new Scanner(System.in);

		int lucky=input.nextInt();

		if(map.size()<lucky){

			System.out.println("You can not make this choice.Try again.");
			return null;

		}

		Random random=new Random();
		int randomKey;
		Person luckyp=null;
		List<Integer> keys = new ArrayList<Integer>(map.keySet());

		for (int i = 0; i < lucky; i++) {

			randomKey = keys.get( random.nextInt(keys.size()) );
			luckyp = map.get(randomKey);	
			keys.remove(Integer.valueOf(randomKey));
			randomList.add(luckyp);
		}

		return randomList;
	}

	public static void deletePerson(){
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			HashMap<Integer, Person> map=readFromList();
			Scanner input = new Scanner(System.in);
			System.out.println("Please enter id:");
			int idelete= input.nextInt();
			if(map.containsKey(idelete)){
				map.remove(idelete);
			}else{
				throw new CustomException("There is not");
			}
			String file = "C:\\Users\\gmbim23\\Desktop\\Participants_List.txt";

			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write("");
			fw = new FileWriter(file,true);
			bw = new BufferedWriter(fw);
			for(Map.Entry<Integer, Person> entry: map.entrySet()) {
				Person person = entry.getValue();
				String content = person.id+"/"+person.name+"/"+person.surname;
				bw.append(content);
				bw.newLine();
			}
		} 
		catch(CustomException e){
			System.out.println(e.message);
			Draw.menubar();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			throw e;
		}

		finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		Draw.menubar();
	}



	public static void writeLucky(List<Person>list){

		String file = "C:\\Users\\gmbim23\\Desktop\\Winner.txt";

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			fw = new FileWriter(file,true);
			bw = new BufferedWriter(fw);

			for (Person person : list) {

				String content =  person.id+"/"+person.name+"/"+person.surname;

				bw.append(content);
				bw.newLine();
			}

		} catch (IOException e) {

			e.printStackTrace();

		}

		finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}	
	
	public static void writeParticipants(){
		
		System.out.println("Participant List:");
		
		HashMap<Integer, Person> map=readFromList();
		for(Map.Entry<Integer, Person> entry: map.entrySet()) {
		    System.out.println(entry.getValue().toString());
		    
		}
		
	}
}
