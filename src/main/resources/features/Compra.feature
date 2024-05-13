#language:en

Feature: Realizar compra de boletos de tren

    Background:
        Given que el usuario se encuentra en la pagina de inicio del sitio web

    Scenario Outline: Compra de boletos de solo ida con partida Puno y destino Cusco usando el servicio Belmond Andean Explorer
        When el usuario selecciona las opciones de busqueda de su viaje "<busqueda>"
        Examples:
            | busqueda |
            | 1 |