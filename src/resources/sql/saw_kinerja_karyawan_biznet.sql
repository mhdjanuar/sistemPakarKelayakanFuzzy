-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 09, 2025 at 02:10 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `saw_kinerja_karyawan_biznet`
--

-- --------------------------------------------------------

--
-- Table structure for table `alternatif`
--

CREATE TABLE `alternatif` (
  `id` int(11) NOT NULL,
  `id_employee` int(11) NOT NULL,
  `id_sub_kreteria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `alternatif`
--

INSERT INTO `alternatif` (`id`, `id_employee`, `id_sub_kreteria`) VALUES
(9, 6, 2),
(10, 6, 5),
(11, 6, 10),
(12, 6, 12),
(13, 1, 1),
(14, 1, 4),
(15, 1, 7),
(16, 1, 11),
(17, 4, 2),
(18, 4, 4),
(19, 4, 7),
(20, 4, 11);

-- --------------------------------------------------------

--
-- Table structure for table `criteria`
--

CREATE TABLE `criteria` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `type` enum('benefit','cost') NOT NULL,
  `bobot` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `criteria`
--

INSERT INTO `criteria` (`id`, `nama`, `type`, `bobot`) VALUES
(1, 'Dive for Result', 'benefit', 30.00),
(2, 'Costumer Focus Orientation', 'benefit', 25.00),
(3, 'Team Work', 'benefit', 25.00),
(4, 'Initiative-innovation', 'benefit', 25.00);

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `position` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`id`, `name`, `position`) VALUES
(1, 'Muhammad Januar', 'Developer'),
(4, 'Helmy Akbar', 'Staff'),
(5, 'Tanneke Iqbal', 'Head Office'),
(6, 'Galang Wijaya', 'HRD');

-- --------------------------------------------------------

--
-- Table structure for table `sub_criteria`
--

CREATE TABLE `sub_criteria` (
  `id` int(11) NOT NULL,
  `id_kreteria` int(11) NOT NULL,
  `jumlah_bobot` decimal(5,2) NOT NULL,
  `deskripsi` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sub_criteria`
--

INSERT INTO `sub_criteria` (`id`, `id_kreteria`, `jumlah_bobot`, `deskripsi`) VALUES
(1, 1, 3.00, 'Selalu mencapai target lebih dari ekspektasi'),
(2, 1, 2.00, 'Hampir mencapai target'),
(3, 1, 1.00, 'Tidak mencapai target sama sekali'),
(4, 2, 3.00, 'Sangat responsif dan ramah terhadap pelanggan'),
(5, 2, 2.00, 'Cukup responsif namun kurang memahami kebutuhan'),
(6, 2, 1.00, 'Tidak peduli terhadap pelanggan'),
(7, 3, 3.00, 'Sangat kooperatif dan membantu semua anggota'),
(8, 3, 2.00, 'Bekerja sama bila diperlukan'),
(10, 3, 1.00, 'Tidak bisa bekerja dalam tim'),
(11, 4, 3.00, 'Sering memberi ide kreatif yang bermanfaat'),
(12, 4, 2.00, 'Jarang memberi ide namun cukup proaktif'),
(14, 4, 1.00, 'Tidak pernah memberi ide dan pasif');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `alamat` text DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `alamat`, `username`, `password`, `email`) VALUES
(1, 'Administrator', 'Jl. Proklamasi No. 1, Jakarta', 'admin', 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alternatif`
--
ALTER TABLE `alternatif`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_employee` (`id_employee`),
  ADD KEY `id_sub_kreteria` (`id_sub_kreteria`);

--
-- Indexes for table `criteria`
--
ALTER TABLE `criteria`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sub_criteria`
--
ALTER TABLE `sub_criteria`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_kreteria` (`id_kreteria`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `alternatif`
--
ALTER TABLE `alternatif`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `criteria`
--
ALTER TABLE `criteria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `sub_criteria`
--
ALTER TABLE `sub_criteria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `alternatif`
--
ALTER TABLE `alternatif`
  ADD CONSTRAINT `alternatif_ibfk_1` FOREIGN KEY (`id_employee`) REFERENCES `employees` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `alternatif_ibfk_2` FOREIGN KEY (`id_sub_kreteria`) REFERENCES `sub_criteria` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `sub_criteria`
--
ALTER TABLE `sub_criteria`
  ADD CONSTRAINT `sub_criteria_ibfk_1` FOREIGN KEY (`id_kreteria`) REFERENCES `criteria` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
