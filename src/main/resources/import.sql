

-- Inserção de produtos
Insert into produto (nome, descricao) values ('Maça', 'Maça fresca');
Insert into produto (nome, descricao) values ('Banana', 'Banana fresca');
Insert into produto (nome, descricao) values ('Iogurte', 'Iogurte bebida láctea');
Insert into produto (nome, descricao) values ('Manteiga', 'Manteiga de origem animal');
Insert into produto (nome, descricao) values ('Sorvete', 'Sorvete napolitano');
Insert into produto (nome, descricao) values ('Asas de Frango', 'Asas de frango ja temperadas');

-- Inserção de vendedores
Insert into vendedor (nome) values ('Julio');
Insert into vendedor (nome) values ('Manuel');
Insert into vendedor (nome) values ('Clara');
Insert into vendedor (nome) values ('Zoe');

-- Inserção de anuncios
Insert into anuncio (vendedor_id, produto_id, preco, volume, temperatura_maxima, temperatura_minima, tipo) values (1, 1, 5, 0.0001, 25, 10, 0);
Insert into anuncio (vendedor_id, produto_id, preco, volume, temperatura_maxima, temperatura_minima, tipo) values (1, 3, 12, 0.001, 18, 5, 1);
Insert into anuncio (vendedor_id, produto_id, preco, volume, temperatura_maxima, temperatura_minima, tipo) values (2, 2, 6, 0.0002, 25, 15, 0);
Insert into anuncio (vendedor_id, produto_id, preco, volume, temperatura_maxima, temperatura_minima, tipo) values (2, 6, 5, 0.002, 5, -15, 2);
Insert into anuncio (vendedor_id, produto_id, preco, volume, temperatura_maxima, temperatura_minima, tipo) values (3, 4, 7, 0.0001, 15, 2, 1);
Insert into anuncio (vendedor_id, produto_id, preco, volume, temperatura_maxima, temperatura_minima, tipo) values (3, 2, 6.5, 0.0002, 25, 15, 0);
Insert into anuncio (vendedor_id, produto_id, preco, volume, temperatura_maxima, temperatura_minima, tipo) values (4, 5, 23, 0.0015, 5, -15, 2);
Insert into anuncio (vendedor_id, produto_id, preco, volume, temperatura_maxima, temperatura_minima, tipo) values (4, 3, 13, 0.001, 18, 5, 1);

-- Inserção de armazem
Insert into armazem (nome, CEP) values ('CD_Louveira', '00100-100');
Insert into armazem (nome, CEP) values ('CD_Guarulhos', '07215-010');

-- Inserção de representantes
Insert into representante (armazem_id, nome) values (1, 'Ismael');
Insert into representante (armazem_id, nome) values (2, 'Nathan');

-- Inserção de setor
Insert into setor (armazem_id, nome, volume) values (1, 'Fresco', 1000);
Insert into setor (armazem_id, nome, volume) values (1, 'Refrigerado', 1000);
Insert into setor (armazem_id, nome, volume) values (1, 'Congelado', 1000);
Insert into setor (armazem_id, nome, volume) values (2, 'Fresco', 500);
Insert into setor (armazem_id, nome, volume) values (2, 'Refrigerado', 500);
Insert into setor (armazem_id, nome, volume) values (2, 'Congelado', 500);

-- Inserção de ordem_entrada
Insert into ordem_entrada (setor_id, data_criacao) values (1, '2022-01-27 11:00:00');
Insert into ordem_entrada (setor_id, data_criacao) values (2, '2022-01-27 12:00:00');
Insert into ordem_entrada (setor_id, data_criacao) values (1, '2022-01-28 08:00:00');
Insert into ordem_entrada (setor_id, data_criacao) values (3, '2022-01-29 07:00:00');
Insert into ordem_entrada (setor_id, data_criacao) values (2, '2022-01-30 10:00:00');
Insert into ordem_entrada (setor_id, data_criacao) values (1, '2022-02-01 17:00:00');
Insert into ordem_entrada (setor_id, data_criacao) values (3, '2022-02-02 16:00:00');
Insert into ordem_entrada (setor_id, data_criacao) values (2, '2022-02-03 14:00:00');
Insert into ordem_entrada (setor_id, data_criacao) values (4, '2022-02-01 17:00:00');
Insert into ordem_entrada (setor_id, data_criacao) values (5, '2022-02-02 16:00:00');
Insert into ordem_entrada (setor_id, data_criacao) values (6, '2022-02-03 14:00:00');

-- Inserção de estoques
Insert into estoque (anuncio_id, ordem_entrada_id, quantidade_inicial, quantidade_atual, temperatura_atual, data_validade, data_producao) values (1, 1, 1000, 1000, 20, '2022-02-15', '2022-01-27 11:00:00');
Insert into estoque (anuncio_id, ordem_entrada_id, quantidade_inicial, quantidade_atual, temperatura_atual, data_validade, data_producao) values (1, 1, 1000, 1000, 20, '2022-02-10', '2022-01-27 11:00:00');
Insert into estoque (anuncio_id, ordem_entrada_id, quantidade_inicial, quantidade_atual, temperatura_atual, data_validade, data_producao) values (1, 1, 1000, 1000, 20, '2022-03-01', '2022-01-27 11:00:00');
Insert into estoque (anuncio_id, ordem_entrada_id, quantidade_inicial, quantidade_atual, temperatura_atual, data_validade, data_producao) values (2, 2, 100, 100, 10, '2022-10-27', '2022-01-25 12:10:00');
Insert into estoque (anuncio_id, ordem_entrada_id, quantidade_inicial, quantidade_atual, temperatura_atual, data_validade, data_producao) values (3, 3, 800, 800, 18, '2022-02-20', '2022-01-24 08:03:00');
Insert into estoque (anuncio_id, ordem_entrada_id, quantidade_inicial, quantidade_atual, temperatura_atual, data_validade, data_producao) values (4, 4, 500, 500, -3, '2022-10-20', '2022-01-25 15:55:00');
Insert into estoque (anuncio_id, ordem_entrada_id, quantidade_inicial, quantidade_atual, temperatura_atual, data_validade, data_producao) values (5, 5, 1500, 1500, 3, '2022-07-05', '2022-01-20 07:34:00');
Insert into estoque (anuncio_id, ordem_entrada_id, quantidade_inicial, quantidade_atual, temperatura_atual, data_validade, data_producao) values (6, 6, 3000, 3000, 17, '2022-02-23', '2022-01-28 14:14:00');
Insert into estoque (anuncio_id, ordem_entrada_id, quantidade_inicial, quantidade_atual, temperatura_atual, data_validade, data_producao) values (7, 7, 350, 350, -5, '2022-11-17', '2022-01-25 22:15:00');
Insert into estoque (anuncio_id, ordem_entrada_id, quantidade_inicial, quantidade_atual, temperatura_atual, data_validade, data_producao) values (8, 8, 500, 500, 7, '2022-12-10', '2022-01-27 16:44:00');

Insert into estoque (anuncio_id, ordem_entrada_id, quantidade_inicial, quantidade_atual, temperatura_atual, data_validade, data_producao) values (1, 9, 200, 200, 20, '2022-03-15', '2022-01-27 11:00:00');
Insert into estoque (anuncio_id, ordem_entrada_id, quantidade_inicial, quantidade_atual, temperatura_atual, data_validade, data_producao) values (5, 10, 500, 500, 3, '2022-02-21', '2022-01-20 07:34:00');
Insert into estoque (anuncio_id, ordem_entrada_id, quantidade_inicial, quantidade_atual, temperatura_atual, data_validade, data_producao) values (7, 11, 350, 350, -5, '2022-11-17', '2022-01-25 22:15:00');

--Inserção de comprador
-- Insert into comprador (nome) values ('Fernando Ventura');
-- Insert into comprador (nome) values ('Marcos Augusto');
-- Insert into comprador (nome) values ('Maria Ferreira');
-- Insert into comprador (nome) values ('Carlos Henrique');
-- Insert into comprador (nome) values ('Constatino Augusto');
-- Insert into comprador (nome) values ('Camila Pitanga');
-- Insert into comprador (nome) values ('Toni Ferreira');
-- Insert into comprador (nome) values ('Augusto Ventura');
-- Insert into comprador (nome) values ('Ventura Ferreira');


--Inserção de usuario
Insert into usuario(username, enabled, password) values ('lucian', true, '$2a$10$tzz.cCKJZz9nFCu.KT8Dcumnj9CR58Owwx/.VPhMqrQ4rt.EJRQhG' );
Insert into usuario(username, enabled, password) values ('iohara', true, '$2a$10$tzz.cCKJZz9nFCu.KT8Dcumnj9CR58Owwx/.VPhMqrQ4rt.EJRQhG' );
Insert into usuario(username, enabled, password) values ('nathan', true, '$2a$10$tzz.cCKJZz9nFCu.KT8Dcumnj9CR58Owwx/.VPhMqrQ4rt.EJRQhG' );
Insert into usuario(username, enabled, password) values ('ismael', true, '$2a$10$tzz.cCKJZz9nFCu.KT8Dcumnj9CR58Owwx/.VPhMqrQ4rt.EJRQhG' );
Insert into usuario(username, enabled, password) values ('vanessa', true, '$2a$10$tzz.cCKJZz9nFCu.KT8Dcumnj9CR58Owwx/.VPhMqrQ4rt.EJRQhG' );

--Inserção de comprador
Insert into comprador (usuario_username) values ('lucian');
Insert into comprador (usuario_username) values ('iohara');
Insert into comprador (usuario_username) values ('nathan');
Insert into comprador (usuario_username) values ('ismael');
Insert into comprador (usuario_username) values ('vanessa');

--Inserção de perfil
Insert into perfil (nome) VALUES ('Representante');
Insert into perfil (nome) VALUES ('Comprador');
Insert into perfil (nome) VALUES ('Vendedor');

--Inserção de perfil_usuario
Insert into perfil_usuario (perfil_id, username) values (1, 'lucian');
Insert into perfil_usuario (perfil_id, username) values (2, 'iohara');
Insert into perfil_usuario (perfil_id, username) values (2, 'nathan');
Insert into perfil_usuario (perfil_id, username) values (3, 'ismael');
Insert into perfil_usuario (perfil_id, username) values (1, 'vanessa');


-- Inserção de Carrinho_comprador
Insert into carrinho (comprador_id, data_criacao, status) values (1, '2021-01-27 11:00:00', 0);
Insert into carrinho (comprador_id, data_criacao, status) values (1, '2021-01-25 11:00:00', 0);
Insert into carrinho (comprador_id, data_criacao, status) values (1, '2021-01-17 11:00:00', 0);
Insert into carrinho (comprador_id, data_criacao, status) values (2, '2020-01-27 11:00:00', 0);
Insert into carrinho (comprador_id, data_criacao, status) values (2, '2022-01-17 11:00:00', 0);
Insert into carrinho (comprador_id, data_criacao, status) values (3, '2020-01-17 11:00:00', 0);
Insert into carrinho (comprador_id, data_criacao, status) values (3, '2022-01-17 11:00:00', 0);
Insert into carrinho (comprador_id, data_criacao, status) values (4, '2021-01-17 11:00:00', 0);
Insert into carrinho (comprador_id, data_criacao, status) values (5, '2022-01-27 11:00:00', 0);
Insert into carrinho (comprador_id, data_criacao, status) values (5, '2020-01-27 11:00:00', 0);

--Inserção de Item_carrinho
Insert into item_carrinho (anuncio_id, carrinho_id, quantidade, preco) values (1, 1, 10, 5);
Insert into item_carrinho (anuncio_id, carrinho_id, quantidade, preco) values (1, 1, 12, 12);
Insert into item_carrinho (anuncio_id, carrinho_id, quantidade, preco) values (2, 1, 10, 12);
Insert into item_carrinho (anuncio_id, carrinho_id, quantidade, preco) values (2, 1, 4, 12);
Insert into item_carrinho (anuncio_id, carrinho_id, quantidade, preco) values (3, 1, 15, 7);
Insert into item_carrinho (anuncio_id, carrinho_id, quantidade, preco) values (3, 1, 10, 7);
Insert into item_carrinho (anuncio_id, carrinho_id, quantidade, preco) values (3, 1, 22, 7);
Insert into item_carrinho (anuncio_id, carrinho_id, quantidade, preco) values (3, 1, 11, 7);
Insert into item_carrinho (anuncio_id, carrinho_id, quantidade, preco) values (4, 1, 12, 23);
Insert into item_carrinho (anuncio_id, carrinho_id, quantidade, preco) values (4, 1, 12, 23);
Insert into item_carrinho (anuncio_id, carrinho_id, quantidade, preco) values (4, 1, 10, 23);