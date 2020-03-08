-- USE java_akka_workshop;

CREATE TABLE events (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  -- phase
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE tickets (
  `id` INT NOT NULL AUTO_INCREMENT,
  `event_id` INT NOT NULL,
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
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE orders (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ticket_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`ticket_id`) REFERENCES tickets(`id`),
  FOREIGN KEY(`user_id`) REFERENCES users(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
