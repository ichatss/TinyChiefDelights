-- Тут очищаем записи в таблицах
truncate table public.pg_order cascade;
truncate table public.cook cascade;
truncate table public.pg_user cascade;
truncate table public.customer cascade;
truncate table public.dish cascade;
truncate table public.review cascade;
truncate table public.basket cascade;


-- Тут перезапускаем счетчик последовательности id в секвенции
alter sequence cook_id_seq restart;
alter sequence customer_id_seq restart;
alter sequence dish_id_seq restart;
alter sequence hibernate_sequence restart;
alter sequence pg_order_id_seq restart;
alter sequence pg_user_id_seq restart;
alter sequence review_id_seq restart;
alter sequence basket_id_seq restart with 6;


-- Создаем новых пользователей
insert into public.pg_user(login, password, name, last_name, role)
values ('customer', 'qwe123', 'Наполеон', 'Бонапарт', 'CUSTOMER');
insert into public.pg_user(login, password, name, last_name, role)
values ('leo12', 'df23', 'Леонель', 'Месси', 'CUSTOMER');
insert into public.pg_user(login, password, name, last_name, role)
values ('garry1212', 'potter32', 'Гарри', 'Поттер', 'CUSTOMER');
insert into public.pg_user(login, password, name, last_name, role)
values ('parker1989', 'parker43', 'Питтер', 'Паркер', 'CUSTOMER');
insert into public.pg_user(login, password, name, last_name, role)
values ('zurab2020', 'reksoft2020', 'Зураб', 'Белый', 'CHEF');
insert into public.pg_user(login, password, name, last_name, role)
values ('tomhardi@icloud.com', 'hardtomy123', 'Том', 'Харди', 'COOK');
insert into public.pg_user(login, password, name, last_name, role)
values ('fid_castro', 'social.kuba1969', 'Фидель', 'Кастро', 'COOK');
insert into public.pg_user(login, password, name, last_name, role)
values ('conor.mma12', 'ufc4543', 'Конор', 'МакГрегор', 'COOK');
insert into public.pg_user(login, password, name, last_name, role)
values ('book.dor', 'dor1232', 'Дориан', 'Грей', 'COOK');
insert into public.pg_user(login, password, name, last_name, role)
values ('ilya.1999', 'gre4735', 'Илья', 'Столяров', 'COOK');
insert into public.pg_user(login, password, name, last_name, role)
values ('robbi.m12', 'margo7897', 'Марго', 'Робби', 'COOK');
insert into public.pg_user(login, password, name, last_name, role)
values ('coo123', 'qwe123', 'Вин', 'Дизель', 'COOK');
insert into public.pg_user(login, password, name, last_name, role)
values ('admin', 'qwe123', 'Артур', 'Вартанян', 'ADMIN');
insert into public.pg_user(login, password, name, last_name, role)
values ('hug45', 'df23', 'Хьюго', 'Босс', 'ADMIN');
insert into public.pg_user(login, password, name, last_name, role)
values ('donkorle', 'potter32', 'Вито', 'Корлеоне', 'ADMIN');
insert into public.pg_user(login, password, name, last_name, role)
values ('adm123', 'qwe123', 'Вэйн', 'Руни', 'ADMIN');

-- Тут шифруем пароли пользователей
update public.pg_user
set password = crypt(password, gen_salt('bf', 8));


-- Созлаем новых поваров
insert into public.cook(rating, cook_status, about_cook, user_id, type, start_salary)
values (4.7, true, 'Повар с 10 летним стажем работы. Работал в таких городах как Москва, Рим и Мадрид. ' ||
                   'Является побеителем конкурса поваров 2010 в Стамбуле',
        5, 'CHEF', 150);

insert into public.cook(rating, cook_status, about_cook, user_id, type, start_salary)
values (3.4, true, 'Повар с 5 летним стажем работы. Работал в таких городах как Воронеж и Краснодар. ' ||
                   'Окончил кулинарный колледж с золотой медалью. Является победителем конкурса "Лучший мясник ВРН 2018"',
        6, 'MEAT_COOK', 100);

insert into public.cook(rating, cook_status, about_cook, user_id, type, start_salary)
values (4.2, true, 'Повар с 7 летним стажем работы. Работал в таких городах как Сочи и Стамбул. ' ||
                   'Является лучшим кондитером в городе. Победителем конкурса "Лучший мясник ВРН 2010, 2011, 2014"',
        7, 'CONFECTIONER', 70);

insert into public.cook(rating, cook_status, about_cook, user_id, type, start_salary)
values (2.5, true, 'Повар-стажер. Общий опыт работы 1 год в ресторане "Tiny Chief". ' ||
                   'Отлично разделывает рыбу и владеет грамотной подачей блюд.',
        8, 'FISH_COOK', 90);

insert into public.cook(rating, cook_status, about_cook, user_id, type, start_salary)
values (4.1, true, 'Повар с опытом работы более 8 лет. Является лучшим рыбником города. ' ||
                   'Сделает для Вас самую вкусную форель под сливками.',
        9, 'FISH_COOK', 90);

insert into public.cook(rating, cook_status, about_cook, user_id, type, start_salary)
values (4, true, 'Повар с 9 летним стажем работы. Работал в крупных ресторанах Москвы и Санкт-Питербурга. ' ||
                 'Мясные стейки, люля-кебаб и шашлыки самых разных видов. Является победителем конкурса "Лучший мясник ВРН 2013"',
        10, 'MEAT_COOK', 100);

insert into public.cook(rating, cook_status, about_cook, user_id, type, start_salary)
values (2.7, true, 'Повар с 4 летним стажем работы. Работал в самых разных кондитерских города. ' ||
                   'Фирменный хачапури и мороженные самых разных видов - вот, что Вас удивит.',
        11, 'CONFECTIONER', 70);

insert into public.cook(rating, cook_status, about_cook, user_id, type, start_salary)
values (3, true, 'Повар с 3 летним стажем работы. Работал в самых разных ресторанах города. ' ||
                 'Может порадовать Вас абсолютно лбыми видами мясных блюд!',
        12, 'MEAT_COOK', 100);


-- Создаем новых заказчиков
insert into public.customer(wallet, user_id)
VALUES (50000, 1);

insert into public.customer(wallet, user_id)
VALUES (100, 2);

insert into public.customer(wallet, user_id)
VALUES (7833, 3);

insert into public.customer(wallet, user_id)
VALUES (867, 4);


-- Заполняем таблицу блюд
insert into public.dish(name, dish_cost, weight, cooking_time, about_dish, type)
VALUES ('Хинкали', 105, 60, 20,
        'Хинка́ли — блюдо грузинской кухни из горных областей Пшави, Мтиулети и Хевсурети Грузии, ' ||
        'далее блюдо распространилось в другие районы Кавказа и по всему бывшему СССР. ' ||
        'Кусок сочного мясо помещается в центр теста и сворачивается концом в комок.', 'MEAT');

insert into public.dish(name, dish_cost, weight, cooking_time, about_dish, type)
VALUES ('Чакапули', 600, 400, 45,
        'Чакапули — национальное грузинское блюдо, представляющее собой молодое мясо, тушённое с зеленью и специями. ' ||
        'Чаще всего используют баранину, а для праздничного варианта — ягнятину. ' ||
        'Особую пикантность блюду добавляет свежий эстрагон, также часто для приготовления используют ткемали.',
        'MEAT');

insert into public.dish(name, dish_cost, weight, cooking_time, about_dish, type)
VALUES ('Сациви', 750, 530, 60, 'Сациви — соус грузинской кухни. ' ||
                                'Также по названию соуса может называться готовое блюдо.' ||
                                ' Наибольшее распространение из блюд с данным соусом получила домашняя птица, ' ||
                                'в основном курица под соусом сациви, называемая просто «сациви». ', 'MEAT');

insert into public.dish(name, dish_cost, weight, cooking_time, about_dish, type)
VALUES ('Шашлык', 700, 300, 30, 'Шашлы́к — изначально блюдо из баранины мелкой нарезки, ' ||
                                'нанизанное на шампур и запеченное на древесном угле в мангале, при этом возможно применение маринада на Ваш вкус!',
        'MEAT');

insert into public.dish(name, dish_cost, weight, cooking_time, about_dish, type)
VALUES ('Хачапури', 1000, 460, 45, 'Хачапу́ри — блюдо грузинской кухни, ' ||
                                   'грузинское национальное мучное изделие, ' ||
                                   'закрытый пирожок с начинкой из сыра и яйца.', 'CONFECTIONERY');

insert into public.dish(name, dish_cost, weight, cooking_time, about_dish, type)
VALUES ('Лобиани', 800, 360, 30, 'Лобиани — традиционные грузинские пироги с начинкой из варёной фасоли.',
        'CONFECTIONERY');

insert into public.dish(name, dish_cost, weight, cooking_time, about_dish, type)
VALUES ('Сациви из рыбы', 990, 470, 45,
        'Сациви из рыбы — традиционное грузинское блюдо. Рыба с начинкой из соуса под сациви.', 'FISH');

insert into public.dish(name, dish_cost, weight, cooking_time, about_dish, type)
VALUES ('Осетр по-Царски', 2000, 500, 20, 'Осетр по-Царски — блюдо царей. ' ||
                                          'Красная тушка рыбки, покрытая лимонным соусом и вкуснейшими специями.',
        'FISH');

insert into public.dish(name, dish_cost, weight, cooking_time, about_dish, type)
VALUES ('Окунь с овощами', 1490, 420, 35, 'Окунь с овощами - блюдо для любителей морской кухнки.' ||
                                          ' Цельный окунь обжаренный с обоих сторон нежно подается с варенной картошкой и ломтиами морвки с луком.',
        'FISH');

--Заполняем таблицу бакетов
insert into public.basket(id)
values (1);
insert into public.basket(id)
values (2);
insert into public.basket(id)
values (3);
insert into public.basket(id)
values (4);
insert into public.basket(id)
values (5);

--Заполняем кросс-таблицу бакет диш
insert into basket_dish(basket_id, dish_id)
values (1, 1);
insert into basket_dish(basket_id, dish_id)
values (1, 1);
insert into basket_dish(basket_id, dish_id)
values (1, 7);
insert into basket_dish(basket_id, dish_id)
values (1, 5);
insert into basket_dish(basket_id, dish_id)
values (1, 4);
insert into basket_dish(basket_id, dish_id)
values (1, 4);
insert into basket_dish(basket_id, dish_id)
values (2, 1);
insert into basket_dish(basket_id, dish_id)
values (2, 9);
insert into basket_dish(basket_id, dish_id)
values (3, 4);
insert into basket_dish(basket_id, dish_id)
values (3, 4);
insert into basket_dish(basket_id, dish_id)
values (3, 1);
insert into basket_dish(basket_id, dish_id)
values (4, 3);
insert into basket_dish(basket_id, dish_id)
values (5, 6);
insert into basket_dish(basket_id, dish_id)
values (5, 6);
insert into basket_dish(basket_id, dish_id)
values (5, 8);
insert into basket_dish(basket_id, dish_id)
values (5, 9);
insert into basket_dish(basket_id, dish_id)
values (5, 9);
insert into basket_dish(basket_id, dish_id)
values (5, 2);
insert into basket_dish(basket_id, dish_id)
values (5, 2);

-- Заполняем таблицу заказов
insert into pg_order(address, phone_number, date_order, order_status, customer_id, basket_id, review_id, cost)
VALUES ('г.Воронеж, ул.Владимира Невского 45Д, кв.2', +79805421578, '03.03.2020 20:00', false, 1, 1, 1, 1458);

insert into pg_order(address, phone_number, date_order, order_status, customer_id, basket_id, review_id, cost)
VALUES ('г.Воронеж, ул.Ленина 5А, кв.55', +79805561578, '08.04.2020 22:00', false, 2, 2, 3, 3422);

insert into pg_order(address, phone_number, date_order, order_status, customer_id, basket_id, review_id, cost)
VALUES ('г.Воронеж, ул.Владимира Невского 2', +74504521778, '22.01.2020 14:00', false, 3, 3, 2, 5423);

insert into pg_order(address, phone_number, date_order, order_status, customer_id, basket_id, review_id, cost)
VALUES ('г.Воронеж, пл.Космонавтов 12, кв.34', +79764561478, '11.03.2020 12:25', false, 1, 4, 4, 3242);


-- Заполняем таблицу отзывов
insert into review(review, rate, order_id)
VALUES ('Отличный повар. Сразу видно - мастер своего дела! Очень вкусно! Спасибо!',
        5, 1);

insert into review(review, rate, order_id)
VALUES ('Хороший повар. Очень вкусно! Спасибо!',
        4, 3);

insert into review(review, rate, order_id)
VALUES ('Отвратительно. Нашли волос в блюде!',
        2, 2);

insert into review(review, rate, order_id)
VALUES ('Сойдет. Могло быть получше! Ожидал большего!',
        3, 4);


-- Заполняем кросс-таблицу cook_dish
insert into cook_dish(cook_id, dish_id)
VALUES (1, 1);
insert into cook_dish(cook_id, dish_id)
VALUES (1, 2);
insert into cook_dish(cook_id, dish_id)
VALUES (1, 3);
insert into cook_dish(cook_id, dish_id)
VALUES (1, 4);
insert into cook_dish(cook_id, dish_id)
VALUES (1, 5);
insert into cook_dish(cook_id, dish_id)
VALUES (1, 6);
insert into cook_dish(cook_id, dish_id)
VALUES (1, 7);
insert into cook_dish(cook_id, dish_id)
VALUES (1, 8);
insert into cook_dish(cook_id, dish_id)
VALUES (1, 9);
insert into cook_dish(cook_id, dish_id)
VALUES (2, 1);
insert into cook_dish(cook_id, dish_id)
VALUES (2, 2);
insert into cook_dish(cook_id, dish_id)
VALUES (2, 3);
insert into cook_dish(cook_id, dish_id)
VALUES (2, 4);
insert into cook_dish(cook_id, dish_id)
VALUES (3, 5);
insert into cook_dish(cook_id, dish_id)
VALUES (3, 6);
insert into cook_dish(cook_id, dish_id)
VALUES (4, 7);
insert into cook_dish(cook_id, dish_id)
VALUES (4, 8);
insert into cook_dish(cook_id, dish_id)
VALUES (4, 9);
insert into cook_dish(cook_id, dish_id)
VALUES (5, 7);
insert into cook_dish(cook_id, dish_id)
VALUES (5, 8);
insert into cook_dish(cook_id, dish_id)
VALUES (5, 9);
insert into cook_dish(cook_id, dish_id)
VALUES (6, 1);
insert into cook_dish(cook_id, dish_id)
VALUES (6, 2);
insert into cook_dish(cook_id, dish_id)
VALUES (6, 3);
insert into cook_dish(cook_id, dish_id)
VALUES (6, 4);
insert into cook_dish(cook_id, dish_id)
VALUES (7, 5);
insert into cook_dish(cook_id, dish_id)
VALUES (7, 6);
insert into cook_dish(cook_id, dish_id)
VALUES (8, 1);
insert into cook_dish(cook_id, dish_id)
VALUES (8, 2);
insert into cook_dish(cook_id, dish_id)
VALUES (8, 3);
insert into cook_dish(cook_id, dish_id)
VALUES (8, 4);

--Заполняем кросс-таблицу ордер кук
insert into order_cook(order_id, cook_id)
values (1, 6);
insert into order_cook(order_id, cook_id)
values (1, 7);
insert into order_cook(order_id, cook_id)
values (2, 8);
insert into order_cook(order_id, cook_id)
values (3, 1);
insert into order_cook(order_id, cook_id)
values (3, 3);
insert into order_cook(order_id, cook_id)
values (3, 5);