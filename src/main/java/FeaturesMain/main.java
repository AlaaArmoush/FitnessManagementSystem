package FeaturesMain;

import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		String id, pass;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome To Our Fitness Management System!");

		do {
			System.out.print("Please Enter Your ID: ");
			id = scanner.nextLine();
			if (!UserDataBase.userExist(id))
				System.out.println("Please Enter A Valid ID!!!");
			else {
				System.out.print("Please Enter Your Password: ");
				pass = scanner.nextLine();
				if (!UserDataBase.getUser(id).getPassword().equals(pass))
					System.out.println("Wrong Credintials !!!");
				else {
					switch (UserDataBase.getUser(id).getRole()) {
					case "Admin":
						AdminMenu();
						break;

					case "Instructor":
						InstructorMenu();
						break;

					case "Client":
						ClientMenu();
						break;

					default:
						System.out.println("Invalid role.");
						break;

					}
				}
			}

		} while (true);

	}

	private static void ClientMenu() {
		// TODO Auto-generated method stub

	}

	private static void InstructorMenu() {
		// TODO Auto-generated method stub

	}

	private static void AdminMenu() {
		// TODO Auto-generated method stub

	}
}
