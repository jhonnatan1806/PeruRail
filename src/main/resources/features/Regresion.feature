#language:en
Feature: Pruebas de Regresion

    Background:
        Given que el usuario se encuentra en la pagina de inicio del sitio web

    Scenario Outline: Compra correcta de boletos de tren
        When el usuario selecciona las opciones de busqueda de su viaje "<search>"
        And selecciona la cantidad de cabinas y pasajeros "<trains>"
        And se completa los datos de los pasajeros "<trains>"
        And se selecciona el metodo de pago y se aceptan los TyC
        Then se validan los detalles de la compra
        And se realiza la compra de los boletos
        Examples:
            | search    | trains    |
            | 1         | 1,2       |

    Scenario Outline: Validacion de limite maximo de reserva de cabinas por operacion
        When el usuario selecciona las opciones de busqueda de su viaje "<search>"
        And selecciona la cantidad de cabinas y pasajeros "<trains>"
        Then el sistema muestra un mensaje de error
        Examples:
            | search    | trains    |
            | 1         | 3         |

    Scenario Outline: Validacion de limite maximo de boletos por operacion
        When el usuario selecciona las opciones de busqueda de su viaje "<search>"
        And selecciona la cantidad de cabinas y pasajeros "<trains>"
        Then el sistema muestra un mensaje de error
        Examples:
            | search    | trains    |
            | 2         | 4         |

    Scenario Outline: Verificacion de inclusion de adultos en reserva de cabinas
        When el usuario selecciona las opciones de busqueda de su viaje "<search>"
        And selecciona la cantidad de cabinas y pasajeros "<trains>"
        Then el sistema muestra un mensaje de error
        Examples:
            | search    | trains    |
            | 3         | 5         |