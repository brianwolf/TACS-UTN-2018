package ar.utn.tacs.telegram.command;

public class Help extends Command {

	public static String exec() {
		return "Bienvenido a TACSCryptoBot\n\n"
				+ "Desde aquí podrá comprar y vender sus cryptomonedas.\n"
				+ "Tambien podrá conocer la cotización actual y tenencia total de su portafolio.\n\n"
				+ "Desarrollo para la catedra de Tecnologías Avanzadas de Construcción de Software.\n"
				+ "Universidad Tecnológica Nacional - Facultad Regional Buenos Aires.\n\n"
				+ "Debe tener una cuenta en el sistema para operar.\n"
				+ "En caso contrario contacte al administrador.\n\n"
				+ "Escriba /commands para ver el listado de comandos disponibles en el sistema.\n\n"
				+ "Alumnos\n"
				+ "Alexis Taberna\n"
				+ "Brian Lobo\n"
				+ "Juan Pablo Bulbulian";
	}

}
