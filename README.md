
<section align="center">
  <h1 align="center">🌮Tacotito’s Food Truck🌮</h1>
  <a href="https://www.mentta.com/blog/origen-de-los-tacos-mexicanos/" target="_blank"><img src="https://i.ibb.co/WHRGpcz/foodtruck.jpg" alt="Tacos_Info"></a>
   <section align="center">
   <img src="https://img.shields.io/badge/STATE-FINISHED-green" alt="Estado del Proyecto">
     </section>
</section>


# Índice
- [Sobre Tacotito’s :truck:](#sobre-tacotitos-truck)

- [Programas necesarios :memo:](#white_check_mark-programas-necesarios-memo)

- [Descargar proyecto ⚙️](#white_check_mark-descargar-proyecto-⚙️)
   
- [Crear Base de Datos 🔧](#white_check_mark-crear-base-de-datos-🔧)


- [Abrir Proyecto :open_file_folder:](#white_check_mark-abrir-proyecto-open_file_folder)
    - [Configurar ConexionDb.java 📦](#white_check_mark-configurar-conexiondbjava-📦)
    - [Ejecutar el proyecto 🚀](#white_check_mark-ejecutar-el-proyecto-🚀)




- [Tecnologías utilizadas 🛠️](#tecnologías-utilizadas-🛠️)

- [Autor ✒️](#autor-✒️)


## Sobre Tacotito’s :truck:

<p align="justify">
Es un sistema realizado según el patrón MVC (Modelo, Vista, Controlador) que permite el ABM (Alta, Baja, Modificación), de tacos y de todos los productos que lo componen. A su vez, determina el valor total de un taco generado entre otras funcionalidades (construido con JAVA y MYSQL).
</p>



### :white_check_mark: `Programas necesarios :memo:`
- Descargar e Instalar :arrow_down_small: 
  - Aprende a instalar Netbeans completamente :point_right: [8-minute tutorial ⏱️](https://www.youtube.com/watch?v=MXHsvSvJpHI)! 
  - <a href="https://www.oracle.com/ar/java/technologies/downloads/" target="_blank"> 
        Java JDK
    </a>    
  - <a href="https://netbeans.apache.org/" target="_blank"> 
        Apache Netbeans
    </a> 
  - <a href="https://www.apachefriends.org/es/index.html" target="_blank"> 
        XAMPP
    </a> 

### :white_check_mark: `Descargar proyecto ⚙️`
- <a href="https://github.com/manita02/Tacotitos" target="_blank"> Repositorio (Tacotito’s Food Truck) en GitHub </a>
- <a href="https://docs.github.com/es/repositories/working-with-files/using-files/downloading-source-code-archives" target="_blank"> Hacer click en <>Code y luego Download ZIP </a>

### :white_check_mark: `Crear Base de Datos 🔧` 
- Ejecutar XAMPP
- Iniciar los servidores Apache y MySQL
  <section align="center">
       <img src="https://upload.wikimedia.org/wikipedia/commons/d/de/XAMPP_Windows_10.PNG" alt="Servidores activos">
  </section>
- <a href="https://www.youtube.com/watch?v=giCmjKBmK6A" target="_blank"> Abrir phpMyAdmin presionando el boton Admin en el servidor MySQL desde XAMPP </a> 
- <a href="https://disenowebakus.net/crear-una-base-de-datos-phpmyadmin-mysql-php.php" target="_blank"> Crear la base de datos, ponerle un nombre. IMPORTANTE NO AGREGARLE TABLAS A LA BD, tiene que estar VACÍA </a> 
- <a href="https://help.one.com/hc/es/articles/115005588189--C%C3%B3mo-importar-una-base-de-datos-a-phpMyAdmin-" target="_blank"> Importar el archivo finalpoo_tacotitos.sql (que se encuentra en el repositorio) para que se creen las tablas </a> 

### :white_check_mark: `Abrir Proyecto :open_file_folder:`
- Copiar la carpeta TacotitosMVC (que se encuentra en el repositorio) en Documentos dentro de la carpeta NetBeansProjects: 
    ```
    C:\Users\pepe05\Documents\NetBeansProjects
    ```
- Ejecutar Apache NetBeans IDE
- <a href="https://www.youtube.com/watch?v=pqvPri4enR4" target="_blank"> Abrir proyecto --> buscarlo dentro de Documentos--> NetBeansProjects </a> 
- Si aparece el TacotitosMVC con un simbolo de advertencia hay que resolver problemas --> Click derecho al icono del proyecto☕ --> Resolve Projects Problems --> luego apretar boton resolve
  <section align="center">
       <img src="https://i.ibb.co/RcBp1MK/resolve.jpg" alt="Resolve projects problems">
  </section>
- Ahora hay que buscar el mysql-connector-java-8.0.30 (que se encuentra en el repositorio) seleccionarlo y hacer click en Open
  <section align="center">
       <img src="https://i.ibb.co/k2MVYSz/mysql-Connector.jpg" alt="mysql-connector">
  </section>
- Si en Description sale 'This problem was resolved', el problema fue resuelto con éxito.

   ### :white_check_mark: `Configurar ConexionDb.java 📦`
   - Desplegar los archivos del proyecto y buscar la clase ConexionBD.java que esta en la carpeta Controlador
   - En la variable dbName hay que poner el nombre de la base de datos creada anteriormente
   - En la variable usuario hay que poner el nombre de usuario con el que se conectará a la BD. Si tenemos un usuario con diferente nombre al de root, modificarlo y escribir el nombre que corresponda. 
   - Si tenemos una <a href="https://www.mclibre.org/consultar/webapps/lecciones/phpmyadmin-1-soluciones.html" target="_blank"> contraseña para nuestro usuario </a> hay que escribirla en la variable clave. Sino tenemos contraseña hay que escribir lo siguiente:
     ```bash
     String clave = "";
     ```
     <section align="center">
       <img src="https://i.ibb.co/gjgQMZg/conexion-BD.jpg" alt="conexionBD">
     </section>

    ### :white_check_mark: `Ejecutar el proyecto 🚀`
    - Hacer click derecho al icono del proyecto☕ --> Run
    <section align="center">
       <img src="https://i.ibb.co/vqpN0qR/run-Project.jpg" alt="runProject">
    </section>
   
   
## Tecnologías utilizadas 🛠️
<section align="center">
<a href="https://www.java.com/es/download/help/whatis_java.html#:~:text=Java%20es%20una%20plataforma%20inform%C3%A1tica,crean%20muchos%20servicios%20y%20aplicaciones." target="_blank"> <img src="https://www.sommelierdecafe.com/wp-content/uploads/2009/06/java-logo1-1.png" alt="java" width="70" height="120"/> </a> 🔩
<a href="https://www.ionos.es/digitalguide/servidores/know-how/que-es-mysql/#:~:text=MySQL%20es%20un%20sistema%20de,por%20ejemplo%2C%20WordPress%20y%20TYPO3." target="_blank"> <img class="img" src="https://styles.redditmedia.com/t5_2qm6k/styles/communityIcon_dhjr6guc03x51.png" alt="mysql" width="120" height="120"/> </a> 🔩
<a href="https://www.phpmyadmin.net/" target="_blank"> <img class="img" src="https://www.techspot.com/images2/downloads/topdownload/2014/05/phpMyAdmin.png" alt="phpmyadmin" width="120" height="120"/> </a>
</section>




## Autor ✒️
| [<img src="https://img.freepik.com/vector-premium/logotipo-playa-isla-tropical-ilustracion-insignia-surf-vintage_194708-664.jpg" width=115><br><sub>Ana Lucia Juarez</sub>](https://github.com/manita02) | 
| :---: |
