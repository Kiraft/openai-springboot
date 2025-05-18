-- Tabla de usuarios
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password_hash TEXT NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de categorías
CREATE TABLE categories (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(50) UNIQUE NOT NULL
);

-- Tabla de libros
CREATE TABLE books (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(200) NOT NULL,
                       description TEXT,
                       author VARCHAR(100),
                       cover_image_url TEXT,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de relación muchos-a-muchos entre libros y categorías
CREATE TABLE book_categories (
                                 book_id INTEGER REFERENCES books(id) ON DELETE CASCADE,
                                 category_id INTEGER REFERENCES categories(id) ON DELETE CASCADE,
                                 PRIMARY KEY (book_id, category_id)
);

-- Tabla de capítulos (opcional)
CREATE TABLE chapters (
                          id SERIAL PRIMARY KEY,
                          book_id INTEGER REFERENCES books(id) ON DELETE CASCADE,
                          title VARCHAR(200),
                          content TEXT, -- puedes cambiar esto a URL si usas PDFs o imágenes
                          chapter_number INTEGER,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de relación usuario-libro (opcional para favoritos, seguimiento, etc.)
CREATE TABLE user_books (
                            user_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
                            book_id INTEGER REFERENCES books(id) ON DELETE CASCADE,
                            is_favorite BOOLEAN DEFAULT FALSE,
                            last_read_chapter INTEGER,
                            last_read_at TIMESTAMP,
                            PRIMARY KEY (user_id, book_id)
);
