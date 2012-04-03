-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Anamakine: localhost
-- Üretim Zamanı: 25 Ocak 2011 saat 14:41:34
-- Sunucu sürümü: 5.1.41
-- PHP Sürümü: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Veritabanı: `sinema`
--

-- --------------------------------------------------------

--
-- Tablo yapısı: `gosterimler`
--

CREATE TABLE IF NOT EXISTS `gosterimler` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filmadi` text NOT NULL,
  `seans` time NOT NULL,
  `salon` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=46 ;

--
-- Tablo döküm verisi `gosterimler`
--

INSERT INTO `gosterimler` (`id`, `filmadi`, `seans`, `salon`) VALUES
(41, 'av mevsimi', '15:00:00', 1),
(42, 'av mevsimi', '18:40:00', 1),
(40, 'av mevsimi', '10:00:00', 1),
(45, 'matrix', '18:30:00', 2),
(44, 'matrix', '14:55:00', 2),
(43, 'matrix', '11:00:00', 2);

-- --------------------------------------------------------

--
-- Tablo yapısı: `koltuklar`
--

CREATE TABLE IF NOT EXISTS `koltuklar` (
  `k_id` int(11) NOT NULL AUTO_INCREMENT,
  `koltukno` text NOT NULL,
  `salon` int(11) NOT NULL,
  `filmi` text NOT NULL,
  `seansno` time NOT NULL,
  PRIMARY KEY (`k_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17 ;

--
-- Tablo döküm verisi `koltuklar`
--

INSERT INTO `koltuklar` (`k_id`, `koltukno`, `salon`, `filmi`, `seansno`) VALUES
(1, '34', 1, 'av mevsimi', '10:00:00'),
(2, '24', 1, 'av mevsimi', '10:00:00'),
(3, '31', 1, 'av mevsimi', '10:00:00'),
(4, '4', 1, 'av mevsimi', '10:00:00'),
(5, '5', 1, 'av mevsimi', '10:00:00'),
(6, '34', 1, 'av mevsimi', '16:00:00'),
(7, '32', 1, 'av mevsimi', '11:00:00'),
(8, '33', 1, 'av mevsimi', '11:00:00'),
(9, '24', 1, 'av mevsimi', '11:00:00'),
(10, '25', 1, 'av mevsimi', '11:00:00'),
(11, '13', 1, 'av mevsimi', '18:40:00'),
(12, '14', 1, 'av mevsimi', '18:40:00'),
(13, '15', 2, 'matrix', '18:30:00'),
(14, '16', 2, 'matrix', '18:30:00'),
(15, '21', 2, 'matrix', '18:30:00'),
(16, '22', 2, 'matrix', '18:30:00');

-- --------------------------------------------------------

--
-- Tablo yapısı: `salon`
--

CREATE TABLE IF NOT EXISTS `salon` (
  `salon_id` int(11) NOT NULL AUTO_INCREMENT,
  `salonno` int(6) NOT NULL,
  `bos` int(1) NOT NULL,
  PRIMARY KEY (`salon_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=49 ;

--
-- Tablo döküm verisi `salon`
--

INSERT INTO `salon` (`salon_id`, `salonno`, `bos`) VALUES
(44, 1, 1),
(48, 2, 1),
(18, 3, 0),
(11, 4, 0),
(47, 5, 0),
(39, 6, 0);

-- --------------------------------------------------------

--
-- Tablo yapısı: `seanslar`
--

CREATE TABLE IF NOT EXISTS `seanslar` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT,
  `seans` time NOT NULL,
  `f_adi` text NOT NULL,
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=46 ;

--
-- Tablo döküm verisi `seanslar`
--

INSERT INTO `seanslar` (`s_id`, `seans`, `f_adi`) VALUES
(40, '11:00:00', 'matrix'),
(41, '14:55:00', 'matrix'),
(42, '18:30:00', 'matrix'),
(43, '11:00:00', 'av mevsimi'),
(44, '16:00:00', 'av mevsimi'),
(45, '18:40:00', 'av mevsimi');

-- --------------------------------------------------------

--
-- Tablo yapısı: `viz_filmler`
--

CREATE TABLE IF NOT EXISTS `viz_filmler` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `filmismi` text NOT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=16 ;

--
-- Tablo döküm verisi `viz_filmler`
--

INSERT INTO `viz_filmler` (`fid`, `filmismi`) VALUES
(14, 'matrix'),
(15, 'av mevsimi');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
