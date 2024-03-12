create table client(

	id bigint not null auto_increment,
	nome varchar(100) not null,
	address varchar(100) not null,

	primary key(id)
);

create table route(

	id bigint not null auto_increment,
	    origin_client_id bigint not null,
	    destination_client_id bigint not null,
    	distance double not null,
    	route_date date,
    	foreign key (origin_client_id) references client (id),
        foreign key (destination_client_id) references client (id),

    	primary key(id)
);


