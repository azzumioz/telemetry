DELETE FROM measures;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO measures (temperature, co2, humidity, dateTime)
VALUES (20, 1900, 59, '2015-05-30 10:00:00'),
       (21, 1901, 45, '2015-05-30 10:10:00'),
       (22, 1902, 46, '2015-05-30 10:20:00'),
       (23, 1903, 54, '2015-05-30 10:30:00'),
       (24, 1904, 55, '2015-05-30 10:40:00'),
       (25, 1905, 60, '2015-05-30 10:50:00'),
       (26, 1906, 63, '2015-05-30 11:00:00');

