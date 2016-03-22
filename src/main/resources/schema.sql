DROP TABLE IF EXISTS rel_environments_hosts;
DROP TABLE IF EXISTS environments;
DROP TABLE IF EXISTS hosts;

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

CREATE TABLE rel_environments_hosts
(
  environment_id INTEGER,
  host_id        INTEGER,
  FOREIGN KEY (environment_id) REFERENCES environments (environment_id),
  FOREIGN KEY (host_id) REFERENCES hosts (host_id)
);

COMMIT;

