CREATE TABLE user
(
  id INTEGER PRIMARY KEY,
  login TEXT NOT NULL,
  title TEXT NOT NULL,
  email TEXT NOT NULL
);
CREATE UNIQUE INDEX user_login_unique ON user (login);
CREATE UNIQUE INDEX user_email_unique ON user (email);