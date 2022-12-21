-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Dic 21, 2022 alle 23:21
-- Versione del server: 10.4.27-MariaDB
-- Versione PHP: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wineshop`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `test`
--

CREATE TABLE `test` (
  `id` int(11) NOT NULL,
  `nome` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='tabella di test';

--
-- Dump dei dati per la tabella `test`
--

INSERT INTO `test` (`id`, `nome`) VALUES
(1, 'test'),
(2, 'test');

-- --------------------------------------------------------

--
-- Struttura della tabella `thirdparty`
--

CREATE TABLE `thirdparty` (
  `id` int(11) NOT NULL,
  `servizio` enum('corriere','fornitore') NOT NULL,
  `nome` char(30) NOT NULL,
  `cognome` char(30) NOT NULL,
  `mail` char(30) NOT NULL,
  `telefono` char(13) NOT NULL,
  `codicefiscale` char(20) NOT NULL,
  `indirizzoaziendale` char(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `id` int(11) NOT NULL COMMENT 'chiave primaria',
  `ruolo` enum('admin','client','employee') NOT NULL,
  `username` char(20) NOT NULL,
  `password` char(20) NOT NULL,
  `nome` char(30) NOT NULL,
  `cogmone` char(30) NOT NULL,
  `c_fiscale` char(16) NOT NULL COMMENT 'c.f. unico',
  `mail` char(40) NOT NULL,
  `telefono` char(13) NOT NULL,
  `indirizzo` char(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`id`, `ruolo`, `username`, `password`, `nome`, `cogmone`, `c_fiscale`, `mail`, `telefono`, `indirizzo`) VALUES
(1, 'admin', 'admin', 'admin', 'Agostino', 'Poggi', 'fhfusjfio', 'A.Poggi@unipr.it', '3333333333', 'via Campus 1'),
(18, 'admin', 'user', 'user', 'user', 'usercognome', 'codfisc', 'mail@mail.it', '4544545456', 'via Campus 3');

-- --------------------------------------------------------

--
-- Struttura della tabella `wines`
--

CREATE TABLE `wines` (
  `id` int(11) NOT NULL,
  `nome` char(50) NOT NULL,
  `produttore` char(50) NOT NULL,
  `provenienza` char(50) NOT NULL,
  `anno` date NOT NULL,
  `vitigno` char(50) NOT NULL,
  `notetechinche` text NOT NULL,
  `prezzo` float NOT NULL COMMENT 'prezzo a bottiglia',
  `promo` tinyint(1) NOT NULL,
  `quantita` int(11) NOT NULL COMMENT 'in bottiglie'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `thirdparty`
--
ALTER TABLE `thirdparty`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `c_fiscale` (`c_fiscale`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indici per le tabelle `wines`
--
ALTER TABLE `wines`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `test`
--
ALTER TABLE `test`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `thirdparty`
--
ALTER TABLE `thirdparty`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'chiave primaria', AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `wines`
--
ALTER TABLE `wines`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
