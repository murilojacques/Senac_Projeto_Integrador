SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `Sistema_Banco`
--

-- --------------------------------------------------------
CREATE DATABASE `Sistema_Banco`;

CREATE TABLE `conta`(
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `nome_usuario` VARCHAR(60) NOT NULL,
    `endereco` VARCHAR(200) NOT NULL,
    `cpf` INT (11) NOT NULL,
    `cnpj` INT(14) NOT NULL,
    `tipo` VARCHAR(20) NOT NULL,
    `saldo` FLOAT(12,2) NOT NULL,
    `status` BOOLEAN NOT NULL,
    `pfpj` Varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `transacoes`(
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `conta_origem_id` INT NOT NULL,
    `conta_destino_id` INT NOT NULL,
    `alteracao_conta` VARCHAR(10) NOT NULL,
    `valor` FLOAT NOT NULL,
    CONSTRAINT `conta_origem_fk` FOREIGN KEY(`conta_origem_id`) REFERENCES `conta`(`id`),
    CONSTRAINT `conta_destino_fk` FOREIGN KEY(`conta_destino_id`) REFERENCES `conta`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


SELECT * FROM conta;

