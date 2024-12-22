# Challenge de Crowdar

Este proyecto contiene pruebas automatizadas utilizando Java, Selenium, TestNG y Maven. Sigue las instrucciones a continuación para configurar el entorno y ejecutar los tests.

---

## Requisitos previos

Antes de ejecutar este proyecto, asegúrate de cumplir con los siguientes requisitos:

1. **Java Development Kit (JDK) 11**
    - Descarga e instala JDK 11 desde [Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) o [OpenJDK](https://openjdk.org/).
    - Configura la variable de entorno `JAVA_HOME` apuntando a la ubicación del JDK.
    - Verifica la instalación ejecutando en una terminal:
      ```
      java -version
      ```

2. **Maven**
    - Descarga e instala Maven desde [Maven Download](https://maven.apache.org/download.cgi).
    - Configura la variable de entorno `MAVEN_HOME` apuntando a la ubicación de Maven.
    - Verifica la instalación ejecutando:
      ```
      mvn -version
      ```

3. **Drivers de Navegador**
    - Descarga los siguientes drivers y colócalos en la carpeta `drivers` dentro de la carpeta **Home** de tu usuario:
        - [ChromeDriver](https://sites.google.com/chromium.org/driver/) (compatible con tu versión de Google Chrome).
        - [MSEdgeDriver](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) (compatible con tu versión de Microsoft Edge).
    - Por ejemplo, si tu carpeta de usuario es `C:\Users\tu_usuario`, coloca los drivers en:
      ```
      C:\Users\tu_usuario\drivers\chromedriver.exe
      C:\Users\tu_usuario\drivers\msedgedriver.exe
      ```

4. **Sistemas Operativos Soportados**
    - Windows 11 (64 bits).

---

## Configuración del proyecto

1. **Clonar el repositorio**
    - Clona este repositorio desde GitHub utilizando el siguiente comando:
      ```
      git clone <URL_DEL_REPOSITORIO>
      ```

2. **Importar el proyecto en un IDE**
    - Abre el proyecto en tu IDE preferido (IntelliJ IDEA, Eclipse, etc.).
    - Asegúrate de que el proyecto esté configurado con soporte para Maven.

3. **Instalar dependencias**
    - Navega al directorio del proyecto y ejecuta el siguiente comando para descargar las dependencias requeridas:
      ```
      mvn clean install
      ```

---

## Ejecución de los tests

1. **Desde el IDE**
    - Abre la clase de prueba deseada o el archivo `testng.xml`.
    - Haz clic derecho y selecciona "Run" para ejecutar los tests.

2. **Desde la línea de comandos**
    - Ejecuta el siguiente comando desde el directorio del proyecto:
      ```
      mvn test
      ```

---

## Estructura del proyecto

- **src/main/java**: Código fuente principal.
- **src/test/java**: Clases de prueba.
- **testng.xml**: Archivo de configuración de TestNG.
- **drivers/**: Carpeta para almacenar los drivers del navegador.

---

## Notas adicionales

- Asegúrate de que los drivers coincidan con la versión del navegador instalado en tu sistema.
- Si encuentras problemas, verifica los logs generados en la consola para identificar la causa.

---