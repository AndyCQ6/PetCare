# Análisis y diseño orientado a objetos. PetCare

## 1. Descripción del sistema

PetCare es un sistema de gestión para una clínica veterinaria desarrollado en Java. Su propósito es administrar propietarios, mascotas, veterinarios, consultorios, citas y servicios veterinarios, además de generar comprobantes y consultar los cobros realizados.

El sistema aplica los principios de la programación orientada a objetos mediante encapsulamiento, abstracción, herencia, polimorfismo y relaciones entre clases.

## 2. Identificación y análisis de clases

| Clase / tipo | Tipo | Atributos principales | Responsabilidades |
|---|---|---|---|
| Main | Concreta | No identificados en el análisis | Iniciar la aplicación y ejecutar el escenario de demostración. |
| Clinica | Concreta | nombre, consultorios, veterinarios, citas, mascotas, ultimoFolioCita | Administrar los recursos de la clínica, registrar mascotas, agendar citas, asignar consultorios y consultar cobros. |
| Cita | Concreta | citaId, fechaHora, estado, mascota, veterinario, consultorio, servicio, observaciones, comprobante | Representar una cita, controlar su estado, registrar observaciones y generar comprobantes. |
| Consultorio | Concreta | numero | Representar un consultorio e identificarlo mediante su número. |
| EstadoCita | Enumeración | PROGRAMADA, ATENDIDA, CANCELADA | Definir los estados permitidos para una cita. |
| HistorialClinico | Concreta | citas | Registrar y consultar las atenciones de una mascota. |
| Comprobante | Concreta | contadorFolios, folio, total, cita | Generar y representar el comprobante de cobro de una cita. |
| Persona | Abstracta | nombre, apellido, telefono, correo | Proporcionar los datos y comportamientos comunes de las personas del sistema. |
| Propietario | Concreta | direccion, mascotas | Administrar los datos del propietario y sus mascotas. |
| Veterinario | Concreta | cedulaProfesional, especialidad | Representar al veterinario y su información profesional. |
| Recepcionista | Concreta | turno | Representar al personal de recepción y permitir el registro de citas. |
| Mascota | Abstracta | nombre, edad, peso, propietario, historial | Definir las características y comportamientos comunes de las mascotas. |
| Perro | Concreta | raza, nivelEnergia | Representar perros y definir su sonido característico. |
| Gato | Concreta | domestico | Representar gatos y definir su sonido característico. |
| AveExotica | Concreta | longitudAlas | Representar aves exóticas y definir su sonido característico. |
| Facturable | Interfaz | No tiene atributos de instancia | Establecer el contrato de cálculo de costos mediante `calcularCosto()`. |
| Servicio | Abstracta | nombre | Definir la estructura común de los servicios veterinarios. |
| Cirugia | Concreta | complejidad, horasQuirofano, tarifaPorHora | Calcular el costo de una cirugía considerando sus características. |
| ConsultaGeneral | Concreta | tarifaBase | Representar una consulta general y calcular su costo. |
| Vacunacion | Concreta | costoVacuna, tarifaAplicacion | Representar una vacunación y calcular el costo total. |

**Nota:** los atributos de las clases hijas se complementan con los atributos heredados de sus respectivas clases abstractas. Los métodos de acceso, constructores y validaciones forman parte de las responsabilidades de cada clase.

## 3. Jerarquías de herencia

El sistema contiene tres jerarquías principales.

### 3.1 Jerarquía de personas

`Persona` es una clase abstracta que concentra los datos compartidos por las personas del sistema. De ella heredan:

- Propietario
- Veterinario
- Recepcionista

Esta decisión evita repetir atributos como nombre, apellido, teléfono y correo electrónico.

### 3.2 Jerarquía de mascotas

`Mascota` es una clase abstracta que representa las características comunes de los animales registrados. De ella heredan:

- Perro
- Gato
- AveExotica

Cada clase concreta implementa el método abstracto `hacerSonido()`, proporcionando un comportamiento diferente según el tipo de mascota.

### 3.3 Jerarquía de servicios

`Servicio` es una clase abstracta que proporciona una estructura común para los servicios veterinarios. De ella heredan:

- Cirugia
- ConsultaGeneral
- Vacunacion

Cada servicio implementa su propio cálculo de costo.

## 4. Implementación de interfaces

La interfaz `Facturable` establece el método:

```java
BigDecimal calcularCosto();
```

Esta interfaz permite que diferentes servicios compartan un contrato para calcular sus costos, sin importar las particularidades de cada uno.

Las clases concretas que implementan este contrato son:

- Cirugia
- ConsultaGeneral
- Vacunacion

`Servicio` aporta únicamente la estructura común (el atributo `nombre`); el contrato de costo lo cumple cada servicio concreto. La implementación permite usar polimorfismo para calcular costos de diferentes servicios mediante una misma abstracción.

## 5. Relaciones entre clases

### 5.1 Asociaciones

- **Cita — Mascota:** una cita corresponde a una mascota.
- **Cita — Veterinario:** una cita tiene asignado un veterinario.
- **Cita — Consultorio:** una cita puede tener asignado un consultorio.
- **Cita — Servicio:** una cita registra el servicio veterinario que se realizará.
- **Mascota — Propietario:** cada mascota está relacionada con su propietario.
- **Comprobante — Cita:** el comprobante contiene la referencia a la cita correspondiente.
- **HistorialClinico — Cita:** el historial registra las citas y atenciones de una mascota.

### 5.2 Agregación

**Clinica — Veterinario**

La clínica mantiene una colección de veterinarios. Estos se incorporan mediante `agregarVeterinario(Veterinario veterinario)`, por lo que pueden existir independientemente de la clínica que los registra.

### 5.3 Composición

**Mascota — HistorialClinico**

Cada mascota crea su propio historial clínico en su constructor mediante `new HistorialClinico()`. El historial pertenece a esa mascota y no se comparte como un objeto independiente.

**Clinica — Consultorio**

La clínica crea los consultorios mediante `crearConsultorio(int numero)`, instancia cada objeto y lo incorpora a su colección. Por ello se representa como una relación de composición.

## 6. Ejemplos de objetos

### Ejemplo 1: Propietario

Un propietario puede representarse mediante un objeto de la clase `Propietario`, con nombre, apellido, teléfono, correo electrónico y dirección. Puede registrar y administrar varias mascotas.

### Ejemplo 2: Mascota

Un objeto de la clase `Perro` puede representar a una mascota llamada Max, con edad, peso, propietario, raza y nivel de energía. También cuenta con un historial clínico propio y puede responder al método `hacerSonido()`.

### Ejemplo 3: Servicio

Un objeto de la clase `Vacunacion` puede representar un servicio con costo de vacuna y tarifa de aplicación. Al invocar `calcularCosto()`, devuelve el costo correspondiente mediante `BigDecimal`.

## 7. Clases candidatas descartadas

Durante el análisis se identificaron conceptos que parecen candidatos a clase, pero que no necesitan representarse como entidades independientes.

### 7.1 Dirección

La dirección del propietario se maneja como un atributo `String` dentro de `Propietario`. Para los requerimientos actuales no necesita identidad, comportamiento ni ciclo de vida propios.

### 7.2 Teléfono

El teléfono se maneja como un atributo `String` dentro de `Persona`. No requiere una clase independiente porque su función actual es almacenar un dato de contacto y no representa una entidad con responsabilidades propias.

## 8. Conclusión

El diseño de PetCare utiliza la orientación a objetos para representar los elementos principales de una clínica veterinaria. La herencia permite reutilizar características comunes, las clases abstractas establecen estructuras generales, las interfaces definen contratos y el polimorfismo permite ejecutar comportamientos específicos según el tipo de objeto.

Las asociaciones, agregaciones y composiciones representan las relaciones entre las entidades del sistema y permiten organizar sus responsabilidades. En conjunto, estas decisiones favorecen un diseño modular, organizado y extensible.
