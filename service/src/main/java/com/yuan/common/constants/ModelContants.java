package com.yuan.common.constants;

/**
 * @author joryun ON 2017/10/22.
 */
public class ModelContants {

    public interface UserContant {
        Integer USER_FLAG_TRUE = 1;//未删除
        Integer USER_FLAG_FALSE = 0;//删除
        Integer USER_TYPE_WULINGAOSHOU = 1;//武林高手
        Integer USER_TYPE_DAOGUANYINGYEYUAN = 2;//道馆营业员
        Integer USER_TYPE_DAOGUANGUANLIYUAN = 3;//道馆管理员
        Integer USER_TYPE_DAOGUANFUZEREN = 4;//道馆负责人
        Integer USER_ISFINISHINFORMATION = 1;//完善了资料
        Integer USER_IS_NOT_FINISHINFORMATION = 0;//没有完善资料

        Integer BIND_FALSE = 0;  //未绑定
        Integer BIND_TRUE = 1;  //已绑定

    }

    public interface OrderContant {
        Integer ORDER_OFF_LINE = 0;//线下
        Integer ORDER_ON_LINE = 1;//线上
        Integer ORDER_STATE_HAS_PAY = 1;//已付款
        Integer ORDER_STATE_HAS_NOT_PAY = 0;//未付款
    }

    //管理员
    public interface ManagerContant {
        String ADMIN_MANAGER = "ADMIN";     //超级管理员
        String SIMPLE_MANAGER = "SIMPLE";    //普通管理员(运营人员)
        String SHOP_MANAGER = "SHOP"; //店铺管理员
    }

}
