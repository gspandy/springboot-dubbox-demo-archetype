#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.dto.ListFilter;
import ${package}.dto.PageSearch;
import ${package}.dto.SearchFilter;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mq
 * Date: 2017/4/19
 * Time: 11:55
 * To change this template use File | Settings | File Templates.
 */
public interface CURDService<T> {

    List<T> list(ListFilter listFilter);

    Page<T> page(PageSearch var1);

    T detail(String var1);

    T createOrUpdte(T var1);

    void delete(String var1);
}
