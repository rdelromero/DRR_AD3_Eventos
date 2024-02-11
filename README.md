Desarrollo web en entorno servidor

# AD-1. AD-3. Trabajo en Equipo. Spring Boot Security, Jpa Eventos

## Enunciado

ROL CLIENTE CON SPRING BOOT MVS, JPA, SECURITY

Una empresa se dedica a montar eventos.
Cada evento es de un tipo distinto. Los tipos de evento son: concierto, despedida, cumpleaños, boda, ….., y nos interesa un código que lo identifique y su descripción.
A un evento se inscriben clientes, de los que debemos guardar los datos que figuran en la base de datos de usuario.

![imagen](/Desarrollo_WE_Servidor_AD-3_imagen1.png)

Un CLIENTE puede apuntarse a más de un evento, y de cada evento puede hacer hasta 10 reservas, en la misma o en distintas reservas(controlar).

Permitimos la entrada a la aplicación de usuarios anónimos, pueden navegar, seleccionar por tipos , por destacados y activos y ver detalle de un evento, y nada más.
Un cliente cuando se registra le damos el rol “Cliente”.
Cuando un cliente registrado entra a la aplicación con la url “/clientes/**” ve esta pantalla, con este menú y con la lista de eventos destacados

![imagen](/Desarrollo_WE_Servidor_AD-3_imagen2.png)

DESARROLLO DE LA ACTIVIDAD. ROL CLIENTE

• HomeController: “/”
Un cliente entra en este Controlado, y con esta url, tanto como invitado, como cuando se loga(formulario usuario/password) e inicia la sesión (guardar el usuario logado en un atributo de sesión). En este caso le mandamos a la página principal en donde verá la lista de Eventos activos y destacados y la opción de ver detalle.

También podrá ver un desplegable de los tipos de eventos para poder filtrar. Y un botón añadido de Eventos para obtener todos los eventos activos o los destacados.

Y un botón de Mis reservas. Y el botón de cerrar sesión.

Casos de uso(url general “/eventos”):
- “/activos” -> Sacar el listado de todos los eventos activos y opción detalle
- “/destacados” -> Sacar el listado de todos los eventos destacados y opción detalle
- “/detalle/{id}” -> con @Get mostrar un html con los datos del evento, y un formulario con la cantidad(máximo 10 por reserva) y un botón que ponga “reservar”. Siempre y cuando la cantidad de plazas que quiere no superen el aforo máximo.
- “/reservar/{id} “ -> dar de alta la reserva de ese evento. Posibilidades:
    * Todo correcto, mensaje reserva realizada.
    * Se supera el aforo máximo. No permitir la reserva e informar.
    * Ya tenía una reserva a este evento con 10 reservas: informar
    * No permitir cantidad de entradas en una reserva superior a 10

![imagen](/Desarrollo_WE_Servidor_AD-3_imagen3.png)

Nota: falta el Precio del evento en esta imagen
La imagen del evento poner una aunque siempre sea la misma( o ninguna)

actualizar
- /misReservas “ -> listado con las reservas activas(fecha de inicio > que la actual). Con un botón de cancelar.
- /cancelar/{id} “ -> Borrar la reserva en la Base de Datos, y volver al listado de mis reservas.

![imagen](/Desarrollo_WE_Servidor_AD-3_imagen4.png)