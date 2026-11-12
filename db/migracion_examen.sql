-- =====================================================================
-- Migración del examen
-- Estudiante: Débora Cisneros      Tema: B
-- =====================================================================
USE rrhh_examen;

-- 1. ALTER TABLE departamento: agregue las dos columnas de su tema
--    (tipo, NOT NULL y DEFAULT).

-- 1. Agregar columnas requeridas con tipo, NOT NULL, DEFAULT y CHECK
ALTER TABLE departamento
    ADD COLUMN salario_base DECIMAL(10,2) NOT NULL DEFAULT 600.00 CHECK (salario_base > 0),
    ADD COLUMN dotacion_maxima INT NOT NULL DEFAULT 1 CHECK (dotacion_maxima >= 1);





-- 2. UPDATE: asigne valores a los tres departamentos de ejemplo
--    según los casos de prueba de su tema.
UPDATE departamento 
SET salario_base = 750.00, dotacion_maxima = 15 
WHERE id = 1; -- Ventas (para casos de prueba 4 y 5)

UPDATE departamento 
SET salario_base = 800.00, dotacion_maxima = 10 
WHERE id = 2; -- Auditoría Interna

UPDATE departamento 
SET salario_base = 900.00, dotacion_maxima = 10 
WHERE id = 3; -- Soporte Técnico (para caso de prueba 6)

UPDATE departamento 
SET salario_base = 950.00, dotacion_maxima = 5 
WHERE id = 4; -- Recursos Humanos (para caso de prueba 1)





