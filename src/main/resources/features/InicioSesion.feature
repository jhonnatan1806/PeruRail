#language: es
Característica: Iniciar sesion en la web y realiza compras

  Esquema del escenario: Inicio de sesion
    Dado Usuario ingresa pagina seleccionada "<caso_prueba>"
    Cuando Usuario ingresa sus datos "<caso_prueba>"
    Entonces Valido que el ingreso sea satisfactorio
    Ejemplos:
      | caso_prueba |
      | 1           |

  Esquema del escenario: Compra de articulos
    Dado Usuario selecciona items a comprar "<caso_prueba>"
    Cuando Realiza la compra de los items "<caso_prueba>"
    Entonces Valido la compra exitosa

    Ejemplos:
      | caso_prueba |
      | 1           |

  Esquema del escenario: Obtener Informacion
    Dado El usuario inicio sesion en la web SauceDemo "<caso_prueba>"
    Cuando Ingrese a visualizar un producto "<caso_prueba>"
    Entonces Obtendra la informacion del producto "<caso_prueba>"
    Ejemplos:
      | caso_prueba |
      | 1           |