# winy-parent
综合型电商框架(入手即可用)

现状：目前大型电商项目，采用各种中间件，集群来部署，为了支持大用户量和 高负载，从而达到给用户提供优质的用户体验。

目的：本框架从0开始，集成各类工作当中用到的主流中间件，并且实现其各自的经典案例。采用maven的方式，各中间件抽出为jar的形式，
     不管是拿来学习探讨，或者直接拿到项目中使用，只要直接拿到本项目中的jar引入到自己项目中即可。即插即用，即是框架的真正含义。


基础技术选型: spring springmvc velocity mybatis rabbitmq jackson redis dubbo elasticsearch logback 等等。

框架各模块说明：

winy-parent：父工程

winy-portal : mvc层，web， dubbo消费者

winy-service: 业务处理层

winy-core: 核心层

winy-dao: model数据处理层(mybatis)

winy-rabbitmq: 消息层

winy-dubbo-facade: dubbo接口

winy-dubbo-facade-impl： dubbo服务提供者

当前程序初始入口：
http://localhost:8888/order/toOrderPage

winy-portal 工程中：
1. 新增订单操作中  包含了rabbitmq死信队列的操作(一段时间后未支付订单取消)
2. 取消订单操作中  包含了dubbo服务的实现
