package com.wbyweb.bolg.common;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * 常量类
 * @author WeiBiaoYi
 *
 */
public class Const {
	//定义用户常量
	public static final String CURRENT_USER="current_user";
	public static final String EMAIL="email";
	public static final String USERNAME="username";
	public static final String ERROR_CODE="error";
    public static final String COOKIENAME="Cookie_name";
    public static final String MESSAGE="message";

    //Redis
    public static final long DEFAULT_EXPIRE = 60 * 60 * 24;     //默认过期时长，单位：秒 (一天)
    public static final long NOT_EXPIRE = -1;                       //不设置过期时长
    public static final String ARTICLEDETAILED="article_detailed";   //文章详细信息
    public static final String ARTICLELIST="article_list";           //文章列表
	
	//定义排序规则
	public interface ProductListOrderBy{
	        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc","price_asc");
	}

	public interface Cart{
		int CHECKED=1;//购物车选中状态
		int UN_CHECKED=0;//购物车中未选中状态
		String LIMIT_NUM_FAIL="LIMIT_NUM_FAIL";
		String LIMIT_NUM_SUCCESS="LIMIT_NUM_SUCCESS";
	}
	
	//定义角色接口
	public interface Role{
		/**
		 * 普通用户
		 */
		int ROLE_CUSTOMER=0;
		/**
		 * 管理员
		 */
		int ROLE_ADMIN=1;
	}
	
	public enum ProductStatusEnum{
        ON_SALE(1,"在线");
        private String value;
        private int code;
        ProductStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

	/**
	 * 订单状态
	 */
	public enum OrderStatusEnum{
		CANCELED(0,"已取消"),
		NO_PAY(10,"未支付"),
		PAID(20,"已付款"),
		SHIPPED(40,"已发货"),
		ORDER_SUCCESS(50,"订单完成"),
		ORDER_CLOSE(60,"订单关闭");
		private String value;
		private int code;
		OrderStatusEnum(int code,String value){
			this.code = code;
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public int getCode() {
			return code;
		}

		public static OrderStatusEnum codeOf(int code){
			for (OrderStatusEnum orderStatusEnum : values()){
				if (orderStatusEnum.getCode() == code){
					return orderStatusEnum;
				}
			}
			throw new RuntimeException("没有找到对于的枚举！");
		}
	}

	/**
	 * 支付类型
	 */
	public enum PaymentTypeEnum{
		WE_CHAT_PAY(1, "微信支付"), ALI_PAY(2, "支付宝"), UNION_PAY(3, "银联支付");
		private String value;
		private int code;
		PaymentTypeEnum(int code,String value){
			this.code = code;
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public int getCode() {
			return code;
		}

		public static PaymentTypeEnum codeOf(int code){
			for (PaymentTypeEnum paymentTypeEnum : values()){
				if (paymentTypeEnum.getCode() == code){
					return paymentTypeEnum;
				}
			}
			throw new RuntimeException("没有找到对于的枚举！");
		}
	}
}
