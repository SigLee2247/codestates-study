
CREATE TABLE `user` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) not NULL,
  `email` varchar(255) not NULL
);

CREATE TABLE `content` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(255) not NULL,
  `body` varchar(255) not NULL,
  `created_at` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
  `userId` int,
  FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
);



CREATE TABLE `category` (
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(255) not NULL
);
CREATE TABLE `content_category` (
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `contentId` int not NULL ,
    `categoryId` int not NULL,
    FOREIGN KEY (`contentId`) REFERENCES `user` (`id`),
    FOREIGN KEY (`categoryId`) REFERENCES `user` (`id`)
);
CREATE TABLE `role` (
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(255)
);
ALTER TABLE `user` ADD roleId int;
ALTER TABLE `user` ADD FOREIGN KEY (`roleId`) REFERENCES `role` (`id`);
