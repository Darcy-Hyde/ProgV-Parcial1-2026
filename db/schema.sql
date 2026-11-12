-- =====================================================================
-- Examen Programación V - Sistema de Gestión de Personal (RRHH)
-- Base de datos MySQL 8
-- =====================================================================
DROP DATABASE IF EXISTS rrhh_examen;
CREATE DATABASE rrhh_examen CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE rrhh_examen;

-- (Modificacion segun parte 1.3)
CREATE TABLE departamento (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    codigo          VARCHAR(10)    NOT NULL UNIQUE,
    nombre          VARCHAR(100)   NOT NULL,
    salario_base    DECIMAL(10,2) NOT NULL CHECK (salario_base > 0),
    dotacion_maxima INT            NOT NULL CHECK (dotacion_maxima >= 1)
);


CREATE TABLE dotacion (
    id               INT AUTO_INCREMENT PRIMARY KEY,
    departamento_id  INT NOT NULL UNIQUE,
    cantidad         INT NOT NULL DEFAULT 0,
    CONSTRAINT fk_dotacion_departamento FOREIGN KEY (departamento_id)
        REFERENCES departamento (id) ON DELETE CASCADE,
    CONSTRAINT chk_dotacion_cantidad CHECK (cantidad >= 0)
);

-- (Modificacion segun parte 1.3)
INSERT INTO departamento (codigo, nombre, salario_base, dotacion_maxima) VALUES
    ('DEP-001', 'Ventas', 750.00, 15),
    ('DEP-002', 'Auditoría Interna', 800.00, 10),
    ('DEP-003', 'Soporte Técnico', 900.00, 10),
    ('DEP-004', 'Recursos Humanos', 950.00, 5);

INSERT INTO dotacion (departamento_id, cantidad) VALUES
    (1, 12),
    (2, 0),
    (3, 6),
    (4, 5);

