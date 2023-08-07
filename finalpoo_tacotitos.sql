-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-11-2022 a las 02:06:34
-- Versión del servidor: 8.0.30
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `finalpoo_tacotitos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alimento`
--

CREATE TABLE `alimento` (
  `idalimento` int NOT NULL,
  `nombre_alimento` varchar(45) NOT NULL,
  `precio_alimento` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `alimento`
--

INSERT INTO `alimento` (`idalimento`, `nombre_alimento`, `precio_alimento`) VALUES
(1, 'Carne', '24.00'),
(2, 'Quinoa', '15.00'),
(3, 'Pollo', '20.00'),
(6, 'Atún', '22.00'),
(7, 'Jamón', '13.00'),
(8, 'Queso', '12.00'),
(9, 'Huevo', '10.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `idcliente` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `celular` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medio_envio`
--

CREATE TABLE `medio_envio` (
  `idmedio_envio` int NOT NULL,
  `nombre_medio` varchar(45) NOT NULL,
  `patente` varchar(45) NOT NULL,
  `celular_contacto` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `idpedido` int NOT NULL,
  `totalPedido` decimal(10,2) NOT NULL,
  `fecha` datetime NOT NULL,
  `idmedio_envio` int NOT NULL,
  `idcliente` int NOT NULL,
  `idtaco` int NOT NULL,
  `entregado_si_no` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salsa`
--

CREATE TABLE `salsa` (
  `idsalsa` int NOT NULL,
  `nombre_salsa` varchar(45) NOT NULL,
  `precio_salsa` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `salsa`
--

INSERT INTO `salsa` (`idsalsa`, `nombre_salsa`, `precio_salsa`) VALUES
(1, 'Pico de gallo', '15.00'),
(2, 'Guacamole', '17.00'),
(4, 'Roja', '14.00'),
(5, 'Chile habanero', '16.00'),
(6, 'Chamoy', '20.00'),
(7, 'Chipotle', '19.00'),
(8, 'Endiablada', '22.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `taco`
--

CREATE TABLE `taco` (
  `idtaco` int NOT NULL,
  `preciotaco` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `taco`
--

INSERT INTO `taco` (`idtaco`, `preciotaco`) VALUES
(1, '75.00'),
(2, '127.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tortilla`
--

CREATE TABLE `tortilla` (
  `idtortilla` int NOT NULL,
  `nombre_tortilla` varchar(45) NOT NULL,
  `precio_tortilla` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `tortilla`
--

INSERT INTO `tortilla` (`idtortilla`, `nombre_tortilla`, `precio_tortilla`) VALUES
(1, 'Maíz', '20.00'),
(2, 'Harina', '25.00'),
(3, 'Sobaquera', '22.00'),
(5, 'Penchuques', '18.00'),
(6, 'Chicharrón', '19.00'),
(7, 'Trigo', '17.00'),
(8, 'Avena', '15.00'),
(9, 'Nopal', '26.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `union_alimento_taco`
--

CREATE TABLE `union_alimento_taco` (
  `idalimento` int NOT NULL,
  `idtaco` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `union_alimento_taco`
--

INSERT INTO `union_alimento_taco` (`idalimento`, `idtaco`) VALUES
(1, 2),
(7, 2),
(8, 2),
(9, 2),
(2, 2),
(6, 1),
(8, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `union_salsa_taco`
--

CREATE TABLE `union_salsa_taco` (
  `idsalsa` int NOT NULL,
  `idtaco` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `union_salsa_taco`
--

INSERT INTO `union_salsa_taco` (`idsalsa`, `idtaco`) VALUES
(1, 2),
(8, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `union_tortilla_taco`
--

CREATE TABLE `union_tortilla_taco` (
  `idtortilla` int NOT NULL,
  `idtaco` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `union_tortilla_taco`
--

INSERT INTO `union_tortilla_taco` (`idtortilla`, `idtaco`) VALUES
(5, 2),
(1, 2),
(6, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alimento`
--
ALTER TABLE `alimento`
  ADD PRIMARY KEY (`idalimento`),
  ADD UNIQUE KEY `nombre_alimento_UNIQUE` (`nombre_alimento`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idcliente`);

--
-- Indices de la tabla `medio_envio`
--
ALTER TABLE `medio_envio`
  ADD PRIMARY KEY (`idmedio_envio`),
  ADD UNIQUE KEY `nombre_medio_UNIQUE` (`nombre_medio`),
  ADD UNIQUE KEY `patente_UNIQUE` (`patente`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`idpedido`),
  ADD KEY `fk_pedido_medio_envio1_idx` (`idmedio_envio`),
  ADD KEY `fk_pedido_cliente1_idx` (`idcliente`),
  ADD KEY `fk_pedido_taco1_idx` (`idtaco`);

--
-- Indices de la tabla `salsa`
--
ALTER TABLE `salsa`
  ADD PRIMARY KEY (`idsalsa`),
  ADD UNIQUE KEY `nombre_salsa_UNIQUE` (`nombre_salsa`);

--
-- Indices de la tabla `taco`
--
ALTER TABLE `taco`
  ADD PRIMARY KEY (`idtaco`);

--
-- Indices de la tabla `tortilla`
--
ALTER TABLE `tortilla`
  ADD PRIMARY KEY (`idtortilla`),
  ADD UNIQUE KEY `nombre_tortilla_UNIQUE` (`nombre_tortilla`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alimento`
--
ALTER TABLE `alimento`
  MODIFY `idalimento` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idcliente` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `medio_envio`
--
ALTER TABLE `medio_envio`
  MODIFY `idmedio_envio` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `idpedido` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `salsa`
--
ALTER TABLE `salsa`
  MODIFY `idsalsa` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `taco`
--
ALTER TABLE `taco`
  MODIFY `idtaco` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tortilla`
--
ALTER TABLE `tortilla`
  MODIFY `idtortilla` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `fk_pedido_cliente1` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`),
  ADD CONSTRAINT `fk_pedido_medio_envio1` FOREIGN KEY (`idmedio_envio`) REFERENCES `medio_envio` (`idmedio_envio`),
  ADD CONSTRAINT `fk_pedido_taco1` FOREIGN KEY (`idtaco`) REFERENCES `taco` (`idtaco`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
