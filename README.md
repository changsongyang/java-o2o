# java-ssm
java 电商系统(开发完成) 
# maven 模块管理
<pre>
parent(父工程)<br>
  |——manager(srm系统-sms系统)<br>
    |——manager-dao(数据访问层)<br>
    |——manager-pojo(实体类)<br>
    |——manager-service(业务逻辑层)<br>
    |——manager-web(view层)<br>
  |——dubbo(dubbo系统)<br>
    |——dubbo-dao(数据访问层)<br>
    |——dubbo-pojo(实体类)<br>
    |——dubbo-service(业务逻辑层)<br>
  |——dubbo-web(view层)<br>
  |——rest(webservice发布系统)<br>
  |——order(订单系统)<br>
  |——portal(前台系统)<br>
  |——search(solr搜索系统)<br>
  |——sso(单点登录系统)<br>
  |——commom(工具类)<br>
</pre>
