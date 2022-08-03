CREATE TABLE session (
  id char(36) NOT NULL,
   close_date datetime NOT NULL,
   created_at datetime NOT NULL,
   CONSTRAINT pk_session PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE agenda (
  id char(36) NOT NULL,
   title VARCHAR(255) NOT NULL,
   `description` VARCHAR(255) NOT NULL,
   created_at datetime NOT NULL,
   session_entity_id char(36) NULL,
   CONSTRAINT pk_agenda PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE vote (
  id char(36) NOT NULL,
   associate_id VARCHAR(255) NOT NULL,
   created_at datetime NOT NULL,
   vote_decision INT NOT NULL,
   agenda_entity_id char(36) NULL,
   CONSTRAINT pk_vote PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

