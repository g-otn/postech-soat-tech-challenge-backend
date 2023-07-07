-- Registros para a categoria 'Lanche'
INSERT INTO prd_product (deleted, price, quantity, category, creation_date, last_update_date, name)
VALUES (false, 10.99, 5, (SELECT id FROM cat_category WHERE name = 'Lanche'), NOW(), NOW(), 'Hambúrguer');

INSERT INTO prd_product (deleted, price, quantity, category, creation_date, last_update_date, name)
VALUES (false, 8.99, 3, (SELECT id FROM cat_category WHERE name = 'Lanche'), NOW(), NOW(), 'Sanduíche de Frango');

-- Registros para a categoria 'Acompanhamento'
INSERT INTO prd_product (deleted, price, quantity, category, creation_date, last_update_date, name)
VALUES (false, 3.99, 10, (SELECT id FROM cat_category WHERE name = 'Acompanhamento'), NOW(), NOW(), 'Batata Frita');

INSERT INTO prd_product (deleted, price, quantity, category, creation_date, last_update_date, name)
VALUES (false, 2.99, 8, (SELECT id FROM cat_category WHERE name = 'Acompanhamento'), NOW(), NOW(), 'Nuggets');

-- Registros para a categoria 'Bebida'
INSERT INTO prd_product (deleted, price, quantity, category, creation_date, last_update_date, name)
VALUES (false, 4.99, 20, (SELECT id FROM cat_category WHERE name = 'Bebida'), NOW(), NOW(), 'Refrigerante');

INSERT INTO prd_product (deleted, price, quantity, category, creation_date, last_update_date, name)
VALUES (false, 2.49, 15, (SELECT id FROM cat_category WHERE name = 'Bebida'), NOW(), NOW(), 'Suco');

-- Registros para a categoria 'Sobremesa'
INSERT INTO prd_product (deleted, price, quantity, category, creation_date, last_update_date, name)
VALUES (false, 6.99, 5, (SELECT id FROM cat_category WHERE name = 'Sobremesa'), NOW(), NOW(), 'Sorvete');

INSERT INTO prd_product (deleted, price, quantity, category, creation_date, last_update_date, name)
VALUES (false, 4.49, 3, (SELECT id FROM cat_category WHERE name = 'Sobremesa'), NOW(), NOW(), 'Bolo de Chocolate');