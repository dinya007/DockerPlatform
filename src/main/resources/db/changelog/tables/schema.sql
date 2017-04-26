DROP TABLE IF EXISTS rel_environments_hosts;
DROP TABLE IF EXISTS rel_environments_networks;
DROP TABLE IF EXISTS environments;
DROP TABLE IF EXISTS applications;
DROP TABLE IF EXISTS networks;
DROP TABLE IF EXISTS hosts;
DROP TABLE IF EXISTS image_properties;
DROP TABLE IF EXISTS images;
DROP TABLE IF EXISTS jvm_args;


CREATE TABLE networks
(
  id             INTEGER IDENTITY PRIMARY KEY NOT NULL,
  name           VARCHAR(100),
  created_date   TIMESTAMP,
  modified_date  TIMESTAMP,
);

COMMIT;

CREATE TABLE environments
(
  environment_id INTEGER IDENTITY PRIMARY KEY NOT NULL,
  name           VARCHAR(100),
  created_date   TIMESTAMP,
  modified_date  TIMESTAMP,
);

COMMIT;

CREATE TABLE hosts
(
  host_id        INTEGER IDENTITY PRIMARY KEY NOT NULL,
  url            VARCHAR(50),
  name           VARCHAR(100),
  host_path      VARCHAR(500),
  registry_flag  BOOLEAN,
  created_date   TIMESTAMP,
  modified_date  TIMESTAMP,
);

COMMIT;

CREATE TABLE rel_environments_hosts
(
  environment_id INTEGER,
  host_id        INTEGER,
  FOREIGN KEY (environment_id) REFERENCES environments (environment_id),
  FOREIGN KEY (host_id) REFERENCES hosts (host_id)
);

COMMIT;

CREATE TABLE rel_environments_networks
(
  environment_id INTEGER,
  network_id     INTEGER,
  FOREIGN KEY (environment_id) REFERENCES environments (environment_id),
  FOREIGN KEY (network_id) REFERENCES networks (id)
);

COMMIT;

CREATE TABLE images
(
  image_id        INTEGER IDENTITY PRIMARY KEY NOT NULL,
  docker_image_id VARCHAR(100),
  name            VARCHAR(100)
);

COMMIT;

CREATE TABLE applications
(
  id          INTEGER IDENTITY PRIMARY KEY NOT NULL,
  container_id            VARCHAR(500),
  host_id                 INTEGER,
  image_id                INTEGER,
  state                    INTEGER,
  FOREIGN KEY (host_id)   REFERENCES hosts (host_id),
  FOREIGN KEY (image_id)  REFERENCES images (image_id)
);

COMMIT;