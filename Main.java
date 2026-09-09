package paq;

import java.util.*;
import java.io.*;

public class Main {

	static Scanner scLine = new Scanner(System.in);
	static Scanner scInt = new Scanner(System.in);

	static ArrayList<Contacto> contactos = new ArrayList();

	static boolean cambios = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try (FileInputStream fis = new FileInputStream("contactos.bin");
				ObjectInputStream ois = new ObjectInputStream(fis)) {

			contactos = (ArrayList<Contacto>) ois.readObject();

			System.out.println("Fichero leído\n");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("No existe fichero previo. Se iniciará con lista vacía.\n");

		}

		boolean salir = false;
		do {
			System.out.println("\n---PROGRAMA---");
			System.out.println("1.-Listar contactos que están en memoria");
			System.out.println("2.-Introducir un nuevo contacto");
			System.out.println("3.-Eliminar un contacto");
			System.out.println("4.-Actualizar fichero");
			System.out.println("5.-Salir");
			System.out.println("Elige una opción: ");
			int opcion = scInt.nextInt();

			switch (opcion) {

			case 1:
				listadoContactos();
				break;

			case 2:
				introducirContacto();
				break;

			case 3:
				eliminarContacto();
				break;

			case 4:
				actualizarFichero();
				break;

			case 5:
				if (cambios) {
					System.out.println("Guardando cambios antes de salir...");
					try (FileOutputStream fos = new FileOutputStream("contactos.bin");
							ObjectOutputStream oos = new ObjectOutputStream(fos)) {

						oos.writeObject(contactos);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}
				}
				System.out.println("Saliendo del programa...");
				salir = true;
				break;

			}

		} while (!salir);

	}

	private static void actualizarFichero() {
		// TODO Auto-generated method stub

		if (!cambios) {
			System.out.println("No hay cambios en el fichero. No se actualiza");
		} else {
			try (FileOutputStream fos = new FileOutputStream("contactos.bin");
					ObjectOutputStream oos = new ObjectOutputStream(fos)) {

				oos.writeObject(contactos);

				System.out.println("Fichero actualizado");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cambios = false;
		}
		System.out.println("Pulsa enter para continuar");
		scLine.nextLine();

	}

	private static void eliminarContacto() {
		// TODO Auto-generated method stub

		System.out.println("DNI del contacto a eliminar:");
		String dni = scLine.nextLine();

		for (int i = 0; i < contactos.size(); i++) {

			if (contactos.get(i).getDni().equalsIgnoreCase(dni)) {
				contactos.remove(i);
				cambios = true;
				System.out.println("Contacto eliminado de memoria\n");
				System.out.println("Pulsa enter para continuar");
				scLine.nextLine();
				return;

			}

		}

		System.out.println("No existe contacto con ese DNI\n");
		System.out.println("Pulsa enter para continuar");
		scLine.nextLine();
	}

	private static void introducirContacto() {
		// TODO Auto-generated method stub

		System.out.println("Introduce DNI:");
		String dni = scLine.nextLine();

		for (Contacto cx : contactos) {

			if (cx.getDni().equalsIgnoreCase(dni)) {
				System.out.println("No se puede introducir el contacto. Ya existe un contacto con ese DNI ");
				System.out.println("Pulsa enter para continuar");
				scLine.nextLine();
				return;
			}

		}

		System.out.println("Nombre: ");
		String nombre = scLine.nextLine();

		System.out.println("Apellido: ");
		String apellido = scLine.nextLine();

		System.out.println("Telefono: ");
		String tel = scLine.nextLine();

		System.out.println("Email: ");
		String email = scLine.nextLine();

		Contacto nuevo = new Contacto(dni, nombre, apellido, tel, email);
		contactos.add(nuevo);

		cambios = true;

		System.out.println("Contacto añadido a memoria\n");

		System.out.println("Pulsa enter para continuar");
		scLine.nextLine();
	}

	private static void listadoContactos() {
		// TODO Auto-generated method stub
		if (contactos.isEmpty()) {
			System.out.println("No hay contactos en memoria.\n");
		} else {
			for (Contacto c : contactos)
				System.out.println(c);
			System.out.println();
		}
		System.out.println("Pulsa enter para continuar");
		scLine.nextLine();
	}

}
