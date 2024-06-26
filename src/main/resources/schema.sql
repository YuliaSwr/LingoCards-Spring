-- Accounts Table
CREATE TABLE IF NOT EXISTS accounts
(
    id           SERIAL PRIMARY KEY,
    first_name   VARCHAR(50)  NOT NULL,
    last_name    VARCHAR(50)  NOT NULL,
    email        VARCHAR(100) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    avatar_image VARCHAR(255)
);

-- Set Table
CREATE TABLE IF NOT EXISTS sets
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(100) NOT NULL,
    user_id INT          NOT NULL,
    FOREIGN KEY (user_id) REFERENCES accounts (id)
);

-- Flashcard Table
CREATE TABLE IF NOT EXISTS flashcard
(
    id     SERIAL PRIMARY KEY,
    front  TEXT NOT NULL,
    back   TEXT NOT NULL,
    set_id INT  NOT NULL,
    FOREIGN KEY (set_id) REFERENCES sets (id)
);
