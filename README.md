# To-Do App

Aplicación móvil Android para gestionar tareas (añadir, editar, marcar como completadas, borrar), usando una base de datos local (Room).  

## ¿Qué es? / ¿Qué hace?

- Permite crear tareas con título y descripción.  
- Mostrar la lista de tareas en un RecyclerView.  
- Marcar tareas como completadas (tachado).  
- Editar y eliminar tareas.
- Persistencia local con SQLite / Room.  

## Instalación y ejecución

1. Copia el repositorio:

   https://github.com/FroppyDev/To-Do-App.git

2. Abrir en Android Studio

    Selecciona "Clone Repository"

    Pega el url del primer paso

    dale al boton de "clone"

3. Sincronizar Gradle

    Android Studio detectará todas las dependencias y generará los archivos necesarios.

4. Ejecutar la app

    Conecta un dispositivo físico o usa un emulador

    Presiona Run ▶ en Android Studio

    Android studio se encargara de instalar y ejecutar la aplicacion en el dispositivo fisico o emulado (Recuerda activar la depuracion USB)

## Estructura del proyecto

To-Do-App/
 ├── .gitignore
 ├── README.md
 ├── app/
 │   ├── build.gradle
 │   ├── proguard-rules.pro
 │   └── src/
 │       ├── androidTest/
 │       ├── main/
 │       │   ├── AndroidManifest.xml
 │       │   ├── java/
 │       │   │   └── com/fic/cursoandroid2025g4/
 │       │   │       ├── controller/
 │       │   │       │   └── task/
 │       │   │       │       └── TaskController.java
 │       │   │       ├── model/
 │       │   │       │   └── task/
 │       │   │       │       ├── Task.java
 │       │   │       │       ├── TaskDao.java
 │       │   │       │       └── TaskDatabase.java
 │       │   │       └── view/
 │       │   │           └── task/
 │       │   │               ├── TaskActivity.java
 │       │   │               ├── TaskAdapter.java
 │       │   │               └── TaskViewHolder.java
 │       │   └── res/
 │       │       ├── layout/
 │       │       │   ├── activity_task.xml
 │       │       │   ├── task_dialog.xml
 │       │       │   └── task_item.xml
 │       │       ├── drawable/
 │       │       ├── values/
 │       │       │   ├── colors.xml
 │       │       │   ├── strings.xml
 │       │       │   └── themes.xml
 │       │       └── mipmap/
 │       └── test/
 └── build.gradle
 └── settings.gradle

## Estructura de la base de datos

Tabla: Task

id INTEGER (PK, AUTOINCREMENT)
task_title TEXT
task_description TEXT
created_at TEXT
is_completed BOOLEAN

## Interfaces de usuario

![1](https://github.com/user-attachments/assets/3d212e25-98fa-4fc2-b78a-5b7216ff6151)
![2](https://github.com/user-attachments/assets/61de8e61-e156-41f5-ade0-9ce7ea617671)
![3](https://github.com/user-attachments/assets/f4b5066e-c970-45ae-ab46-f24ed32fced6)
![4](https://github.com/user-attachments/assets/c8ae1c57-2c40-4deb-b202-89241729dec5)
![5](https://github.com/user-attachments/assets/e137adc4-36ac-42d5-b340-2963ef39d4f9)

