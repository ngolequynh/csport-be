-- insert query
insert into account (account_id, email, social_id, user_name, credentials_updated) values ('1','emaila@gmail.com','12345678','corey', extract(epoch FROM now()) * 1000),('2','emailb@gmail.com','987654321','hans', extract(epoch FROM now()) * 1000),('3','emailc@gmail.com','123789456','hai', extract(epoch FROM now()) * 1000),('4','emaild@gmail.com','12312789456','hiệp', extract(epoch FROM now()) * 1000);
insert into activity (activity_id, title, location, account_id, duration, is_active, created_date, last_modified_date, activity_date, distance, workout_type, activity_type) values ('1', 'titlea', 'loca', '1', '1.5' ,true, current_timestamp, current_timestamp,'2001-02-16 20:38:40','2.5',null,'0'), ('2', 'titleb', 'locb', '1', '12.0' ,true, current_timestamp,current_timestamp,'2001-02-16 20:38:40','5',null,'0'), ('3', 'titlec', 'locc', '2', '1.6' ,true, current_timestamp, current_timestamp,'2001-02-16 20:38:40','10',null,'1'), ('4', 'titled', 'locd', '3', '2.5' ,true, current_timestamp, current_timestamp,'2001-02-16 20:38:40',null,'Cardio','2');
insert into profile (profile_id, full_name,created_date, image, last_modified_date, account_id,introduction, hobbies, status) values ('1','Nguyen Van A',current_timestamp ,'https://s3.amazonaws.com/uifaces/faces/twitter/_williamguerra/128.jpg', current_timestamp , '1','Good student','Creating bugs',2),('2','Tran Van Be',current_timestamp ,'https://s3.amazonaws.com/uifaces/faces/twitter/_williamguerra/128.jpg', current_timestamp , '2','Coder','Hiding bugs', 2),('3','Le Van Xe',current_timestamp ,'https://s3.amazonaws.com/uifaces/faces/twitter/_williamguerra/128.jpg', current_timestamp , '3','Fix-bugger','Replicating bugs',2),('4','Le Van Luyen',current_timestamp ,'https://s3.amazonaws.com/uifaces/faces/twitter/_williamguerra/128.jpg', current_timestamp , '4','Fix-bugger','Ad-Hoc bugs',2);
insert into team (team_id, active, created_date, host_id, last_modified_date, name) values ('1', true, current_timestamp , 1, current_timestamp , 'running'),('2', true, current_timestamp , 2, current_timestamp , 'gym');
insert into team_account (team_id, account_id) values ('1', '1'), ('1', '2'), ('2', '2')