package com.db.app.travelersapp.constant;

public abstract class SQLCommand
{
    //public static String QUERY_1 = "select dest_name from destination;";
    public static String QUERY_2 = "select reg_id from region where reg_name=?";
    public static String QUERY_1 = "select dest_name from destination;";
    public static String check_region="select count(*) from region where reg_name=?";
    public static String dummy="select * from region";
    public static String get_hotel="select uni_type.uni_name,uni_type.uni_hour,uni_type.uni_contact,uni_type.uni_desc,hotel.hotel_type,uni_type.uni_addr from hotel,uni_type,region,destination where hotel.uni_id=uni_type.uni_id and uni_type.dest_id=destination.dest_id and destination.reg_id=region.reg_id ";
    //public static String get_hotel="select * from hotel; ";

}
