package com.db.app.travelersapp.constant;

public abstract class SQLCommand
{

    public static String checkRegion="select reg_id from region where reg_name=";
    public static String checkDest = "select dest_id from destination where dest_name=";
    public static String getHotel="select u.uni_id as _id, avg(c.rating) as rating, u.uni_name from hotel h,uni_type u,destination d, cust_rating c where u.dest_id=d.dest_id and u.uni_id=c.uni_id and h.uni_id=u.uni_id and d.dest_name=";
    public static String getRestaurant="select u.uni_id as _id,avg(c.rating) as rating, u.uni_name from restaurant r,uni_type u,destination d,cust_rating c where r.uni_id=u.uni_id and u.uni_id=c.uni_id and u.dest_id=d.dest_id and d.dest_name=";
    public static String getEntertainment="select u.uni_id as _id,avg(c.rating) as rating, u.uni_name from entertainment e,uni_type u,destination d, cust_rating c where e.uni_id=u.uni_id and u.dest_id=d.dest_id and u.uni_id=c.uni_id and d.dest_name=";
    public static String getDestination = "select d.dest_id as _id, d.dest_name from destination d, region r where r.reg_id=d.reg_id and r.reg_name=";
    public static String getEmergency = "select e.emer_id as _id,e.emer_name,e.emer_addr,e.emer_contact from emergency e, destination d where e.dest_id = d.dest_id and d.dest_name=";


}
