DROP TABLE IF EXISTS measures;
DROP TABLE IF EXISTS weather;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE measures
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    temperature DOUBLE PRECISION                  NOT NULL,
    co2         INT                               NOT NULL,
    humidity    DOUBLE PRECISION                  NOT NULL,
    dateTime    TIMESTAMP           DEFAULT now() NOT NULL
);

CREATE TABLE weather
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name        VARCHAR                           NOT NULL,
    temperature DOUBLE PRECISION                  NOT NULL,
    humidity    DOUBLE PRECISION                  NOT NULL,
    pressure    INT                               NOT NULL,
    icon        VARCHAR                           NOT NULL,
    rain        DOUBLE PRECISION                  NOT NULL,
    description VARCHAR                           NOT NULL,
    clouds      INT                               NOT NULL,
    wind_speed  DOUBLE PRECISION                  NOT NULL,
    wind_deg    INT                               NOT NULL,
    dateTime    TIMESTAMP           DEFAULT now() NOT NULL,
    sunrise     TIMESTAMP                         NOT NULL,
    sunset      TIMESTAMP                         NOT NULL
);
