CREATE TYPE "role" AS ENUM (
  'USER',
  'ADMIN'
);

CREATE TYPE "log_type" AS ENUM (
  'REGISTER',
  'LOGIN',
  'LOGOUT'
);

CREATE TABLE "custom_user" (
  "id" int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "email" text NOT NULL,
  "hashed" text NOT NULL,
  "salt" text NOT NULL,
  "role" role NOT NULL DEFAULT 'USER'
);

CREATE TABLE "user_data" (
  "user_id" int PRIMARY KEY REFERENCES "custom_user"("id")
  ON UPDATE CASCADE
  ON DELETE CASCADE,
  "username" text NOT NULL,
  "points" int NOT NULL DEFAULT 0
);

CREATE TABLE "telegram_account" (
  "user_id" int PRIMARY KEY REFERENCES "user_data"("user_id")
  ON UPDATE CASCADE
  ON DELETE CASCADE,
  "chat_id" int NOT NULL,
  "username" text NOT NULL,
  "is_confirmed" boolean NOT NULL DEFAULT false
);

CREATE TABLE "word_category" (
  "id" int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "category" text NOT NULL
);

CREATE TABLE "word" (
  "id" int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "word" text NOT NULL,
      "word_category_id" int NOT NULL REFERENCES "word_category"
  ON UPDATE CASCADE
  ON DELETE RESTRICT
);

CREATE TABLE "meaning" (
  "id" int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "word_id" int NOT NULL REFERENCES "word"("id")
  ON UPDATE CASCADE
  ON DELETE CASCADE,
  "level" int NOT NULL,
  "meaning" text NOT NULL
);

CREATE TABLE "wordlist" (
  "id" int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "name" text NOT NULL,
  "word_count" int NOT NULL,
  "popularity" int NOT NULL DEFAULT 0
);

CREATE TABLE "wordlist_word" (
  "wordlist_id" int NOT NULL REFERENCES "wordlist"("id")
  ON UPDATE CASCADE
  ON DELETE CASCADE,
  "word_id" int NOT NULL REFERENCES "word"("id")
  ON UPDATE CASCADE
  ON DELETE RESTRICT,
  PRIMARY KEY("wordlist_id", "word_id")
);

CREATE TABLE "userdata_wordlist" (
  "userdata_id" int NOT NULL REFERENCES "user_data"("user_id")
  ON UPDATE CASCADE
  ON DELETE CASCADE,
  "wordlist_id" int NOT NULL REFERENCES "wordlist"("id")
  ON UPDATE CASCADE
  ON DELETE RESTRICT,
  PRIMARY KEY("userdata_id", "wordlist_id")
);

CREATE TABLE "userdata_word" (
  "userdata_id" int NOT NULL REFERENCES "user_data"("user_id")
  ON UPDATE CASCADE
  ON DELETE CASCADE,
  "word_id" int NOT NULL REFERENCES "word"("id")
  ON UPDATE CASCADE
  ON DELETE RESTRICT,
  PRIMARY KEY("userdata_id", "word_id"),
  "is_learned" boolean NOT NULL DEFAULT false,
  "guess_streak" int DEFAULT 0
);

CREATE TABLE "log" (
  "id" int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "created_at" timestamp NOT NULL,
  "type" log_type NOT NULL,
  "user_id" int NOT NULL REFERENCES "custom_user"("id")
  ON UPDATE CASCADE
  ON DELETE CASCADE
);
