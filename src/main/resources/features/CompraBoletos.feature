#language:en

Feature: Realizar compra de boletos de tren

    Background:
        Given que el usuario se encuentra en la pagina de inicio del sitio web

    Scenario Outline: Compra correcta de boletos de tren
        When el usuario selecciona las opciones de busqueda de su viaje "<search>"
        And selecciona la cantidad de cabinas y pasajeros "<cabin>"
        And se agrega los datos de los pasajeros "<user>"
        And se selecciona el metodo de pago y se aceptan los TyC
        Then se validan los detalles de la compra
        And se realiza la compra de los boletos
        Examples:
            | search   | cabin | user   |
            | 1        | 1     | 1      |

    Scenario Outline: Compra incorrecta de boletos con cabinas > 9
        When el usuario selecciona las opciones de busqueda de su viaje "<search>"
        And selecciona la cantidad de cabinas y pasajeros "<cabin>"
        Then el sistema muestra un mensaje de error
        Examples:
            | search   | cabin  |
            | 1        | 3      |

    Scenario Outline: Compra incorrecta de boletos con pasajeros > 9
        When el usuario selecciona las opciones de busqueda de su viaje "<search>"
        And selecciona la cantidad de cabinas y pasajeros "<cabin>"
        Then el sistema muestra un mensaje de error
        Examples:
            | search   | cabin |
            | 3        | 4     |

    Scenario Outline: Compra incorrecta de boletos sin incluir adultos
        When el usuario selecciona las opciones de busqueda de su viaje "<search>"
        And selecciona la cantidad de cabinas y pasajeros "<cabin>"
        Then el sistema muestra un mensaje de error
        Examples:
            | search   | cabin |
            | 3        | 5     |
