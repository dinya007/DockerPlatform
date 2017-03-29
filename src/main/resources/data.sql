INSERT INTO ENVIRONMENTS (environment_id, name, CREATED_DATE, MODIFIED_DATE) VALUES (0, 'dev', SYSDATE, SYSDATE);
COMMIT;


INSERT INTO HOSTS (host_id, url, name, host_path, registry_flag, CREATED_DATE, MODIFIED_DATE)
VALUES (0, '192.168.99.100', 'default', '/Users/denis/.docker/machine/machines/default', TRUE, SYSDATE, SYSDATE);
-- INSERT INTO HOSTS (host_id, url, name, host_path, registry_flag, CREATED_DATE, MODIFIED_DATE) VALUES
--   (1, '192.168.99.101', 'mh-keystore', '/Users/denis/.docker/machine/machines/mh-keystore', FALSE, SYSDATE, SYSDATE);
-- INSERT INTO HOSTS (host_id, url, name, host_path, registry_flag, CREATED_DATE, MODIFIED_DATE) VALUES
--   (2, '192.168.99.102', 'master-node', '/Users/denis/.docker/machine/machines/master-node', FALSE, SYSDATE, SYSDATE);
-- INSERT INTO HOSTS (host_id, url, name, host_path, registry_flag, CREATED_DATE, MODIFIED_DATE) VALUES
--   (3, '192.168.99.103', 'slave-node-1', '/Users/denis/.docker/machine/machines/slave-node-1', FALSE, SYSDATE, SYSDATE);
COMMIT;

INSERT INTO rel_environments_hosts (environment_id, host_id) VALUES (0, 0);
-- INSERT INTO rel_environments_hosts (environment_id, host_id) VALUES (0, 3);
COMMIT;

-- INSERT INTO NETWORKS (network_id, docker_network_id, name, created_date, modified_date)
-- VALUES (0, '65f76c30159a', 'dev-network', SYSDATE, SYSDATE);
-- COMMIT;

-- INSERT INTO rel_environments_networks (environment_id, network_id) VALUES (0, 0);
-- INSERT INTO ENVIRONMENTS (name, CREATED_DATE, MODIFIED_DATE) VALUES ('qa', SYSDATE, SYSDATE);
-- COMMIT;