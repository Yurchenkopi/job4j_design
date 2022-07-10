INSERT INTO role
VALUES 
    ('1', 'teacher'),
    ('2', 'student'),
    ('3', 'visitor');
    
INSERT INTO rules
VALUES 
    ('1', 'read only'),
    ('2', 'unlimited');
    
INSERT INTO category
VALUES 
    ('1', 'important'),
    ('2', 'inessential');
    
INSERT INTO state
VALUES 
    ('1', 'under review'),
    ('2', 'verified');
    
INSERT INTO users
VALUES 
    ('1', 'Ivan Ivanov', '1'),
    ('2', 'Kolya Petrov', '2'),
    ('3', 'Senya Sidorov', '3');
    
INSERT INTO role_rules
VALUES 
    ('1', '1', '2'),
    ('2', '2', '1'),
    ('3', '2', '2'),
    ('4', '3', '1');
    
INSERT INTO item
VALUES 
    ('1', 'Introduction', '1', '1', '1'),
    ('2', 'Testing', '2', '2', '2'),
    ('3', 'Final', '3', '2', '2');
    
INSERT INTO comments
VALUES 
    ('1', 'Ok', '1'),
    ('2', 'Need help', '2');
    
INSERT INTO attachs
VALUES 
    ('1', 'Name.jpg', '1'),
    ('2', 'Name.sql', '2');