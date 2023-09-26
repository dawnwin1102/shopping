package com.leo.demo.shopping.controller.base;


/**
 * @author leo
 * @date 2023/9/24
 */
public class BaseApiController {
//    private Logger log = LoggerFactory.getLogger(this.getClass());
//
//    protected <T, R> BaseResponse<R> execService(T request, Function<T, R> function) {
//        BaseResponse<R> baseResponse = new BaseResponse<>();
//        try {
//            log.info(String.format("【Request】%s ", request));
//            R result = function.apply(request);
//            baseResponse.setResult(result);
//        } catch (BusinessException e) {
//            log.warn(e.getCode(), e.getMessage(), "", "");
//            baseResponse.setResult(null);
//            baseResponse.setCode(e.getCode());
//            baseResponse.setMessage(e.getMessage());
//        } catch (Exception e) {
//            log.error(e.getMessage() + "|" + ExceptionUtils.getStackTrace(e));
//            baseResponse.setResult(null);
//            baseResponse.setCode(ResponseCode.Code_1000.getCode());
//            baseResponse.setMessage(ResponseCode.Code_1000.getMessage());
//        } finally {
//            log.info(String.format("【Response】%s", baseResponse));
//        }
//
//        return baseResponse;
//    }

}
