# online_store

## 网上商城系统

持续更新。。。

## 说明

* 框架：SpringBoot，Spring，Spring Data JPA，Shiro，Swagger，Maven
* 数据库：Docker Image MySQL Final，Docker Image Redis Final
* 服务器：ECS CentOS 7，Nginx
* 本地环境：Mac OS，JDK1.8
* 开发平台：IntelliJ IDEA 2017.2
* 图片存储：OSS or Qiniu
* CI：Docker + Jenkins
* 容器：Docker

## 功能模块（粗略版）

### 前台功能模块

#### 用户模块

* 用户注册
* 用户登录
* 修改密码
* 找回密码
* 个人信息展示
* 个人信息编辑
* 退出登录
* 用户注册

#### 商品模块

* 商品展示
* 商品详情
* 商品轮播展示
* 按分类查询(分页)
* 按商品名查询(模糊匹配，分页)
* 按店铺查询(分页)
* 多条件组合查询(分页)

#### 分类模块

* 显示所有分类

#### 店铺模块

* 店铺列表(分页)
* 店铺详情

#### 店铺模块

* 我的购物车
* 添加购物条目
* 更新购物条目
* 删除条目
* 批量删除条目
* 查询条目信息

#### 订单模块

* 我的订单
* 生成订单
* 订单详情
* 取消订单
* 确认订单
* 订单支付
* 历史订单

#### 积分模块

* 签到领积分
* 购买商品获取积分
* 评价获取积分

#### 店铺入驻模块

* 入驻申请

### 后台功能模块

#### 管理员模块 & 权限管理模块

* 管理员角色分配(一级，二级...)
* 管理员登录
* 添加管理员
* 管理员列表展示(分页)
* 修改管理员
* 删除管理员

#### 分类管理模块

* 添加一级分类
* 修改一级分类
* 删除一级分类
* 添加二级分类
* 修改二级分类
* 删除二级分类

#### 商品管理模块

* 多条件组合查询
* 添加商品
* 编辑商品
* 上架/下架商品
* 删除商品

#### 店铺管理模块

* 店铺列表(分页)(超管 && 平管权限)
* 添加店铺(超管 && 平管权限)
* 删除店铺(超管 && 平管权限)
* 添加店铺商品
* 修改店铺商品
* 上架/下架店铺商品
* 删除店铺商品

#### 订单管理模块

* 按状态查询
* 查询订单详情
* 取消订单
* 发货
* 历史订单

#### 店铺入驻审核模块

* 申请审核
* 删除申请
* 审核历史


