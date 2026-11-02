SIGEP - Sistema de Gestión de Pedidos

Sistema de escritorio hecho en Java (Swing) para el restaurante Crazy Burguer's. Nuestro sistema permite gestionar, productos y pedidos, y generar el ticket del pedido en PDF.

 
 Contenido del repositorio

- src/: es el código fuente del proyecto (Maven)
- pom.xml: son las dependencias y configuración de Maven
- database/sigep.sql: el script SQL con la estructura y los datos de la base de datos
- Releases: instalador SIGEP-1.0.exe 

Requisitos
- Para el instalador: Windows y conexión a internet.
- Para ejecutar desde el código: Java JDK 21 y Apache NetBeans 

Base de datos

El sistema usa MySQL con las tablas usuario, producto, pedido y detallePedido. El script completo está en database/sigep.sql.

Credenciales de conexión (base de datos en Aiven):

- Servidor: mysql-37cfeb24-sigepcrazyb.g.aivencloud.com
- Puerto: 25461
- Base de datos: defaultdb
- Usuario: avnadmin
- Contra: AVNS_4rQPFrm5_F3fx9op6Na

Cómo ejecutar el proyecto
Opción A: con el instalador 

1. Entra a la sección Releases de este repositorio 
2. Descarga SIGEP-1.0.exe de la versión v1.0.
3. Ejecutelo
4. Como Requiere conexión a internet para conectarse a la base de datos en la nube porque asi la tuvimos que alojar para que se pudieran acceder a los datos
   deberá tomar en cuenta que la información carga algo lenta por ejemplo al filtrar por categorias pareciera que no pero o se le da otra vez o se espera a que carguen los productos.

Opción B: desde el código, con la base de datos en la nube (Aiven)

1. Clonar o descargar este repositorio y abrirlo en NetBeans
2. Esperar a que Maven descargue las dependencias.
3. Abrir src/main/java/crazy/DAO/ConexionBD.java y en la línea 10 reemplazar TU_PASSWORD_AQUI por la contraseña indicada arriba. (AVNS_4rQPFrm5_F3fx9op6Na) Lo hicimos asi porque sino no podiamos hacer el push
4. Clic derecho sobre el proyecto > Clean and Build.
5. Clic derecho sobre SIGEP.java (paquete crazy.UI) > Run File.


Usuarios de prueba

- Administrador: usuario admin, contraseña admin123 o
- Administrador: usuario: Gerardo Arrivillaga, contraseña Gerar12cread

link de video promocional:


Fernando Daniel Monzón Fuentes
