CREATE DATABASE IF NOT EXISTS TravelOffice;
USE TravelOffice;

CREATE TABLE IF NOT EXISTS Hotel (
    id_hotel BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    city VARCHAR(50),
    rating TINYINT
) CHARACTER SET = cp1250;

CREATE TABLE IF NOT EXISTS Trip (
    id_trip BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    from_country VARCHAR(50),
    to_country VARCHAR(50),
    arrival_dateTime DATETIME(0),
    departure_dateTime DATETIME(0),
    duration TINYINT,
    price INT,
    free_seats TINYINT,
    active TINYINT,
    photo_url TEXT,
    id_hotel BIGINT UNSIGNED,
    FOREIGN KEY (id_hotel) REFERENCES Hotel(id_hotel)
) CHARACTER SET = cp1250;

CREATE TABLE IF NOT EXISTS User (
    id_user BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50) UNIQUE,
    password TEXT
) CHARACTER SET = cp1250;

CREATE TABLE IF NOT EXISTS Bought_trip (
    id_bought_trips BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_user BIGINT UNSIGNED,
    id_trip BIGINT UNSIGNED,
    seats TINYINT UNSIGNED,
    insurance TINYINT UNSIGNED,
    purchase_dateTime DATETIME(0),
    FOREIGN KEY (id_user) REFERENCES User(id_user),
    FOREIGN KEY (id_trip) REFERENCES Trip(id_trip)
) CHARACTER SET = cp1250;

INSERT INTO user (user_name, password) VALUES('admin', 'admin');

INSERT INTO hotel (name, city, rating) VALUES
('Brown Beach House', 'Trogir', 3),
('Hotel Park - Potestas Ltd.', 'Split', 4),
('Eurostars Gran Hotel A Toxa', 'Pontevedra', 5),
('Hotel Riviera', 'Włochy, Rzym', 4),
('Hotel Olympus', 'Grecja, Ateny', 5),
('Hotel Lisbon', 'Portugalia, Lizbona', 4),
('Hotel Fjord', 'Norwegia, Bergen', 5),
('Hotel Pyramid', 'Egipt, Kair', 5),
('Hotel Bosphorus', 'Turcja, Stambuł', 4);


INSERT INTO trip (from_country, to_country, arrival_dateTime, departure_dateTime, duration, price, free_seats, active, photo_url, id_hotel)
VALUES
('Polska', 'Hiszpania', '2026-07-20 12:30:00', '2026-07-27 15:30:00', 7, 250000, 54, 1, 'https://traveltalks.esky.pl/wp-content/uploads/2024/04/Widok-na-Peniscola-ze-szczytu-zamku-papieza-Luny-w-Walencji-Hiszpania-%C2%A9-Shutterstock.jpg', 1),
('Polska', 'Chorwacja', '2026-08-17 12:30:00', '2026-08-27 15:30:00', 10, 250000, 43, 1, 'https://www.eccoholiday.com/p/wp-content/uploads/2020/03/chorwacja-1.jpg', 2),
('Polska', 'Chorwacja', '2026-09-13 12:30:00', '2026-09-27 15:30:00', 14, 250000, 33, 1, 'https://www.eccoholiday.com/p/wp-content/uploads/2020/03/chorwacja-1.jpg', 3),
('Polska', 'Włochy', '2026-07-25 10:00:00', '2026-08-05 18:00:00', 11, 270000, 48, 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTlZW_JQdjALC4cFO8RQId-N6D1sfbIph3moQ&s', 4),
('Polska', 'Grecja', '2026-08-01 09:00:00', '2026-08-10 20:00:00', 9, 260000, 50, 1, 'https://media.brate.com/images/europa/grecja/grecja.jpeg?tr=n-hero', 5),
('Polska', 'Portugalia', '2026-09-05 11:00:00', '2026-09-15 19:00:00', 10, 280000, 35, 1, 'https://fly.pl/wp-content/uploads/2017/03/Portugalia-Algarve.jpg', 6),
('Polska', 'Norwegia', '2026-07-15 08:00:00', '2026-07-22 17:00:00', 7, 300000, 40, 1, 'https://traveltalks.esky.pl/wp-content/uploads/2024/05/Wioska-Reine-Norwegia-%C2%A9-Shutterstock.jpg', 7),
('Polska', 'Egipt', '2026-08-20 07:30:00', '2026-08-30 22:00:00', 10, 320000, 45, 1, 'https://www.gdzie-i-kiedy.pl/site/images/illustration/egypte_555.jpg', 8),
('Polska', 'Turcja', '2026-09-10 06:30:00', '2026-09-20 21:00:00', 10, 310000, 38, 1, 'https://wakacjeamigos.pl/uploads/blog/retina_3611257e-c39a-41f1-81b4-0d5422551d60.jpg', 9);