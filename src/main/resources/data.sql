INSERT INTO ENVIRONMENTS (name, CREATED_DATE, MODIFIED_DATE) VALUES ('dev', SYSDATE, SYSDATE);
-- INSERT INTO ENVIRONMENTS (name, CREATED_DATE, MODIFIED_DATE) VALUES ('qa', SYSDATE, SYSDATE);
COMMIT;


INSERT INTO HOSTS (url, name, host_path, registry_flag, CREATED_DATE, MODIFIED_DATE) VALUES ('192.168.99.100', 'default', '/Users/denis/.docker/machine/machines/default', TRUE , SYSDATE, SYSDATE);
-- INSERT INTO HOSTS (url, name, host_path, registry_flag, CREATED_DATE, MODIFIED_DATE) VALUES ('192.168.99.101', 'test-machine', '/Users/denis/.docker/machine/machines/default', FALSE , SYSDATE, SYSDATE);
COMMIT;

INSERT INTO rel_environments_hosts (environment_id, host_id) VALUES (0, 0);
-- INSERT INTO rel_environments_hosts (environment_id, host_id) VALUES (0, 1);
-- INSERT INTO rel_environments_hosts (environment_id, host_id) VALUES (1, 1);
COMMIT;