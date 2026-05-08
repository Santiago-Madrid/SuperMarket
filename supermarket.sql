CREATE SCHEMA db_supermarket;
USE db_supermarket;

CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255)
);

ALTER TABLE categories ADD COLUMN state BOOLEAN DEFAULT TRUE;

CREATE TABLE suppliers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nit VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(150) NOT NULL,
    phone VARCHAR(20),
    address VARCHAR(255),
    email VARCHAR(100)
);

CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_id BIGINT NOT NULL,
    name VARCHAR(150) NOT NULL,
    purchase_price DECIMAL(10, 2) NOT NULL,
    sale_price DECIMAL(10, 2) NOT NULL,
    stock BIGINT NOT NULL DEFAULT 0,
    barcode VARCHAR(50) NOT NULL UNIQUE,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE RESTRICT
);

ALTER TABLE products CHANGE active state BOOLEAN;

CREATE TABLE product_supplier (
    product_id BIGINT NOT NULL,
    supplier_id BIGINT NOT NULL,
    last_supply_date DATE,
    PRIMARY KEY (product_id, supplier_id),
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id) ON DELETE CASCADE
);

CREATE TABLE employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    identification_number VARCHAR(20) NOT NULL UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    position ENUM('ADMINISTRATOR', 'CASHIER', 'ASSISTANT') NOT NULL,
    hire_date DATE NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE sales (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sale_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    employee_id BIGINT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    tax DECIMAL(10, 2) NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE RESTRICT
);

CREATE TABLE sale_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sale_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity BIGINT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (sale_id) REFERENCES sales(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE RESTRICT
);

INSERT INTO categories (id, name, description) VALUES
(1, 'Lácteos', 'Leche, queso, yogur y productos lácteos'),
(2, 'Bebidas', 'Gaseosas, jugos, agua y bebidas alcohólicas'),
(3, 'Abarrotes', 'Enlatados, arroz, frijoles y pastas'),
(4, 'Frutas y Verduras', 'Productos frescos del campo'),
(5, 'Carnes', 'Res, pollo, cerdo y embutidos'),
(6, 'Limpieza', 'Productos de limpieza para el hogar'),
(7, 'Panadería', 'Pan, pasteles y productos de repostería'),
(8, 'Electrónicos', 'Pequeños electrodomésticos y accesorios');

-- 2. Datos de Proveedores
INSERT INTO suppliers (id, nit, name, phone, address, email) VALUES
(1, '900123456-1', 'Lácteos del Valle S.A.', '+57 6012345678', 'Calle 45 # 20-30, Bogotá', 'ventas@lacteosvalle.com'),
(2, '900234567-2', 'Bebidas Colombianas SAS', '+57 6023456789', 'Av. Principal # 15-45, Medellín', 'contacto@bebidascolombianas.com'),
(3, '900345678-3', 'Abarrotes Nacionales Ltda.', '+57 6034567890', 'Carrera 25 # 8-12, Cali', 'pedidos@abarrotesnacionales.com'),
(4, '900456789-4', 'Frescos del Campo S.A.', '+57 6045678901', 'Km 5 Vía Puerto, Barranquilla', 'ventas@frescosdelcampo.com'),
(5, '900567890-5', 'Carnes Premium SAS', '+57 6056789012', 'Zona Industrial # 78, Bucaramanga', 'carnes@premium.com'),
(6, '900678901-6', 'Limpieza Total Ltda.', '+57 6067890123', 'Calle 80 # 45-67, Cartagena', 'servicio@limpiezatotal.com'),
(7, '900789012-7', 'Panificadora Central S.A.', '+57 6078901234', 'Diagonal 10 # 25-40, Pereira', 'ventas@panificadora.com'),
(8, '900890123-8', 'Electrodomésticos Modernos SAS', '+57 6089012345', 'Autopista Norte # 123, Bogotá', 'comercial@electromodernos.com');

INSERT INTO products (id, category_id, name, purchase_price, sale_price, stock, barcode, active) VALUES
-- Lácteos
(1, 1, 'Leche Entera 1L', 15.00, 22.50, 150, '7501000100101', TRUE),
(2, 1, 'Yogurt Natural 1L', 18.00, 28.00, 80, '7501000100202', TRUE),
(3, 1, 'Queso Oaxaca 400g', 45.00, 68.00, 50, '7502000300303', TRUE),
(4, 1, 'Queso Manchego 500g', 85.00, 125.00, 25, '7521002200404', TRUE),
(5, 1, 'Mantequilla 250g', 12.00, 19.50, 60, '7526002700909', TRUE),

(6, 2, 'Coca-Cola 600ml', 8.00, 12.50, 200, '7503000400404', TRUE),
(7, 2, 'Jugo de Manzana 1L', 12.00, 18.00, 120, '7504000500505', TRUE),
(8, 2, 'Agua Mineral 1L', 5.00, 8.00, 300, '7505000600606', TRUE),
(9, 2, 'Cerveza Corona 355ml', 12.00, 20.00, 200, '7522002300505', TRUE),
(10, 2, 'Jugo de Naranja 1L', 14.00, 21.00, 90, '7527002800101', TRUE),

(11, 3, 'Arroz Blanco 1kg', 12.00, 18.50, 100, '7506000700707', TRUE),
(12, 3, 'Frijoles Negros 1kg', 22.00, 35.00, 75, '7507000800808', TRUE),
(13, 3, 'Atún 140g', 12.00, 19.00, 90, '7508000900909', TRUE),
(14, 3, 'Aceite Vegetal 1L', 22.00, 35.00, 60, '7523002400606', TRUE),
(15, 3, 'Espagueti 500g', 8.00, 13.50, 120, '7528002900202', TRUE),

(16, 4, 'Manzana Roja 1kg', 25.00, 42.00, 60, '7509001000101', TRUE),
(17, 4, 'Plátano 1kg', 10.00, 18.00, 45, '7510001100202', TRUE),
(18, 4, 'Lechuga', 8.00, 15.00, 30, '7511001200303', TRUE),
(19, 4, 'Tomate 1kg', 15.00, 25.00, 40, '7524002500707', TRUE),
(20, 4, 'Banano 1kg', 12.00, 20.00, 55, '7529003000303', TRUE),

(21, 5, 'Pechuga de Pollo 1kg', 65.00, 95.00, 40, '7512001300404', TRUE),
(22, 5, 'Carne Molida de Res 1kg', 85.00, 125.00, 35, '7513001400505', TRUE),
(23, 5, 'Jamón 500g', 55.00, 85.00, 55, '7514001500606', TRUE),
(24, 5, 'Salchicha 500g', 28.00, 45.00, 65, '7525002600808', TRUE),
(25, 5, 'Costillas de Cerdo 1kg', 70.00, 105.00, 30, '7530003100404', TRUE),

(26, 6, 'Cloro 1L', 12.00, 20.00, 85, '7515001600707', TRUE),
(27, 6, 'Jabón de Lavandería 400g', 8.00, 15.00, 100, '7516001700808', TRUE),
(28, 6, 'Jabón Líquido para Platos 500ml', 6.00, 11.50, 120, '7531003200505', TRUE),
(29, 6, 'Limpiador de Pisos 1L', 10.00, 18.00, 75, '7532003300606', TRUE),

(30, 7, 'Pan Blanco 680g', 25.00, 38.00, 70, '7517001800909', TRUE),
(31, 7, 'Galletas Marías 240g', 12.00, 22.00, 110, '7518001900101', TRUE),
(32, 7, 'Pan Integral 680g', 28.00, 42.00, 55, '7533003400707', TRUE),
(33, 7, 'Muffins 4 unidades', 15.00, 24.00, 45, '7534003500808', TRUE),

(34, 8, 'Licuadora 500W', 450.00, 699.00, 15, '7519002000202', TRUE),
(35, 8, 'Cafetera 12 tazas', 350.00, 549.00, 12, '7520002100303', TRUE),
(36, 8, 'Tostadora 2 rebanadas', 120.00, 189.00, 20, '7535003600909', TRUE);

INSERT INTO product_supplier (product_id, supplier_id, last_supply_date) VALUES
-- Lácteos del Valle abastece lácteos
(1, 1, '2024-03-01'), (2, 1, '2024-03-01'), (3, 1, '2024-03-05'), (4, 1, '2024-03-10'), (5, 1, '2024-03-12'),
-- Bebidas Colombianas abastece bebidas
(6, 2, '2024-03-10'), (7, 2, '2024-03-10'), (8, 2, '2024-03-12'), (9, 2, '2024-03-15'), (10, 2, '2024-03-15'),
-- Abarrotes Nacionales abastece abarrotes
(11, 3, '2024-03-08'), (12, 3, '2024-03-08'), (13, 3, '2024-03-15'), (14, 3, '2024-03-10'), (15, 3, '2024-03-12'),
-- Frescos del Campo abastece frutas y verduras
(16, 4, '2024-03-03'), (17, 4, '2024-03-03'), (18, 4, '2024-03-05'), (19, 4, '2024-03-07'), (20, 4, '2024-03-07'),
-- Carnes Premium abastece carnes
(21, 5, '2024-03-07'), (22, 5, '2024-03-07'), (23, 5, '2024-03-10'), (24, 5, '2024-03-12'), (25, 5, '2024-03-12'),
-- Limpieza Total abastece productos de limpieza
(26, 6, '2024-03-05'), (27, 6, '2024-03-05'), (28, 6, '2024-03-08'), (29, 6, '2024-03-08'),
-- Panificadora Central abastece panadería
(30, 7, '2024-03-02'), (31, 7, '2024-03-02'), (32, 7, '2024-03-05'), (33, 7, '2024-03-05'),
-- Electrodomésticos Modernos abastece electrónicos
(34, 8, '2024-03-01'), (35, 8, '2024-03-01'), (36, 8, '2024-03-08'),
-- Relaciones adicionales (productos con múltiples proveedores)
(6, 3, '2024-03-10'), (11, 2, '2024-03-12');

-- 5. Datos de Empleados
INSERT INTO employees (id, identification_number, full_name, position, hire_date, salary, active) VALUES
(1, '123456789', 'Juan Pérez Rodríguez', 'ADMINISTRATOR', '2023-01-15', 2500000.00, TRUE),
(2, '987654321', 'María García López', 'ADMINISTRATOR', '2023-02-20', 2400000.00, TRUE),
(3, '456789123', 'Carlos Martínez Sánchez', 'CASHIER', '2023-03-10', 1500000.00, TRUE),
(4, '789123456', 'Ana Rodríguez Gómez', 'CASHIER', '2023-04-05', 1500000.00, TRUE),
(5, '321654987', 'Luis Fernández Castro', 'CASHIER', '2023-05-12', 1500000.00, TRUE),
(6, '654987321', 'Laura Díaz Mendoza', 'ASSISTANT', '2023-06-18', 1300000.00, TRUE),
(7, '147258369', 'Pedro Romero Silva', 'ASSISTANT', '2023-07-22', 1300000.00, TRUE),
(8, '258369147', 'Sofia Vargas Ruiz', 'ASSISTANT', '2023-08-30', 1300000.00, TRUE),
(9, '369147258', 'Diego Castillo Mora', 'CASHIER', '2024-01-10', 1600000.00, TRUE),
(10, '951753852', 'Valentina Herrera Paz', 'ASSISTANT', '2024-02-15', 1350000.00, TRUE);

-- 6. Datos de Ventas (Cabecera)
INSERT INTO sales (id, sale_datetime, employee_id, subtotal, tax, total) VALUES
(1, '2024-03-15 10:30:00', 3, 145.50, 27.65, 173.15),
(2, '2024-03-15 11:45:00', 3, 89.00, 16.91, 105.91),
(3, '2024-03-15 14:20:00', 4, 235.00, 44.65, 279.65),
(4, '2024-03-16 09:15:00', 5, 67.50, 12.83, 80.33),
(5, '2024-03-16 16:30:00', 4, 178.00, 33.82, 211.82),
(6, '2024-03-17 12:00:00', 9, 325.50, 61.85, 387.35),
(7, '2024-03-17 18:45:00', 3, 94.00, 17.86, 111.86),
(8, '2024-03-18 10:00:00', 5, 456.00, 86.64, 542.64),
(9, '2024-03-18 15:30:00', 9, 124.50, 23.66, 148.16),
(10, '2024-03-19 11:20:00', 4, 289.00, 54.91, 343.91),
(11, '2024-03-20 09:00:00', 1, 56.00, 10.64, 66.64),
(12, '2024-03-20 14:30:00', 2, 234.50, 44.56, 279.06),
(13, '2024-03-21 11:15:00', 6, 89.50, 17.01, 106.51),
(14, '2024-03-21 16:45:00', 7, 167.00, 31.73, 198.73),
(15, '2024-03-22 10:20:00', 8, 78.50, 14.92, 93.42);

INSERT INTO sale_details (id, sale_id, product_id, quantity, unit_price, subtotal) VALUES
-- Venta 1
(1, 1, 1, 2, 22.50, 45.00),
(2, 1, 2, 1, 28.00, 28.00),
(3, 1, 3, 1, 68.00, 68.00),
(4, 1, 8, 1, 4.50, 4.50),
-- Venta 2
(5, 2, 6, 3, 12.50, 37.50),
(6, 2, 7, 2, 18.00, 36.00),
(7, 2, 27, 1, 15.50, 15.50),
-- Venta 3
(8, 3, 21, 1, 95.00, 95.00),
(9, 3, 12, 2, 35.00, 70.00),
(10, 3, 11, 1, 18.50, 18.50),
(11, 3, 16, 1, 42.00, 42.00),
(12, 3, 13, 1, 9.50, 9.50),
-- Venta 4
(13, 4, 18, 1, 15.00, 15.00),
(14, 4, 19, 2, 25.00, 50.00),
(15, 4, 8, 1, 2.50, 2.50),
-- Venta 5
(16, 5, 22, 2, 125.00, 250.00),
(17, 5, 31, 3, 22.00, 66.00),
(18, 5, 8, 1, 12.00, 12.00),
(19, 5, 23, 1, 85.00, 85.00),
-- Venta 6
(20, 6, 34, 1, 699.00, 699.00),
(21, 6, 4, 1, 125.00, 125.00),
(22, 6, 31, 2, 22.00, 44.00),
-- Venta 7
(23, 7, 6, 2, 12.50, 25.00),
(24, 7, 14, 1, 35.00, 35.00),
(25, 7, 1, 1, 22.50, 22.50),
(26, 7, 27, 1, 11.50, 11.50),
-- Venta 8
(27, 8, 3, 3, 68.00, 204.00),
(28, 8, 21, 2, 95.00, 190.00),
(29, 8, 17, 1, 62.00, 62.00),
-- Venta 9
(30, 9, 2, 2, 28.00, 56.00),
(31, 9, 4, 1, 125.00, 125.00),
(32, 9, 11, 1, 18.50, 18.50),
(33, 9, 19, 1, 25.00, 25.00),
-- Venta 10
(34, 10, 35, 1, 549.00, 549.00),
(35, 10, 24, 2, 45.00, 90.00),
(36, 10, 8, 3, 8.00, 24.00),
-- Venta 11
(37, 11, 8, 2, 8.00, 16.00),
(38, 11, 27, 2, 15.00, 30.00),
(39, 11, 5, 1, 10.00, 10.00),
-- Venta 12
(40, 12, 30, 2, 38.00, 76.00),
(41, 12, 26, 3, 20.00, 60.00),
(42, 12, 33, 2, 24.00, 48.00),
(43, 12, 10, 1, 50.50, 50.50),
-- Venta 13
(44, 13, 15, 3, 13.50, 40.50),
(45, 13, 29, 1, 18.00, 18.00),
(46, 13, 20, 2, 15.50, 31.00),
-- Venta 14
(47, 14, 25, 2, 105.00, 210.00),
(48, 14, 32, 1, 42.00, 42.00),
(49, 14, 28, 3, 11.50, 34.50),
(50, 14, 7, 1, 18.00, 18.00),
-- Venta 15
(51, 15, 36, 1, 189.00, 189.00),
(52, 15, 9, 2, 20.00, 40.00),
(53, 15, 17, 3, 18.00, 54.00);