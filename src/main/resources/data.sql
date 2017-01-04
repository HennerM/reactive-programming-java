INSERT INTO Supplier (name, inventoryApi)
VALUES ('Acme', 'http://localhost:9000/acme/inventory');

INSERT INTO Supplier (name, inventoryApi)
VALUES ('Hofwirt', 'http://localhost:9000/hofwirt/inventory');

INSERT INTO Product (name, supplierId)
VALUES ('Schnitzel', 1);

INSERT INTO Product (name, supplierId)
VALUES ('Schnitzel', 2);

INSERT INTO Product (name, supplierId)
VALUES ('Eistee', 1);

INSERT INTO Product (name, supplierId)
VALUES ('Kekse', 2);