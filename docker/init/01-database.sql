# create databases
CREATE DATABASE IF NOT EXISTS `kcd`;

# create root user and grant rights
CREATE USER 'root'@'localhost' IDENTIFIED BY 'p@ssw0rd';
GRANT ALL ON *.* TO 'root'@'%';
