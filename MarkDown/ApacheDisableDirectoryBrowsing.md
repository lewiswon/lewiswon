# Apache禁用目录列表

### 方法一

在/etc/httpd/conf/httpd.conf或/etc/apache2/conf/apache2.conf文件中把

```xml
<Directory /var/www/>
	Options Indexes FollowSymLinks
	AllowOverride None
    Require all granted
</Directory>
```

改为

```xml
<Directory /var/www/>
	Options  FollowSymLinks
	AllowOverride None
    Require all granted
</Directory>
```

### 方法二

在需要禁用的目录中创建`.htaccess`文件,并在文件中加入以下内容

```
Options  -Indexes
```



来源：[stackoverflow](http://stackoverflow.com/questions/2530372/how-do-i-disable-directory-browsing)

