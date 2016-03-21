INSERT INTO ENVIRONMENTS (name, CREATED_DATE, MODIFIED_DATE) VALUES ('dev', SYSDATE, SYSDATE);
INSERT INTO ENVIRONMENTS (name, CREATED_DATE, MODIFIED_DATE) VALUES ('qa', SYSDATE, SYSDATE);
COMMIT;


INSERT INTO HOSTS (url, name, CREATED_DATE, MODIFIED_DATE) VALUES ('tcp://192.168.99.100:2376', 'default', SYSDATE, SYSDATE);
INSERT INTO HOSTS (url, name, CREATED_DATE, MODIFIED_DATE) VALUES ('tcp://192.168.99.100:2376', 'test-machine', SYSDATE, SYSDATE);
COMMIT;

INSERT INTO rel_environments_hosts (environment_id, host_id) VALUES (0, 0);
INSERT INTO rel_environments_hosts (environment_id, host_id) VALUES (0, 1);
INSERT INTO rel_environments_hosts (environment_id, host_id) VALUES (1, 1);
COMMIT;