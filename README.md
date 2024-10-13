# REVERSO SOCIAL - Backend 

## Descripción General 👭 💪
Este proyecto, desarrollado como parte del programa pedagógico de Factoria F5, es un proyecto final creado en colaboración con un cliente real. La plataforma promueve la igualdad de género y el empoderamiento de las mujeres mediante dos enfoques principales: Planes de Igualdad y FEMsenior.

Planes de Igualdad:
Dirigido a cargos políticos, sindicales, administraciones públicas y empresas privadas. Este módulo tiene como objetivo eliminar todas las formas de discriminación basadas en sexo, género, orientación sexual, origen, religión o clase social, ofreciendo recursos y herramientas para fomentar la igualdad en diversos ámbitos.

FEMsenior:
FEMsenior es una comunidad creada para mujeres de 50 años o más, con el fin de desafiar los estereotipos relacionados con la edad. Este espacio permite a las usuarias compartir experiencias, participar en eventos, y acceder a oportunidades de empleo y recursos que destacan el poder de la edad como fuente de fortaleza.

La plataforma permite:

- Crear, editar y eliminar eventos: Las administradoras y Femsenior pueden gestionar las distintas secciones tales como son los eventos, los servicios, empleo y recursos desde su creación hasta su eliminación.   
- Acceder a recursos y participación activa: Las usuarias pueden explorar y aprovechar los recursos disponibles, incluyendo mentorías que ofreceran las FemSenior, formaciones y los recursos que se compartan en la comunidad.   
- Unirse a una comunidad activa: La comunidad facilita la colaboración y el apoyo entre mujeres de distintas edades y trasfondos, ofreciendo un espacio para mentorías, participación en servicios y acceso a herramientas tales como una sección de empleo donde podrán 
  subir sus currículumns para que promuevan su desarrollo personal y profesional.   
- Este proyecto invita a mujeres y entidades comprometidas con la igualdad a colaborar en la construcción de un futuro más equitativo.

     
![image](https://github.com/user-attachments/assets/abd87b59-7619-43bb-8407-1b7ba0638130)



       


## Características Principales 🚀

- Gestión de Autenticación y Autorización: Soporte para múltiples roles, como administradoras, Femsenior, y usuarias, con permisos específicos para cada uno, asegurando un acceso controlado a las diferentes funcionalidades de la aplicación.

- Administración de Eventos y Servicios: Las administradoras y Femsenior pueden crear, editar y eliminar eventos, servicios, oportunidades de empleo y otros recursos. Las usuarias pueden visualizar y participar en los eventos y servicios disponibles.

- Sistema de Almacenamiento en la Nube: Integración con Cloudinary para el manejo de archivos, permitiendo almacenar y gestionar imágenes y otros archivos de manera segura y eficiente.

- Base de Datos Relacional: Utilización de PostgreSQL para el almacenamiento de datos, garantizando consistencia, escalabilidad y un manejo estructurado de la información de los usuarios y los recursos.

- Endpoints Probados con Postman: Los endpoints de la API han sido cuidadosamente probados con Postman para asegurar su correcto funcionamiento y facilitar la validación de las funcionalidades durante el desarrollo.

- Arquitectura RESTful: Diseño de la API siguiendo principios REST, permitiendo una fácil integración con el frontend y mejorando la escalabilidad de la aplicación.
  
## Tecnologías Utilizadas 🛠️

**[Java](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)**: Lenguaje principal utilizado en el backend, ofreciendo robustez y rendimiento en la lógica del servidor.   
**[SpringBoot](https://spring.io/projects/spring-boot)**: Framework que simplifica el desarrollo de aplicaciones Java, permitiendo una rápida creación de servicios web RESTful.   
**[SpringSecurity](https://spring.io/projects/spring-security)**: Implementación de autenticación y autorización mediante JWT (JSON Web Tokens), garantizando un acceso seguro y controlado a los recursos.   
**[PostgreSQL](https://www.postgresql.org/)**: Base de datos relacional utilizada para almacenar de forma eficiente y segura la información sobre usuarios, roles y datos relevantes de la aplicación.   
**[Cloudinary](https://cloudinary.com/)**: Solución en la nube para la gestión de archivos y medios, facilitando el almacenamiento, la manipulación y el acceso seguro a archivos multimedia.   
**[Maven](https://maven.apache.org/)**: Herramienta de construcción y gestión de dependencias que permite mantener el proyecto organizado y simplificar la administración de bibliotecas y frameworks.   
**[JUnit 5](https://junit.org/junit5/)**: Biblioteca para la realización de pruebas, empleada en tests de integración que aseguran la funcionalidad y estabilidad del backend.   
**[Mockito](https://site.mockito.org/)**: Framework de pruebas unitarias utilizado junto con JUnit para la creación de tests automatizados, permitiendo simular comportamientos y mejorar la cobertura de código.   
**[Postman](https://www.postman.com/)**: Herramienta utilizada para probar los endpoints de la API, facilitando el desarrollo y asegurando la integridad de las funcionalidades.   
**[Lombok](https://projectlombok.org/)**: Biblioteca que reduce el código boilerplate mediante anotaciones, mejorando la legibilidad y eficiencia en el desarrollo.

## Arquitectura del Proyecto 🗂️

Este proyecto sigue el patrón de arquitectura Modelo-Vista-Controlador (MVC), lo que permite una separación clara de responsabilidades:

- **Modelo**: Contiene la lógica de negocio y se encarga de la gestión de los datos, incluidos los modelos de base de datos.
  
- **Vista**: Responsable de la interfaz de usuario y de la presentación de la información.
  
- **Controlador**: Actúa como intermediario entre la Vista y el Modelo. Aquí manejamos la lógica de la aplicación, controlamos el flujo de datos y gestionamos las peticiones.

Este enfoque modular facilita la mantenibilidad y escalabilidad del proyecto, permitiendo a cada capa evolucionar de forma independiente y mejorando la claridad del código.
```
├── REVERSOSOCIAL
│   ├── src/main/java
│   │    ├── config
│   │    │     ├── admin
│   │    │     │    └──  AdminInitializer
│   │    │     │── data
|   |    |     |   └──  DataLoader
│   │    │     ├── exception
|   |    |     |     ├── validation
|   |    |     |     |   ├── anotation
|   |    |     |     |       └── ValdPdf
|   |    |     |     |   └── validator
|   |    |     |     |       └──  PdfFileValidator
|   |    |     |     ├── CustomException
|   |    |     |     ├── ErrorObject
|   |    |     |     ├── EventFullException
|   |    |     |     ├── ExistingEmailException
|   |    |     |     ├── ExistingUsernameException
|   |    |     |     ├── GlobalExceptionHandler
|   |    |     |     ├── InvalidCredentialsException
|   |    |     |     ├── ResourceNotFoundException
|   |    |     |     └── UsernameNotFoundException
|   |    |     ├── security 
|   |    |          ├── jwt
|   |    |          |   ├── JWTAuthenticationConfig
|   |    |          |   └──  JWTAuthenticationFilter
│   │    │          └──  SecurityConfig
│   │    ├── controller
│   │    │     ├── AuthController
│   │    │     ├── EmployController
│   │    │     ├── EventController
│   │    │     ├── ResourceController
│   │    │     └── ServiceController
│   │    ├── models
|   |    |     ├── dto
│   │    │     |      ├── AuthResponseeDto
│   │    │     |      ├── EmployDto
│   │    │     |      ├── EventDto
│   │    │     |      ├── LoginDto
│   │    │     |      ├── RegisterDto
│   │    │     |      ├── ResourceDto
|   |    |     |      └── ServiceBusinessDto
|   |    |     ├── entity
|   |    |            ├── Employ
|   |    |            ├── EPermission
|   |    |            ├── ERole
|   |    |            ├── Event
|   |    |            ├── Permission
|   |    |            ├── Resource
|   |    |            ├── Role
|   |    |            ├── Sector
|   |    |            ├── ServiceBusiness
|   |    |            └── User
│   │    ├── repository
│   │    │      ├── EmployRepository
│   │    │      ├── EventRepository
│   │    │      ├── PermissionRepository
│   │    │      ├── ResourceRepository
│   │    │      ├── RoleRepository
│   │    │      ├── SectorRepository
│   │    │      ├── ServiceBusinessRepository
|   |    |      └── UserRepository 
|   |    |     
│   │    ├── service
│   │    │      ├── impl
│   │    │      |     ├── CustomUserDetailsService
│   │    │      |     ├── EmployServiceImpl
│   │    │      |     ├── EventServiceImpl
│   │    │      |     ├── ResourceServiceImpl
│   │    │      |     ├── ServiceBusinessServiceImpl
│   │    │      |     └── UserServiceImpl
│   │    │      ├── EmployService
│   │    │      ├── EventService
│   │    │      ├── FileStorageService
│   │    │      ├── ResourceService
│   │    │      ├── ServiceBusinessService
│   │    │      └── UserService
│   │    │── ReversosocialApplication     
│   │    ├── resources
│   │    │    ├── application.properties
│   │    │    └── appication-test.properties
│   │    └── test
│   │           ├── controller 
│   │           |      ├── AuthControllerTest
│   │           |      ├── EventControllerTest
│   │           |      └── ServiceBusinessControllerTest
│   │           ├── service
│   │           |      ├── EventServiceImplTest
│   │           |      └── UserServiceImplTest
│   │           └── Reversosocial ApplicationTests
│   │                    
│   ├── target
│   ├── .env
│   ├── .gitignore
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
└── └── README.md
```
  
## Configuración del Proyecto 🔧

Clonar este repositorio:

```
git clone https://github.com/Deiximar/reversosocial.git
```

Navegar al directorio del proyecto:

```
cd reversosocial
```
Configurar la base de datos PostgreSQL:

- Crear una base de datos y actualizar los detalles de conexión en el archivo application.properties.
  
Configurar las credenciales de Cloudinary:

- Añadir las claves de Cloudinary en el archivo .env o en application.properties.
  
Construir y ejecutar la aplicación:

```
mvn clean install
mvn spring-boot:run
```

## Variables de Entorno ⚙️

Debes configurar las siguientes variables de entorno en tu archivo .env o application.properties:

- JWT_SECRET_KEY: Clave secreta para la generación de tokens JWT.
- CLOUDINARY_API_KEY: API Key de tu cuenta de Cloudinary.
- CLOUDINARY_API_SECRET: API Secret de Cloudinary.
- CLOUDINARY_CLOUD_NAME: Nombre del Cloudinary.
  
## Contribución 👥

Si deseas contribuir a este proyecto, sigue los siguientes pasos:

1. Haz un fork del repositorio.
  
2. Crea una nueva rama:

```
git checkout -b feature/nueva-funcionalidad
```

3. Realiza tus cambios y haz commit:

```
git commit -m 'Añadir nueva funcionalidad'
```

4. Sube los cambios a tu rama:
```
git push origin feature/nueva-funcionalidad
```

5. “Envía un Pull Request para revisión”.

   ## Integración con FrontEnd 🔗
   
 Para obtener detalles sobre la configuración del frontend, consulta el README del [FrontEnd README.](https://github.com/Paola077/Reverso_social_FrontEnd)

Autora ✒️
- [**Ziortza**](https://github.com/indiakka) 
- [**Cristina**](https://github.com/CrisZDE)
- [**Paola**](https://github.com/Paola077)
- [**Mabel**](https://github.com/MabelZar)
- [**Laura**](https://github.com/Lauraa23)
- [**Deiximar**](https://github.com/Deiximar)
- [**Carolina**](https://github.com/CarolBV)
