import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;

public class Agenda extends TreeMap<String, String> {

	public String Ejecutar (String cmd) {
		Scanner s = new Scanner(System.in);
		int estado = 0;
		String token;
		String nombre = null;
		while (estado != 5) {
			switch (estado) {
			case 0:
				try {
					token = s.skip("fin|buscar|\\p{L}+(\\s+\\p{L}+)").match().group();
					if (token.equals("fin")) { 
						estado = 5;
					}else if (token.equals("buscar")) 
						estado = 2;
					else {
						nombre = token;
						estado = 1;
					}
				} catch (NoSuchElementException e) { 
					System.out.println("Se esperaba 'buscar' o 'fin' o un nombre");
					estado = 5;
				}

				break;
			case 1:
				try {
					s.skip("-");
					estado = 3;
				} catch (NoSuchElementException e) {
					System.out.println("Se esperaba '-'");
					estado = 5;
				}
				break;
			case 2:
				try {
					s.skip(":");
					estado = 4;
				} catch (NoSuchElementException e) {
					System.out.println("Se esperaba ':'");
					estado = 5;
				}
				break;
			case 3:
				try {
					token = s.skip("\\d{9}").match().group();
					agenda.put(nombre, token);
					estado = 5;
				} catch (NoSuchElementException e) {
					System.out.println("Se esperaba un telefono");
					estado = 5;
				}
				break;
			case 4:
				try {
					token = s.skip("[a-zA-ZáéíóúÁÉÍÓÚ]+\\s+([a-zA-ZáéíóúÁÉÍÓÚ]+\\s+)*[a-zA-ZáéíóúÁÉÍÓÚ]+|[a-zA-ZáéíóúÁÉÍÓÚ]+").match().group();
					String telefono = agenda.get(token); 
					if (telefono != null) 
						System.out.println(token + " -> " + telefono);
					else
						System.out.println(token + " no se encuentra en la agenda");
					estado = 5;
				} catch (NoSuchElementException e) {
					System.out.println("Se esperaba un nombre");
					estado = 5;
				}
				break;
			}
		}
		return null;
	}

	private void cargar (String file){

	}

	private void guardar (String file) {

	}
}	


