#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dto;

/**
 * Created by X on 2017/4/17.
 * DTO与Entiry类型转换
 */
public interface Converter<A, B> {

    A doForward(B b);

    B doBackward(A a);
}
