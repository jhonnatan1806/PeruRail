#language:en

Feature: Realizar compra de boletos de tren

    Background:
        Given que el usuario se encuentra en la pagina de inicio del sitio web

    Scenario Outline: Compra correcta de boletos de tren
        When el usuario selecciona las opciones de busqueda de su viaje "<search>"
        And selecciona la cantidad de cabinas y pasajeros "<trains>"
        And se agrega los datos de los pasajeros "<passengers_data>"
        And se selecciona el metodo de pago y se aceptan los TyC
        Then se validan los detalles de la compra
        And se realiza la compra de los boletos
        Examples:
            | search    | trains    | passengers_data   |
            | 1         | 1         | 1                 |

    Scenario Outline: Compra incorrecta de boletos con cabinas > 9
        When el usuario selecciona las opciones de busqueda de su viaje "<search>"
        And selecciona la cantidad de cabinas y pasajeros "<trains>"
        Then el sistema muestra un mensaje de error
        Examples:
            | search    | trains    |
            | 1         | 3         |

    Scenario Outline: Compra incorrecta de boletos con pasajeros > 9
        When el usuario selecciona las opciones de busqueda de su viaje "<search>"
        And selecciona la cantidad de cabinas y pasajeros "<trains>"
        Then el sistema muestra un mensaje de error
        Examples:
            | search    | trains    |
            | 2         | 4         |

    Scenario Outline: Compra incorrecta de boletos sin incluir adultos
        When el usuario selecciona las opciones de busqueda de su viaje "<search>"
        And selecciona la cantidad de cabinas y pasajeros "<trains>"
        Then el sistema muestra un mensaje de error
        Examples:
            | search    | trains    |
            | 3         | 5         |
