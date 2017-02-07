# Apache配置虚拟主机的方法

1.在`..../conf/httpd.conf`中打开相关配置

```xml
LoadModule vhost_alias_module modules/mod_vhost_alias.so

Include conf/extra/httpd-vhosts.conf
```

2.在`.../conf/extras/httpd-vhost.conf`中添加vhost

```xml
<VirtualHost *:80>
    ServerAdmin webmaster@app.com
    DocumentRoot "/srv/http/update/public"
    ServerName app.com
    ErrorLog "logs/dummy-host2.example.com-error.log"
    CustomLog "logs/dummy-host2.example.com-access.log" common
</VirtualHost>
```

