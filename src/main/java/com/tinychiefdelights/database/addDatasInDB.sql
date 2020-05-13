truncate table public.pg_order cascade;
truncate table public.cook cascade;
truncate table public.pg_user cascade;
truncate table public.customer cascade;
truncate table public.dish cascade;
truncate table public.review cascade;


insert into public.pg_user(login, password, name, last_name, role) values (
                        'customer', 'qwe123', 'Наполеон', 'Бонапарт', 'CUSTOMER');
insert into public.pg_user(login, password, name, last_name, role) values (
                        'leo12', 'df23', 'Леонель', 'Месси', 'CUSTOMER');
insert into public.pg_user(login, password, name, last_name, role) values (
                        'garry1212', 'potter32', 'Гарри', 'Поттер', 'CUSTOMER');
insert into public.pg_user(login, password, name, last_name, role) values (
                        'parker1989', 'parker43', 'Питтер', 'Паркер', 'CUSTOMER');

insert into public.pg_user(login, password, name, last_name, role) values (
                        'zurab2020', 'reksoft2020', 'Зураб', 'Белый', 'COOK');
insert into public.pg_user(login, password, name, last_name, role) values (
                         'tomhardi@icloud.com', 'hardtomy123', 'Том', 'Харди', 'COOK');
insert into public.pg_user(login, password, name, last_name, role) values (
                         'fid_castro', 'social.kuba1969', 'Фидель', 'Кастро', 'COOK');
insert into public.pg_user(login, password, name, last_name, role) values (
                         'conor.mma12', 'ufc4543', 'Конор', 'МакГрегор', 'COOK');
insert into public.pg_user(login, password, name, last_name, role) values (
                         'book.dor', 'dor1232', 'Дориан', 'Грей', 'COOK');
insert into public.pg_user(login, password, name, last_name, role) values (
                         'ilya.1999', 'gre4735', 'Илья', 'Столяров', 'COOK');
insert into public.pg_user(login, password, name, last_name, role) values (
                         'robbi.m12', 'margo7897', 'Марго', 'Робби', 'COOK');
insert into public.pg_user(login, password, name, last_name, role) values (
                         'coo123', 'qwe123', 'Вин', 'Дизель', 'COOK');

insert into public.pg_user(login, password, name, last_name, role) values (
                         'admin', 'qwe123', 'Артур', 'Вартанян', 'ADMIN');
insert into public.pg_user(login, password, name, last_name, role) values (
                          'hug45', 'df23', 'Хьюго', 'Босс', 'ADMIN');
insert into public.pg_user(login, password, name, last_name, role) values (
                          'donkorle', 'potter32', 'Вито', 'Корлеоне', 'ADMIN');
insert into public.pg_user(login, password, name, last_name, role) values (
                           'adm123', 'qwe123', 'Вэйн', 'Руни', 'ADMIN');

update public.pg_user set password = crypt(password, gen_salt('bf', 8));

insert into public.cook(rating, cook_status, about_cook, user_id, type) values (
                        4.7, true, ''
                                                                               )