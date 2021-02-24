-- Tables
CREATE TABLE brand (
    id   INT NOT NULL PRIMARY KEY,
    name TEXT
);

CREATE TABLE beer (
    id       INT NOT NULL PRIMARY KEY,
    brand_id INT NOT NULL CONSTRAINT fk_beer_brand REFERENCES brand,
    type     TEXT
);

CREATE TABLE message (
    id      INT NOT NULL PRIMARY KEY,
    beer_id INT NOT NULL CONSTRAINT fk_message_beer REFERENCES beer,
    value   TEXT
);

-- Init insert
INSERT INTO brand (id, name)
VALUES (1, 'Brewdog'),
       (2, 'Paulaner');

INSERT INTO beer (id, brand_id, type)
VALUES (1, 1, 'IPA'),
       (2, 2, 'Weizenbier');

INSERT INTO  message (id, beer_id, value)
VALUES (1, 1, 'Nice beer'),
       (2, 2, 'Good beer'),
       (3, 1, 'Bitter beer'),
       (4, 2, 'Heavy beer');
