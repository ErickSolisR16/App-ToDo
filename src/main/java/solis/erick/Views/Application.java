package solis.erick.Views;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class, args);
		showOptions();
	}

	private static void showOptions() throws IOException {
		int option;
		do {
			System.out.println();
			System.out.println("0. Salir");
			System.out.println("1. Crear tarea");
			System.out.println("2. Mostrar tareas");
			System.out.println("3. Actualizar tarea");
			System.out.println("4. Eliminar tarea");
			option = selectOption();
			executeOption(option);
			System.out.println();
		} while (option != 0);
	}

	private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	private static int selectOption() throws IOException {
		System.out.print("Seleccion una opcion: ");
		return Integer.parseInt(in.readLine());
	}

	private static void executeOption(int pOption) {
		switch (pOption) {
			case 0:
				System.out.println("Gracias por usar la aplicacion");
				break;
			case 1:
				createTask();
				break;
			case 2:
				showListTask();
				break;
			case 3:
				updateTask();
				break;
			case 4:
				deleteTask();
				break;
			default:
				System.out.println("Opcion invalida");
		}
	}

	private static void createTask() {
	}

	private static void showListTask() {

	}

	private static void updateTask() {

	}

	private static void deleteTask() {

	}

}
