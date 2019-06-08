package com.myshop.common.hystrix;

import com.myshop.common.constants.HttpStatusConstants;
import com.myshop.common.dto.BaseResult;
import com.myshop.common.utils.MapperUtils;

import java.util.ArrayList;

/**
 * 通用的熔断方法
 * @Author yang
 * @Date 2019/6/3
 */
public class Fallback {

    /**
     * 502错误
     * @return
     */
    public static String badGatway() {
        BaseResult.Error error = new BaseResult.Error(HttpStatusConstants.BAD_BATEWAY.getCode(),HttpStatusConstants.BAD_BATEWAY.getContent());
        ArrayList arrayList = new ArrayList();
        arrayList.add(error);
        BaseResult baseResult = BaseResult.notok(arrayList);

        try {
            return MapperUtils.obj2json(baseResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
