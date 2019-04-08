# 数据库初始化脚本

# 创建数据库
CREATE DATABASE flashsale;
# 使用数据库
use flashsale;

# 创建商品库存表
CREATE TABLE goods(
  `goods_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `number` int NOT NULL COMMENT '库存数量',
  `start_time` timestamp NOT NULL COMMENT '抢购开启时间',
  `end_time` timestamp NOT NULL COMMENT '抢购结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (goods_id),
  #   索引，加速查询，比如在减库存时判断时间是否合理的时候
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)
#   主键由1000开始自增
)ENGINE = InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '商品库存表';

# 初始化数据
# 插入多行测试数据
insert into goods(name,number,start_time,end_time)
values ('500元抢购进口马桶盖',100,'2019-04-08 00:00:00','2019-04-10 00:00:00'),
       ('日本原装进口马桶盖',50,'2019-04-10 00:00:00','2019-04-15 12:00:00'),
       ('豪华级别马桶盖',200,'2019-04-09 12:00:00','2019-04-20 12:00:00');
# MySQL支持字符串表示时间，会自动转换

# 用户抢购成功明细表
# 用户登录认证相关的信息
CREATE TABLE success_sale(
  `goods_id` BIGINT NOT NULL COMMENT '商品库存id',
  `user_phone` BIGINT NOT NULL COMMENT '用户手机号',
  `state` tinyint NOT NULL DEFAULT -1 COMMENT '状态表示：-1无效，0成功，1已付款，2已发货',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (goods_id,user_phone),   /* 联合主键 */
  key idx_create_time(create_time)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '用户抢购成功明细表';

