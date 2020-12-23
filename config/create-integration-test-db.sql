--
--  Template for creating a new SQL Server Database appropriate for running ITs
--

-- Create application and flyway logins (e.g. for user 'demouser')
CREATE LOGIN demouser with password='sup3rSEC'
CREATE LOGIN demouser_ddl with password='ev3nSecreter'

-- Create application database
CREATE DATABASE Spring_Demo_IT
USE Spring_Demo_IT

-- Create application user
CREATE USER demouser for login demouser
EXEC sp_addrolemember 'db_datawriter', demouser
EXEC sp_addrolemember 'db_datareader', demouser
GRANT EXECUTE TO demouser

-- Create flyway user
CREATE USER demouser_ddl for login demouser_ddl
EXEC sp_addrolemember 'db_ddladmin', demouser_ddl
EXEC sp_addrolemember 'db_datawriter', demouser_ddl
EXEC sp_addrolemember 'db_datareader', demouser_ddl
