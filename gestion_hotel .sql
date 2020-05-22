-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  Dim 17 mai 2020 à 20:26
-- Version du serveur :  10.1.38-MariaDB
-- Version de PHP :  7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gestion_hotel`
--

-- --------------------------------------------------------

--
-- Structure de la table `chambres`
--

CREATE TABLE `chambres` (
  `Id` int(11) NOT NULL,
  `Type` varchar(255) DEFAULT NULL,
  `Tarif` double DEFAULT NULL,
  `capacite` int(11) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `chambres`
--

INSERT INTO `chambres` (`Id`, `Type`, `Tarif`, `capacite`, `Description`) VALUES
(1, 'chambre éco', 50, 4, 'chambre éco qui partagent une salle de bains commune sur le palier'),
(2, 'chambre confort', 100, 5, 'chambre confort avec une salle de bains privative et une télévision'),
(3, 'chambre confort+', 150, 6, 'chambre confort plus munies d\'une salle de bains avec baignoire et de la climatisation');

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE `clients` (
  `Id` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `GroupId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`Id`, `Name`, `Email`, `Password`, `GroupId`) VALUES
(1, 'khaoula', 'khaoulaoubella20@gmail.com', '123', 1),
(2, 'chaimae khachane', 'khachanachaimaa@gmail.com', '123', 1),
(3, 'youssef', 'hotel418@gmail.com', '123', 2),
(4, 'youssef mathouri', 'youssef.mathouri1997@gmail.com', 'youssef', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `groups`
--

CREATE TABLE `groups` (
  `Id` int(11) NOT NULL,
  `GroupName` varchar(255) DEFAULT NULL,
  `isAdmin` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `groups`
--

INSERT INTO `groups` (`Id`, `GroupName`, `isAdmin`) VALUES
(1, 'User', 0),
(2, 'Gérant', 1);

-- --------------------------------------------------------

--
-- Structure de la table `reservations`
--

CREATE TABLE `reservations` (
  `Id` int(11) NOT NULL,
  `dateArrive` date DEFAULT NULL,
  `dateDepart` date DEFAULT NULL,
  `NbrPersonne` int(11) DEFAULT NULL,
  `NbrNuit` int(11) DEFAULT NULL,
  `chambreTypeId` int(11) DEFAULT NULL,
  `clientId` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reservations`
--

INSERT INTO `reservations` (`Id`, `dateArrive`, `dateDepart`, `NbrPersonne`, `NbrNuit`, `chambreTypeId`, `clientId`, `status`) VALUES
(13, '2020-05-28', '2020-05-19', 2, 7, 1, 1, 3),
(14, '2020-05-21', '2020-05-23', 2, 2, 3, 1, 2),
(15, '2020-05-17', '2020-05-18', 1, 3, 2, 2, 2),
(16, '2020-05-25', '2020-05-22', 2, 4, 1, 2, 3),
(17, '2020-05-30', '2020-05-31', 1, 1, 3, 2, 2),
(18, '2020-05-15', '2020-05-12', 2, 4, 3, 4, 2),
(19, '2020-05-23', '2020-05-23', 1, 3, 2, 4, 3);

-- --------------------------------------------------------

--
-- Structure de la table `status`
--

CREATE TABLE `status` (
  `Id` int(11) NOT NULL,
  `Description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `status`
--

INSERT INTO `status` (`Id`, `Description`) VALUES
(1, 'En attente'),
(2, 'Accepté'),
(3, 'Refusé');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `chambres`
--
ALTER TABLE `chambres`
  ADD PRIMARY KEY (`Id`);

--
-- Index pour la table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `GroupId` (`GroupId`);

--
-- Index pour la table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`Id`);

--
-- Index pour la table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `status` (`status`),
  ADD KEY `clientId` (`clientId`),
  ADD KEY `chambreTypeId` (`chambreTypeId`);

--
-- Index pour la table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`Id`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `clients`
--
ALTER TABLE `clients`
  ADD CONSTRAINT `clients_ibfk_1` FOREIGN KEY (`GroupId`) REFERENCES `groups` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`status`) REFERENCES `status` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`clientId`) REFERENCES `clients` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reservations_ibfk_3` FOREIGN KEY (`chambreTypeId`) REFERENCES `chambres` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
