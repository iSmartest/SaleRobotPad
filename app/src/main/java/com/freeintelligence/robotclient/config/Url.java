package com.freeintelligence.robotclient.config;


/**
 * 接口地址
 */
public class Url {
    //服务器地址
//    public static final String HTTP ="http://101.200.60.12:8080/robot-manager-web/pai/car";//网络地址
    public static final String IMAGE_HTTP = "http://101.200.60.12:8080/robot-manager-web";
    public static final String HTTP ="http://192.168.1.6:8080/robot-manager-web/pai/car";//本地地址
    public static final String HOTCAR=HTTP+"/carList";
    public static final String HOTCARDEAILS=HTTP+"/car";//新车详情
    public static final String SECONDDEAILS=HTTP+"/secondCar";//二手车详情
    /*@param storeId 4S店id
    @param carId 车ID*/
    public static final String INSPRCT=HTTP+"/maintainOrRepairOrInsurance";
   /* @param storeId 4S店id
    @param customerId 用户id
    @param type 类型 1 维修  2 保养  3 保险*/
   public static final String DETAIL=HTTP+"/detail";
   /* 维修保养详情
    @param storeId 4s店id
    @param customerId 用户id
    @param type 类型 1维修 2保养
    @param detailId 详情id*/
   public static final String VIP=HTTP+"/scoreOrConsumption";
   /* vip
    @param storeId 4s店id
    @param customerId 用户id
    @param type 类型  1积分  2消费
    @param page 分页*/
    public static final String BRIEF=HTTP+"/explain";
    /*产品讲解
    @param storeId 4S店id
    @param page 分页*/
    public static final String SHOW=HTTP+"/carPrudent";
   /* 产品展示
    @param storeId 4s店id
    @param page 分页*/
   public static final String TESTDRIVE=HTTP+"/testDrive";
    /*爱车试驾
    @param storeId 4s店id
    @param date 时间
    @param phoneNumber 手机号
    @param carId 车型id*/
    public static final String MAINTAIN=HTTP+"/appointment";
  /*  维修保养预约
    @param storeId 4s店id
    @param date 时间
    @param phoneNumber 手机号
    @param carNumber 车牌号
    @param type 类型 1维修 2保养*/
  public static final String CALL=HTTP+"/callSale";
  //呼叫服务
    //4s店idstoreId
    // 机器人唯一标识index
    // 返回的登录id  eid
    public static final String LOGIN=HTTP+"/adminLogin";
    //4s店登录
    //账号phone  李教头:18332212560
    // 密码password  1234
    // 机器人唯一标识index
    public static final String RECEPTIONTABLE=HTTP+"/reception";
    //屏幕端的咨询
    //carId storeOd
    public static final String SCREENCONSULT=HTTP+"/ScreenConsult";
    //机器人端的咨询。
   // RobotConsult
   // keyWord  storeId
    public static final String ROBOTCONSULT=HTTP+"/RobotConsult";
    //VIP登录以及发送验证码
    public static final String LOADVCODE=HTTP+"/sendCode";
    public static final String LOADLOGIN=HTTP+"/login";
    //问卷调查
    public static final String EXAMTION=HTTP+"/examtion";
    //访问4s店所以车的类型
    public static final String CARTAPY=HTTP+"/carType";

    public static final String UPLOADIMG=HTTP+"/uploadImg";
}
