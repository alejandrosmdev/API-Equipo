const express = require("express");
const cors = require('cors');
const bodyParser = require('body-parser');
const { Pool } = require("pg");
const req = require("express/lib/request");
const res = require("express/lib/response");

const app = express();
const PORT = 3000;

app.use(cors());
app.use(bodyParser.json());

const myPool = new Pool({
    user: "postgres",
    host: xxxxxxxxxxxxxxxxxxxxxx",
    database: "postgres",
    password: "xxxxxxxxxxx",
    port: 5432,
    ssl: {
        rejectUnauthorized: false,
    },
});
app.listen(PORT, () => {
  console.log(`Servidor corriendo en http://localhost:${PORT}`);
});
myPool.connect((err, client, release) => {//comprobante de conexion correcta a BBDD, es puramente estetico
  if (err) {
    return console.error('Error al conectar con la base de datos', err);
  }
  console.log('Conexión exitosa a la base de datos');
  release();
});

//CONEXION A BBDD DE AWS
//MÉTODO PARA RECUPERAR TODAS LAS PELÍCULAS
app.get("/movies", async (req, res) => {
  try {
    const query = `SELECT * FROM movie;`;
    const { rows } = await myPool.query(query);
    res.json({
      lstPeliculas: rows,
      message: "Películas obtenidas con éxito"
    });
  } catch (error) {
    console.error("Error en la consulta");
    res.status(500).json({ error: "Error interno del servidor" });
  }
});
//MÉTODO PARA HACER EL LOGIN(@GET)
app.post('/login', async (req, res) => {
  const { email, password } = req.body;

  try {
      const user = await myPool.query('SELECT * FROM usuario WHERE email = $1', [email]);
     // console.log(user); // Log para ver el resultado
     if (user.rows.length === 1) {
      // Log para ver el usuario
      console.log(user.rows[0]);
      if (user.rows[0].password === password) {
          // Usuario autenticado correctamente
          res.status(200).json({ message: 'Inicio de sesión exitoso', user: user.rows[0] });
      } else {
          res.status(401).json({ error: 'Credenciales incorrectas logErr01' });
      }
  } else {
      res.status(401).json({ error: 'No se encontró al usuario... logErr02' });
  }
  

  } catch (error) {
      console.error('Error al iniciar sesión:', error);
      res.status(500).json({ error: 'Error interno del servidor' });
  }
});

/*
DEJAS LA URL TALCUAL -> http://localhost:3000/login
PARA COMPROBARLO EN THUNDERCLIENT NECESITAS METER EN HEADERS UN "Content-Type->application/json"
Y EN BODY METES EN JSON LOS PARAMETROS QUE NECESITES
*/

//Método para actualizar la película con
app.put('/update', async (req, res) => {
  const { id, titulo, descripcion, director, anyo } = req.body;

  try {
      // Validar que todos los campos necesarios están presentes
      if (!id || !titulo || !descripcion || !director || !anyo) {
          return res.status(400).json({ error: 'Todos los campos son obligatorios.' });
      }

      const result = await myPool.query(
          'UPDATE movie SET titulo = $1, descripcion = $2, director = $3, anyo = $4 WHERE id = $5',
          [titulo, descripcion, director, anyo, id]
      );

      if (result.rowCount > 0) {
          res.status(200).json({
              message: 'Película actualizada con éxito',
              movie: { id, titulo, descripcion, director, anyo }
          });
      } else {
          res.status(404).json({ error: 'Película no encontrada' });
      }
  } catch (error) {
      console.error('Error al actualizar la película:', error);
      res.status(500).json({ error: 'Error interno del servidor', details: error.message });
  }
});


app.put('/peliculas/:id', async (req, res) => {
  const { id } = req.params; // Obtener el ID de la película desde los parámetros de la URL
  const { titulo } = req.body; // Obtener el nuevo título del cuerpo de la solicitud

  try {
      const result = await myPool.query('UPDATE movie SET titulo = $1 WHERE id = $2', [titulo, id]);

      if (result.rowCount === 0) {
          // No se encontró la película con ese ID
          return res.status(404).json({ error: 'Película no encontrada' });
      }

      // Película actualizada correctamente
      res.status(200).json({ message: 'Título de la película actualizado exitosamente' });
  } catch (error) {
      console.error('Error al actualizar el título:', error);
      res.status(500).json({ error: 'Error interno del servidor', details: error.message });
  }
});