package solis.erick.Views;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.ArrayList;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class, args);
		showOptions();
	}

	private static void showOptions() throws IOException {
		int option;
		do {
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

	private static void executeOption(int pOption) throws IOException {
		switch (pOption) {
			case 0 -> System.out.println("Gracias por usar la aplicacion");
			case 1 -> createTask();
			case 2 -> showListTask();
			case 3 -> updateTask();
			case 4 -> deleteTask();
			default -> System.out.println("Opcion invalida");
		}
	}

	private static final Manager manager = new Manager();

	private static void createTask() throws IOException {
		/*System.out.println("Digite el titulo de la tarea");
		String title = in.readLine();
		System.out.println("Digite la descripcion de la tarea");
		String description = in.readLine();
		if (manager.createTask(title, description)) {
			System.out.println("Tarea creada exitosamente");
		} else {
			System.out.println("No se pudo crear la tarea");
		}*/
	}

	private static void showListTask() {
        if (manager.existTaskList()) {
            System.out.println("Lista de tareas pendientes: ");
            ArrayNode listTask = manager.showListTask();
            for (Object task : listTask) {
                System.out.println(task.toString());
            }
        } else {
            System.out.println("No hay tareas pendientes");
        }
	}

	private static void updateTask() throws IOException {
		if (manager.existTaskList()) {
			showListTask();
			System.out.println("Digite el id de latarea que desea actualizar");
			int id = Integer.parseInt(in.readLine());
			if (manager.searchTask(id)) {
				System.out.println("Digite el nuevo titulo de la tarea");
				String title = in.readLine();
				System.out.println("Digite la nueva descripcion de la tarea");
				String description = in.readLine();
				if (manager.updateTask(id, title, description)) {
					System.out.println("Tarea actualizada exitosamente");
				} else {
					System.out.println("Error al actualizar la tarea");
				}
			} else {
				System.out.println("La tarea que desea actualizar no se encuentra");
			}
		} else {
			System.out.println("No hay tareas pendientes");
		}
	}

	private static void deleteTask() throws IOException {
		if (manager.existTaskList()) {
			showListTask();
			System.out.println("Digite el id de la tarea que desea eliminar");
			int id = Integer.parseInt(in.readLine());
			if (manager.searchTask(id)) {
				if (manager.deleteTask(id)) {
					System.out.println("Tarea eliminada exitosamente");
				} else {
					System.out.println("Error al eliminar la tarea");
				}
			} else {
				System.out.println("La tarea que desea actualizar no se encuentra");
			}
		} else {
			System.out.println("No hay tareas pendientes");
		}
	}

}
