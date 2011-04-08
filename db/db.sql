/*
Using MySQL 5.1
*/
CREATE DATABASE `feed_dataset`

use feed_dataset;

CREATE TABLE `feed_entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uri` varchar(500) NOT NULL,
  `author` varchar(500) DEFAULT NULL,
  `title` varchar(500) NOT NULL,
  `link` varchar(500) NOT NULL,
  `updated_date` timestamp NULL DEFAULT NULL,
  `published_date` timestamp NULL DEFAULT NULL,
  `content` text NOT NULL,
  `enter_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM

ALTER TABLE `feed_dataset`.`feed_entry` 
ADD UNIQUE INDEX `unique_uri_link_inx` (`uri` ASC, `link` ASC) ;

CREATE TABLE `entry_category` (
  `entry_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=MyISAM
 
 

/*This table is optional if storing feed URLs in the DB*
CREATE  TABLE `feed_dataset`.`feed_urls` (
  `name` VARCHAR(500) NOT NULL ,
  `url` VARCHAR(800) NOT NULL ,
  `rss_url` VARCHAR(800) NOT NULL ,
  `when` INT NULL ,
  `parsed` CHAR(1) NOT NULL ,
  `enter_date` TIMESTAMP NOT NULL );

