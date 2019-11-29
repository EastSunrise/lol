update lol.t_config
set value = '1.0'
where name = 'CURRENT_VERSION';

update lol.t_config
set value = 'false'
where name = 'IS_DATABASE_INITIALIZED'
  and region = 5;

update lol.t_config
set value = 'false'
where name = 'IS_DATABASE_INITIALIZED'
  and region = 8;

update lol.t_event
set status = 0
where type = 0;

select *
from lol.t_event
into dumpfile 'data/kr/event.txt';

