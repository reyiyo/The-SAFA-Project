#THE SAFA PROJECT

##Historia
En un principio, el S.A.F.A. fue un sistema pensado en para compartir inforación relacionada con la vida académica de los alumnos, abarcando desde apuntes y exámenes, hasta preguntas y respuestas e información administrativa, orientado a ser una de red social de contenido académico para la comunidad universitaria.
El objetivo principal era lograr centralizar toda la información dispersa entre diferentes foros, páginas de apuntes y otros sistemas (muchos de ellos abandonados), en un solo lugar y hacer que esta información pueda ser calificada, modificada y mejorada por todos y para todos los alumnos y docentes de la Universidad. La principal diferencia con los foros existentes, es que en estos foros la información se organiza por "discusión" y se dificulta encontrar lo que uno realmente busca. Como el S.A.F.A. está orientado a compartir información más que a temas de discusión, la información se indexa y presenta de una forma que facilita al usuario encontrar lo que busca o toda la información existente sobre un tema en particular.

##¿A qué hemos llegado?
Mientras desarrollábamos lo que cuenta la historia nos fuimos dando cuenta que en realidad, con una vuelta más de tuerca, el SAFA podía servir para compartir y administrar cualquier tipo de contenido entre comunidades integradas a través de las redes sociales y no sólo apuntes e información académica.
Por lo que nos esforzamos un poco más para poder hacer un modelo de contenido y etiquetado mucho más genérico y configurable de modo que sirva para indexar y facilitar la búsqueda de cualquier tipo de contenido y/o tema.

##Objetivos
El objetivo final del sistema es que cualquier usuario sin conocimientos avanzados de computación ni servidores pueda tener su propia aplicación de gestión de contenidos sin importar el tipo del contenido ni el tema de los mismos. Que con unas simples respuestas a unas preguntas, el sistema se pueda adaptar a los requerimientos del usuario.

##Características principales

* La importancia de que sea Software Libre: De esta forma se evita que el sistema quede en el abandono, ya que el desarrollo y mantenimiento del mismo será llevado a cabo por todo aquel que quiera participar y, además, el código del mismo siempre estará disponible para todo aquel que quiera comenzar un nuevo proyecto basado en S.A.F.A. sin necesidad de reinventar la rueda, introducirle modificaciones o expandir sus características.

* Actualmente el sistema está basado en una arquitectura cliente-servidor escalable para poder funcionar en cluster, pero el objetivo es que en un futuro pueda ser completamente distribuido. El cliente es un cliente web realizado con las últimas tecnologías web como HTML5 cuya principal característica es la de poder disponer de la información del sistema en modo offline, evitando así la necesidad de una conexión a internet en todo momento y además es una aplicación completamente estática e independiente del servidor, por lo que cualquiera puede desarrollar su propio cliente y utilizarlo con cualquier servidor de SAFA, ya que para todo expone una simple API.

* La API busca ser lo más simple posible, exponiendo servicios REST y permitiendo realizar búsquedas complejas de contenido a través de un DSL de alto nivel.

* Sistema de Etiquetado. Estamos llevando a cabo el desarrollo de un potente sistema de tagging basado en una base de datos de grafos, genérico para casi cualquier sistema que quiera usar tags.

* Red Social: Podrás conectar tus perfiles de las redes sociales más populares con el S.A.F.A. y relacionarte, discutir sobre diversos temas, compartir contenido y opiniones con amigos, compañeros de la facultad, profesores o quien quieras sobre lo que quieras.

* Como frutilla del postre, esperamos hacer que el S.A.F.A. sea completamente extensible y cualquier persona pueda desarrollar y compartir plugins para el mismo, ampliando así sus funcionalidades a la necesidad de cualquier persona.

##Cómo colaborar

Es un proyecto completamente libre en el que todo el mundo es bienvenido a participar. Necesitamos todo tipo de ayuda (no sólo código). Hay muchísimas cosas para hacer y seguro que habrá algo con lo que puedas colaborar para mejorar el mundo :)

Para colaborar, mantenerte informado o simplemente sumarte a la comunidad, unite al [grupo de google](http://groups.google.com/group/safa-content "grupo de google")