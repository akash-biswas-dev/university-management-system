DROP TYPE IF EXISTS degree_type;
DROP TYPE IF EXISTS permissions;

DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS role_permissions;
DROP TABLE IF EXISTS user_profile;
DROP TABLE IF EXISTS salary;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS programs;
DROP TABLE IF EXISTS salary;

CREATE TYPE permissions AS ENUM (
  'STUDENT_READ',
  'STUDENT_WRITE',
  'STUDENT_MANAGE'
  );


CREATE TABLE user_role
(
  name        VARCHAR(30) PRIMARY KEY,
  description VARCHAR(100)
);

CREATE TABLE role_permissions
(
  role_name       VARCHAR(30) NOT NULL,
  permission_name permissions NOT NULL,
  PRIMARY KEY (role_name, permission_name)
);


CREATE TABLE address
(
  id uuid PRIMARY KEY
);

CREATE TABLE users
(
  id         uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  email      VARCHAR(100) UNIQUE NOT NULL,
  username   VARCHAR(100) UNIQUE NOT NULL,
  password   VARCHAR(100)        NOT NULL,
  role       VARCHAR(30)         NOT NULL,
  is_locked  BOOLEAN             NOT NULL,
  is_enabled BOOLEAN             NOT NULL,
  joined_on  DATE                NOT NULL
);

CREATE TYPE degree_type AS ENUM (
  'BACHELOR',
  'MASTER',
  'DOCTORATE',
  'POST_DOC'
  );

CREATE TABLE salary
(
  name        VARCHAR(100) PRIMARY KEY,
  description VARCHAR(500),
  amount      DECIMAL(10, 2) NOT NULL -- Allow 2 digits after decimal point and 10 digits before decimal point.
);

CREATE TABLE user_profile
(
  user_id       uuid PRIMARY KEY,
  first_name    VARCHAR(50)  NOT NULL,
  middle_name   VARCHAR(50),
  last_name     VARCHAR(50)  NOT NULL,
  date_of_birth DATE         NOT NULL,
  address_id    uuid,
  degree_type   degree_type  NOT NULL,
  degree_on     VARCHAR(100) NOT NULL,
  salary_name   VARCHAR(100) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (address_id) REFERENCES address (id) ON DELETE SET NULL,
  FOREIGN KEY (salary_name) REFERENCES salary (name) ON DELETE SET NULL
);

CREATE TABLE programs
(
  name VARCHAR(200) PRIMARY KEY
);


CREATE TABLE students
(
  id uuid PRIMARY KEY
);



