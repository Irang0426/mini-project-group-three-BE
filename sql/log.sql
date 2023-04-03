CREATE TABLE login_log (
                           id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                           user_id BIGINT(20) UNSIGNED NOT NULL,
                           user_agent VARCHAR(255) NOT NULL,
                           client_ip VARCHAR(50) NOT NULL,
                           login_time DATETIME NOT NULL,
                           PRIMARY KEY (id)
);
