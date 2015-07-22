-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 29, 2015 at 07:17 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `myapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `myapp_categories`
--

CREATE TABLE IF NOT EXISTS `myapp_categories` (
  `categoryID` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(50) DEFAULT NULL,
  `expID` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`categoryID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `myapp_categories`
--

INSERT INTO `myapp_categories` (`categoryID`, `categoryName`, `expID`) VALUES
(1, 'taxi', 1),
(2, 'hotel', 1),
(3, 'school', 2),
(4, 'university', 2);

-- --------------------------------------------------------

--
-- Table structure for table `myapp_expenses`
--

CREATE TABLE IF NOT EXISTS `myapp_expenses` (
  `expID` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `expname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`expID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `myapp_expenses`
--

INSERT INTO `myapp_expenses` (`expID`, `expname`) VALUES
(1, 'travel'),
(2, 'education');

-- --------------------------------------------------------

--
-- Table structure for table `myapp_transactions`
--

CREATE TABLE IF NOT EXISTS `myapp_transactions` (
  `transactID` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `userID` smallint(6) DEFAULT NULL,
  `expID` smallint(6) DEFAULT NULL,
  `categoryID` smallint(6) DEFAULT NULL,
  `val` int(11) DEFAULT NULL,
  PRIMARY KEY (`transactID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `myapp_users_details`
--

CREATE TABLE IF NOT EXISTS `myapp_users_details` (
  `userId` int(3) NOT NULL,
  `userName` varchar(20) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `userType` int(1) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `myapp_users_details`
--

INSERT INTO `myapp_users_details` (`userId`, `userName`, `Password`, `userType`) VALUES
(1, 'shawki', 'shawki', 1),
(2, 'rami', 'rami', 0),
(3, 'karim', 'karim', 0),
(4, 'test', '098f6bcd4621d373cade4e832627b4f6', 0),
(5, 'example', '1a79a4d60de6718e8e5b326e338ae533', 1);

-- --------------------------------------------------------

--
-- Table structure for table `myapp_users_groups`
--

CREATE TABLE IF NOT EXISTS `myapp_users_groups` (
  `userID` int(11) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `usergroup` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  KEY `userID` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `myapp_users_groups`
--

INSERT INTO `myapp_users_groups` (`userID`, `username`, `usergroup`) VALUES
(1, 'shawki', 'Admin-Group'),
(2, 'rami', 'Regular-Group'),
(3, 'karim', 'Regular-Group');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
