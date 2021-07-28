
  CREATE TABLE users (
     id          INTEGER IDENTITY PRIMARY KEY,
     name        VARCHAR(15),
     login_id    VARCHAR(10),
     pw          VARCHAR(15),
     birth_date  DATE,
     gender      VARCHAR(5),
     phone_num   VARCHAR(15),
     create_date TIMESTAMP
  );
  CREATE INDEX idx_users_login_id  ON users(login_id);

  CREATE TABLE cocktails (
       id           INTEGER IDENTITY PRIMARY KEY,
       name         VARCHAR(15),
       description  VARCHAR(100),
       abv          INTEGER,
       how          VARCHAR(100),
       photo        BLOB, -- or image url
       created_user_id INTEGER,
       create_date  TIMESTAMP
    );
  ALTER TABLE cocktails ADD CONSTRAINT fk_cocktails_users FOREIGN KEY (created_user_id) REFERENCES users (id);

  CREATE TABLE alcohols (
       id           INTEGER IDENTITY PRIMARY KEY,
       name         VARCHAR(15),
       type  VARCHAR(100),
       abv          INTEGER,
       description        VARCHAR(100)
  );

  CREATE TABLE etc_materials (
       id           INTEGER IDENTITY PRIMARY KEY,
       name         VARCHAR(15),
       type  VARCHAR(100),
       description        VARCHAR(100)
  );

  CREATE TABLE map_cocktail_alcohol (
       cocktail_id           INTEGER,
       alcohol_id           INTEGER,
       quantity             DOUBLE
  );
ALTER TABLE map_cocktail_alcohol ADD CONSTRAINT fk_map_cocktail_alcohol_cocktail_id FOREIGN KEY (cocktail_id) REFERENCES cocktails (id);
ALTER TABLE map_cocktail_alcohol ADD CONSTRAINT fk_map_cocktail_alcohol_alcohol_id FOREIGN KEY (alcohol_id) REFERENCES alcohols (id);
CREATE INDEX idx_map_cocktail_alcohol_cocktail_id  ON map_cocktail_alcohol(cocktail_id);

-- 위의 테이블과 합쳐도 될 듯?
  CREATE TABLE map_cocktail_etc_materials (
       cocktail_id           INTEGER,
       etc_id           INTEGER,
       quantity             DOUBLE
  );
ALTER TABLE map_cocktail_etc_materials ADD CONSTRAINT fk_map_cocktail_etc_materials_cocktail_id FOREIGN KEY (cocktail_id) REFERENCES cocktails (id);
ALTER TABLE map_cocktail_etc_materials ADD CONSTRAINT fk_map_cocktail_etc_materials_etc_id FOREIGN KEY (etc_id) REFERENCES etc_materials (id);
CREATE INDEX idx_map_cocktail_etc_cocktail_id  ON map_cocktail_etc_materials(cocktail_id);

  CREATE TABLE user_keep_cocktail (
       user_id           INTEGER,
       cocktail_id           INTEGER,
       create_date  TIMESTAMP
  );
ALTER TABLE user_keep_cocktail ADD CONSTRAINT fk_user_keep_cocktail_cocktail_id FOREIGN KEY (cocktail_id) REFERENCES cocktails (id);
ALTER TABLE user_keep_cocktail ADD CONSTRAINT fk_user_keep_cocktail_user_id FOREIGN KEY (user_id) REFERENCES users (id);
CREATE INDEX idx_user_keep_cocktail_user_id  ON user_keep_cocktail(user_id);
