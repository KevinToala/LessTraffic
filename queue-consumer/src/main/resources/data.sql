insert into events(id, topic, description) values
  (1, 'ENQUEUE_GEOLOCATION_NODE', 'Topic para el encolamiento de puntos de geolocalización');

insert into actions(event_id, id, ordinal, method, endpoint, template) values
  (1, 1, 2, 'POST', 'api/geolocation/insertNode', '{"latitude":${lat},"longitude":${lng},"cont":"${content}"}'),
  (1, 2, 1, 'GET', 'api/geolocation/hello/${lat}', null);

/*
insert into actions(event_id, id, ordinal, method, endpoint, template) values
  (1, 1, 1, 'POST', 'api/geolocation/insertNode', '{"latitude":${lat},"longitude":${lng}}');
*/