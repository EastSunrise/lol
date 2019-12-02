update lol.t_config
set value = '1.0'
where name = 'CURRENT_VERSION';

update lol.t_config
set value = 'true'
where name = 'IS_DATABASE_INITIALIZED'
  and region = 5;

update lol.t_config
set value = 'false'
where name = 'IS_DATABASE_INITIALIZED'
  and region = 8;

-- 时区
SHOW VARIABLES LIKE '%time_zone%';
SET GLOBAL time_zone = '+8:00';