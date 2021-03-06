DROP TABLE IF EXISTS tb_atividades CASCADE;
CREATE TABLE tb_atividades
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(250) NOT NULL,
    date_created  TIMESTAMP             DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_modified TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO tb_atividades (id, name)
VALUES (1, 'TP1'),
       (2, 'TP3'),
       (3, 'AT');

DROP TABLE IF EXISTS tb_user_association CASCADE;
CREATE TABLE tb_user_association
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    activity_id   INT       NOT NULL,
    user_id       INT       NOT NULL,
    grade       INT       NOT NULL,
    date_created  TIMESTAMP          DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_modified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO tb_user_association (id, activity_id, user_id, grade)
VALUES (4, 2, 2, 10),
       (5, 3, 1, 9),
       (6, 3, 3, 5);