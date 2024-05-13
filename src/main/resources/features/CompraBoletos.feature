#language:en

Feature: Realizar compra de boletos de tren

    Background:
        Given que el usuario se encuentra en la pagina de inicio del sitio web

    Scenario Outline: Compra de boletos de solo ida con partida Puno y destino Cusco usando el servicio Belmond Andean Explorer
        When el usuario selecciona las opciones de busqueda de su viaje "<busqueda>"
        And selecciona la cantidad de cabinas y boletos "<cabin>"
        And se agrega los datos de los pasajeros "<user>"
        And se selecciona el metodo de pago y se aceptan los TyC
        Then se validan los detalles de la compra
        And se realiza la compra de los boleto
        Examples:
            | busqueda | cabin | user |
            | 1        | 2     | 1, 2 |