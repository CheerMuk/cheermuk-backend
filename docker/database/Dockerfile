FROM postgis/postgis:16-3.4-alpine
ADD ../conf.d/postgresql.conf /etc/postgresql/postgresql.conf
CMD ["postgres", "-c", "config_file=/etc/postgresql/postgresql.conf"]
