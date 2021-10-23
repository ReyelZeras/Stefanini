create database Stefanini;

use Stefanini;

create table dados(
idDados int primary key auto_increment,
Nome varchar(100),
Criticidade	Varchar(10),
Horas varchar(20),
solucao varchar(7000)
);

##drop table dados;

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

select * from dados where nome="Cross-Site Scripting: DOM";
desc dados;


create table teste (
idTeste int primary key auto_increment,
Nome varchar(100),
Quantidade varchar(100),
NomeArquivo varchar(100)
);

##drop table teste;

select * from teste;

desc teste;

##select nomeArquivo from teste where nomeArquivo = "";

##insert into dados (Nome, Criticidade, Horas)values
##('Trust Boundary Violation', 'High', 2),
##('Cross-Site Scripting: Persistent', 'Low',3),
##('Poor Style: Value Never Read', 'High', 4),
##('Cross-Site Scripting: Poor Validation', 'Critical', 3),
##('System Information Leak: Internal', 'Low', 1);

select t.Nome, d.Criticidade, (t.Quantidade * d.Horas) as TotalHoras
from teste t
inner join dados d
on d.Nome = t.Nome;

select t.Nome, d.Criticidade, (t.Quantidade * d.Horas) as TotalHoras from teste t inner join dados d on d.Nome = t.Nome where t.nomeArquivo = "massa de teste (1).txt";

select t.Nome, t.Quantidade from teste t where t.Nome not in (select d.Nome from dados d where t.nomeArquivo =  "massa de teste (1).txt"); 

##select t.Nome from dados d inner join teste t on t.Nome = d.Nome where t.nomeArquivo =  "massa de teste (1).txt" 
##and t.Nome not exists (select Nome from dados);

## not exists 
 
update teste set Quantidade = replace(quantidade, ';', '') where idTeste in (1, 2, 3, 4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32);
select idTeste from teste;

## replace

update dados set solucao = 'solucao' where idDados in (6,7,8,9,10,11);