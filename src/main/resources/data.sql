INSERT INTO Supplier (name, inventoryApi)
VALUES ('Acme', 'http://localhost:8080/acme/');

INSERT INTO Supplier (name, inventoryApi)
VALUES ('Hofwirt', 'http://localhost:8080/hofwirt/');

INSERT INTO Product (name, supplierId)
VALUES ('Schnitzel', 1);

INSERT INTO Product (name, supplierId)
VALUES ('Schnitzel', 2);

INSERT INTO Product (name, supplierId)
VALUES ('Eistee', 1);

INSERT INTO Product (name, supplierId)
VALUES ('Kekse', 2);

INSERT INTO Product (name, supplierId)
VALUES ('Kebap', 1);