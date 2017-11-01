package com.yuan.models.user;

import com.yuan.models.FlagType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author joryun ON 2017/10/22.
 */
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    User findByOpenIdAndFlag(String openId, FlagType flag);

    @Modifying
    @Query(value = "update User u set u.freezeBalance = u.freezeBalance + :freezeBalance where u.userId = :userId and" +
            " u.balance > u.freezeBalance")
    int setFreezeBalanceByUserId(@Param("userId") Integer userId, @Param("freezeBalance") Integer freezeBalance);

    //纯余额付款，冻结金额不为空情况
    @Modifying
    @Query(value = "update User u set u.balance = u.balance - ?2 where u.userId = ?1 and u.balance = " +
            "?3 and u.freezeBalance = ?4")
    int setBalanceAndFreezeBalanceByUserId(Integer userId, Integer
            balanceFee, Integer balance, Integer freezeBalance);

    //纯余额付款，冻结金额为空情况
    @Modifying
    @Query(value = "update User u set u.balance = u.balance - ?2 where u.userId = ?1 and u.balance = ?3")
    int setBalanceByUserId(Integer userId, Integer balanceFee, Integer balance);

    @Modifying
    @Query(value = "update User u set u.balance = u.balance - :balanceFee, u.freezeBalance = u.freezeBalance - " +
            ":balanceFee where u.userId = :userId")
    int setBalanceAndFreezeBalanceByUserIdMethodWechat(@Param("userId") Integer userId, @Param("balanceFee") Integer
            balanceFee);

    User findByPhone(String phone);

    User findByPhoneAndFlag(String phone, FlagType flag);

    User findByUserIdAndFlag(Integer userId, FlagType flag);
}
