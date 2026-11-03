# PetCare. Sistema de Gestión Veterinaria

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-build-C71A36?logo=apachemaven&logoColor=white)
![Tipo](https://img.shields.io/badge/App-consola-blue)

Aplicación de consola en **Java** para administrar una clínica veterinaria: propietarios, mascotas, veterinarios, consultorios, citas, servicios y comprobantes de pago, con un diseño orientado a objetos.

Desarrollado para la experiencia educativa **Diseño y Programación Orientada a Objetos** de la Universidad Veracruzana.

## Contenido

- [Funcionalidades](#-funcionalidades)
- [Tecnologías](#-tecnologías)
- [Modelo orientado a objetos](#-modelo-orientado-a-objetos)
- [Diagrama de clases](#-diagrama-de-clases)
- [Estructura del proyecto](#-estructura-del-proyecto)
- [Instalación y ejecución](#-instalación-y-ejecución)
- [Demostración](#-demostración)
- [Documentación adicional](#-documentación-adicional)
- [Uso de inteligencia artificial](#-uso-de-inteligencia-artificial)
- [Autor](#-autor)
- [Licencia](#-licencia)

## Funcionalidades

- Registro y administración de propietarios y sus mascotas (perros, gatos y aves exóticas).
- Administración de veterinarios y recepcionistas.
- Creación y asignación de consultorios.
- Agendamiento y administración de citas con control de estado.
- Servicios: consulta general, vacunación y cirugía.
- Cálculo de costos mediante polimorfismo.
- Generación de comprobantes de pago.
- Consulta de historiales clínicos y de cobros.
- Validación de reglas de negocio.

## Tecnologías

| Herramienta | Uso |
|---|---|
| Java 21 | Lenguaje |
| Maven | Compilación y gestión del proyecto |
| IntelliJ IDEA | Entorno de desarrollo |
| Git y GitHub | Control de versiones |

## Modelo orientado a objetos

| Concepto | Aplicación en el proyecto |
|---|---|
| Encapsulamiento | Atributos privados y protegidos, getters, setters y validaciones |
| Abstracción | Clases abstractas `Persona`, `Mascota` y `Servicio` |
| Herencia | Jerarquías de personas, mascotas y servicios |
| Polimorfismo | `hacerSonido()` y `calcularCosto()` |
| Interfaces | Contrato `Facturable` para el cálculo de costos |
| Composición | `Mascota` crea su `HistorialClinico`; `Clinica` crea sus `Consultorio` |
| Agregación | `Clinica` registra veterinarios existentes |
| Enumeraciones | `EstadoCita` limita los estados de una cita |

El razonamiento detrás de cada decisión está en [`docs/analisis.md`](docs/analisis.md).

## Diagrama de clases

![Diagrama de clases de PetCare](docs/diagramas/Diagrama_de_Clases_PetCare.png)

## Estructura del proyecto

```text
PetCare_OOP/
├── src/
│   └── main/java/
│       ├── App/
│       │   └── Main.java
│       └── modelo/
│           ├── clinica/
│           ├── comprobante/
│           ├── mascotas/
│           ├── personas/
│           └── servicios/
├── docs/
│   ├── analisis.md
│   ├── diagramas/
│   └── capturas/
├── .gitignore
├── pom.xml
├── LICENSE
└── README.md
```

## Instalación y ejecución

### Requisitos

- JDK 21
- Maven 3.9 o superior
- Git

```bash
java -version
mvn -version
```

### Pasos

```bash
# 1. Clonar el repositorio
git clone https://github.com/AndyCQ6/PetCare_OOP.git
cd PetCare_OOP

# 2. Compilar
mvn clean compile

# 3. Ejecutar
java -cp target/classes App.Main
```

También puedes abrir el proyecto en IntelliJ IDEA y ejecutar `src/main/java/App/Main.java`.

## Demostración

`Main` ejecuta un escenario de demostración completo. Las capturas están en [`docs/capturas/`](docs/capturas/):

| Escenario | Captura |
|---|---|
| Registro de propietarios y mascotas | `docs/capturas/01-registro.png` |
| Agendamiento y atención de citas | `docs/capturas/02-citas.png` |
| Validación de reglas de negocio | `docs/capturas/03-validaciones.png` |
| Polimorfismo de mascotas | `docs/capturas/04-polimorfismo.png` |
| Cobros e historial clínico | `docs/capturas/05-cobros.png` |

## Documentación adicional

Análisis del sistema, clases identificadas, responsabilidades y decisiones de diseño: [`docs/analisis.md`](docs/analisis.md).

## 🤖 Uso de inteligencia artificial

Se usaron herramientas de IA como apoyo para resolver dudas, revisar conceptos de programación orientada a objetos y redactar documentación. Las decisiones de diseño, la integración y la adaptación del código fueron revisadas y comprendidas por el autor; la herramienta se usó como apoyo y no como sustituto del criterio propio.

## 👤 Autor

**Andy Corro Quezada**
Estudiante de Ingeniería de Software, Universidad Veracruzana
GitHub: [@AndyCQ6](https://github.com/AndyCQ6)

## 📄 Licencia

Distribuido bajo la licencia MIT [`LICENSE`](LICENSE).
