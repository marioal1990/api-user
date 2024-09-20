# APLICACION TEST BCI REGISTRAR USUARIO

PARA LEVANTAR EL PROYECTO SOLO BASTA CORRER EL SIGUIENTE COMANDO: mvnw spring-boot:run
PARA BAJAR EL PROYECTO SOLO PRESIONAR CTRL + C

SE USA LA BASE DE DATOS H2 CON HIBERNATE, POR ENDE NO ES NECESARIO USAR UN SCRIPT DE BASE DE DATOS.

URL: http://localhost:8888/api-user

Debido a que la seguridad está creada con Spring Security, para poder acceder a los métodos http
del controlador creado, se debe agregar "Basic Authentication" en POSTMAN las siguientes credenciales:

ROL: Admin
USER: admin
PASSWORD: test123
PERMISOS: registrar, login, hearthbeat

ROL: User
USER: user
PASSWORD: test123
PERMISOS: login, hearthbeat

Métodos HTTP disponibles:e
 * POST - http://localhost:8888/api-user/api/v1/user/registro - Método para registrar un usuario
   * objeto entrada:
    {
        "name": "Juan Rodriguez",
        "email": "juan@rodriguez.org",
        "password": "hunter2",
        "phones": [
            {
                "number": "1234567",
                "citycode": "1",
                "contrycode": "57"
            }
        ]
    }

 * GET - http://localhost:8888/api-user/api/v1/user/login - Método para logear un usuario existente
   * objeto entrada:
     {
        "email": "juan@rodriguez.org",
        "password": "hunter2"
     }
 * GET - http://localhost:8888/api-user/api/v1/user/hearthbeat - Método para comprobar el estado del controlador


Validaciones: Las validaciones fueron creadas tanto para el correo electrónico como para la contraseña. Para cambiar 
la validacion de la contraseña se debe ir al archivo java llamado ConstantesUtil.java. 
