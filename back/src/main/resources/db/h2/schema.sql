
  CREATE TABLE users (
     id          INTEGER IDENTITY PRIMARY KEY,
     name        VARCHAR(15),
     login_id    VARCHAR(10),
     pw          VARCHAR(15),
     birth_date  DATE,
     gender      VARCHAR(5),
     phone_num   VARCHAR(15),
     create_date TIMESTAMP,
     update_date TIMESTAMP
  );
  CREATE INDEX idx_users_login_id  ON users(login_id);

  CREATE TABLE ingredients (
       id           INTEGER IDENTITY PRIMARY KEY,
       name         VARCHAR(100),
       abv          INTEGER,
       description  VARCHAR(200)
  );

  CREATE TABLE cocktails (
       id           INTEGER IDENTITY PRIMARY KEY,
       name         VARCHAR(15),
       description  VARCHAR(200),
       glass        VARCHAR(20),
       technique    VARCHAR(30),
       image_path   VARCHAR(30),
       created_user_id INTEGER,
       create_date  TIMESTAMP,
       update_date TIMESTAMP
    );
  ALTER TABLE cocktails ADD CONSTRAINT fk_cocktails_users FOREIGN KEY (created_user_id) REFERENCES users (id);

  CREATE TABLE recipes (
       cocktail_id           INTEGER,
       ingredient_id INTEGER,
       quantity      DOUBLE
    );
  ALTER TABLE recipes ADD CONSTRAINT fk_recipes_cocktails FOREIGN KEY (cocktail_id) REFERENCES cocktails (id);
  ALTER TABLE recipes ADD CONSTRAINT fk_recipes_ingredients FOREIGN KEY (ingredient_id) REFERENCES ingredients (id);
  CREATE INDEX idx_recipes_ingredient_id  ON recipes(ingredient_id);
  CREATE INDEX idx_recipes_cocktail_id  ON recipes(cocktail_id);

  CREATE TABLE user_keep_cocktail (
       user_id           INTEGER,
       cocktail_id           INTEGER,
       create_date  TIMESTAMP
  );
ALTER TABLE user_keep_cocktail ADD CONSTRAINT fk_user_keep_cocktail_cocktail_id FOREIGN KEY (cocktail_id) REFERENCES cocktails (id);
ALTER TABLE user_keep_cocktail ADD CONSTRAINT fk_user_keep_cocktail_user_id FOREIGN KEY (user_id) REFERENCES users (id);
CREATE INDEX idx_user_keep_cocktail_user_id  ON user_keep_cocktail(user_id);

  CREATE TABLE uuid (
       uuid    VARCHAR(36) PRIMARY KEY
  );

