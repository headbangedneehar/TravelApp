package com.db.app.travelersapp.constant;

public abstract class SQLCommand
{

    public static String checkRegion="select reg_id from region where reg_name=";
    public static String checkDest = "select dest_id from destination where dest_name=";
    public static String getHotel="select u.uni_id as _id, avg(c.rating) as rating, u.uni_name from hotel h,uni_type u,destination d, cust_rating c where u.dest_id=d.dest_id and u.uni_id=c.uni_id and h.uni_id=u.uni_id and d.dest_name=";
    public static String getRestaurant="select u.uni_id as _id,avg(c.rating) as rating, u.uni_name from restaurant r,uni_type u,destination d,cust_rating c where r.uni_id=u.uni_id and u.uni_id=c.uni_id and u.dest_id=d.dest_id and d.dest_name=";
    public static String getEntertainment="select u.uni_id as _id,avg(c.rating) as rating, u.uni_name from entertainment e,uni_type u,destination d, cust_rating c where e.uni_id=u.uni_id and u.dest_id=d.dest_id and u.uni_id=c.uni_id and d.dest_name=";
    public static String getDestination = "select d.dest_id as _id, d.dest_name from destination d, region r where r.reg_id=d.reg_id and r.reg_name=";
    //public static String getEmergency = "select e.emer_id as _id,e.emer_name,e.emer_addr,e.emer_contact from emergency e, destination d where e.dest_id = d.dest_id and d.dest_name=";

    public static String QUERY_2 = "select reg_id from region where reg_name=?";
    public static String QUERY_1 = "select dest_name from destination;";
    public static String check_region="select count(*) from region where reg_name=";
    public static String check_dest = "select count(*) from destination where dest_name=";
    //public static String dummy="select * from region";
    public static String dummy="select dest_name from destination,region ";
    public static String checkDestination="select dest_name from destination,region ";
    //public static String get_hotel="select uni_type.uni_name,uni_type.uni_hour,uni_type.uni_contact,uni_type.uni_desc,hotel.hotel_type,uni_type.uni_addr from hotel,uni_type,region,destination where hotel.uni_id=uni_type.uni_id and uni_type.dest_id=destination.dest_id and destination.reg_id=region.reg_id ";
    public static String get_hotel="select uni_type.uni_id as _id, uni_type.uni_name from hotel,uni_type,destination where hotel.uni_id=uni_type.uni_id and uni_type.dest_id=destination.dest_id and destination.dest_name=";
    //public static String get_hotel="select * from hotel; ";
    public static String get_restaurant="select uni_type.uni_id as _id, uni_type.uni_name from restaurant,uni_type,destination where restaurant.uni_id=uni_type.uni_id and uni_type.dest_id=destination.dest_id and destination.dest_name=";
    public static String get_entertainment="select uni_type.uni_id as _id, uni_type.uni_name from entertainment,uni_type,destination where entertainment.uni_id=uni_type.uni_id and uni_type.dest_id=destination.dest_id and destination.dest_name=";

    public static String getEmergency = "select e.emer_id as _id,e.emer_name,e.emer_addr,e.emer_contact from emergency e, destination d where e.dest_id = d.dest_id and d.dest_name=";
    public static String resultRestaurant="select uni_type.uni_name as Name,uni_type.uni_hour as Hours,uni_type.uni_contact as Contact,uni_type.uni_desc as Description,restaurant.rest_type as Type,uni_type.uni_addr as Address from restaurant,uni_type,region,destination where restaurant.uni_id=uni_type.uni_id and uni_type.dest_id=destination.dest_id and destination.reg_id=region.reg_id and uni_type.uni_id=";
    public static String resultEntertainment="select uni_type.uni_name as Name,uni_type.uni_hour as Hours,uni_type.uni_contact as Contact,uni_type.uni_desc as Description,entertainment.enter_type as Type,uni_type.uni_addr as Address from entertainment,uni_type,region,destination where entertainment.uni_id=uni_type.uni_id and uni_type.dest_id=destination.dest_id and destination.reg_id=region.reg_id and uni_type.uni_id=";
    public static String resultHotel="select uni_type.uni_name as Name,uni_type.uni_hour as Hours,uni_type.uni_contact as Contact,uni_type.uni_desc as Description,hotel.hotel_type as Type,uni_type.uni_addr as Address from hotel,uni_type,region,destination where hotel.uni_id=uni_type.uni_id and uni_type.dest_id=destination.dest_id and destination.reg_id=region.reg_id and uni_type.uni_id=" ;

    public static String transinfo="select transport.trans_id as _id,transport.trans_type as trans_type,transport.start_point as start_point from transport,destination where destination.dest_id=transport.dest_id and destination.dest_id in (select uni_type.dest_id from uni_type where uni_type.uni_id='";
    public static  String travelinfo="select ti.travel_dist,ti.travel_time,ti.travel_price from travel_info ti,transport t where ti.trans_id=t.trans_id and uni_id=";

    public static String demo="select t.start_point as 'Start Point',t.trans_type as 'Travel By',ti.travel_dist as Distance,ti.travel_time as Time,ti.travel_price as Price from travel_info ti,transport t where ti.trans_id=t.trans_id and uni_id=";

    public static  String rating="select rating,count(*) from cust_rating where uni_id='";

    public  static  String getDescription="select review from cust_rating where uni_id='";

    public static String addReview="insert into cust_review values('000";

}
