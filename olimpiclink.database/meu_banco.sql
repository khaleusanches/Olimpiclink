CREATE TABLE `categories` (
  `id_category` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name_category` varchar(255) DEFAULT NULL,
  `url_icon_category` VARCHAR(255),
  PRIMARY KEY (`id_category`)
);

CREATE TABLE `cities` (
  `id_city` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name_city` varchar(255) DEFAULT NULL,
  `uf_city` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id_city`)
);
CREATE TABLE `users` (
  `id_user` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `activated_user` tinyint(1) DEFAULT 1,
  `name_user` varchar(255) DEFAULT NULL,
  `login_user` varchar(255) DEFAULT NULL,
  `password_user` varchar(255) DEFAULT NULL,
  `email_user` varchar(255) DEFAULT NULL,
  `profile_picture_user` mediumblob DEFAULT NULL,
  `created_at_user` timestamp NULL DEFAULT current_timestamp(),
  `updated_at_user` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `url_profile_picture_user` varchar(90) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
);

CREATE TABLE `places` (
  `id_place` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `city_id` int(10) unsigned NOT NULL,
  `name_place` varchar(255) DEFAULT NULL,
  `description_place` text DEFAULT NULL,
  `opening_time_place` time DEFAULT NULL,
  `closing_time_place` time DEFAULT NULL,
  `created_at_place` timestamp NULL DEFAULT current_timestamp(),
  `updated_at_place` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id_place`),
  KEY `city_id` (`city_id`),
  CONSTRAINT `places_ibfk_1` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id_city`)
);

CREATE TABLE `comunities` (
  `id_comunity` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name_comunity` varchar(255) DEFAULT NULL,
  `description_comunity` text DEFAULT NULL,
  `icon_comunity` mediumblob DEFAULT NULL,
  `banner_comunity` mediumblob DEFAULT NULL,

  `url_icon_comunity` varchar(255) default null,
  `url_banner_comunity` varchar(255) default null,
  `category_id` int unsigned default null,
  `regras_comunity` varchar(500) default null,
  
  `created_at_comunities` timestamp NULL DEFAULT current_timestamp(),
  `updated_at_comunities` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id_comunity`)
);

CREATE TABLE `leaders` (
  `id_leader` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_leader`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `leaders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`)
);

CREATE TABLE `events` (
  `idEvent` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `place_id` int(10) unsigned NOT NULL,
  `endereco` varchar(255) not null,
  `comunity_id` int(10) unsigned NOT NULL,
  `leader_id` int(10) NOT NULL,
  `nameEvent` varchar(255) DEFAULT NULL,
  `descriptionEvent` text DEFAULT NULL,
  `dateTimeEvent` datetime DEFAULT NULL,
  `closingDateTimeEvent` datetime DEFAULT NULL,
  `created_at_event` datetime not null,
  `updated_at_event` datetime not null,
  PRIMARY KEY (`idEvent`),
  KEY `comunity_id` (`comunity_id`),
  KEY `place_id` (`place_id`),
  CONSTRAINT `events_ibfk_1` FOREIGN KEY (`comunity_id`) REFERENCES `comunities` (`id_comunity`),
  CONSTRAINT `events_ibfk_2` FOREIGN KEY (`place_id`) REFERENCES `places` (`id_place`),
  CONSTRAINT `events_ibfk_3` foreign key (`leader_id`) references `leaders`(`id_leader`)
);

CREATE TABLE `pictures_events` (
  `id_picture_event` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(10) unsigned NOT NULL,
  `archive_picture_event` mediumblob NOT NULL,
  PRIMARY KEY (`id_picture_event`),
  KEY `event_id` (`event_id`),
  CONSTRAINT `pictures_events_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `events` (`idEvent`)
);

CREATE TABLE `publications` (
  `id_publication` int(11) NOT NULL AUTO_INCREMENT,
  `text_publication` text NOT NULL,
  `image_one_publication` mediumblob DEFAULT NULL,
  `image_two_publication` mediumblob DEFAULT NULL,
  `image_three_publication` mediumblob DEFAULT NULL,
  `image_four_publication` mediumblob DEFAULT NULL,
  `comunity_id` int(10) unsigned DEFAULT NULL,
  `place_id` int(10) unsigned DEFAULT NULL,
  `event_id` int(10) unsigned DEFAULT NULL,
  `date_publication` timestamp NULL DEFAULT current_timestamp(),
  `user_id` int(10) unsigned DEFAULT NULL,
  `url_image_one_publication` varchar(60) DEFAULT NULL,
  `url_image_two_publication` varchar(60) DEFAULT NULL,
  `url_image_three_publication` varchar(60) DEFAULT NULL,
  `url_image_four_publication` varchar(60) DEFAULT NULL,
  `activated_publication` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_publication`),
  KEY `comunity_id` (`comunity_id`),
  KEY `place_id` (`place_id`),
  KEY `event_id` (`event_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `publications_ibfk_1` FOREIGN KEY (`comunity_id`) REFERENCES `comunities` (`id_comunity`),
  CONSTRAINT `publications_ibfk_2` FOREIGN KEY (`place_id`) REFERENCES `places` (`id_place`),
  CONSTRAINT `publications_ibfk_3` FOREIGN KEY (`event_id`) REFERENCES `events` (`idEvent`),
  CONSTRAINT `publications_ibfk_4` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`)
);

CREATE TABLE `categories_has_publications` (
  `publication_id` int(10) NOT NULL,
  `category_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`publication_id`,`category_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `categories_has_publications_ibfk_1` FOREIGN KEY (`publication_id`) REFERENCES `publications` (`id_publication`),
  CONSTRAINT `categories_has_publications_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id_category`)
);

CREATE TABLE `category_event` (
  `category_id` int(10) unsigned NOT NULL,
  `Event_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`category_id`,`Event_id`),
  KEY `Event_id` (`Event_id`),
  CONSTRAINT `category_event_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id_category`),
  CONSTRAINT `category_event_ibfk_2` FOREIGN KEY (`Event_id`) REFERENCES `events` (`idEvent`)
);

CREATE TABLE `comunity_has_category` (
  `comunity_id` int(10) unsigned NOT NULL,
  `category_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`comunity_id`,`category_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `comunity_has_category_ibfk_1` FOREIGN KEY (`comunity_id`) REFERENCES `comunities` (`id_comunity`),
  CONSTRAINT `comunity_has_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id_category`)
);

CREATE TABLE `comunity_has_leader` (
  `comunity_id` int(10) unsigned NOT NULL,
  `leader_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`comunity_id`,`leader_id`),
  KEY `leader_id` (`leader_id`),
  CONSTRAINT `comunity_has_leader_ibfk_1` FOREIGN KEY (`comunity_id`) REFERENCES `comunities` (`id_comunity`),
  CONSTRAINT `comunity_has_leader_ibfk_2` FOREIGN KEY (`leader_id`) REFERENCES `leaders` (`id_leader`)
);

CREATE TABLE `marked_presences` (
  `id_marked_presence` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `event_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_marked_presence`),
  KEY `user_id` (`user_id`),
  KEY `event_id` (`event_id`),
  CONSTRAINT `marked_presences_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`),
  CONSTRAINT `marked_presences_ibfk_2` FOREIGN KEY (`event_id`) REFERENCES `events` (`idEvent`)
);

CREATE TABLE `places_has_category` (
  `place_id` int(10) unsigned NOT NULL,
  `category_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`place_id`,`category_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `places_has_category_ibfk_1` FOREIGN KEY (`place_id`) REFERENCES `places` (`id_place`),
  CONSTRAINT `places_has_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id_category`)
);

CREATE TABLE `user_category` (
   `id_user_category` int(10) unsigned auto_increment primary key,
  `user_id` int(10) unsigned NOT NULL,
  `category_id` int(10) unsigned NOT NULL,
  KEY `user_id` (`user_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `user_category_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`),
  CONSTRAINT `user_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id_category`)
);

CREATE TABLE `user_city` (
  `user_id` int(10) unsigned NOT NULL,
  `city_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`city_id`),
  KEY `city_id` (`city_id`),
  CONSTRAINT `user_city_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`),
  CONSTRAINT `user_city_ibfk_2` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id_city`)
);

CREATE TABLE `follow_comunity` (
  `id_follow_comunity` int(10) unsigned primary key auto_increment,
  `user_id` int(10) unsigned NOT NULL,
  `comunity_id` int(10) unsigned NOT NULL,
  KEY `user_id` (`user_id`),
  KEY `comunity_id` (`comunity_id`),
  CONSTRAINT `follow_comunity_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`),
  CONSTRAINT `follow_comunity_ibfk_2` FOREIGN KEY (`comunity_id`) REFERENCES `comunities` (`id_comunity`),
  UNIQUE KEY `unique_follow_comunity` (`user_id`, `comunity_id`)
);

CREATE TABLE `participation_comunity` (
  `id_participation_comunity` int(10) unsigned primary key auto_increment,
  `user_id` int(10) unsigned NOT NULL,
  `comunity_id` int(10) unsigned NOT NULL,
  KEY `user_id` (`user_id`),
  KEY `comunity_id` (`comunity_id`),
  CONSTRAINT `participation_comunity_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`),
  CONSTRAINT `participation_comunity_ibfk_2` FOREIGN KEY (`comunity_id`) REFERENCES `comunities` (`id_comunity`),
  UNIQUE KEY `unique_participation_comunity` (`user_id`, `comunity_id`)
);

CREATE TABLE `request_participation_comunity` (
  `request_participation_comunity` int(10) unsigned primary key auto_increment,
  `user_id` int(10) unsigned NOT NULL,
  `comunity_id` int(10) unsigned NOT NULL,
  `analisado` bool default false,
  `acepted` bool default null,
  KEY `user_id` (`user_id`),
  KEY `comunity_id` (`comunity_id`),
  CONSTRAINT `request_participation_comunity_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`),
  CONSTRAINT `request_participation_comunity_ibfk_2` FOREIGN KEY (`comunity_id`) REFERENCES `comunities` (`id_comunity`),
  UNIQUE KEY `unique_request_participation_comunity` (`user_id`, `comunity_id`)
);

CREATE TABLE `user_followers` (
  `id_user_follower` int unsigned auto_increment primary key,
  `user_id` int(10) unsigned NOT NULL,
  `follower_id` int(10) unsigned NOT NULL,
  key `user_id` (`user_id`),
  KEY `follower_id` (`follower_id`),
  CONSTRAINT `user_followers_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`),
  CONSTRAINT `user_followers_ibfk_2` FOREIGN KEY (`follower_id`) REFERENCES `users` (`id_user`)
);

CREATE TABLE `user_follows` (
    `id_user_follow` INT(10) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT(10) UNSIGNED NOT NULL,
    `follow_id` INT(10) UNSIGNED NOT NULL, 
    CONSTRAINT `FK_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`), 
    CONSTRAINT `FK_follow` FOREIGN KEY (`follow_id`) REFERENCES `users` (`id_user`),
    KEY `follow_id` (`follow_id`) 
);

CREATE TABLE `user_places` (
  `user_id` int(10) unsigned NOT NULL,
  `place_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`place_id`),
  KEY `place_id` (`place_id`),
  CONSTRAINT `user_places_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`),
  CONSTRAINT `user_places_ibfk_2` FOREIGN KEY (`place_id`) REFERENCES `places` (`id_place`)
);

create table `reported_publications`(
	id_report_publication int primary key auto_increment,
    publication_id int not null,
    user_id int unsigned not null,
    reason varchar(300) not null,
    created_at_report_publication datetime not null,
    report_read bool not null,
    foreign key (publication_id) references `publications`(`id_publication`),
    foreign key (user_id) references `users`(`id_user`)
);

create table `reported_events`(
	id_report_events int primary key auto_increment,
    events_id int unsigned not null,
    user_id int unsigned not null,
    reason varchar(300) not null,
    created_at_report_events datetime not null,
    report_read bool not null,
    foreign key (events_id) references `events`(`idEvent`),
    foreign key (user_id) references `users`(`id_user`)
);

