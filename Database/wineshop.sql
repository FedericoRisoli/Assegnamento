-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mar 31, 2023 alle 09:20
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
-- Struttura della tabella `ordinivendita`
--

CREATE TABLE `ordinivendita` (
  `id` int(11) NOT NULL,
  `nome` char(30) NOT NULL,
  `cognome` char(30) NOT NULL,
  `Idcliente` int(11) NOT NULL,
  `ordine` text NOT NULL,
  `indirizzo` char(40) NOT NULL,
  `dataordine` date NOT NULL,
  `dataconsegna` date DEFAULT NULL,
  `completato` tinyint(1) NOT NULL,
  `clienteCompletato` tinyint(1) NOT NULL,
  `prezzo` decimal(10,2) NOT NULL DEFAULT 0.00,
  `firmaimpiegato` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `ordinivendita`
--

INSERT INTO `ordinivendita` (`id`, `nome`, `cognome`, `Idcliente`, `ordine`, `indirizzo`, `dataordine`, `dataconsegna`, `completato`, `clienteCompletato`, `prezzo`, `firmaimpiegato`) VALUES
(28, 'Tito', 'Fiorentino', 26, 'Francia Corta 19 11.0\n', 'Via Torino 8', '2023-03-09', NULL, 0, 0, '50.00', NULL),
(29, 'Tito', 'Fiorentino', 26, 'Francia Corta 16 11.0\n', 'via Torini, 8', '2023-03-09', NULL, 0, 0, '0.00', NULL),
(32, 'Agostino', 'Poggi', 1, 'Francia Corta 15 11.0\n', 'Via Milano 9', '2023-03-09', '2023-05-05', 1, 0, '152.00', NULL),
(34, 'Agostino', 'Poggi', 1, 'Francia Corta 17 11.0\n', 'Via Milano 9', '2023-03-19', NULL, 0, 0, '1.00', NULL),
(62, 'Elia', 'Candida', 39, 'Barbaresco Meruzzano 1 39.49 \n', 'via Campus, 1, PR', '2023-03-30', '2023-04-01', 1, 1, '100.00', NULL),
(63, '', '', 0, 'Barbaresco Meruzzano 15 592.35', '', '2023-03-30', NULL, 0, 0, '0.00', NULL),
(64, 'Elia', 'Candida', 39, 'Francia Corta 13 11.0\n', 'via Campus 2, PR', '2023-03-30', NULL, 0, 0, '150.00', NULL),
(65, 'Agostino', 'Poggi', 1, 'Barbaresco Meruzzano 1 39.53 \n', 'via Campus 1', '2023-03-30', NULL, 0, 0, '39.53', NULL),
(67, 'a', 'a', 37, 'Francia Corta 22 11.0\n', 'll', '2023-03-30', NULL, 0, 0, '0.00', NULL),
(69, 'federico', 'risoli', 40, 'Barbaresco Meruzzano 25 876.17 \n', 'via Torino 8, PR', '2023-03-30', '2023-04-02', 1, 1, '876.17', 1),
(70, 'federico', 'risoli', 40, 'Francia Corta 38 11.0\n', 'via Torini 4 , PR', '2023-03-30', '2023-03-31', 1, 1, '150.00', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `id` int(11) NOT NULL COMMENT 'chiave primaria',
  `ruolo` enum('admin','client','employee','corriere','fornitore') NOT NULL,
  `username` char(20) DEFAULT NULL,
  `password` char(20) DEFAULT NULL,
  `nome` char(30) NOT NULL,
  `cognome` char(30) NOT NULL,
  `c_fiscale` char(16) NOT NULL COMMENT 'c.f. unico',
  `mail` char(40) NOT NULL,
  `telefono` char(13) NOT NULL,
  `indirizzo` char(40) NOT NULL,
  `job_completati` int(11) NOT NULL DEFAULT 0,
  `job_falliti` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`id`, `ruolo`, `username`, `password`, `nome`, `cognome`, `c_fiscale`, `mail`, `telefono`, `indirizzo`, `job_completati`, `job_falliti`) VALUES
(1, 'admin', 'admin', 'admin', 'Agostino', 'Poggi', 'fhfusjfio', 'A.Poggi@unipr.it', '3333333333', 'via Campus 1', 0, 8),
(22, 'employee', 'emp', 'emp', 'e', 'e', 'e', 'e', 'e', 'e', 0, 8),
(23, 'employee', 'fromani', 'fromanivinobuono', 'Franco', 'Romani', 'CLNCZD60T17I473M', 'mciglia@gmail.com', '03161986517', 'Via Vicenza, 15 , 40013-Bologna BO', 0, 0),
(24, 'employee', 'Lucius.Pisani', 'delfino', 'Lucius', 'Pisani', 'BGFDQG74C04I260L', 'l.pis@gmail.com', '05210246834', 'Discesa Gaiola, 93, 85043-Cerri PZ', 0, 0),
(25, 'employee', 'genovesina', 'vinibuoni', 'Paola', 'Genovesi', 'SMKNRI88C04F187W', 'genovesina73@libero.it', '0520896544', 'Via Moiariello, 29, 12010-Vinadio CN', 0, 0),
(26, 'employee', 'Tito', 'tito', 'Tito', 'Fiorentino', 'MSNVMF86M68M045Q', 'fiorentino@gmail.com', '07201380537', 'Via Palermo, 12, 98040-Soccorso ME', 0, 8),
(27, 'employee', 'Giorgia01', 'emp', 'Giorgia', 'Cremonesi', 'FHBDBG86T51D045Q', ' GiorgiaCremonesi@gmail.com', '4567897474', 'Via Gaetano Donizetti, 92, 37040-Locara', 0, 0),
(28, 'client', '4L4K4Z4M', 'unamagia', 'Elia', 'Candida', 'HVHHTB55C23B195C', 'staiattento@gmail.com', '9876541230', 'via Campus 1, PARMA', 0, 0),
(29, 'client', 'WhiteHunter', '1111111111', 'Federico', 'Risoli', 'GLZLFZ77A26F773U', 'risolia@gmail.com', '4832185138', 'via Campus 2, PARMA', 0, 0),
(30, 'client', 'Unipsilo', 'cartello', 'Olivia', 'Spinu', 'QRHSSJ44S46G273S', 'ocimicidae.matematica@gmail.com', '9874563218', 'via Campus 3, PARMA', 0, 0),
(31, 'corriere', NULL, NULL, 'Paolo', 'Terenas', 'trnpla11T29R449A', 'terenasSRL@gmail.com', '6974456622', 'Barriera di Milano 4, Torino', 0, 0),
(32, 'fornitore', NULL, NULL, 'Lucio', 'Dalla', 'tgdng88T29R969A', 'LupoLucioDalla@speedywine.com', '3496942011', 'via Palermo 4, Parma', 0, 0),
(37, 'client', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 0, 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `wines`
--

CREATE TABLE `wines` (
  `id` int(11) NOT NULL,
  `nome` char(50) NOT NULL,
  `produttore` char(50) NOT NULL,
  `provenienza` char(50) NOT NULL,
  `anno` int(4) NOT NULL,
  `vitigno` char(50) NOT NULL,
  `notetecniche` text NOT NULL,
  `qualita` enum('Alta','Media','Bassa') NOT NULL,
  `vendite` int(11) NOT NULL DEFAULT 0,
  `promo` tinyint(1) NOT NULL,
  `quantita` int(11) NOT NULL COMMENT 'in bottiglie'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `wines`
--

INSERT INTO `wines` (`id`, `nome`, `produttore`, `provenienza`, `anno`, `vitigno`, `notetecniche`, `qualita`, `vendite`, `promo`, `quantita`) VALUES
(1, 'francia corta', 'unipr', 'parma', 2022, 'rtewgserg', 'sergsdrgzxcv', 'Alta', 0, 0, 322),
(5, 'Francia Corta', '1701 Francia Corta', 'Francia', 1990, 'Albana', 'Frizzante, fruttato, bianco', 'Bassa', 38, 1, 2),
(6, 'Barbaresco Meruzzano', 'Abrigo Orlando', 'Italia', 1995, 'Barbera', 'Frizzante, fruttato, bianco', 'Alta', 26, 1, 999),
(7, 'Barbaresco DOCG Montersino', 'Abrigo Orlando', 'Italia', 2021, 'Barbera', 'Rosso, fermo, intenso', 'Media', 0, 0, 23),
(8, 'Francia Corta', '1701 Francia Corta', 'Francia', 1987, 'Albana', 'Frizzante, fruttato, bianco', 'Alta', 0, 0, 47),
(9, 'Ghemme', 'Alamos', 'Italia', 1999, 'Barolo bianco', 'Frizzante, fruttato, bianco', 'Bassa', 0, 0, 999),
(10, 'Greco di Tufo', 'Conti Di Buscareto', 'Italia', 2000, 'Biancolella', 'Rosso, fermo, delicato', 'Media', 0, 1, 123),
(11, 'Lison', 'Dosnon', 'Italia', 2019, 'Bonarda', 'Rosso, fermo, intenso', 'Bassa', 0, 0, 44),
(12, 'Montecucco Sangiovese', 'Fongoli', 'Italia', 2008, 'Cortese', 'Rosso, fermo, intenso', 'Bassa', 0, 0, 55),
(13, 'Montefalco Sagrantino', 'Francoli', 'Italia', 2019, 'Durella', 'Rosso, fermo, delicato', 'Media', 0, 0, 69),
(14, 'Montello rosso', 'Illica', 'Italia', 2012, 'Falanghina', 'Rosè, frizzante, delicato', 'Bassa', 0, 0, 120),
(15, 'Purple Desire', 'Desirè Wines', 'Inghilterra', 2019, 'Purple Magic', 'Rosso, fruttato', 'Bassa', 0, 0, 44),
(16, 'White Desire', 'Desirè Wines', 'Inghilterra', 2000, 'White Magic', 'Bianco, fruttato', 'Alta', 0, 1, 34),
(17, 'Pink Desire', 'Desirè Wines', 'Inghilterra', 1999, 'Pink Magic', 'Rosè, fruttato', 'Media', 0, 1, 20);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `ordinivendita`
--
ALTER TABLE `ordinivendita`
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
-- AUTO_INCREMENT per la tabella `ordinivendita`
--
ALTER TABLE `ordinivendita`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'chiave primaria', AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT per la tabella `wines`
--
ALTER TABLE `wines`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
