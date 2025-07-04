-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 04, 2025 at 09:22 AM
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
-- Database: `sistem_pakar_kelayakan_fuzzy`
--

-- --------------------------------------------------------

--
-- Table structure for table `hasil_tuk`
--

CREATE TABLE `hasil_tuk` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_tuk` int(10) UNSIGNED NOT NULL,
  `nilai` decimal(5,2) NOT NULL,
  `remarks` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hasil_tuk`
--

INSERT INTO `hasil_tuk` (`id`, `id_tuk`, `nilai`, `remarks`) VALUES
(1, 1, 87.50, 'Layak'),
(2, 2, 72.30, 'Dipertimbangkan'),
(3, 3, 90.00, 'Layak'),
(4, 4, 45.00, 'Tidak Layak'),
(5, 5, 78.40, 'Dipertimbangkan'),
(6, 5, 90.00, 'Layak'),
(7, 5, 30.00, 'Tidak Layak');

-- --------------------------------------------------------

--
-- Table structure for table `tuk`
--

CREATE TABLE `tuk` (
  `id` int(10) UNSIGNED NOT NULL,
  `nama_tuk` varchar(255) NOT NULL,
  `instansi_penyelenggara` varchar(255) NOT NULL,
  `alamat` text NOT NULL,
  `no_telepon` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tuk`
--

INSERT INTO `tuk` (`id`, `nama_tuk`, `instansi_penyelenggara`, `alamat`, `no_telepon`, `email`) VALUES
(1, 'TUK Las Mandiri Surabaya', 'BLK Surabaya', 'Jl. Industri No. 12, Surabaya, Jawa Timur', '081234567890', 'tuk.sby@blk.go.id'),
(2, 'TUK Las Sewaktu Jakarta', 'LSP Teknik Jakarta', 'Jl. Teknik No. 45, Jakarta Selatan', '082112345678', 'info@lsptjkt.org'),
(3, 'TUK Las Tempat Kerja Bandung', 'SMK Teknik Welding Bandung', 'Jl. Pendidikan No. 77, Bandung, Jawa Barat', '087712345678', 'admin@smkteknikbdg.sch.id'),
(4, 'TUK Las Mandiri Medan', 'Balai Latihan Kerja Medan', 'Jl. Pelatihan No. 5, Medan', '081398765432', 'tuk.medan@blkmedan.go.id'),
(5, 'TUK Las Sewaktu Makassar', 'Politeknik Negeri Ujung Pandang', 'Jl. Pendidikan Teknik No. 9, Makassar', '081276543210', 'tuk@pnup.ac.id');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(100) NOT NULL,
  `alamat` text NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `alamat`, `username`, `password`, `email`) VALUES
(1, 'Admin Sistem', 'Jl. Teknologi No. 1, Jakarta', 'admin', 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `hasil_tuk`
--
ALTER TABLE `hasil_tuk`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_tuk` (`id_tuk`);

--
-- Indexes for table `tuk`
--
ALTER TABLE `tuk`
  ADD PRIMARY KEY (`id`);

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
-- AUTO_INCREMENT for table `hasil_tuk`
--
ALTER TABLE `hasil_tuk`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tuk`
--
ALTER TABLE `tuk`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `hasil_tuk`
--
ALTER TABLE `hasil_tuk`
  ADD CONSTRAINT `hasil_tuk_ibfk_1` FOREIGN KEY (`id_tuk`) REFERENCES `tuk` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
