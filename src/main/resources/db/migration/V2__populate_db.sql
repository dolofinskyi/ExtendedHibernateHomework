INSERT INTO client(name) VALUES
('Taras'),
('George'),
('Anna'),
('Anton'),
('Denys'),
('Max'),
('Bob'),
('John'),
('Joe'),
('Maryna');

INSERT INTO planet(id, name) VALUES
('MARS0', 'Mars'),
('VEN0','Venus'),
('EARTH0','Earth'),
('SAT0','Saturn'),
('NEP0','Neptune'),
('JUP0','Jupiter'),
('MERC0','Mercury');

INSERT INTO ticket(created_at, client_id, from_planet_id, to_planet_id) VALUES
('2002-09-25', 1, 'MARS0', 'SAT0'),
('1990-03-20', 2, 'VEN0', 'NEP0'),
('1987-05-21', 4, 'EARTH0', 'MERC0'),
('1989-01-10', 5, 'SAT0', 'EARTH0'),
('2023-09-13', 3, 'NEP0', 'MARS0'),
('1997-04-13', 8, 'JUP0', 'MERC0'),
('1985-02-06', 7, 'MERC0', 'JUP0'),
('1985-02-06', 9, 'EARTH0', 'NEP0'),
('1985-02-06', 4, 'VEN0', 'VEN0'),
('1985-02-06', 2, 'MERC0', 'NEP0'),
('1985-02-06', 6, 'MARS0', 'EARTH0'),
('1985-02-06', 1, 'NEP0', 'MERC0');