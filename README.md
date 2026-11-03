\# PetCare. Sistema de Gestión Veterinaria



Sistema de consola desarrollado en Java para la administración de una clínica veterinaria. Permite gestionar propietarios, mascotas, veterinarios, consultorios, citas, servicios y comprobantes de pago mediante un diseño orientado a objetos.



\## Descripción del proyecto



PetCare busca facilitar la organización de las actividades principales de una clínica veterinaria, proporcionando una estructura para registrar mascotas y propietarios, administrar citas, asignar servicios y calcular los costos de atención.



El proyecto fue desarrollado como parte de la experiencia educativa \*\*Diseño y Programación Orientada a Objetos\*\* de la Universidad Veracruzana.



\## Tecnologías utilizadas



\- \*\*Lenguaje:\*\* Java 21.

\- \*\*Gestión del proyecto:\*\* Maven.

\- \*\*Entorno de desarrollo:\*\* IntelliJ IDEA.

\- \*\*Control de versiones:\*\* Git y GitHub.

\- \*\*Paradigma:\*\* Programación Orientada a Objetos.



\## Modelo orientado a objetos



El sistema utiliza los siguientes conceptos:



| Concepto | Aplicación en el proyecto |

|---|---|

| Encapsulamiento | Atributos privados y protegidos, getters, setters y validaciones. |

| Abstracción | Clases abstractas `Persona`, `Mascota` y `Servicio`. |

| Herencia | Jerarquías de personas, mascotas y servicios. |

| Polimorfismo | Implementación de `hacerSonido()` y `calcularCosto()`. |

| Interfaces | Contrato `Facturable` para el cálculo de costos. |

| Composición | `Mascota` crea su propio `HistorialClinico`; `Clinica` crea sus `Consultorio`. |

| Agregación | `Clinica` registra veterinarios existentes. |

| Enumeraciones | `EstadoCita` limita los estados posibles de una cita. |



\### Diagrama de clases



El siguiente diagrama representa la estructura principal del sistema:



!\[Diagrama de clases de PetCare](docs/diagramas/Diagrama\_de\_Clases\_PetCare.png)



\## Estructura del proyecto



```text

PetCare\_OOP/

│

├── src/

│   └── main/

│       └── java/

│           ├── App/

│           │   └── Main.java

│           │

│           └── modelo/

│               ├── clinica/

│               ├── comprobante/

│               ├── mascotas/

│               ├── personas/

│               └── servicios/

│

├── docs/

│   ├── analisis.md

│   ├── diagramas/

│   │   └── Diagrama\_de\_Clases\_PetCare.png

│   └── capturas/

│

├── .gitignore

├── pom.xml

├── LICENSE

└── README.md

```



\## Instalación y ejecución



\### Requisitos previos



\- Tener instalado Java JDK 21.

\- Tener instalado Maven o utilizar el Maven Wrapper si está disponible.

\- Tener Git para clonar el repositorio.



\### Clonar el repositorio



```bash

git clone https://github.com/AndyCQ6/PetCare\_OOP.git

cd PetCare\_OOP

```



\### Compilar el proyecto



```bash

mvn clean compile

```



\### Ejecutar



El punto de entrada del programa es:



```text

src/main/java/App/Main.java

```



Puede ejecutarse desde IntelliJ IDEA utilizando la configuración de ejecución de la clase `Main`.



\## Funcionalidades principales



\- Registro y administración de propietarios.

\- Registro de mascotas de diferentes tipos.

\- Administración de veterinarios.

\- Creación y asignación de consultorios.

\- Agendamiento y administración de citas.

\- Registro de consultas generales, vacunaciones y cirugías.

\- Cálculo de costos mediante polimorfismo.

\- Generación de comprobantes.

\- Consulta de historiales clínicos.

\- Validación de reglas de negocio.



\## Evidencias de funcionamiento



Las capturas de pantalla correspondientes al escenario de demostración se encuentran en:



`docs/capturas/`



Se incluirán evidencias de:



\- Registro de propietarios y mascotas.

\- Registro de veterinarios y citas.

\- Atención de citas y aplicación de servicios.

\- Validación de reglas de negocio.

\- Comportamiento polimórfico de las mascotas.

\- Cálculo de cobros y consulta de historiales clínicos.



\## Documentación adicional



El análisis del sistema, las clases identificadas, sus responsabilidades y las decisiones de diseño se encuentran en:



\[`docs/analisis.md`](docs/analisis.md)



\## Uso de herramientas de inteligencia artificial



Se utilizaron herramientas de inteligencia artificial como apoyo durante el proceso de desarrollo, principalmente para resolver dudas, revisar conceptos de programación orientada a objetos y apoyar la elaboración de documentación.



Las decisiones de diseño, integración y adaptación del proyecto fueron revisadas durante el desarrollo. La herramienta se utilizó como apoyo y no como sustituto de la comprensión del código.



\## Autor



\*\*Andy Corro Quezada\*\*



Estudiante de Ingeniería de Software  

Universidad Veracruzana



GitHub: \[@AndyCQ6](https://github.com/AndyCQ6)



\## Licencia



Este proyecto se distribuye bajo la licencia especificada en el archivo \[`LICENSE`](LICENSE).

