# Gestión de Contactos (Fichero binario)

Aplicación de consola en Java que gestiona una lista de contactos en memoria con persistencia en un fichero binario (`contactos.bin`).

## Modelo de datos

**Contacto**: `dni` (clave, único), `nombre`, `apellido`, `telefono`, `email`. Teléfono y email pueden repetirse.

## Persistencia

- Al iniciar, se lee `contactos.bin` y se carga la colección interna en memoria.
- Los cambios (altas y bajas) se aplican solo a la colección interna, **no** al fichero, hasta que se pide explícitamente actualizar.
- El fichero se sobrescribe únicamente al usar "Actualizar fichero" o al salir, y solo si ha habido cambios respecto a la lectura inicial.

## Menú

1. Listar contactos que están en memoria
2. Introducir un nuevo contacto
3. Eliminar un contacto
4. Actualizar fichero
5. Salir

### Introducir un nuevo contacto
Comprueba que no exista ya un contacto con el mismo DNI antes de añadirlo a la colección interna.

### Eliminar un contacto
Se elimina de la colección interna (el fichero no se toca hasta actualizar/salir).

### Actualizar fichero
Sobrescribe `contactos.bin` con el contenido actual de la colección, si ha habido modificaciones.

### Salir
Si hay cambios pendientes, sustituye el contenido del fichero por la colección actual antes de cerrar.

## Autor

**Kaori** — DAW 2025/2026  
[GitHub](https://github.com/Mia-Kaori)
