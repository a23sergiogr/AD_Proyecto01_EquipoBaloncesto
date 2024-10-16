﻿# AD_Proyecto01_EquipoBaloncesto
Haga un programa para la gestión y clasificación de las ligas, como la ACB. Las clasificaciones de los equipos se guardan en archivos binarios o de texto, según decidas. Por ejemplo: Liga ACB.dat.

a) Declare una clase Equipo con los atributos mínimos necesarios: nombre, victorias, derrotas, puntosAfavor a favor, puntosEnContra puntos en contra. Puedes añadir los atributos que te interesen, como ciudad, etc. Tienes libertad para hacerlo, pues, además, te puede servir como práctica. En una liga de fútbol, por ejemplo, se podría añadir el campo estadio y los puntos a favor serían los goles a favor.

Además, ten en cuenta que los atributos puntos, partidos jugados y diferencia de puntos son atributos derivados que se calculan a partir de los partidos ganados, perdidos, puntos a favor y puntos en contra.

Cree los métodos que considere oportunos, pero tome decisiones sobre los métodos get/set necesarios. Así, haz un método que devuelva los puntos, getPuntos, un método getPartidosJugados que devuelva el número de partidos jugados y un método getDiferenciaDePuntos, que devuelva la diferencia de puntos. Obviamente, por ser atributos/propiedades derivados/as, no tienen sentido los métodos de tipo “set” para ellos.

Debe tener, al menos, un constructor para la clase equipo que recoja el nombre y otro que recoja todas las propiedades. No debe existir un constructor por defecto (en la práctica sí si debería tener).

Para poder ordenar los equipos debe implantar la interface Comparable<Equipo>. Piense que debe ordenar por puntos y, a igualdad de puntos, por diferencia de puntos encestados. Además, para poder guardar los objetos (writeObject de ObjectOutputStream) y/o recuperarlos (readObject de ObjectInputStream) debe implantar la interface Serializable. Lo mismo con la clase siguiente, Clasificacion, que debe implementar la interface Serializable.

Sobrescribe el método equals para que se considere que dos Equipos son iguales si tienen el mismo nombre (sin distinguir mayúsculas de minúsculas). Haz lo mismo con hashCode.

b) Declare una clase Clasificacion, con los atributos:

equipos de tipo Set de Equipo, aunque debe existir un constructor que permita crear una clasificación con los equipos que se desee.

competicion de tipo String que recoja el nombre de la competición. Por defecto, la competición debe ser “Liga ACB”.

Defina los métodos para añadir equipos a la clasificación, addEquipo, así como los métodos para eliminar equipo, removeEquipo, y sobrescriba el método toString que devuelva la cadena de la clasificación (StringBuilder)

Los constructores de Clasificación deben crear el conjunto de equipos como tipo TreeSet, para que los ordene automáticamente.

c) Interface DAO<T, K> (Data Access Object) es un patrón de diseño que permite separar la lógica de negocio de la lógica de acceso a los datos. Con los siguientes métodos:

    T get(K id);
    List<T> getAll();
    boolean save(T obxecto);
    boolean delete(T obx);
    boolean deleteAll();
    boolean deleteById(K id);
    void update(T obx);
e) Crea una clase EquipoFileDAO que implemente la interfaz DAO<Equipo, String>. Debe implantar los métodos de la interface. Esta clase debe tener un atributo final, path, de tipo Path con la ruta completa al archivo de datos.

Si se emplea ObjectOutput/InputStream, podría tener un atributo ObjectOutputStream y ObjectInputStream. Si se emplea BufferedWriter/Reader, debe tener un atributo BufferedWriter y BufferedReader. Sin embargo, podría hacerse en cada uno de los métodos de la clase:

Ejemplo de save con ObjectOutputStream personalizado:

boolean append = Files.exists(path);
try (FileOutputStream fos = new FileOutputStream(path.toFile(), append);
     ObjectOutputStream oos = append ? new EquipoOutputStream(fos) : new ObjectOutputStream(fos)) {
oos.writeObject(obxecto);
//            System.out.println("Equipo gardado: " + obxecto);
} catch (IOException e) {
      System.out.println("Erro de Entrada/Saída");
      return false;
}
En la que la clase EquipoOutputStream es una clase que hereda de ObjectOutputStream y sobrescribe el método writeStreamHeader para que no escriba la cabecera del stream.

public class EquipoOutputStream extends ObjectOutputStream {
	public EquipoOutputStream(OutputStream out) throws IOException {
		super(out);
	}

	@Override
	protected void writeStreamHeader() throws IOException {
		// No escribe la cabecera
	}
}
f) Cree una clase ClasificacionFileDAO que implemente la interfaz DAO<Clasificacion, String>. Debe tener un atributo final con la ruta en la que se guardan los datos de la clasificación: ruta. El nombre del archivo debe ser el nombre de la competición seguido de .dat. Constructor al que se le pasa la ruta, etc. Para facilitar el trabajo. los métodos de la clase ClasificacionFileDAO pueden hacer uso de la clase EquipoFileDAO.

Por ejemplo, el método save de ClasificacionFileDAO podría ser:

public boolean save(Clasificacion clasificacion) {
	EquipoFileDAO equipoFileDAO = new EquipoFileDAO(ruta + clasificacion.getCompeticion() + ".dat");
	clasificacion.getEquipos().forEach(equipoFileDAO::save);
	return true;
}
g) El programa debe tener un menú con las siguientes opciones:

a. Añadir equipo (pide el nombre del equipo y los valores de los atributos no derivados, añadiendo el equipo a la clasificación) b. Mostrar clasificación (muestra la clasificación ordenada de los equipos que están cargados en memoria) c. Guardar clasificación (que guarda la clasificación en el archivo clasificacion.dat) d. Cargar clasificación (que carga la clasificación del archivo clasificacion.dat) e. Salir (sale del programa, debiendo preguntar antes).

Utilice la clase Scanner para leer de teclado.
Como mejora, intenta hacerlo con una aplicación gráfica.
