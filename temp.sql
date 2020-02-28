CREATE DATABASE IF NOT EXISTS java_akka_workshop;

SHOW databases;

USE java_akka_workshop;
DROP TABLE tickets;
DROP TABLE events;

CREATE TABLE events (
  `id` VARCHAR(10) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  -- phase
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE tickets (
  `id` VARCHAR(10) NOT NULL,
  `event_id` VARCHAR(10) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `price` INT NOT NULL,
  `quantity` INT NOT NULL,
  `start_at` DATETIME NOT NULL,
  `opening_at`  DATETIME NOT NULL,
  -- phase
  PRIMARY KEY(`id`),
  FOREIGN KEY(`event_id`) REFERENCES events(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE users (
  `id` VARCHAR(10) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE orders (
  `id` VARCHAR(10) NOT NULL,
  `ticket_id` VARCHAR(10) NOT NULL,
  `user_id` VARCHAR(10) NOT NULL,
  `quantity` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`ticket_id`) REFERENCES tickets(`id`),
  FOREIGN KEY(`user_id`) REFERENCES users(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SHOW tables;
