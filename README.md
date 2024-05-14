# Tarea Automatizacion

**Página:** [https://www.perurail.com/](https://www.perurail.com/)

## Tipos de viajes

**Escenario 1**
- **Tipo:** SOLO IDA
- **Servicio:** BELMOND ANDEAN EXPLORER
- **Destino:** CUSCO
- **Ruta1:** PUNO > CUSCO
- **Ruta2:** AREQUIPA > PUNO > CUSCO

**Escenario 2**
- **Tipo:** IDA Y VUELTA
- **Servicio:** EXPEDITION
- **Destino:** MACHUPICCHU
- **Ruta1:** CUSCO > MACHUPICCHU

## Compra de boletos

1. Compra de boletos de solo ida, con partida Puno y destino Cusco, usando el servicio Belmond Andean Explorer.
2. Compra de boletos de solo ida,  con partida Arequipa y destino Cusco, usando el servicio Belmond Andean Explorer
3. Compra de boletos de ida y vuelta, con partida Cusco y destino Machupichu, usando el servicio PeruRail Expedition

## Validaciones

1. Intento de compra de más de 9 boletos en una operación
2. Compra de boletos sin incluir adultos
3. Selección de más de 9 cabinas en una operación

## Configuración de ambiente

**Contenido de qa.properties**

```bash
url.website=https://www.perurail.com
```

## Tabla busqueda (search)

| tipo         | servicio*               | partida  | destino     |
|--------------|-------------------------|----------|-------------|
| SOLO IDA     | BELMOND ANDEAN EXPLORER | PUNO     | CUSCO       |
| SOLO IDA     | BELMOND ANDEAN EXPLORER | AREQUIPA | CUSCO       |
| IDA Y VUELTA | PERURAIL EXPEDITION     | CUSCO    | MACHUPICCHU |

> **Servicio:** En algunas ocasiones no aparece el campo servicio, considerar la posibilidad.

## Tabla de trenes (trains)

| cabinas | adultos | ninhos |
|---------|---------|--------|
| 1       | 1       | 0      |
| 1       | 0       | 1      |
| 6       | 6       | 4      |

## Tabla de datos (passengers data)

| nombre | apellido | genero | pais | dni      | cumple     | telefono  | correo           |
|--------|----------|--------|------|----------|------------|-----------|------------------|
| John   | Doe      | M      | PER  | 12345678 | 01/01/2004 | 999888777 | john.d@gmail.com |

## Tabla de pagos (confirmation and payments)

| tarjeta    |
|------------|
| VISA       |
| MASTERCARD |
| AMERICAN   |
| PAYPAL     |

## Compra de boletos de solo ida, con partida Puno y destino Cusco, usando el servicio Belmond Andean Explorer

| Paso a Paso                                                                      | Datos                                                                                                                                                                  | Resultado Esperado                                                                                                                        |
|----------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------|
| El usuario ingresa al navegador y carga el sitio web.                            | url: https://www.perurail.com/es/                                                                                                                                      | El sistema carga el sitio web y muestra una sección 1: busqueda.                                                                          |
| El usuario ingresa los datos de su viaje en la sección de busqueda               | tipo: Solo Ida <br/> origen: Puno <br/>destino: Cusco servicio: BAE <br/> fecha: XX/XX/2024                                                                            | El sistema muestra los campos con los datos seleccionados.                                                                                |
| El usuario presiona el boton buscar                                              |                                                                                                                                                                        | El sistema redirecciona a la seccion 2: trenes y muestra un resumen del viaje con los criterios de busqueda seleccionados.                |
| El usuario selecciona el tipo de habitación y el numero de cabinas que alquilara | tipo: Suit Cabin <br/> cabinas: 1 <br/> adultos: 1 <br/> niños: 0                                                                                                      | El sistema muestra el subtotal del importe por las cabinas seleccionadas.                                                                 |
| El usuario presiona el boton continuar                                           |                                                                                                                                                                        | El sistema redirecciona a la seccion 3: datos de pasajeros y muestra una lista de formularios con la cantidad de pasajeros seleccionados. |
| Se ingresan los datos del pasajero                                               | nombre: john <br/> apellido: doe <br/> genero: M <br/> pais: PER <br/> dni: 12345678 <br/> cumple: 01/01/2004 <br/> telefono: 999888777 <br/> correo: john.d@gmail.com | El sistema muestra los campos con los datos seleccionados.                                                                                |
| El usuario presiona el boton continuar                                           |                                                                                                                                                                        | El sistema redirecciona a la seccion 4: confirmacion y pagos donde se muestra el importe total a pagar y un resumen de los boletos        |
| El usuario selecciona el tipo de tarjeta que va a utilizar                       | tipo: VISA                                                                                                                                                             | El sistema marca con un check verde el tipo de tarjeta seleccionada                                                                       |
| El usuario acepta los terminos y condiciones                                     |                                                                                                                                                                        | El sistema habilita el boton de pagar                                                                                                     |
| El usuario ingresa los datos de su tarjeta                                       |                                                                                                                                                                        | El sistema valida el pago                                                                                                                 |
