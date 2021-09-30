create database Stefanini;

use Stefanini;

create table dados(
idDados int primary key auto_increment,
Nome varchar(100),
Criticidade	Varchar(10),
Horas int
);

drop table dados;

insert into dados (Nome, Criticidade, Horas)values
('Cross-Site Scripting: DOM',	'Critical',	1),
('Path Manipulation',	'High',	2),
('Unreleased Resource: Streams',	'High'	, 2),
('Portability Flaw: File Separator',	'High'	, 4 ),
('JSON Injection',	'Critical'	,5), 
('Dynamic Code Evaluation: Code Injection',	'Critical'	,4),
('Null Dereference',	'High',	4),
('Portability Flaw: Locale Dependent Comparison',	'High',	6), 
('Access Specifier Manipulation	High', 'High',	8),
('Dynamic Code Evaluation: Unsafe Deserialization'	,'High', 0),
('OGNL Expression Injection: Struts 2',	'Critical',	22); 

select * from dados;


create table teste (

idTeste int primary key auto_increment,
Nome varchar(100),
Quantidade int

);


insert into teste (Nome, Quantidade)values
('Trust Boundary Violation', 89),
('Cross-Site Scripting: Persistent', 76),
('Poor Style: Value Never Read', 71),
('Cross-Site Scripting: Poor Validation', 68),
('System Information Leak: Internal', 62);

select * from teste;

insert into dados (Nome, Criticidade, Horas)values
('Trust Boundary Violation', 'High', 2),
('Cross-Site Scripting: Persistent', 'Low',3),
('Poor Style: Value Never Read', 'High', 4),
('Cross-Site Scripting: Poor Validation', 'Critical', 3),
('System Information Leak: Internal', 'Low', 1);


select t.Nome, d.Criticidade, (t.Quantidade * d.Horas) as TotalHoras
from teste t
inner join dados d
on d.Nome = t.Nome;
 

 