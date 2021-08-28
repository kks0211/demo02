package com.board.config.http;

public enum BaseResponseCode {

    /*SUCCESS(1001, "정상적으로 처리되었습니다."), // 성공
    ERROR(1002, "오류가 발생하였습니다."), // 실패
    DATA_IS_NULL(1003, "요청하신 {0} 데이터는 Null 입니다."),    //NULL
    VALIDATE_REQUIRED(1004, "{0}({1}) 필드는 필수로 입력하셔야 합니다."),    // 필수체크*/

    SUCCESS,                // 성공
    ERROR,                  // 실패
    DATA_IS_NULL,           //NULL
    VALIDATE_REQUIRED,      // 필수체크
    ;

    /*
    private int status;
    private String message;

    BaseResponseCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public String message() {
        return message;
    }

    public int status() {
        return status;
    }*/

}
